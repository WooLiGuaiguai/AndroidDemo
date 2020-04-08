package com.example.servicetest;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

import androidx.core.app.NotificationCompat;

public class MyService extends Service {
    public MyService() {
    }
    //onCreate() 方法会在服务创建的时候调用
    @Override
    public void onCreate() {
        super.onCreate();
        Log.d("MyService","服务启动");
        Intent intent=new Intent(this,MainActivity.class);
        PendingIntent pIntent=PendingIntent.getActivity(this,0,intent,0);
        Notification notification=new NotificationCompat.Builder(this)
                .setContentTitle("This is content title")
                .setContentText("This is content text")
                .setWhen(System.currentTimeMillis())
                .setSmallIcon(R.mipmap.ic_launcher)
                .setLargeIcon(BitmapFactory.decodeResource(getResources(),
                        R.mipmap.ic_launcher))
                .setContentIntent(pIntent)
                .build();
        startForeground(1,notification);
    }
    //onStartCommand() 方法会在每次服务启动的时候调用
    //如果我们希望服务一旦启动就立刻去执行某个动作，就可以将逻辑写在onStartCommand() 方法里。
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d("MyService", "onStartCommand executed");
        return super.onStartCommand(intent, flags, startId);
    }
    //onDestroy() 方法会在服务销毁的时候调用。而当服务销毁时，在onDestroy()中回收那些不再使用的资源
    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("MyService", "服务结束");
    }
    private DownloadBinder mBinder=new DownloadBinder();
    class DownloadBinder extends Binder{
        public void startDownload() {
            Log.d("MyService", "startDownload executed");
        }
        public int getProgress() {
            Log.d("MyService", "getProgress executed");
            return 0;
        }
    }
    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }
}
