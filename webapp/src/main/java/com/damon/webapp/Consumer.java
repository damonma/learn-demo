package com.damon.webapp;

import com.damon.dubbo.provider.api.IHelloService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Consumer
 *
 * @author damon
 * @date 2017/5/10
 */
public class Consumer {
    public static void main(String[] args) throws Exception {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[]
                {"classpath:webapp-applicationContext.xml"});
        context.start();

        IHelloService helloService = (IHelloService)context.getBean("helloService"); // 获取远程服务代理
        helloService.hello("world"); // 执行远程方法

    }
}
