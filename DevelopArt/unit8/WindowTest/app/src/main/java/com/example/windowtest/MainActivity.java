package com.example.windowtest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void onButtonClick(View v){
        switch (v.getId()){
            case R.id.button1:
                Intent intent = new Intent(this, TestActivity.class);
                startActivity(intent);
                break;
            case R.id.button2:
                Intent intent1=new Intent(this,DemoActivity_1.class);
                startActivity(intent1);
                break;
            default:
                break;
        }
    }
}
