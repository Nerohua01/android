package com.example.demo6;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SQLActivity extends Activity {
    private final Uri uri = Uri.parse("content://com.example.demo6.PersonProvider/info");
    private ContentValues values;
    private ContentResolver resolver;
    private EditText et_id;
    private EditText et_name;
    private EditText et_phone;
    private EditText et_queryAll;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sqllayout);

        et_id = (EditText) findViewById(R.id.editText1);
        et_name = (EditText) findViewById(R.id.editText2);
        et_phone = (EditText) findViewById(R.id.editText3);
        et_queryAll = (EditText) findViewById(R.id.eT);
    }

    public void btnAdd(View view) {
        resolver = getContentResolver();
        values = new ContentValues();
        if (et_name.length() != 0 && et_phone.length() != 0){
            values.put("name",et_name.getText().toString());
            values.put("phone",et_phone.getText().toString());
            Uri newUri = resolver.insert(uri,values);
            Toast.makeText(this,"增加成功",Toast.LENGTH_SHORT).show();
            btnQueryAll(view);
        } else {
            et_name.setHint("请在此输入需增加的姓名");
            et_phone.setHint("请在此输入需增加的号码");
        }
    }

    public void btnQuery(View view) {
        resolver = getContentResolver();
        if (et_name.length() != 0){
            Cursor cursor = resolver.query(uri,new String[]{"_id","name","phone"},"name=?",new String[]{et_name.getText().toString()},null);
            if (cursor.getCount() != 0){
                cursor.moveToFirst();
                et_id.setText(cursor.getString(0));
                et_name.setText(cursor.getString(1));
                et_phone.setText(cursor.getString(2));
                cursor.close();
            }
            else {
                Toast.makeText(this,"未查询到结果！",Toast.LENGTH_SHORT).show();
            }
        }
        else {
            et_name.setHint("请在此输入需查询的姓名");
            et_phone.setHint("号码");
        }
    }

    public void btnQueryAll(View view) {
        resolver = getContentResolver();

        List<Map<String,String>> data = new ArrayList<Map<String,String>>();
        Cursor cursor = resolver.query(uri,new String[]{"_id","name","phone"},null,null,null);
        while (cursor.moveToNext()){
            Map<String,String> map = new HashMap<String,String>();
            map.put("_id",cursor.getString(0));
            map.put("name",cursor.getString(1));
            map.put("phone",cursor.getString(2));
            data.add(map);
        }
        cursor.close();
        et_queryAll.setText(new String(data.toString()));
    }

    public void btnUpdate(View view) {
        resolver = getContentResolver();
        values = new ContentValues();
        if (et_name.length() != 0 && et_phone.length() != 0){
            values.put("phone",et_phone.getText().toString());
            int updateCount = resolver.update(uri,values,"name=?",new String[]{et_name.getText().toString()});
            Toast.makeText(this,"成功更新了" + updateCount + "条记录",Toast.LENGTH_SHORT).show();
            btnQueryAll(view);
        } else {
            et_name.setHint("请在此输入需修改的姓名");
            et_phone.setHint("请在此输入修改后的号码");
        }
    }

    public void btnDelete(View view) {
        resolver = getContentResolver();    //检查姓名输入框是否为空
        if (et_name.length() != 0){         //构建删除条件
            int deleteCount = resolver.delete(uri,"name=?",new String[]{et_name.getText().toString()});
            Toast.makeText(this,"成功删除了" + deleteCount + "条记录",Toast.LENGTH_SHORT).show();
            btnQueryAll(view);
        } else {
            et_name.setHint("请在此输入需删除的姓名");
            et_phone.setHint("号码");
        }
    }
}
