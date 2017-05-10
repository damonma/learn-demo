package com.damon.designPatterns.memento;

/**
 * MementoTest
 *
 * @author damon
 * @date 2017/5/8
 */
public class MementoTest {

    public static void main(String[] args) {
        Originator originator = new Originator(100,100);
        System.out.println("Before fighting BOSS...");
        originator.display();

        //存档
        Caretaker caretaker = new Caretaker();
        caretaker.setMemento(originator.saveMemento());

        //Fighting
        System.out.println("Fighting...");
        originator.setBloodVal(20);
        originator.setMagicVal(20);
        originator.display();

        //回复存档
        System.out.println("Restore...");
        originator.restoreMemento(caretaker.getMemento());
        originator.display();
    }
}
