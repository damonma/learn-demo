package com.damon.designPatterns.chainOfResponsibility;

import java.math.BigDecimal;

/**
 * ProjectHandler
 *
 * @author damon
 * @date 2017/5/5
 */
public class ProjectHandler extends ConsumeHandler {

    public void doHandler(String user, BigDecimal free) {
        if (free.doubleValue() < 100) {
            if (user.equals("jj")) {
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
