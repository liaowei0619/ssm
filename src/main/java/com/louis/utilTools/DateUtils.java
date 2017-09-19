package com.louis.utilTools;

import com.auth0.jwt.internal.org.apache.commons.lang3.StringUtils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by liaowei on 2017/1/8. DateUtils 日期时间工具类
 */
public class DateUtils {

    private static String dateTimePattern = "yyyy-MM-dd HH:mm:ss";

    private static String data_Pattern = "yyyy-MM-dd";

    /**
     * 获取当前时间：yyyy-MM-dd HH:mm:ss
     */
    public static Date getDate() {
        Calendar calendar = Calendar.getInstance();
        return calendar.getTime();
    }

    /**
     * 获取字符串：yyyy-MM-dd HH:mm:ss
     */
    public static String getStrDate(Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(dateTimePattern);
        return dateFormat.format(date);
    }

    /**
     * String 转化为 Datetime yyyymmdd
     *
     * @param str
     * @return
     */
    public static Date convertStrToDateTime(String str) {
        Date date = null;
        SimpleDateFormat sdf = new SimpleDateFormat(data_Pattern);
        try {
            date = sdf.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    /**
     * String 转化为 Datetime yyyymmdd hhmmsss
     *
     * @param str
     * @return
     */
    public static Date convertStrToTime(String str) {
        Date date = null;
        SimpleDateFormat sdf = new SimpleDateFormat(dateTimePattern);
        try {
            date = sdf.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    /**
     * @param str yyyy/mm/dd
     * @return yyyymmdd
     */
    public static String convert_yyyymmdd(String str) {
        String result = "";
        if (StringUtils.isNotEmpty(str) && str.length() >= 10) {
            String[] splitStr = str.split("/");
            for (int i = 0; i < splitStr.length; i++) {
                result += splitStr[i];
            }

        }
        return result;
    }

    /**
     * 字符串的年月日 yyyymmdd
     *
     * @param date
     * @return
     */
    public static String convert_yyyymmdd(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat(data_Pattern);
        String str = sdf.format(date);
        return str;
    }

    /**
     * 生成Id
     *
     * @return
     */
    public static String createId() {
        return new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date());
    }

    /**
     * 获取时间差
     *
     * @param startTime
     * @param endTime
     * @return
     */
    public static long getDiffms(String startTime, String endTime) {
        DateFormat df = new SimpleDateFormat(dateTimePattern);
        try {
            Date d1 = df.parse(startTime);
            Date d2 = df.parse(endTime);
            long diff = d1.getTime() - d2.getTime();
            return Math.abs(diff / (1000 * 60));
        } catch (Exception e) {
        }
        return 0;
    }

    /**
     * 将字符串的YYYYMMDDHHmmss转化为 年 月 日 时
     *
     * @param param1
     * @return
     */
    public static String convertYMD(String param1) {
        String result = null;
        if (StringUtils.isNotEmpty(param1) && param1.length() >= 12) {
            String[] yuds = param1.split(" ");

            String param = yuds[0].substring(0, 10);
            String[] ymd = param.split("-");
            result = ymd[0] + "年" + ymd[1] + "月" + ymd[2] + "日"
                    + yuds[1].substring(0, 2) + "时";
        }

        return result;
    }

    /**
     * 得到当前时间几天后的时间
     *
     * @param day
     * @return
     */
    public static String getDateAfter(String time, int day) {
        Calendar now = Calendar.getInstance();
        now.setTime(convertStrToTime(time));
        now.set(Calendar.DATE, now.get(Calendar.DATE) + day);
        return getStrDate(now.getTime());
    }

    /**
     * 向某个时间点增减分组
     *
     * @param date
     * @param time
     * @return
     */
    public static String addmuTime(String date, Integer time) {
        SimpleDateFormat sdf = new SimpleDateFormat(dateTimePattern);// 格式化
        Date inDate = null;
        try {
            inDate = sdf.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Calendar calendar = Calendar.getInstance();// 素输入时间
        calendar.setTime(inDate);
        calendar.add(Calendar.MINUTE, time);// 加time分钟

        Date outDate = calendar.getTime();
        return sdf.format(outDate);
    }

    public  static  int muliNum(int num){
        int result;
        if (num<=1){
    
            result= num;
        }else {
            result= num*muliNum(num-1);
        }
        return result;
    }
    
    public static void main(String[] args) {
    
        for (int i = 0; i < 10 ; i++) {
            System.out.println(i+"-阶乘...."+muliNum(i));
        }
        
    }
}
