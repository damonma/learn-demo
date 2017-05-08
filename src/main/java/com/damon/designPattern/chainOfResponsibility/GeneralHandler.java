package com.damon.designPattern.chainOfResponsibility;

import java.math.BigDecimal;

/**
 * GeneralHandler
 *
 * @author damon
 * @date 2017/5/5
 */
public class GeneralHandler extends ConsumeHandler {

    public void doHandler(String user, BigDecimal free) {
        if (free.doubleValue() >= 5000) {
            if  (user.equals("zzh")) {
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
