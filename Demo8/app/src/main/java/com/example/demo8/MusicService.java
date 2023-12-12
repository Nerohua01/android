package com.example.demo8;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Message;

import java.sql.Time;
import java.util.Timer;
import java.util.TimerTask;


public class MusicService extends Service {
    private MediaPlayer player;  //播放组件
    private Timer timer;    //计时器

    public MusicService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        player = new MediaPlayer();  //实例化
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        return  new MusicController();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if(player==null) return;
        if(player.isLooping()){
            player.stop();
        }
        player.release();  //释放资源
        player = null;
    }



    //添加计时器 用于进度条
    public void addTime(){
        if(timer == null){
            timer = new Timer();
            TimerTask taksk = new TimerTask() {
                @Override
                public void run() {
                    if(player==null){
                        return;
                    }
                    //分别获取总长度和播放进度
                    int duration = player.getDuration();
                    int currentDuration = player.getCurrentPosition();

                    //创建Message对象
                    Message msg = MainActivity.handler.obtainMessage();
                    //将音乐的总时长，播放时长封装到消息对象中
                    Bundle bundle = new Bundle();
                    bundle.putInt("duration",duration);
                    bundle.putInt("currentDuration",currentDuration);
                    msg.setData(bundle);
                    //将消息添加到主线程
                    MainActivity.handler.sendMessage(msg);
                }
            };
            //开始计时任务后 5毫秒 执行第一次任务 以后500毫秒执行一次任务
            timer.schedule(taksk,5,500);
        }
    }

    //自定义一个Binder类
    class MusicController extends Binder{
        public void play(){
            try{
                player.reset(); //重置音乐播放器
                //加载多媒体文件
                player = MediaPlayer.create(getApplicationContext(),R.raw.zbdx);
                player.start();
                addTime(); //添加计时器
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        public void pausePlay(){
            player.pause();  //暂停音乐播放
        }
        public void continePlay(){
            player.start();  //暂停音乐播放
        }
        public void seekTo(int progress){
            player.seekTo(progress);  //设置音乐的播放位置
        }
    }

}