package com.example.demo3;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
/**
 * 创建一个用户界面
 * 启动停止RandomService服务
 * 将服务产生的随机数更新到用户界面上
 * */
public class MainActivity extends AppCompatActivity {

    private static Handler handler = new Handler(); //创建一个处理器用于在主线程中更新UI
    private static TextView labelView = null;  //用于显示随机数的文本视图，默认为空
    private static double randomDouble;  //存储随机数的变量

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);  //设置主界面的布局，layout中的activity_main
        labelView =(TextView) findViewById(R.id.num_random2);     //文本视图
        Button start =(Button) findViewById(R.id.start_random2);  //开始按钮
        Button stop =(Button) findViewById(R.id.stop_random2);    //停止按钮
        final Intent serviceIntent = new Intent(MainActivity.this,RandomService.class);  //创建用于启动服务的Intent对象
        start.setOnClickListener(new Button.OnClickListener(){
            public void onClick(View view){
                startService(serviceIntent);
            }   //点击开始按钮时，启动RandomService服务
        });
        stop.setOnClickListener(new Button.OnClickListener(){
            public void onClick(View view){
                stopService(serviceIntent);
            }
        });     //点击停止按钮时，关闭RandomService服务
    }

    /**
     * UpdateGUI是共有的界面更新函数，后台线程通过调用该函数，将后台产生的数据refreshDouble传递到这个函数内部
     * POST -- 将Runnable对象传递给界面线程的消息队列
     * @param refreshDouble
     */

    public static void UpdateGUI(double refreshDouble){
        randomDouble = refreshDouble;  //将传入的值存储在randomDouble变量中
        handler.post(RefreshLable);    //通过处理器在主线程上发布一个Runnable更新UI
    }

    /**
     * Runnable对象中需要重载的run()函数，界面更新
     * 设置labelView的文本为randomDouble的字符串
     */
    private static Runnable RefreshLable = new Runnable() {
        @Override
        public void run() {
            labelView.setText(String.valueOf(randomDouble)); //更新界面显示的文本字符串randomDouble
        }
    };
}
