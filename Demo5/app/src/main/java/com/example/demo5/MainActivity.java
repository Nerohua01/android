package com.example.demo5;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;


//主活动类
public class MainActivity extends AppCompatActivity {

    //在活动创建时调用的方法
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);   //设置布局UI
    }

    //启动静态注册的广播接收器
    public void staticSys(View view){
        Log.d("demo5","button------------------------");  //在日志中打印信息
        Intent intent = new Intent(MainActivity.this, StaticReceiver.class);   //创建Intent
        intent.putExtra("demo5","demo5555");  //添加额外的信息  //使用putExtra封装数据
        startActivity(intent);  //启动活动
    }

    //启动动态注册的广播接收器
    public void actionSys(View view){
        Intent intent = new Intent(MainActivity.this, ActionReceiver.class);  //创建Intent
        startActivity(intent);  //启动活动
    }

    //启动有序广播接收器
    public void orderSys(View view){
        Intent intent = new Intent(MainActivity.this, OrderReceiver.class); //创建Intent
        startActivity(intent);  //启动活动
    }
}