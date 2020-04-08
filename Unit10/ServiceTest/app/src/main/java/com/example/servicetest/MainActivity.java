package com.example.servicetest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.io.InputStream;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private MyService.DownloadBinder mbinder;
    private ServiceConnection connection=new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            mbinder=(MyService.DownloadBinder)iBinder;
            mbinder.startDownload();
            mbinder.getProgress();
        }
        @Override
        public void onServiceDisconnected(ComponentName componentName) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button startService = (Button) findViewById(R.id.start_service);
        Button stopService = (Button) findViewById(R.id.stop_service);
        startService.setOnClickListener(this);
        stopService.setOnClickListener(this);
        Button bindService = (Button) findViewById(R.id.bind_service);
        Button unbindService = (Button) findViewById(R.id.unbind_service);
        bindService.setOnClickListener(this);
        unbindService.setOnClickListener(this);
        Button startIntentService = (Button) findViewById(R.id.start_intent_service);
        startIntentService.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {//startService() 和stopService() 方法都是定义在Context 类
        switch (view.getId()){
            case R.id.start_service:
                Intent intent=new Intent(this,MyService.class);
                startService(intent);
                break;
            case R.id.stop_service:
                Intent intent2=new Intent(this,MyService.class);
                stopService(intent2);
                break;
            case R.id.bind_service:
                Intent binderIntent=new Intent(this,MyService.class);
                bindService(binderIntent,connection,BIND_AUTO_CREATE);
                break;
            case R.id.unbind_service:
                unbindService(connection);
                break;
            case R.id.start_intent_service:
                Log.d("MainActivity","主线程的id是"+Thread.currentThread().getId());
                Intent intent1=new Intent(this,MyInterService.class);
                startService(intent1);
                break;
            default:
                break;
        }
    }
}
