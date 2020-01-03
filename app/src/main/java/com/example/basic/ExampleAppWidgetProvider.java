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
                views.setTextViewText(R.id.tv_text, "You drank " + String.format("%d", i)+" cups of water!\nGreat Job!");
            }
            else{
                int j = 8-i;
                views.setTextViewText(R.id.tv_text, "You drank " + String.format("%d", i)+" cups of water!\n"+"Drink " + String.format("%d", j) + " more cups!");
            }

            appWidgetManager.updateAppWidget(appWidgetId, views);

        }

    }

}


