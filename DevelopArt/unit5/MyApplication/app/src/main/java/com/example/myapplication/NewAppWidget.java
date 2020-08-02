package com.example.myapplication;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;

/**
 * Implementation of App Widget functionality.
 */
public class NewAppWidget extends AppWidgetProvider {

    public static final String TAG = "MyWidgetProvider";

    public static final String CLICK_ACTION = "com.gaop.HutHelper.action_click";

    @Override
    public void onReceive(Context context, Intent intent) {
        super.onReceive(context, intent);//不可去掉
        if(intent.getAction().equals(CLICK_ACTION)){
            RemoteViews remoteviews = new RemoteViews(context.getPackageName(), R.layout.widget);
            remoteviews.setTextViewText(R.id.tv_widget_content, "已点击");

            //获得appwidget管理实例，用于管理appwidget以便进行更新操作
            AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);
            //相当于获得所有本程序创建的appwidget
            ComponentName componentName = new ComponentName(context, NewAppWidget.class);
            //更新appwidget
            appWidgetManager.updateAppWidget(componentName, remoteviews);
        }
    }


    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        final int counter = appWidgetIds.length;
        for (int i = 0; i < counter; i++) {
            int appWidgetId = appWidgetIds[i];
            onWidgetUpdate(context, appWidgetManager, appWidgetId);
        }

    }

    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }

    private void onWidgetUpdate(Context context, AppWidgetManager appWidgetManager, int appWidgetId) {

        RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.widget);

        Intent intent = new Intent(context, NewAppWidget.class);
        intent.setAction(CLICK_ACTION);
        PendingIntent pendingIntentpre = PendingIntent.getBroadcast(context, 0, intent, 0);
        //打开MainActivity
        Intent intent2 = new Intent(context,MainActivity.class);
        PendingIntent pendingIntent2 = PendingIntent.getActivity(context, 0, intent2, 0);
        remoteViews.setOnClickPendingIntent(R.id.btn_widget_openactivity, pendingIntent2);

        appWidgetManager.updateAppWidget(appWidgetId, remoteViews);
    }
}

