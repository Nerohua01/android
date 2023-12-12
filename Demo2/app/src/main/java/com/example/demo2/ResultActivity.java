package com.example.demo2;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.example.demo2.R;
public class ResultActivity extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        TextView name = findViewById(R.id.name);
        TextView number = findViewById(R.id.number);
        TextView sex = findViewById(R.id.sex);
        TextView email = findViewById(R.id.email);
        //获取本Activity的Intent
        Intent intent = getIntent();
        //获取传递的数据
        Student stu = (Student)intent.getSerializableExtra("student");
        name.setText("姓名："+stu.getName());
        number.setText("学号："+stu.getNumber());
        sex.setText("性别："+stu.getSex());
        email.setText("邮箱："+stu.getEmail());

        //拿到一个SharedPreference对象
        SharedPreferences sp = getSharedPreferences("studentInfo", MODE_PRIVATE);
        //拿到编辑器
        SharedPreferences.Editor ed = sp.edit();
        //写数据
        ed.putString("name", name.getText().toString());
        ed.putString("number",number.getText().toString());
        ed.putString("sex",sex.getText().toString());
        ed.putString("email",email.getText().toString());
        //提交
        ed.commit();
        //显示
        show();
    }
    public void show(){
        //拿到一个SharedPreference对象
        SharedPreferences sp = getSharedPreferences("studentInfo", MODE_PRIVATE);
        //从SharedPreference里取数据
        String stuName = sp.getString("name","");
        String stuNumber = sp.getString("number","");
        String stuSex = sp.getString("sex","");
        String stuEmail = sp.getString("email","");
        System.out.println("姓名："+stuName);
        System.out.println("学号："+stuNumber);
        System.out.println("性别："+stuSex);
        System.out.println("邮箱："+stuEmail);
    }
}

