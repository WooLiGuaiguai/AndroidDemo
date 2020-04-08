package com.example.intentdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class Main2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        System.out.println("name is"+getIntent().getStringExtra("name")
                +"age is"+getIntent().getStringExtra("age")
                +"sex is"+getIntent().getStringExtra("sex"));
    }
}
