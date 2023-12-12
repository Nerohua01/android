package com.example.demo8;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.animation.ObjectAnimator;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {//继承activity,实现View.OnClickListener接口
    private ImageView iv_cover;
    private static SeekBar sb;
    private static TextView tv_progress, tv_total;
    private Button btn_play, btn_pause, btn_continue, btn_exit;
    private ObjectAnimator animator;

    private MusicService.MusicController musicController;//音乐服务控制器 Binder实例，跨进程通信
    private MyserviceConn myserviceConn; //连接实例
    private Intent intent; //全局意图
    private boolean isUnbind = false;  //记录是否被解绑

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //初始化视图
        initView();

    }
    //初始化视图的方法
    private void initView(){
        iv_cover = findViewById(R.id.iv_cover);
        animator = ObjectAnimator.ofFloat(iv_cover,"rotation",0f,360.0f);
        animator.setDuration(10000); //旋转一周的时长
        animator.setInterpolator(new LinearInterpolator());  //匀速转动
        animator.setRepeatCount(-1);  //-1表示无限循环播放

        sb = findViewById(R.id.sb);
        sb.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            //滑动条变化的处理
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                if( i == seekBar.getMax()){  //滑动最大值，结束动画
                    animator.pause();
                }
            }
            //开始滑动时的处理
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }
            //停止滑动的处理
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                int progress = seekBar.getProgress();  //获取进度值
                //调用服务的seekTo方法改进音乐进度
                musicController.seekTo(progress);
            }
        });

        tv_progress = findViewById(R.id.tv_progress);
        tv_total = findViewById(R.id.tv_total);

        btn_play = findViewById(R.id.play);
        btn_pause = findViewById(R.id.pause);
        btn_continue = findViewById(R.id.continue2);
        btn_exit = findViewById(R.id.stop);

        intent = new Intent(MainActivity.this,MusicService.class);
        myserviceConn = new MyserviceConn();
        bindService(intent,myserviceConn,BIND_AUTO_CREATE);

        btn_play.setOnClickListener(this);
        btn_pause.setOnClickListener(this);
        btn_continue.setOnClickListener(this);
        btn_exit.setOnClickListener(this);
    }

    //自定义解绑方法
    private void myUnbind(boolean isUnbind){
        if(!isUnbind){
            isUnbind = true;
            musicController.pausePlay();  //暂停播放
            unbindService(myserviceConn); //解绑
            stopService(intent);
        }
    }

    //用于实现连接服务的自定义类
    class MyserviceConn implements ServiceConnection{

        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            musicController = (MusicService.MusicController)iBinder;
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {

        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.play:
                musicController.play();
                animator.start();
                break;
            case R.id.pause:
                musicController.pausePlay();
                animator.pause();
                break;
            case R.id.continue2:
                musicController.continePlay();
                animator.start();
                break;
            case R.id.stop:
                myUnbind(isUnbind);
                finish();

                break;
        }
    }

    //创建消息处理的对象、
    public static Handler handler = new Handler(){
        //处理子线程传来的消息
        @Override
        public void handleMessage(@NonNull Message msg) {
            Bundle bundle = msg.getData(); //获取信息
            int duration = bundle.getInt("duration");
            int currentDuration = bundle.getInt("currentDuration");

            sb.setProgress(currentDuration);
            sb.setMax(duration);


            //显示总时长
            int minute = duration/1000/60;
            int second = duration/1000%60;
            String strMinute = "";
            String strSecond = "";
            if(minute<10){
                strMinute = "0"+minute;
            }else{
                strMinute = minute + "";
            }
            if(second<10){
                strSecond = "0"+second;
            }else{
                strSecond = second + "";
            }
            //显示总时长结束
            tv_total.setText(strMinute+":"+strSecond);


            //显示播放时长开始
            minute = currentDuration/1000/60;
            second = currentDuration/1000%60;
            if(minute<10){
                strMinute = "0"+minute;
            }else{
                strMinute = minute + "";
            }
            if(second<10){
                strSecond = "0"+second;
            }else{
                strSecond = second + "";
            }
            tv_progress.setText(strMinute+":"+strSecond);
            //播放时长结束

        }
    };

}