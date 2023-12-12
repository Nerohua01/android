package com.example.demo5;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.Gravity;
import android.widget.Toast;

//广播接收器的实现类
public class MyReceiver extends BroadcastReceiver {

    //构造函数
    public MyReceiver() {
    }

    //在接收到广播时调用的方法
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d("demo5","-------------进入-------------");  //在日志中打印信息，表示进入了广播接收器的onReceive方法
        Toast t = Toast.makeText(context,"广播方式："+intent.getStringExtra("info"), Toast.LENGTH_SHORT);  //创建Toast消息
        t.setGravity(Gravity.TOP,0,40);  //设置Toast消息显示的位置
        t.show();  //显示Toast消息
        Log.d("demo5","--------------广播展示---------");  //在日志中打印信息，表示广播展示已完成
    }
}
