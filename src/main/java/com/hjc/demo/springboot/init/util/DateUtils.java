package com.hjc.demo.springboot.init.util;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Date;

public class DateUtils {
    public static void main(String[] args) {
        LocalDateTime localDateTime = LocalDateTime.now();
        localDateTime.plusDays(2);
        String str1 = "2019-03-11 03:17:09";
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime start = LocalDateTime.parse(str1, dtf);
        System.out.println(start + "===" + localDateTime);
        long day = ChronoUnit.DAYS.between(start, localDateTime);
        long hour = ChronoUnit.HOURS.between(start.plusDays(day), localDateTime);
        long min = ChronoUnit.MINUTES.between(start.plusDays(day), localDateTime);
        System.out.println(day);
        System.out.println(hour);
        System.out.println(min);


        ZoneId zone = ZoneId.systemDefault();
        Instant instant = start.atZone(zone).toInstant();
        Date date = Date.from(instant);

        System.out.println(betweenDayDesc(date, 4));
    }

    /**
     * 获取与目前相距天数描述
     *
     * @param date 最初时间
     * @param plusDay 加上的天数
     * @return
     */
    public static String betweenDayDesc(Date date, int plusDay) {
        LocalDateTime startDay = LocalDateTime.ofInstant(date.toInstant(),
                ZoneId.systemDefault()).plusDays(plusDay);
        LocalDateTime now = LocalDateTime.now();
        long betweenDay = ChronoUnit.DAYS.between(now, startDay);
        long betweenHour = ChronoUnit.HOURS.between(now.plusDays(betweenDay), startDay);
        long betweenMinutes = ChronoUnit.MINUTES.between(now.plusDays(betweenDay).plusHours(betweenHour), startDay);
        System.out.println(betweenMinutes);
        System.out.println(startDay);
        if (betweenMinutes <= 0) {
            return "0天";
        }
        return new StringBuilder().append(betweenDay).append("天").append(betweenHour)
                .append("小时").append(betweenMinutes).append("分").toString();
    }
}
