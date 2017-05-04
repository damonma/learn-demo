package com.damon.designPattern.flyweight;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * FlyweightCompositeFactory
 *
 * @author damon
 * @date 2017/5/4
 */
public class FlyweightCompositeFactory {

    private Map<String, Flyweight> flyweightMap = new HashMap<String, Flyweight>();

    public Flyweight factory(List<String> compositeStates) {
        ConcreteCompositeFlyweight compositeFlyweight = new ConcreteCompositeFlyweight();
        for (String s : compositeStates) {
            compositeFlyweight.add(s, this.factory(s));
        }

        return compositeFlyweight;
    }

    public Flyweight factory(String s) {
        Flyweight flyweight = flyweightMap.get(s);
        if (null == flyweight) {
            flyweight = new ConcreteFlyweight(s);
            flyweightMap.put(s, flyweight);
        }

        return flyweight;
    }
}
