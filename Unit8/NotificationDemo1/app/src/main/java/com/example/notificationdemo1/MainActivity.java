package com.example.notificationdemo1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.io.File;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(this);
    }
    @Override
    public void onClick(View view) {
        Intent intent=new Intent(MainActivity.this,Main2Activity.class);
        //设置通知的点击事件
        PendingIntent pintent=PendingIntent.getActivity(MainActivity.this,0,intent,0);
        //1.确定获取系统的何种服务
        NotificationManager manager=(NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
        //2.创建Notification对象
        Notification notification=new NotificationCompat.Builder(this)
                .setContentTitle("这是一个通知")
                .setContentText("只有小图标标题 内容")
                .setSmallIcon(R.mipmap.ic_launcher)
                //.setWhen(System.currentTimeMillis())  通知被创建的时间
                //.setLargeIcon(R.drawable.big_image)   设置大背景
                .setContentIntent(pintent)//增加Intent对象
                .setAutoCancel(true)//点击通知之后通知栏消失该通知 也可以manager.cancel(通知的id);
                .setSound(Uri.fromFile(new File("/system/media/audio/ringtones/Luna.ogg")))//设置音效
                //控制手机振动需要声明权限<uses-permission android:name="android.permission.VIBRATE" />
                .setVibrate(new long[]{0,1000,1000,1000})//设置手机静止、震动、再静止、在震动
                //setLights() 方法接收3个参数， 第一个参数用于指定LED灯的颜色， 第二个参数用于
                //指定LED灯亮起的时长， 以毫秒为单位， 第三个参数用于指定LED灯暗去的时长， ms为单位。
                .setLights(Color.GREEN, 1000, 1000)
                //.setDefaults(NotificationCompat.DEFAULT_ALL)设置默认效果 一旦设置所有自定义均无效
                .build();
        //3.显示通知管理器显示该通知  notify接收两个参数 前一个参数指定id 后一个传递notification对象
        manager.notify(1,notification);
    }

}
