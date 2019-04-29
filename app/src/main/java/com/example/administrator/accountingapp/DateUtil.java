package com.example.administrator.accountingapp;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 *
 */
public class DateUtil {//将unix时间->11:11
    public static String getFormattedTime(long timeStamp){//静态方法不需要初始化实例
        SimpleDateFormat format=new SimpleDateFormat("HH:mm");
        return  format.format(new Date(timeStamp));
    }
    //2019-03-05
    public static String getFormattedDate(){
        SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
        return  format.format(new Date());
    }

    private static Date strToDate(String date){
        SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
        try{
            return format.parse(date);//转为yyyy-MM-dd格式
        }catch (Exception e){
            e.printStackTrace();
        }

        return new Date();
    }
    //获取星期
    public static String getWeekDay(String date){
        String[] weekdays = {"Sunday","Monday","Tuesday","Wednesday","Thursday","Friday","Saturday"};
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(strToDate(date));
        int index = calendar.get(Calendar.DAY_OF_WEEK)-1;
        return  weekdays[index];
    }
    //获取月份
    public static String getDateTitle(String date){
        String[] months ={"January", "February", "March", "April", "May", "June","July", "August","September","October", "November", "December"};
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(strToDate(date));
        int monthIndex = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int year=calendar.get(Calendar.YEAR);
        return  String.valueOf(year)+" "+ months[monthIndex] + " "+String.valueOf(day);//返回2019 March 24格式
    }

}
