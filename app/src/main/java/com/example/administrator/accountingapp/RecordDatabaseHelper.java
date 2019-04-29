package com.example.administrator.accountingapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.LinkedList;

public class RecordDatabaseHelper extends SQLiteOpenHelper{

    public static final String DB_NAME="Record";
    private static final String CREATE_RECORD_DB="create table Record("
            + "id integer primary key autoincrement,"
            + "uuid text,"
            + "type integer,"
            + "category text,"
            + "remark text,"
            + "amount double,"
            + "time integer,"
            + "date date)";
    public RecordDatabaseHelper( Context context,  String name,  SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_RECORD_DB);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    /**
     * 添加数据
     * @param bean 数据
     */
    public void addRecord(RecordBean bean){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put("uuid",bean.getUuid());
        values.put("type",bean.getType());
        values.put("category",bean.getCategory());
        values.put("remark",bean.getRemark());
        values.put("amount",bean.getAmount());
        values.put("date",bean.getDate());
        values.put("time",bean.getTimeStamp());
        db.insert(DB_NAME,null,values);
        values.clear();
    }

    /**
     * 删除数据
     * @param uuid
     */
    public void removeRecord(String uuid){
        SQLiteDatabase db=this.getWritableDatabase();
        db.delete(DB_NAME,"uuid=?",new String[]{uuid});
    }

    /**
     * 编辑数据
     * @param uuid
     * @param record
     */
    public void editRecord(String uuid,RecordBean record){
        removeRecord(uuid);
        record.setUuid(uuid);
        addRecord(record);
    }

    /**读取数据
     * @param datestr
     * @return LinkedList<RecordBean>
     */
    public LinkedList<RecordBean>  readrRecord(String datestr){

        LinkedList<RecordBean>records=new LinkedList<>();
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cursor=db.rawQuery("select DISTINCT * from Record where date=?order by time asc",new String[]{datestr});
        if(cursor.moveToFirst()){
            do{
                String uuid=cursor.getString(cursor.getColumnIndex("uuid"));
                int type=cursor.getInt(cursor.getColumnIndex("type"));
                String category=cursor.getString(cursor.getColumnIndex("category"));
                String remark=cursor.getString(cursor.getColumnIndex("remark" ));
                double amount=cursor.getDouble(cursor.getColumnIndex("amount"));
                String date=cursor.getString(cursor.getColumnIndex("date"));
                long timeStamp=cursor.getLong(cursor.getColumnIndex("time"));

                RecordBean record=new RecordBean();
                record.setUuid(uuid);
                record.setType(type);
                record.setCategory(category);
                record.setRemark(remark);
                record.setAmount(amount);
                record.setDate(date);
                record.setTimeStamp(timeStamp);
                records.add(record);
            }while (cursor.moveToNext());
        }
        cursor.close();
        return records;
    }

    /**
     * 获取有用日期
     * @return
     */
    public LinkedList<String> getAvaliableDate(){
        LinkedList<String> dates=new LinkedList<>();
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cursor=db.rawQuery("select DISTINCT * from Record order by date asc",new String[]{});
        if(cursor.moveToFirst()){
            do{
                String date=cursor.getString(cursor.getColumnIndex("date"));
                if(!dates.contains(date)){
                    dates.add(date);
                }
            }while (cursor.moveToNext());
        }
        cursor.close();
        return dates;
    }

    /**
     * @param month
     * @return
     */
    /*public double Search(int month){
        double sum=0;
        String sql="";
        SQLiteDatabase db=getWritableDatabase();
        switch (month){
            case 1:
                sql="select * from Record where date BETWEEN '2019-01-01' AND '2019-01-31'";
                break;
            case 2:
                sql="select * from Record where date BETWEEN '2019-02-01' AND '2019-02-28'";
                break;
            case 3:
                sql="select * from Record where date BETWEEN '2019-03-01' AND '2019-03-31'";
                break;
            case 4:
                sql="select * from Record where date BETWEEN '2019-04-01' AND '2019-04-30'";
                break;
            case 5:
                sql="select * from Record where date BETWEEN '2019-05-01' AND '2019-05-31'";
                break;
            case 6:
                sql="select * from Record where date BETWEEN '2019-06-01' AND '2019-06-30'";
                break;
            case 7:
                sql="select * from Record where date BETWEEN '2019-07-01' AND '2019-07-31'";
                break;
            case 8:
                sql="select * from Record where date BETWEEN '2019-08-01' AND '2019-08-31'";
                break;
            case 9:
                sql="select * from Record where date BETWEEN '2019-09-01' AND '2019-09-30'";
                break;
            case 10:
                sql="select * from Record where date BETWEEN '2019-10-01' AND '2019-10-31'";
                break;
            case 11:
                sql="select * from Record where date BETWEEN '2019-11-01' AND '2019-11-30'";
                break;
            case 12:
                sql="select * from Record where date BETWEEN '2019-12-01' AND '2019-12-31'";
                break;

        }

        Cursor cursor=db.rawQuery(sql,new String[]{});
        if(cursor.moveToFirst()){
            do{
                sum+=cursor.getDouble(cursor.getColumnIndex("amount"));
            }while (cursor.moveToNext());
        }
        else {
            sum=0.00;
        }
        return sum;
    }*/
}
