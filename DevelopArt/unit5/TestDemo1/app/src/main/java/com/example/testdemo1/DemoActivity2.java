package com.example.testdemo1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

public class DemoActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo2);
        Toast.makeText(this, getIntent().getStringExtra("sid"), Toast.LENGTH_SHORT).show();
    }
}
