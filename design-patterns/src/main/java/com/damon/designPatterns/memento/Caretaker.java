package com.damon.designPatterns.memento;

/**
 * Caretaker
 *
 * @author damon
 * @date 2017/5/8
 */
public class Caretaker {

    private Memento memento;

    public Memento getMemento() {
        return memento;
    }

    public void setMemento(Memento memento) {
        this.memento = memento;
    }
}
