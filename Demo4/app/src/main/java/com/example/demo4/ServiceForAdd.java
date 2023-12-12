package com.example.demo4;

/**
 在 ServiceForAdd 类内部，有一个内部类 mybinder，它扩展了 Binder。这个内部类允许获取对服务的引用。
    在 onBind 方法中，当活动绑定到服务时，返回 mybinder 类的一个实例。
    ServiceForAdd 类中的 add 方法执行两个整数的加法操作并返回结果。
*/

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

public class ServiceForAdd extends Service {

    public ServiceForAdd() {
        //构造函数
    }

    public class mybinder extends Binder { // 内部类，用于获取对服务的引用
        public  ServiceForAdd getservice(){   // 返回当前的service实例
            return ServiceForAdd.this;

        }
    }

    @Override
    public IBinder onBind(Intent intent) {       //当活动绑定到服务时，返回mybinder类的一个实例
        return  new mybinder();
    }

    public int add(int a,int b){                 //执行两个整数的加法操作并返回结果
        return a+b;
    }
    @Override
    public void onDestroy() {
        super.onDestroy();          //销毁服务
    }
}

