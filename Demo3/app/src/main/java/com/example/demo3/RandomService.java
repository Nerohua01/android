package com.example.demo3;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import androidx.annotation.Nullable;

public class RandomService extends Service {
    private Thread workThread;  // 创建一个线程对象用于执行后台工作

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;    //不支持绑定，返回null
    }

    @Override
    public void onCreate() {
        super.onCreate();
        System.out.println("onCreate");   //打印onCreate表示服务已经创建
        //线程组，需要执行的Runnable对象，线程的名称
        //创建后台工作线程，将null用于线程组，backgroundWork用于执行的Runnable对象，线程名称为“WorkThread”
        workThread = new Thread(null,backgroundWork,"WorkThread");
    }

    @Override
    public void onStart(Intent intent, int startId) {
        super.onStart(intent, startId);
        if(!workThread.isAlive()){
            System.out.println("onStart");  //打印onStart表示服务已启动
            workThread.start();             //启动后台工作线程
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        workThread.interrupt();             // 中断后台工作线程
    }

    private Runnable backgroundWork = new Runnable() {   //创建一个Runnable对象用于后台工作
        @Override
        public void run() {
            try {
                while(!Thread.interrupted()){            //在线程中未被中断时执行下面的代码
                    //double random = Math.random();     //生成一个随机数的浮点值[0,1)
                    int min = 1;
                    int max = 100;
                    //生成一个随机整数
                    int random = min + (int) (Math.random() * ((max - min) + 1));

                    System.out.println(random);  //输出这个随机整数到控制台
                    MainActivity.UpdateGUI(random);        //调用MainActivity类中的UpdateGUI方法更新用户界面
                    Thread.sleep(2000);              //使线程休眠2秒       2000ms==2s
                }
            } catch (InterruptedException e) {
                e.printStackTrace();     //捕获处理异常，将异常信息打印到控制台
            }
        }
    };
}

