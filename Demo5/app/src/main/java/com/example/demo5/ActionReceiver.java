package com.example.demo5;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;

//定义广播接收器的主活动类
public class ActionReceiver extends AppCompatActivity {
    //定义广播的动作（Action）
    protected static final String ACTION = "com.example.demo5";

    //创建广播接收器实例
    private MyReceiver myReceiver;

    //在活动创建时调用的方法
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actionlayout);  //设置布局UI
    }

    //发送广播的方法，通过按钮点击触发
    public void sendAct(View view){
        Intent intent=new Intent();       //实例化Intent
        intent.setAction(ACTION);      //设置Intent的action属性
        intent.putExtra("info","动态方法"); //添加额外的信息
        sendBroadcast(intent); //发送广播
    }

    //注册广播接收器的方法，通过按钮点击触发
    public void register(View view){
        myReceiver = new MyReceiver();   //创建广播接收器实例
        IntentFilter filter = new IntentFilter();  //创建IntentFilter实例
        filter.addAction(ACTION);             //添加要过滤的动作
        registerReceiver(myReceiver, filter);    //注册广播接收器
    }
    public void unregister(View view){
        unregisterReceiver(myReceiver);   //取消注册广播接收器
    }

}