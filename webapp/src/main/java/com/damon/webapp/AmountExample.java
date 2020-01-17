package com.damon.webapp;

import java.time.*;

/**
 * @author damon
 * @version 2019/6/14
 */
public class AmountExample {

    public static void main(String[] args) {
        LocalTime time1 = LocalTime.of(15, 7, 50);
        LocalTime time2 = LocalTime.of(16, 8, 50);
        Duration d1 = Duration.between(time1, time2);
        System.out.println("时间间隔:" + d1.toString());
        System.out.println("时间间隔秒数:" + d1.getSeconds());

        LocalDateTime dateTime1 = LocalDateTime.of(2018, 05, 12, 14, 22, 28);
        LocalDateTime dateTime2 = LocalDateTime.of(2019, 6, 12, 14, 22, 28);
        Duration d2 = Duration.between(dateTime1, dateTime2);
        System.out.println("时间间隔:" + d2.toString());
        System.out.println("时间间隔秒数:" + d2.getSeconds());

        Instant instant1 = Instant.ofEpochSecond(1560421023);
        Instant instant2 = Instant.now();
        Duration d3 = Duration.between(instant1, instant2);
        System.out.println("时间间隔:" + d3.toString());
        System.out.println("时间间隔秒数:" + d3.getSeconds());

        LocalDate now = LocalDate.now();
        LocalDate other = LocalDate.of(2018, 8, 11);
        Period period = Period.between(now, other);
        System.out.println("两个日期间隔:" + period.getYears() + "年" + period.getMonths() +"月" + period.getDays() + "天");
        System.out.println("两个日期间隔年份:" + period.getYears() + "年");
        System.out.println("两个日期间隔月份:" + period.getMonths() +"月");
        System.out.println("两个日期间隔天数:" + period.getDays() + "天");
    }
}
