package com.damon.webapp;

import java.time.Instant;
import java.time.temporal.ChronoField;

/**
 * @author damon
 * @version 2019/6/14
 */
public class InstantExample {

    public static void main(String[] args) {
        Instant now = Instant.now();
        System.out.println("当前时间戳:" + now);
        System.out.println("秒数:" + now.getEpochSecond());
        System.out.println("纳秒:" + now.getNano());
        System.out.println("纳秒:" + now.getLong(ChronoField.NANO_OF_SECOND));

        Instant other = Instant.ofEpochSecond(1560421023);
        System.out.println("时间戳:" + other.toString());
        System.out.println("当前时间戳是否在指定时间戳之前:" + now.isBefore(other));
        System.out.println("当前时间戳是否在指定时间戳之后:" + now.isAfter(other));
    }
}

