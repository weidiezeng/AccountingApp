package com.example.administrator.accountingapp;

import java.text.SimpleDateFormat;
import java.util.Date;

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
}
