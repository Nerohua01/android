package com.example.demo4;

/**
在 onStart 方法中，创建一个 Intent 来启动 ServiceForAdd 服务并通过 bindService 绑定到它。这个方法在活动启动时被调用。
在 onCreate 方法中，将活动的内容视图设置为在 "activity_main.xml" 中定义的布局。还初始化了各种UI元素，
    如 EditText、Button 和 TextView，并为 result 按钮设置了一个 OnClickListener。
在按钮的 onClick 方法中，从 add1 和 add2 EditText 字段中获取值，调用 ServiceForAdd 服务的 add 方法执行加法操作，
    并将结果显示在 textView 中。
 MainActivity 中的 onServiceConnected 和 onServiceDisconnected 方法实现了 ServiceConnection 接口。
 onServiceConnected 在服务连接时被调用，它使用 mybinder 类获取对 ServiceForAdd 服务的引用。
 onServiceDisconnected 在服务断开连接时被调用。
*/

import androidx.appcompat.app.AppCompatActivity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    EditText add1; //加数1
    EditText add2;  //加数2
    Button result;  //按钮：求和
    TextView textView;    //显示结果
    ServiceForAdd ServiceForAdd;  //引用ServiceForAdd服务
    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);   //设置布局为activity_main.xml

        add1 = findViewById(R.id.add1);          //初始化add1    EditView
        add2 = findViewById(R.id.add2);          //初始化add2    EditView
        result = findViewById(R.id.result);      //初始化result  -->  按钮求和
        textView = findViewById(R.id.resultshow); //初始化textView

        //创建一个Intent用来启动ServiceForAdd服务
        Intent intent = new Intent(MainActivity.this,ServiceForAdd.class);

        //使用bindService将服务绑定到活动
        bindService(intent,serviceConnection,BIND_AUTO_CREATE);

        result.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ServiceForAdd != null) {  // 检查 ServiceForAdd 是否为非空
                    if (add1.getText().toString().isEmpty() || add2.getText().toString().isEmpty()) {   //如果add1或者add2为空
                        // 处理输入为空的情况
                        textView.setText("请输入有效数字");
                        return;
                    }

                    int a = Integer.parseInt(add1.getText().toString());    //获取加数1的整数值给a
                    int b = Integer.parseInt(add2.getText().toString());    //获取加数2的整数值给b
                    int add = ServiceForAdd.add(a, b);                      //调用ServiceForAdd服务中的add方法执行加法操作
                    textView.setText("计算结果为：" + add + " ");             //显示计算结果
                } else {
                    // 如果 ServiceForAdd 为空，显示错误消息
                    textView.setText("服务未成功绑定");
                }
            }
        });
    }

    private ServiceConnection  serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder iBinder) {
                              // 当服务连接时触发的回调方法
                              // 通过 IBinder 获取服务的实例

            ServiceForAdd = ((ServiceForAdd.mybinder)iBinder).getservice();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            ServiceForAdd = null;
        }
    };


}
