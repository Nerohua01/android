package com.example.demo5;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

//有序广播的活动类
public class OrderReceiver extends Activity {

    //在活动创建时调用的方法
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.orderlayout);  //设置布局UI
    }

    //发送有序广播的方法，通过点击按钮触发
    public void sendOrd(View view) {
        Intent intent = new Intent();  //创建Intent
        intent.setAction("demo5.order");  //设置Intent的action属性为“demo5.order”
        intent.putExtra("info","有序广播方法");  //添加额外的信息封装
        intent.setPackage(getPackageName());   //设置Intent的包名
        Log.d("demo5","----------有序广播---------------");  //在日志中打印信息，表示发送有序广播
        sendOrderedBroadcast(intent,null);   //发送有序广播
    }



}
