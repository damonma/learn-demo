package com.damon.webapp;

import java.util.concurrent.Callable;

/**
 * @author damon
 * @version 2019/2/25
 */
public class TestThread implements Callable<Integer> {

    @Override
    public Integer call() {
        doHandle();
        return 1;
    }

    private void doHandle() {
        throw new RuntimeException("异常测试");
    }
}
