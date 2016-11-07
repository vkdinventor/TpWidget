package com.example.vikash.tpwidget;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.net.wifi.WifiManager;
import android.util.Log;
import android.widget.RemoteViews;
import android.widget.Toast;

import java.lang.reflect.Method;
import java.util.Random;

public class MainActivity extends AppWidgetProvider{

    String TAG="com.example.vikash.tpwidget";
    WifiManager mWifiManager;
    public static boolean status;
    boolean hotspot;
    public static int j=0;

    @Override
    public void onEnabled(Context context) {
        super.onEnabled(context);
//
    }

    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        mWifiManager=(WifiManager) context.getSystemService(Context.WIFI_SERVICE);
        for(int i=0; i<appWidgetIds.length; i++){
            int widgetId = appWidgetIds[i];
            String number = String.format("%03d", (new Random().nextInt(900) + 100));

            RemoteViews remoteViews = new RemoteViews(context.getPackageName(),
                    R.layout.activity_main);
            HotspotManager.configApState(context); // change Ap state :boolean

            remoteViews.setTextViewText(R.id.text, number+" "+HotspotManager.isApOn(context));

            Intent intent = new Intent(context, MainActivity.class);
            intent.setAction(AppWidgetManager.ACTION_APPWIDGET_UPDATE);
            intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS, appWidgetIds);
            PendingIntent pendingIntent = PendingIntent.getBroadcast(context,
                    0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
            remoteViews.setOnClickPendingIntent(R.id.button, pendingIntent);
            appWidgetManager.updateAppWidget(widgetId, remoteViews);

        }
    }

    public boolean startHotSpot(boolean enable) {

        mWifiManager.setWifiEnabled(false);
        Method[] mMethods = mWifiManager.getClass().getDeclaredMethods();
        for (Method mMethod : mMethods) {
            if (mMethod.getName().equals("setWifiApEnabled")) {
                try {
                    mMethod.invoke(mWifiManager, null, enable);
                    Log.v(TAG,"hotspot enabled");
                    return true;
                } catch (Exception ex) {
                    Log.v(TAG,"Excpetion: "+ ex.toString());
                }
                break;
            }
            Log.v(TAG,"setWifiApEnabled method : "+mMethod.toString());
        }
        return false;
    }
}