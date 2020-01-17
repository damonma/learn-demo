package com.damon.webapp;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;

/**
 * @author damon
 * @version 2019/6/14
 */
public class LocalDateExample {

    public static void main(String[] args) {
        LocalDate date = LocalDate.of(2019, 06, 11);
        System.out.println("年份:"+date.getYear());

        Month month = date.getMonth();
        System.out.println("月份(单词):"+month);
        int monthValue = date.getMonthValue();
        System.out.println("月份(数字)"+monthValue);

        System.out.println("多少号:"+date.getDayOfMonth());


        System.out.println("返回由此日期表示的月份的长度:"+date.lengthOfMonth());
        System.out.println("返回由此日期表示的年份的长度:"+date.lengthOfYear());

        DayOfWeek dayOfWeek = date.getDayOfWeek();
        System.out.println("星期几(单词):"+dayOfWeek);
        System.out.println("星期几(数字):"+dayOfWeek.getValue());


        System.out.println("一年中的第几天:"+date.getDayOfYear());


        System.out.println("是否是闰年:"+ date.isLeapYear());

        LocalDate a = LocalDate.of(2019, 6, 30);
        System.out.println("检查此日期是否在指定日期之后:"+ date.isAfter(a));
        System.out.println("检查此日期是否在指定日期之前:"+date.isBefore(a));
        System.out.println("检查此日期是否等于指定的日期。"+date.isEqual(a));

        System.out.println("将日期输出:"+date.toString());
    }
}
