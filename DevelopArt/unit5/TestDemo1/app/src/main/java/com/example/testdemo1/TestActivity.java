package com.example.testdemo1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RemoteViews;

public class TestActivity extends AppCompatActivity implements View.OnClickListener {
    private static int sId = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        Button button1 = (Button) findViewById(R.id.button1);
        button1.setOnClickListener(this);
        Button button2 = (Button) findViewById(R.id.button2);
        button2.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.button1:
                sId++;
                NotificationManager manager=(NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
                Intent intent=new Intent(this,DemoActivity1.class);
                PendingIntent pendingIntent=PendingIntent.getActivity(this,0,intent,PendingIntent.FLAG_UPDATE_CURRENT);
                Notification notification=new NotificationCompat.Builder(this)
                        .setContentTitle("通知标题栏")
                        .setContentText("通知内容栏")
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setContentIntent(pendingIntent)
                        .build();
                manager.notify(1,notification);
                break;
            case R.id.button2:
                sId++;
                NotificationManager manager2=(NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
                Intent intent2=new Intent(this,DemoActivity1.class);
                intent2.putExtra("sid", "" + sId);
                PendingIntent pendingIntent2=PendingIntent.getActivity(this,0,intent2,PendingIntent.FLAG_UPDATE_CURRENT);
                Notification notification2=new NotificationCompat.Builder(this)
                        .setContentTitle("通知标题栏")
                        .setContentText("通知内容栏")
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .build();
                //创建一个RemoteViews对象
                RemoteViews remoteViews=new RemoteViews(getPackageName(),R.layout.layout_notification);
                //通过RemoteViews提供的方法来更新View
                //需要更新的TestView的id以及要设置的文本
                remoteViews.setTextViewText(R.id.msg,"第五单元:  "+sId);
                //更新ImageView的id以及要设置的图片
                remoteViews.setImageViewResource(R.id.icon, R.drawable.icon1);
                PendingIntent openActivity2Intent=PendingIntent.getActivity(this,
                        0,new Intent(this,DemoActivity2.class),PendingIntent.FLAG_UPDATE_CURRENT);
                remoteViews.setOnClickPendingIntent(R.id.open_activity2,openActivity2Intent);
                notification2.contentView=remoteViews;
                notification2.contentIntent=pendingIntent2;
                manager2.notify(2,notification2);
                break;

            default:
                break;
        }
    }
}
