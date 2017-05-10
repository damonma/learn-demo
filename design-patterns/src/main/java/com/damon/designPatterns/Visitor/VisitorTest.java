package com.damon.designPatterns.Visitor;


import java.util.List;

/**
 * VisitorTest
 *
 * @author damon
 * @date 2017/5/8
 */
public class VisitorTest {

    public static void main(String[] args) {
        List<Element> list = ObjectStructure.getList();
        for(Element e:list){
            e.accept(new Visitor());
        }
    }
}
