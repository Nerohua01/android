package com.example.demo6;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    //处理按钮点击事件的方法
    public void One(View view) {
        Intent intent = new Intent(MainActivity.this,SQLActivity.class);  //创建意图跳转到SQLActivity
        startActivity(intent);  //启动SQLActivity
    }
}
