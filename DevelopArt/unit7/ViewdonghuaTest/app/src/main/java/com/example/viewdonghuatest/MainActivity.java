package com.example.viewdonghuatest;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Animation animation= AnimationUtils.loadAnimation(this,R.anim.animation_test);
        Button button1=(Button)findViewById(R.id.button1);
        AlphaAnimation alphaAnimation=new AlphaAnimation(0,1);
        alphaAnimation.setDuration(3000);
        button1.startAnimation(alphaAnimation);
        /*ImageView imageView=(ImageView)findViewById(R.id.image111);
        AnimationDrawable drawable=(AnimationDrawable)imageView.getBackground();
        drawable.start();*/
        Button button4=(Button)findViewById(R.id.button4);
        AnimatorSet set=(AnimatorSet) AnimatorInflater.loadAnimator(MainActivity.this,R.animator.property_animator);
        set.setTarget(button4);
        set.start();

    }
    public void onButtonClick(View v) {
        if (v.getId() == R.id.button2) {
            Intent intent = new Intent(this, TestActivity.class);
            startActivity(intent);
            overridePendingTransition(R.anim.enter_anim, R.anim.exit_anim);
        } /*else if (v.getId() == R.id.button2) {
            Intent intent = new Intent(this, DemoActivity_1.class);
            startActivity(intent);
        }*/ else if (v.getId() == R.id.button3) {
            Intent intent = new Intent(this, LayoutAnimationDemo.class);
            startActivity(intent);
        }
    }
}
