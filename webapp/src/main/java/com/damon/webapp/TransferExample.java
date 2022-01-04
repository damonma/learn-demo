package com.damon.webapp;

import java.time.*;
import java.util.Calendar;
import java.util.Date;

/**
 * @author damon
 * @version 2019/6/14
 */
public class TransferExample {

    public static void main(String[] args) {
        // 新API
        LocalDateTime ldt1 = LocalDateTime.of(2019, 6, 10, 10, 12, 30);
        System.out.println("当前日期时间:" + ldt1);
        // LocalDateTime ==> LocalDate
        LocalDate ld1 = ldt1.toLocalDate();
        System.out.println("日期:" + ld1);
        // LocalDateTime ==> LocalTime
        LocalTime lt1 = ldt1.toLocalTime();
        System.out.println("时间:" + lt1);

        // LocalDate,LocalTime ==> LocalDateTime
        LocalDate ld2 = LocalDate.MAX;
        LocalTime lt2 = LocalTime.MAX;
        LocalDateTime ldt2 = LocalDateTime.of(ld2, lt2);
        System.out.println("日期时间:" + ldt2);

        // LocalDateTime ==> Instant
        Instant i1 = ldt1.toInstant(ZoneOffset.UTC);
        System.out.println("时间戳:" + i1);

        // Instant ==> LocalDateTime
        LocalDateTime ldt3 = LocalDateTime.ofInstant(Instant.ofEpochSecond(1560421023), ZoneId.of("UTC"));
        System.out.println("日期时间:" + ldt3);


        // 新旧API
        // LocalDate ==> Date
        Date d1 = Date.from(ld1.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
        System.out.println("旧日期:" + d1);

        // LocalDateTime ==> Date
        Date d2 = Date.from(ldt1.atZone(ZoneId.systemDefault()).toInstant());
        System.out.println("旧日期时间:" + d2);

        // Date ==> LocalDateTime
        ZoneId zoneId = ZoneId.systemDefault();
        LocalDateTime ldt4 = new Date().toInstant().atZone(zoneId).toLocalDateTime();
        System.out.println("新日期时间:" + ldt4);

        // Instant ==> Date
        Date d3 = Date.from(i1);
        System.out.println("旧日期:" + d3);

        // Date ==> Instant
        Instant i2 = new Date().toInstant();
        System.out.println("时间戳:" + i2);

        // Calendar ==> Instant
        Instant i3 = Calendar.getInstance().toInstant();
        System.out.println("时间戳:" + i3);
    }
}
