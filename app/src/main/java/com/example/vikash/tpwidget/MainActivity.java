package com.example.vikash.tpwidget;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.widget.RemoteViews;
import android.widget.Toast;

public class MainActivity extends AppWidgetProvider{

    @Override
    public void onEnabled(Context context) {
        super.onEnabled(context);
//
    }

    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        for(int i=0; i<appWidgetIds.length; i++){
            int currentWidgetId = appWidgetIds[i];
            String url = "http://stackoverflow.com";

            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.setData(Uri.parse(url));

            PendingIntent pending = PendingIntent.getActivity(context, 0,intent, 0);
            RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.activity_main);

            long time=System.currentTimeMillis()/1000;
            views.setTextViewText(R.id.text," "+time);
            Log.v("TAG", " "+time);
            views.setOnClickPendingIntent(R.id.button, pending);
            appWidgetManager.updateAppWidget(currentWidgetId,views);
            Toast.makeText(context, "widget added", Toast.LENGTH_SHORT).show();
        }
    }
}