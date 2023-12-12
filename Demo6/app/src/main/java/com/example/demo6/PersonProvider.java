package com.example.demo6;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class PersonProvider extends ContentProvider {

    //创建uri匹配器
    private static UriMatcher mUriMatcher = new UriMatcher(-1);
    //匹配成功
    private static final int SUCCESS = 1;

    private PersonDBOpenHelper helper;

    //匹配uri路径
    static {
        mUriMatcher.addURI("com.example.demo6.PersonProvider","info",SUCCESS);
    }

    //调用方法
    @Override
    public boolean onCreate() {
        //创建数据库实例
        helper = new PersonDBOpenHelper(getContext());
        return false;
    }

    //插入数据
    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        int code = mUriMatcher.match(uri);
        if (code == SUCCESS) {
            //读取数据库
            SQLiteDatabase db = helper.getReadableDatabase();
            //插入数据返回插入行的id-->rowId
            long rowId = db.insert("info",null,values);
            if (rowId>0) {
                //构造新插入数据的Uri
                Uri insertedUri = ContentUris.withAppendedId(uri,rowId);
                //通知数据已改变
                getContext().getContentResolver().notifyChange(insertedUri,null);
                return insertedUri;
            }
            //关闭数据库
            db.close();
            //返回插入数据的uri
            return uri;
        }else {
            try {
                //抛出异常，表示插入失败，路径不正确
                throw new IllegalAccessException("插入失败，路径不正确！");
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    //查询数据的方法
    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] strings, @Nullable String s, @Nullable String[] strings1, @Nullable String s1) {
        int code = mUriMatcher.match(uri);
        if (code == SUCCESS) {
            //读取数据库
            SQLiteDatabase db = helper.getReadableDatabase();
            //查询数据并返回Cursor-->info
            return db.query("info",strings,s,strings1,null,null,s1);
        }else {
            try {
                throw new IllegalAccessException("查询失败，路径不正确！");
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues contentValues, @Nullable String s, @Nullable String[] strings) {

        int code = mUriMatcher.match(uri);
        if (code == SUCCESS) {
            //获取数据库
            SQLiteDatabase db = helper.getReadableDatabase();
            //更新'info'表中的数据并获取更新行数
            int count = db.update("info",contentValues,s,strings);
            if (count>0) {
                // 通知数据已改变
                getContext().getContentResolver().notifyChange(uri,null);
            }
            //关闭数据库
            db.close();
            //返回更新行数
            return count;
        }else {
            try {
                throw new IllegalAccessException("更新失败，路径不正确！");
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return 0;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String s, @Nullable String[] strings) {
        int code = mUriMatcher.match(uri);
        if (code == SUCCESS) {
            //获取数据库
            SQLiteDatabase db = helper.getReadableDatabase();
            //从'info'表中删除数据并获取删除行数
            int count = db.delete("info",s,strings);
            if (count>0) {
                //通知数据已改变
                getContext().getContentResolver().notifyChange(uri,null);
            }
            //关闭数据库
            db.close();
            //返回删除行数
            return count;
        }else {
            try {
                throw new IllegalAccessException("删除失败，路径不正确！");
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return code;
    }
}

