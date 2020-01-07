package com.example.basic;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.widget.RemoteViews;



public class ExampleAppWidgetProvider extends AppWidgetProvider {

    public ExampleAppWidgetProvider() {
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        for(int appWidgetId : appWidgetIds) {
            Intent intent = new Intent(context, MainActivity.class);

            intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS, appWidgetIds);

            PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);

            RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.example_widget);
            views.setOnClickPendingIntent(R.id.tv_text, pendingIntent);

            SharedPreferences  a = PreferenceManager.getDefaultSharedPreferences(context);
            int i = a.getInt("i", 0);
            if(i >= 8){
                views.setTextViewText(R.id.tv_text, String.format("%d", i)+"잔의 물을 마셨구나!\n참 잘했어요!");
            }
            else{
                int j = 8-i;
                views.setTextViewText(R.id.tv_text, String.format("%d", i)+"잔의 물을 마셨구나!\n"+String.format("%d", j)+"잔의 물을 더 마시세요!");
            }

            appWidgetManager.updateAppWidget(appWidgetId, views);

        }

    }

}


