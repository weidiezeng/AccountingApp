package com.example.administrator.accountingapp;

public class RecordBean {
    public enum RecordType{
        RECORD_TYPE_EXPENSE,RECORD_TYPE_INCOME
    }
    private double amount;
    private RecordType type;
    private String category;
    private String remark;
    private String date;
    private long timeStamp;
}
