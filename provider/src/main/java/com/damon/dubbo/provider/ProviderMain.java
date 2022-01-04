package com.damon.dubbo.provider;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

/**
 * ProviderMain
 *
 * @author damon
 * @date 2017/5/10
 */
public class ProviderMain {

    public static void main(String[] args) throws IOException {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[]
                {"classpath:provider-applicationContext.xml"});
        context.start();

        System.in.read();
    }

}
