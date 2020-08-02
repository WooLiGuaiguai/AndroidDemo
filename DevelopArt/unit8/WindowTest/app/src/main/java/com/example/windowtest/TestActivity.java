package com.example.windowtest;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

public class TestActivity extends AppCompatActivity implements View.OnClickListener, View.OnTouchListener {

    private Button button1;
    private Button floatbutton;
    private WindowManager manager;//创建Windowmanager
    private WindowManager.LayoutParams layoutParams;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        button1=(Button)findViewById(R.id.button1);
        manager=(WindowManager)getSystemService(Context.WINDOW_SERVICE);
        button1.setOnClickListener(this);
    }
    @SuppressLint("ClickableViewAccessibility")
    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.button1:
                floatbutton=new Button(this);
                floatbutton.setText("浮动窗按钮");
                layoutParams=new WindowManager.LayoutParams(WindowManager.LayoutParams.WRAP_CONTENT,
                        WindowManager.LayoutParams.WRAP_CONTENT,0,0, PixelFormat.TRANSPARENT);
                layoutParams.flags= WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL
                        | WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE
                        | WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED;
                layoutParams.type = WindowManager.LayoutParams.TYPE_SYSTEM_ERROR;
                layoutParams.gravity= Gravity.LEFT|Gravity.TOP;
                layoutParams.x=100;
                layoutParams.y=300;
                floatbutton.setOnTouchListener(this);
                if (Build.VERSION.SDK_INT >= 23) {
                    if (!Settings.canDrawOverlays(this)) {
                        Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivityForResult(intent, 1);
                    } else {

                        manager.addView(floatbutton,layoutParams);
                    }
                }
        }
    }
    public boolean onTouch(View view, MotionEvent event){
        int rawX = (int) event.getRawX();
        int rawY = (int) event.getRawY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_MOVE:
                layoutParams.x = rawX;
                layoutParams.y = rawY;
                manager.updateViewLayout(floatbutton, layoutParams);
                break;
            default:
                break;
        }
        return false;
    }


}
