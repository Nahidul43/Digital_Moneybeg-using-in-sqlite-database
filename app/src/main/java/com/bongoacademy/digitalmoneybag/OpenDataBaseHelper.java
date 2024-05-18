package com.bongoacademy.digitalmoneybag;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class OpenDataBaseHelper extends SQLiteOpenHelper {
    public OpenDataBaseHelper( Context context) {
        super(context, "digital_Moneybag", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table expense(id INTEGER PRIMARY KEY AUTOINCREMENT,amount DOUBLE,reason TEXT,time DOUBLE)");
        sqLiteDatabase.execSQL("create table income(id INTEGER PRIMARY KEY AUTOINCREMENT,amount DOUBLE,reason TEXT,time DOUBLE)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("drop table if exists expense");
        sqLiteDatabase.execSQL("drop table if exists income");

    }


    public void addExpense(double amount,String reason){
        SQLiteDatabase database=this.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put("amount",amount);
        values.put("reason",reason);
        values.put("time",System.currentTimeMillis());
        database.insert("expense",null,values);
    }





    public void addincome(double amount,String reason){
        SQLiteDatabase database=this.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put("amount",amount);
        values.put("reason",reason);
        values.put("time",System.currentTimeMillis());
        database.insert("income",null,values);
    }






    public double CalculateTotalExpense(){
        double totalamount=0;
        SQLiteDatabase database=this.getReadableDatabase();
        Cursor cursor=database.rawQuery("select * from expense",null);
       while(cursor.moveToNext()){
           if(cursor!=null&&cursor.getCount()>0){
               double amount=cursor.getDouble(1);
               totalamount=totalamount+amount;
           }
       }
        return totalamount;
    }



    public double CalculateTotalIncome(){
        double total=0;
        SQLiteDatabase database=this.getReadableDatabase();
        Cursor cursor=database.rawQuery("select * from income",null);
        while(cursor.moveToNext()){
            if(cursor!=null&&cursor.getCount()>0){
                double amount=cursor.getDouble(1);
                total=total+amount;
            }
        }
        return total;
    }


    public Cursor GetAllExpense(){
        SQLiteDatabase database=this.getReadableDatabase();
        Cursor cursor=database.rawQuery("select * from expense",null);
        return cursor;
    }

    public Cursor GetAllincome(){
        SQLiteDatabase database=this.getReadableDatabase();
        Cursor cursor=database.rawQuery("select * from income",null);
        return cursor;
    }


    public void DeleteExpense(String id){
        SQLiteDatabase database=this.getWritableDatabase();
        database.execSQL("delete from expense where id like "+id);

    }


    public void Deleteincome(String id){
        SQLiteDatabase database=this.getWritableDatabase();
        database.execSQL("delete from income where id like "+id);

    }

    public void updateExpense(int id, double amount, String reason) {
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("amount", amount);
        values.put("reason", reason);
        database.update("expense",values,"id = ?",new String[]{String.valueOf(id)});

    }

    public void updateIncome(int id, double amount, String reason) {
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("amount", amount);
        values.put("reason", reason);
        database.update("income",values,"id = ?",new String[]{String.valueOf(id)});

    }




}




