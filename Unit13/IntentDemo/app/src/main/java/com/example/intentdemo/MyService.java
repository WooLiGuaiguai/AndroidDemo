package com.example.intentdemo;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.SystemClock;
import android.util.Log;

public class MyService extends Service {
    public MyService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d("MyService","服务开启");
    }

    @Override//实现一个长时间后台定时运行的服务
    public int onStartCommand(Intent intent, int flags, int startId) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Log.d("MyService","开始执行语句命令");
            }
        }).start();
        //首先获取服务实例
        AlarmManager manager=(AlarmManager)getSystemService(ALARM_SERVICE);
        //设置定时任务
        long trigAtTime= SystemClock.elapsedRealtime() +3*1000;
        Intent intent1=new Intent(this,MyService.class);
        PendingIntent pendingIntent=PendingIntent.getService(this,0,intent1,0);
        manager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP,trigAtTime,pendingIntent);
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("MyService","服务结束");
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
