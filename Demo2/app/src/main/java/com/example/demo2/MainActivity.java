package com.example.demo2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.demo2.R;

public class MainActivity extends AppCompatActivity {
    private EditText name;
    private EditText number;

    private RadioButton female;
    private  RadioButton male;
    private EditText email;
    private Button btn;
    private String sex;
    private TextView textviewlast;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);   //布局UI
        name = findViewById(R.id.name);
        number = findViewById(R.id.number);
        female = findViewById(R.id.female);
        male = findViewById(R.id.male);

        textviewlast = findViewById(R.id.textviewlast);

        email = findViewById(R.id.email);
        RadioGroup radioGroup = findViewById(R.id.radioGroup);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton rb =  findViewById(checkedId);
                sex = rb.getText().toString();

                textviewlast.setText("当前选中性别:"+rb.getText());

            }
        });

        btn = findViewById(R.id.register);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Student stu = new Student(
                        name.getText().toString(),
                        number.getText().toString(),
                        sex, email.getText().toString());
                Bundle data = new Bundle();
                data.putSerializable("student",stu);
                Intent intent = new Intent(MainActivity.this,ResultActivity.class);
                intent.putExtras(data);

                //启动Activity
                startActivity(intent);
            }
        });
    }
}

