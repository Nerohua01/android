package com.example.demo6;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

    //SQLite数据库辅助类，用于创建和管理数据库
public class PersonDBOpenHelper extends SQLiteOpenHelper {
    //构造函数，版本号为1，数据库名称为info.db
    public PersonDBOpenHelper(Context context) {
        super(context,"info.db",null,1);
    }

    //创建数据库表
    @Override
    public void onCreate(SQLiteDatabase db) {
        //创建名为"info"的表，包含_id、name、phone三个字段，其中_id为主键自增，phone字段要求唯一性
        db.execSQL("create table info(_id integer primary key autoincrement,name varchar(50),phone varchar(20),UNIQUE(phone))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //实现方法，在版本更新时进行升级操作，处理数据库结构的修改
    }
}

