package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RemoteViews;

import com.example.myapplication.utils.MyConstants;

public class DemoActivity_2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo_2);
    }
    public void onButtonClick(View v) {
        RemoteViews remoteViews = new RemoteViews(getPackageName(), R.layout.layout_simulated_notification);
        remoteViews.setTextViewText(R.id.msg, "msg from process:" + android.os.Process.myPid());
        remoteViews.setImageViewResource(R.id.icon, R.mipmap.icon1);
        /*PendingIntent pendingIntent = PendingIntent.getActivity(this,
                0, new Intent(this, DemoActivity_1.class), PendingIntent.FLAG_UPDATE_CURRENT);*/
        PendingIntent openActivity2PendingIntent = PendingIntent.getActivity(
                this, 0, new Intent(this, DemoActivity_2.class), PendingIntent.FLAG_UPDATE_CURRENT);
        //remoteViews.setOnClickPendingIntent(R.id.item_holder, pendingIntent);
        remoteViews.setOnClickPendingIntent(R.id.open_activity2, openActivity2PendingIntent);
        Intent intent = new Intent(MyConstants.REMOTE_ACTION);
        intent.putExtra(MyConstants.EXTRA_REMOTE_VIEWS, remoteViews);
        sendBroadcast(intent);
    }
}
