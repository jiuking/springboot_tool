package com.hjc.demo.springboot.init;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;

/**
 * @author : Administrator
 * @date : 2019/3/21 0021 17:22
 * @description :
 */
public class DateTest {
    public static void main1(String[] args) {
        String _asdf;
        String $asdf;
        String adf$_;

        String d = "yyyy-MM-dd";
        String date = DateTest.format(new Date(),d);
        System.out.println(date);
        Date date1 = stringToDate("2019-03-23", d);
        System.out.println(format(stringToDate(date,d),d));
        java.time.LocalDate localDate = java.time.LocalDate.now();
        System.out.println(localDate.isBefore(java.time.LocalDate.parse("2019-03-23", java.time.format.DateTimeFormatter.ofPattern("yyyy-MM-dd"))));
        java.time.LocalDateTime localDateTime = java.time.LocalDateTime.now();
        boolean is = localDateTime.isBefore(java.time.LocalDateTime.ofInstant(date1.toInstant(),
                ZoneId.systemDefault()));
        System.out.println(localDateTime);
        System.out.println(java.time.LocalDateTime.ofInstant(date1.toInstant(),
                ZoneId.systemDefault()));
        System.out.println(localDateTime.compareTo(java.time.LocalDateTime.ofInstant(date1.toInstant(),
                ZoneId.systemDefault())));
        System.out.println(localDateTime.equals(java.time.LocalDateTime.ofInstant(date1.toInstant(),
                ZoneId.systemDefault())));
        System.out.println(is);

        System.out.println(compareToNowOvertime(date1));
    }
    public static String format(Date date, String pattern) {
        if (date != null) {
            SimpleDateFormat df = new SimpleDateFormat(pattern);
            return df.format(date);
        }
        return null;
    }

    public static String format1(Date date, String pattern) {
        if (date != null) {
            SimpleDateFormat df = new SimpleDateFormat(pattern);
            return df.format(date);
        }
        return null;
    }

    public static Date stringToDate(String strDate, String pattern) {
        if (StringUtils.isBlank(strDate)) {
            return null;
        }

        DateTimeFormatter fmt = DateTimeFormat.forPattern(pattern);
        return fmt.parseLocalDateTime(strDate).toDate();
    }
    static List<String> list;
    public static void main(String[] args) {
        Date date1 = stringToDate("2019-03-23", "yyyy-MM-dd");
        System.out.println(compareToNowOvertime(date1));
        Set set = new HashSet();
        System.out.println(new ArrayList(set).size());
//        list.forEach(System.out::println);
        System.out.println(list);

        String json = "{\"result\":\"Y\",\"idCard\":null,\"telphone\":\"18037148757\",\"name\":\"增信通111\",\"resCode\":\"1\",\"message\":\"接口调用成功\",\"type\":\"ZYSP\"}";
        System.out.println(Objects.isNull(JSONObject.parseObject(json).get("idCard")));
    }

    public static boolean compareToNowOvertime(Date date) {
        java.time.LocalDate beforeDate = LocalDateTime.ofInstant(date.toInstant(),
                ZoneId.systemDefault()).toLocalDate();
        java.time.LocalDate now = java.time.LocalDate.now();
        if (now.equals(beforeDate)) {
            return false;
        }
        return beforeDate.isBefore(now);
    }
}
