package com.taylor.ioc.core;

/**
 * @ClassName ApplicationContext
 * @Description TODO
 * @Author lidengtai
 * @Date 2019/7/7 14:45
 * @Version 1.0
 */
public abstract class ApplicationContext {

    /**
     * 通过容器中装配的别名获取对应实例
     * @param beanName
     * @return
     */
    public Object getBean(String beanName){
        return doGetBean(beanName);
    }

    //真正执行根据别名获取实例的方法
    protected abstract Object doGetBean(String beanName);

}
