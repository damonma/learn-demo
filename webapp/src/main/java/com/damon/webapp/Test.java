package com.damon.webapp;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;


/**
 * @author damon
 * @version 2019/2/25
 */
public class Test {

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        TestThread testThread = new TestThread();
        Future<Integer> feature = executorService.submit(testThread);

        executorService.shutdown();
        try {
            Integer result = feature.get();
            System.out.println(result);
        } catch (InterruptedException e) {
            System.out.println("InterruptedException");
        } catch (ExecutionException e) {
            System.out.println("ExecutionException");
        }

        System.out.println("end");
    }
}
