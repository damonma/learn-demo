package com.damon.designPattern.flyweight;

import com.sun.xml.internal.ws.util.localization.NullLocalizable;

import java.util.HashMap;
import java.util.Map;

/**
 * ConcreteCompositeFlyweight
 *
 * @author damon
 * @date 2017/5/4
 */
public class ConcreteCompositeFlyweight implements Flyweight {

    private Map<String, Flyweight> flyweightMap = new HashMap<String, Flyweight>();

    public void add(String key, Flyweight flyweight) {
        flyweightMap.put(key, flyweight);
    }

    public void operation(String state) {
        Flyweight flyweight = null;

        for (String key : flyweightMap.keySet()) {
            flyweight = flyweightMap.get(key);
            flyweight.operation(state);
        }
    }
}
