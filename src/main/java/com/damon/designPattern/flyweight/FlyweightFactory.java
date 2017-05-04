package com.damon.designPattern.flyweight;

import java.util.HashMap;
import java.util.Map;

/**
 * FlyweightFactory
 *
 * @author damon
 * @date 2017/5/4
 */
public class FlyweightFactory {

    private Map<String, ConcreteFlyweight> flyweightMap = new HashMap<String, ConcreteFlyweight>();

    public ConcreteFlyweight factory(String str) {
        ConcreteFlyweight flyweight = flyweightMap.get(str);
        if (null == flyweight) {
            flyweight = new ConcreteFlyweight(str);
            flyweightMap.put(str, flyweight);
        }
        return flyweight;
    }

    public int getFlyweightSize() {
        return flyweightMap.size();
    }
}
