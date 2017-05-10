package com.damon.designPatterns.chainOfResponsibility;

import java.math.BigDecimal;

/**
 * DeptHandler
 *
 * @author damon
 * @date 2017/5/5
 */
public class DeptHandler extends ConsumeHandler {

    public void doHandler(String user, BigDecimal free) {
        if (free.doubleValue() < 5000) {
            if (user.equals("qq")) {
                System.out.println(user + " no pass");
            } else {
                System.out.println(user + " pass");
            }
        } else {
            if (null != getNextHandler()) {
                getNextHandler().doHandler(user, free);
            }
        }
    }
}
