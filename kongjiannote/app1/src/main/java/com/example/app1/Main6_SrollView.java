package com.example.app1;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ScrollView;
import android.widget.TextView;

public class Main6_SrollView extends AppCompatActivity {
    private ScrollView scrollView;
    private TextView textView;
    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main6__sroll_view);
        scrollView=(ScrollView)findViewById(R.id.scroll);
        textView=(TextView)findViewById(R.id.text);
        textView.setText(R.string.文本内容);
        scrollView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch (motionEvent.getAction()){
                    case MotionEvent.ACTION_MOVE://界面滑动
                        if(scrollView.getScrollY()<=0){
                            Log.d("Main6_SrollView","在顶部");
                        }
                        if(scrollView.getChildAt(0).getMeasuredHeight()<=
                        scrollView.getScrollY()+scrollView.getHeight()){
                            Log.d("Main6_SrollView","在底部");
                        }
                        //textView.append(getResources().getString(R.string.文本内容));
                        break;
                }
                return false;
            }
        });
    }
}
