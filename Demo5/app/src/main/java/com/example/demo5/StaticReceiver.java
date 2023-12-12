package com.example.demo5;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.util.StringTokenizer;

//静态注册广播的活动类
public class StaticReceiver extends Activity {

    //在活动创建时调用的方法
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.staticlayout);  //设置布局UI

        Intent intent = getIntent();  //获取启动该活动的Intent
        String str = intent.getStringExtra("demo5");   //从Intent中获取额外的信息
        Log.d("demo5","----------demo5-------------");  //在日志中打印信息，表示活动已创建

    }


    //发送静态注册广播的方法，通过按钮点击触发
    public void send(View view){
        Intent intent = new Intent();  //创建Intent
        intent.setAction("demo5");   //设置Intent的action属性为“demo5”
        intent.putExtra("info","静态方法");  //添加额外的信息
        intent.setComponent(new ComponentName(getPackageName(),"com.example.demo5.MyReceiver"));   //设置Intent的组件名
        Log.d("demo5","------------demo5----------------");  //在日志中打印信息，表示发送静态注册广播
        sendBroadcast(intent);  //发送广播
    }

}
