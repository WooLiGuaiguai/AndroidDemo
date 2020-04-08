package com.example.servicetest;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

import androidx.annotation.Nullable;

public class MyInterService extends IntentService {
    public MyInterService() {
        super("MyInterServicesss");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        Log.d("MyInterService","当前线程的名字"+Thread.currentThread().getId());
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("MyInterService","服务终止");
    }
}
