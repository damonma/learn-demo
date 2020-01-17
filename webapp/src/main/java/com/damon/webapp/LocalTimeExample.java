package com.damon.webapp;

import java.time.LocalTime;
import java.time.temporal.ChronoField;

/**
 * @author damon
 * @version 2019/6/14
 */
public class LocalTimeExample {

    public static void main(String[] args) {
        LocalTime now = LocalTime.now();
        System.out.println("当前时间:" + now.toString());
        System.out.println("一天中第几小时:" + now.get(ChronoField.HOUR_OF_DAY));
        System.out.println("一天中第几分钟:" + now.get(ChronoField.MINUTE_OF_DAY));
        System.out.println("一天中第几秒:" + now.get(ChronoField.SECOND_OF_DAY));

        LocalTime time = LocalTime.of(15, 41, 30);
        int hour = time.getHour();
        int minute = time.getMinute();
        int second = time.getSecond();
        System.out.println("小时:"+hour+"分钟:"+minute+"秒:"+second);
        System.out.println("时间:" + time.toString());

        System.out.println("当前时间是否在指定时间之后:" + now.isAfter(time));
        System.out.println("当前时间是否在指定时间之前:" + now.isBefore(time));
    }
}
