package com.damon.designPattern.chainOfResponsibility;

import java.math.BigDecimal;

/**
 * ChainTest
 *
 * @author damon
 * @date 2017/5/5
 */
public class ChainTest {

    public static void main(String[] args) {
        ConsumeHandler project = new ProjectHandler();
        ConsumeHandler dept = new DeptHandler();
        ConsumeHandler general = new GeneralHandler();
        project.setNextHandler(dept);
        dept.setNextHandler(general);
        project.doHandler("jj", new BigDecimal(2000));
        project.doHandler("jj", new BigDecimal(300));
        project.doHandler("qq", new BigDecimal(2000));
        project.doHandler("zzh", new BigDecimal(20000));
        project.doHandler("qq", new BigDecimal(20000));
    }
}
