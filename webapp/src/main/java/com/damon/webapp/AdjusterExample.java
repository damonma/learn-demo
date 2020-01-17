package com.damon.webapp;

import java.time.*;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;

/**
 * @author damon
 * @version 2019/6/14
 */
public class AdjusterExample {

    public static void main(String[] args) {
//        localDateTime();
//        instant();
        adjuster();
    }

    private static void localDateTime() {
        LocalDateTime localDateTime = LocalDateTime.of(2019, 06, 10, 12, 30,20);
        System.out.println("当前日期时间:" + localDateTime.toString());

        // 加50秒
        LocalDateTime sdt1 = localDateTime.plusSeconds(50);
        System.out.println("加50秒:" + sdt1);
        LocalDateTime sdt2 = localDateTime.plus(50, ChronoUnit.SECONDS);
        System.out.println("加50秒:" + sdt2);

        // 加3分
        LocalDateTime mdt1 = localDateTime.plusMinutes(3);
        System.out.println("加3分:" + mdt1);
        LocalDateTime mdt2 = localDateTime.plus(3, ChronoUnit.MINUTES);
        System.out.println("加3分:" + mdt2);

        // 减10小时
        LocalDateTime hdt1 = localDateTime.minusHours(10);
        System.out.println("减10小时:" + hdt1);
        LocalDateTime hdt2 = localDateTime.minus(10, ChronoUnit.HOURS);
        System.out.println("减10小时:" + hdt2);

        // 加一周
        LocalDateTime wd1 = localDateTime.plusWeeks(1);
        System.out.println("加一周:" + wd1);
        LocalDateTime wd2 = localDateTime.plus(1, ChronoUnit.WEEKS);
        System.out.println("加一周:" + wd2);

        // 加一天
        LocalDateTime dd1 = localDateTime.plusDays(1);
        System.out.println("加一天:" + dd1);
        LocalDateTime dd2 = localDateTime.plus(1, ChronoUnit.DAYS);
        System.out.println("加一天:" + dd2);

        // 减一月
        LocalDateTime md1 = localDateTime.minusMonths(1);
        System.out.println("减一月:" + md1);
        LocalDateTime md2 = localDateTime.minus(1, ChronoUnit.MONTHS);
        System.out.println("减一月:" + md2);

        // 减一年
        LocalDateTime yd1 = localDateTime.minusYears(1);
        System.out.println("减一年:" + yd1);
        LocalDateTime yd2 = localDateTime.minus(1, ChronoUnit.YEARS);
        System.out.println("减一月:" + yd2);

        LocalDateTime dd3 = localDateTime.with(ChronoField.DAY_OF_MONTH, 11);
        System.out.println("设置天数:" + dd3);
        LocalDateTime md3 = localDateTime.with(ChronoField.MONTH_OF_YEAR, 05);
        System.out.println("设置月份:" + md3);
        LocalDateTime yd3 = localDateTime.with(ChronoField.YEAR, 2018);
        System.out.println("设置年份:" + yd3);
    }

    private static void instant() {
        Instant instant = Instant.ofEpochMilli(1560496740);
        System.out.println("当前时间戳:" + instant.toString());

        // 加一天
        Instant si1 = instant.plus(Period.ofDays(1));
        System.out.println("加一天:" + si1);

        // 加1000秒
        Instant si2 = instant.plus(1000, ChronoUnit.SECONDS);
        System.out.println("加1000秒:" + si2);

        // 减一周
        Instant si3 = instant.minus(Period.ofWeeks(1));
        System.out.println("减一周:" + si3);

        // 减一小时
        Instant si4 = instant.minus(1, ChronoUnit.HOURS);
        System.out.println("减一小时:" + si4);

        Instant wi1 = instant.with(ChronoField.MILLI_OF_SECOND, 100);
        System.out.println("设置毫妙:" + wi1);
        Instant wi2 = instant.with(ChronoField.MICRO_OF_SECOND, 5000);
        System.out.println("设置微妙:" + wi2);
        Instant wi3 = instant.with(ChronoField.NANO_OF_SECOND, 2000);
        System.out.println("设置纳秒:" + wi3);
        Instant wi4 = instant.with(ChronoField.NANO_OF_SECOND, 2000);
        System.out.println("设置秒:" + wi4);
    }

    private static void adjuster() {
        LocalDate localDate = LocalDate.of(2019, 6, 10);
        System.out.println("当前时间:" + localDate.toString());

        // 当前日期后的周一(含当天)
        LocalDate wfd = localDate.with(TemporalAdjusters.nextOrSame(DayOfWeek.MONDAY));
        System.out.println("当前日期后的周一(含当天):" + wfd);
        // 当前日期后的周一(不含当天)
        LocalDate wfd2 = localDate.with(TemporalAdjusters.next(DayOfWeek.MONDAY));
        System.out.println("当前日期后的周一(不含当天):" + wfd2);


        LocalDate localDate2 = LocalDate.of(2019, 6, 9);
        System.out.println("当前时间:" + localDate2.toString());
        // 当前日期后的周日(含当天)
        LocalDate wld = localDate2.with(TemporalAdjusters.previousOrSame(DayOfWeek.SUNDAY));
        System.out.println("当前日期后的周日(含当天):" + wld);
        // 当前日期后的周日(不含当天)
        LocalDate wld2 = localDate2.with(TemporalAdjusters.previous(DayOfWeek.SUNDAY));
        System.out.println("当前日期后的周日(不含当天):" + wld2);

        // 本月第一天
        LocalDate mfd = localDate.with(TemporalAdjusters.firstDayOfMonth());
        System.out.println("本月第一天:" + mfd);
        // 上月最后一天
        LocalDate mld = localDate.minusMonths(1).with(TemporalAdjusters.lastDayOfMonth());
        System.out.println("上月最后一天:" + mld);

        // 本年第一天
        LocalDate yfd = localDate.with(TemporalAdjusters.firstDayOfYear());
        System.out.println("本年第一天:" + yfd);
        // 本年最后一天
        LocalDate yld = localDate.with(TemporalAdjusters.lastDayOfYear());
        System.out.println("本年最后一天:" + yld);

        LocalDate localDate3 = LocalDate.of(2019, 6, 14);
        System.out.println("当前时间:" + localDate3.toString());
        // 自定义调整策略
        TemporalAdjuster nextWorkingDay = TemporalAdjusters.ofDateAdjuster(
            temporal -> {
                //读取当前天数
                DayOfWeek dow = DayOfWeek.of(temporal.get(ChronoField.DAY_OF_WEEK));
                //正常情况增加1天
                int dayToAdd = 1;
                //如果当天是周五,增加三天
                if (dow == DayOfWeek.FRIDAY) {
                    dayToAdd = 3;
                }
                //如果当天是周六增加两天
                if (dow == DayOfWeek.SATURDAY) {
                    dayToAdd = 2;
                }
                //增加恰当的天数后,返回修改的日期
                return temporal.plus(dayToAdd, ChronoUnit.DAYS);
            });
        LocalDate sd = localDate3.with(nextWorkingDay);
        System.out.println("自定义调整策略:" + sd);
    }
}
