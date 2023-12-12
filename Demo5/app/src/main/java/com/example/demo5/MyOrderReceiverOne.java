package com.example.demo5;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

//有序广播接收器的第一个实现类
public class MyOrderReceiverOne extends BroadcastReceiver {

    //在接收到广播时调用的方法
    @Override
    public void onReceive(Context context, Intent intent) {

        System.out.println("1:我收到有序的啦");   //在控制台打印信息，表示接收到了有序广播
    }
}
