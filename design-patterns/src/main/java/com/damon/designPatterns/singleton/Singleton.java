package com.damon.designPatterns.singleton;

/**
 * Singleton
 * 单例模式的五种写法：
 * 1、饿汉模式：线程安全，空间换时间
 * 2、饱汉模式：非线程安全
 * 3、双锁模式：线程安全，但效率不高
 * 4、静态内部类模式：线程安全，高效
 * 5、枚举模式：线程安全，简洁高效
 *
 * @author damon
 * @date 2017/4/26
 */
public class Singleton {

    /**
    * 饿汉模式

    private static Singleton singleton = new Singleton();

    private Singleton() {

    }

    public static Singleton getInstance() {
        return singleton;
    }
    */

    /**
     * 饱汉模式

    private static Singleton singleton;

    private Singleton() {

    }

    public static Singleton getInstance() {
        if (null == singleton) {
            singleton = new Singleton();
        }

        return singleton;
    }
     */

    /**
     * 双锁模式

    private volatile static Singleton singleton;

    private Singleton() {

    }

    public static Singleton getInstance() {
        if (null == singleton) {
            synchronized (Singleton.class) {
                if (null == singleton) {
                    synchronized (Singleton.class) {
                        singleton = new Singleton();
                    }
                }
            }
        }

        return singleton;
    }
     */

    /**
     * 静态内部类模式
     */
    private static class SingletonHolder {
        private static final Singleton singleton = new Singleton();
    }

    private Singleton() {

    }

    public static Singleton getInstance() {
        return SingletonHolder.singleton;
    }
}

/**
 * 枚举模式

public enum Singleton {
    SINGLETON;
}
 */
