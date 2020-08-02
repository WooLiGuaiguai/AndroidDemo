package com.example.testdemo1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

public class DemoActivity1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo1);
        Toast.makeText(DemoActivity1.this,"普通通知栏的点击事件",Toast.LENGTH_SHORT).show();
    }
}
