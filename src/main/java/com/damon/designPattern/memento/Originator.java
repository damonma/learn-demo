package com.damon.designPattern.memento;

/**
 * Originator
 *
 * @author damon
 * @date 2017/5/8
 */
public class Originator {

    private int bloodVal;

    private int magicVal;

    public Originator(int bloodVal, int magicVal) {
        this.bloodVal = bloodVal;
        this.magicVal = magicVal;
    }

    public int getBloodVal() {
        return bloodVal;
    }

    public void setBloodVal(int bloodVal) {
        this.bloodVal = bloodVal;
    }

    public int getMagicVal() {
        return magicVal;
    }

    public void setMagicVal(int magicVal) {
        this.magicVal = magicVal;
    }

    public void display() {
        System.out.println("用户当前状态: ");
        System.out.println("血量：" + bloodVal + ", 蓝量：" + magicVal);
    }

    public Memento saveMemento() {
        return new Memento(getBloodVal(), getMagicVal());
    }

    public void restoreMemento(Memento memento) {
        this.bloodVal = memento.getBloodVal();
        this.magicVal = memento.getMagicVal();
    }
}
