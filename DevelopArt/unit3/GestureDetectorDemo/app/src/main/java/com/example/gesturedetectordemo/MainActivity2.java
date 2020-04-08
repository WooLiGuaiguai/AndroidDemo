package com.example.gesturedetectordemo;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.nineoldandroids.view.ViewHelper;

public class MainActivity2 extends AppCompatActivity {
    TextView tv;
    int mLastX=0;
    int mLastY=0;
    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Button button=(Button)findViewById(R.id.bt);
        final TextView textView=(TextView) findViewById(R.id.tv);
        button.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                int x=(int) motionEvent.getRawX();
                int y=(int) motionEvent.getRawY();
                switch (motionEvent.getAction()){
                    case MotionEvent.ACTION_DOWN :
                        break;
                    case MotionEvent.ACTION_MOVE :
                        int deltaX=x-mLastX;
                        int deltaY=y-mLastY;
                        int tX=(int) (ViewHelper.getTranslationX(view)+deltaX);
                        int tY=(int) (ViewHelper.getTranslationY(view)+deltaY);
                        ViewHelper.setTranslationX(view, tX);
                        ViewHelper.setTranslationY(view, tY);
                        textView.setText("x: "+x+"   y: "+y);
                        break;
                    default:
                        break;
                }
                return false;
            }
        });
    }
}
