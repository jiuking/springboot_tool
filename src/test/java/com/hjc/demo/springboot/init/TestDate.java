package com.hjc.demo.springboot.init;

import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;
import java.util.Date;

/**
 * @author : Administrator
 * @date : 2019/3/12 0012 10:44
 * @description : 日期计算
 */
public class TestDate {
    public static void main1(String[] args) throws ParseException {
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat( "yyyy-MM-dd HH-mm-ss");

        String time1= "2019-03-12 23-59-51";
        String time2= "2019-03-13 23-59-51";
        Date d1 =  simpleDateFormat.parse(time1);
        Date d2 =  simpleDateFormat.parse(time2);

        long day=Math.abs(d2.getTime()-d1.getTime());//得到的两个日期的毫秒数大小
        day = day / (1000 * 60 * 60 * 24);
        System.out.println("相差的天数为： "+day + 1);
        System.out.println(d1.after(d2));

        System.out.println(d1);
        System.out.println(DateUtil.between(d1,d2, DateUnit.DAY));
        System.out.println(dateToNowInterval(d1));

        System.out.println("isOverdue: "+isOverdue(Integer.valueOf(0), dateToNowInterval(d1)));

        System.out.println(LocalDateTime.now());
        test(d1, 1);
//        LocalDate startDate =  LocalDate.of(2017, Month.DECEMBER,5);
//        LocalDate endDate =  LocalDate.of(2017, Month.DECEMBER,20);
//
//        System.out.println(until(startDate));
//        System.out.println(DateUtil.until(startDate,endDate));


    }

    public static int dateToNowInterval(Date date){
//        return (int) ChronoUnit.DAYS.between(LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault()).toLocalDate(), java.time.LocalDate.now());
        long a = date.getTime() - new Date().getTime();
        return 0;
    }

    public static void test(Date d1 ,int plus) {
        LocalDateTime localDateTime = LocalDateTime.now();
        LocalDateTime localDateTime1 = localDateTime.plusDays(-plus);
        System.out.println(localDateTime1);
        ZoneId zoneId = ZoneId.systemDefault();
        ZonedDateTime zdt = localDateTime1.atZone(zoneId);//Combines this date-time with a time-zone to create a  ZonedDateTime.
        Date date1 = Date.from(zdt.toInstant());
        System.out.println(date1);
        System.out.println(d1.before(date1));
    }

    /**
     * 计算当前日期与{@code endDate}的间隔天数
     *
     * @param endDate
     * @return 间隔天数
     */
    static long until(LocalDate endDate){
        return LocalDate.now().until(endDate, ChronoUnit.DAYS);
    }

    /**
     * 计算日期{@code startDate}与{@code endDate}的间隔天数
     *
     * @param startDate
     * @param endDate
     * @return 间隔天数
     */
    static long until(LocalDate startDate, LocalDate endDate){
        return startDate.until(endDate, ChronoUnit.DAYS);
    }

    static boolean isOverdue(long date1, long date2) {
        return date2 - date1 > 0;
    }

    public static void main(String[] args) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat( "yyyy-MM-dd HH-mm-ss");
        String time1= "2019-03-11 11-59-51";
        String time2= "2019-03-13 23-59-51";
        Date d1 = simpleDateFormat.parse(time1);
        System.out.println(plusDaysOverdue(d1, 1));
    }
    public static boolean plusDaysOverdue(Date start, int plus) {
        LocalDateTime localDateTime = LocalDateTime.now().plusDays(-plus);
        ZoneId zoneId = ZoneId.systemDefault();
        ZonedDateTime zdt = localDateTime.atZone(zoneId);
        Date end = Date.from(zdt.toInstant());
        return start.before(end);
    }
}
