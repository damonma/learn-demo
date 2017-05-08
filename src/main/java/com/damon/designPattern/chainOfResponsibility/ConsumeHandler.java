package com.damon.designPattern.chainOfResponsibility;

import java.math.BigDecimal;

/**
 * ConsumeHandler
 *
 * @author damon
 * @date 2017/5/5
 */
public abstract class ConsumeHandler {

    private ConsumeHandler nextHandler;

    public ConsumeHandler getNextHandler() {
        return nextHandler;
    }

    public void setNextHandler(ConsumeHandler nextHandler) {
        this.nextHandler = nextHandler;
    }

    public abstract void doHandler(String user, BigDecimal free);
}
