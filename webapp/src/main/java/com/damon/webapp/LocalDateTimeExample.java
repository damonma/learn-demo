package com.damon.webapp;

import java.time.LocalDateTime;
import java.time.temporal.ChronoField;

/**
 * @author damon
 * @version 2019/6/14
 */
public class LocalDateTimeExample {

    public static void main(String[] args) {
        LocalDateTime now = LocalDateTime.now();
        System.out.println("当前日期时间:" + now.toString());
        System.out.println("周几:" + now.getDayOfWeek());
        System.out.println("周中第几天:" + now.get(ChronoField.DAY_OF_WEEK));
        System.out.println("月中第几天:" + now.getDayOfMonth());
        System.out.println("年中第几天:" + now.getDayOfYear());
        System.out.println("年份:" + now.getYear());
        System.out.println("月份:" + now.getMonth());
        System.out.println("小时:" + now.getHour());
        System.out.println("分钟:" + now.getMinute());
        System.out.println("秒:" + now.getSecond());

        LocalDateTime other = LocalDateTime.of(2019, 06, 10, 15, 20, 24);
        System.out.println("日期时间:" + other.toString());
        System.out.println("当前日期时间是否在指定日期时间之前:" + now.isBefore(other));
        System.out.println("当前日期时间是否在指定日期时间之后:" + now.isAfter(other));
    }
}
