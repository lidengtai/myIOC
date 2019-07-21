package com.taylor.ioc.core;

import com.taylor.ioc.annotation.Autowired;
import com.taylor.ioc.annotation.Repository;
import com.taylor.ioc.annotation.Service;

import java.io.File;
import java.io.FileFilter;
import java.lang.reflect.Field;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @ClassName AnnotationApplicationContext
 * @Description 通过注解来进行完成IOC操作的具体实现类
 * @Author lidengtai
 * @Date 2019/7/7 14:52
 * @Version 1.0
 */
public class AnnotationApplicationContext extends ApplicationContext {

    //包扫描的根路径
    private static final String SCAN_PACKAGE = "com.taylor.ioc.demo";

    private  static Map<String,Object> beanDefinationsMap = new ConcurrentHashMap(256);

    //定义存储所有扫到的类文件的类路径的缓存
    private static List<String> classPathCache = Collections.synchronizedList(new ArrayList());
    //存储所有注册的bean
    private static List<Class<?>> beansCache = Collections.synchronizedList(new ArrayList<>());
    
    public AnnotationApplicationContext() {
        //初始化IOC容器的方法
        initContainer();
    }

    private void initContainer() {
        //1 找出包下面的所有.class类路径
        scanPackage(SCAN_PACKAGE);
        //2 注册bean
        registryBean();
        //3 把bean创建出来
        doCreateBean();
        //4 完成依赖对象的装配操作
        populateBean();
    }

    /**
     * 依赖对象的具体装配方法
     */
    private void populateBean() {
        if (beansCache.size()==0)return;

        for (Class<?> cl: beansCache){
            //获取别名
            String alias = getAlias(cl);

            Object instance = this.beanDefinationsMap.get(alias);

            Field[] fields = cl.getDeclaredFields();
            try {
                for (Field field : fields){
                    if (field.isAnnotationPresent(Autowired.class)){
                        //开启字段访问权限
                        field.setAccessible(true);
                        Autowired autowired = field.getAnnotation(Autowired.class);
                        //判断装配的时候是否指定别名
                        if (!"".equals(autowired.value())){
                            //按照名称装配
                            field.set(instance,this.beanDefinationsMap.get(autowired.value()));
                        }else{
                            //按照类型装配
                            String type = field.getType().getName();
                            field.set(instance,this.beanDefinationsMap.get(type));
                        }
                    }
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 创建bean
     */
    private void doCreateBean() {
        if(beansCache.size()==0){
            return;
        }
        for (Class<?> cl :beansCache){
            try {
                Object instance = cl.newInstance();
                String alias = getAlias(cl);
                //保存到对象的容器中
                beanDefinationsMap.put(alias,instance);
                //判断当前类是否实现了接口
                Class<?>[] interfaces = cl.getInterfaces();
                if (interfaces == null)continue;

                for(Class inter : interfaces){
                    this.beanDefinationsMap.put(inter.getName(),instance);
                }

            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }

        }
    }

    /**
     * 获取别名方法
     * @param cl
     * @return
     */
    private String getAlias(Class<?> cl) {
        //默认别名为首字母小写
        String alias = lowerClassName(cl.getSimpleName());
        //判断是否存在别名
        if (cl.isAnnotationPresent(Repository.class)) {
            Repository repository = cl.getAnnotation(Repository.class);
            if (repository.value() != null && !"".equals(repository.value())) {
                alias = repository.value();
            }
        } else if (cl.isAnnotationPresent(Service.class)) {
            Service service = cl.getAnnotation(Service.class);
            if (service.value() != null && !"".equals(service.value())) {
                alias = service.value();
            }
        }
        return alias;
    }

    /**
     * 把所有要管理的bean的class对象注册到缓存中
     */
    private void registryBean() {
        if (classPathCache.size() == 0){
            return;
        }
        for (String path: classPathCache){
            try {
                //通过反射获取
                Class<?> clazz = Class.forName(path);
                //找出@Service @Repository这注解
                if (clazz.isAnnotationPresent(Repository.class)||clazz.isAnnotationPresent(Service.class)){
                    beansCache.add(clazz);
                }
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

        }
    }

    private void scanPackage(String scanPackage) {
        URL url = this.getClass().getClassLoader().getResource(scanPackage.replaceAll("\\.", "/"));
        try {
            //创建一个文件对象
            File file = new File(url.toURI());
            file.listFiles(new FileFilter() {
                @Override
                public boolean accept(File child) {
                    //递归找文件
                    if (child.isDirectory()){
                        scanPackage(scanPackage+"."+child.getName());
                    }else{
                        if (child.getName() .endsWith(".class")){
//                            System.out.println(child.getName());
                            String classPath = scanPackage + "." + child.getName().replaceAll("\\.class", "");
                            classPathCache.add(classPath);
                        }
                    }
                    return false;
                }
            });
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }


    @Override
    protected Object doGetBean(String beanName) {
        return this.beanDefinationsMap.get(beanName);
    }

    /**
     * 首字母变小写
     * @param className
     * @return
     */
    public String lowerClassName(String className){
        char[] chars = className.toCharArray();
        chars[0] += 32;
        return new String(chars);
    }


    public static void main(String[] args) {
        new AnnotationApplicationContext();
        System.out.println(beanDefinationsMap);
    }
}
