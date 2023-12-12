package com.example.demo7;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class DetailFragment extends Fragment {  //片段
    //创建并返回与片段关联的视图层次结构的方法
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //为片段膨胀布局
        View view = inflater.inflate(R.layout.frament_detail,container,false);
        //返回被膨胀的视图供片段显示
        return  view;
    }
}

