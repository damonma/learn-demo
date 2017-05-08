package com.damon.designPattern.memento;

/**
 * Memento
 *
 * @author damon
 * @date 2017/5/8
 */
public class Memento {

    private int bloodVal;

    private int magicVal;

    public Memento(int bloodVal, int magicVal) {
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
}
