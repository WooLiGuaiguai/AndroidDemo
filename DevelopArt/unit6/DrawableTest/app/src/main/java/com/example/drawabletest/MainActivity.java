package com.example.drawabletest;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.graphics.drawable.ClipDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ScaleDrawable;
import android.graphics.drawable.TransitionDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView textView=(TextView)findViewById(R.id.test_size);
        Drawable drawable=textView.getBackground();
        Log.e("MainActivity", "bg:" + drawable + "w:" + drawable.getIntrinsicWidth()
                + " h:" + drawable.getIntrinsicHeight());

    }
    public void onButtonClick(View v) {
    }
    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
            // test transition
            View v = findViewById(R.id.test_transition);
            TransitionDrawable drawable = (TransitionDrawable) v.getBackground();
            drawable.startTransition(9000);

            // test scale
            View testScale = findViewById(R.id.test_scale);
            ScaleDrawable testScaleDrawable = (ScaleDrawable) testScale.getBackground();
            testScaleDrawable.setLevel(10);

            // test clip
            ImageView testClip = (ImageView) findViewById(R.id.test_clip);
            ClipDrawable testClipDrawable = (ClipDrawable) testClip.getDrawable();
            testClipDrawable.setLevel(8000);

            // test custom drawable
            View testCustomDrawable = findViewById(R.id.test_custom_drawable);
            CustomDrawable customDrawable = new CustomDrawable(Color.parseColor("#0ac39e"));
            testCustomDrawable.setBackgroundDrawable(customDrawable);
        }
    }
}
