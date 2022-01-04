package com.damon.dubbo.provider;

import com.damon.dubbo.provider.api.IHelloService;
import org.springframework.stereotype.Service;

/**
 * HelloService
 *
 * @author damon
 * @date 2017/5/10
 */
@Service("helloService")
public class HelloService implements IHelloService {

    public void hello(String name) {
        System.out.println("Hello " + name);
    }
}
