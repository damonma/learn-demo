package com.damon.webapp;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

/**
 * @author damon
 * @version 2019/6/14
 */
public class FormatterExample {

    public static void main(String[] args) {
        localDate();
        localTime();
        localDateTime();
    }

    private static void localDate() {
        LocalDate localDate1 = LocalDate.of(2019, 06, 10);
        System.out.println("日期默认格式:" + localDate1.toString());

        // 日期转字符串
        DateTimeFormatter yyyyMMdd = DateTimeFormatter.ofPattern("yyyyMMdd");
        String dateStr1 = localDate1.format(yyyyMMdd);
        System.out.println("日期yyyyMMdd格式:" + dateStr1);
        DateTimeFormatter enumFormatter1 = DateTimeFormatter.ISO_WEEK_DATE;
        System.out.println("日期周格式:" + localDate1.format(enumFormatter1));
        DateTimeFormatter style1 = DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL);
        System.out.println("日期完整格式:" + localDate1.format(style1));

        // 字符串转日期
        String dateStr2 = "20190612";
        LocalDate localDate2 = LocalDate.parse(dateStr2, DateTimeFormatter.ofPattern("yyyyMMdd"));
        System.out.println("日期:" + localDate2.toString());
    }

    private static void localTime() {
        LocalTime localTime1 = LocalTime.of(20, 12, 30);
        System.out.println("时间默认格式:" + localTime1.toString());

        // 时间转字符串
        DateTimeFormatter hhmmss = DateTimeFormatter.ofPattern("HHmmss");
        String timeStr1 = localTime1.format(hhmmss);
        System.out.println("时间HHmmdd格式:" + timeStr1);
        DateTimeFormatter enumFormatter2 = DateTimeFormatter.ISO_TIME;
        System.out.println("时间格式:" + localTime1.format(enumFormatter2));
        DateTimeFormatter style2 = DateTimeFormatter.ofLocalizedTime(FormatStyle.SHORT);
        System.out.println("短时间格式:" + localTime1.format(style2));

        // 字符串转时间
        String timeStr2 = "1230:10";
        LocalTime localTime2 = LocalTime.parse(timeStr2, DateTimeFormatter.ofPattern("HHmm:ss"));
        System.out.println("时间:" + localTime2.toString());
    }

    private static void localDateTime() {
        LocalDateTime localDateTime1 = LocalDateTime.of(2019, 06, 10, 10, 30, 12);
        System.out.println("日期时间默认格式:" + localDateTime1.toString());

        // 日期转字符串
        DateTimeFormatter yyyyMMddHHmmss = DateTimeFormatter.ofPattern("yyyyMMdd HHmmss");
        String dateTimeStr1 = localDateTime1.format(yyyyMMddHHmmss);
        System.out.println("日期时间yyyyMMdd HHmmss格式:" + dateTimeStr1);
        DateTimeFormatter enumFormatter3 = DateTimeFormatter.ISO_DATE_TIME;
        System.out.println("日期时间格式:" + localDateTime1.format(enumFormatter3));
        DateTimeFormatter style1 = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.LONG, FormatStyle.SHORT);
        System.out.println("日期时间完整格式:" + localDateTime1.format(style1));

        // 字符串转日期
        String dateTimeStr2 = "2019/06/12 20/30:2";
        LocalDateTime localDateTime2 = LocalDateTime.parse(dateTimeStr2, DateTimeFormatter.ofPattern("yyyy/MM/dd " +
                "HH/mm:s"));
        System.out.println("日期时间:" + localDateTime2.toString());
    }
}
