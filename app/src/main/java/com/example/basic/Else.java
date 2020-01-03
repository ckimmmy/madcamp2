package com.example.basic;


import android.app.Activity;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.preference.PreferenceManager;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import androidx.fragment.app.Fragment;
import static androidx.core.content.ContextCompat.getSystemService;

import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;


import static android.content.Context.NOTIFICATION_SERVICE;

import me.itangqi.waveloadingview.WaveLoadingView;

import static android.content.Context.NOTIFICATION_SERVICE;


/**
 * A simple {@link Fragment} subclass.
 */
public class Else extends Fragment {


    public Else() {
        // Required empty public constructor
    }

    public static final String NOTIFICATION_CHANNEL_ID = "10001";
    private int count = 0;
    int index = 0;
    int k;
    Thread thread;
    boolean isThread=false;

    SharedPreferences a;
    WaveLoadingView waveLoadingView;
    //MainActivity main = (MainActivity) getActivity();

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View waterView = inflater.inflate(R.layout.fragment_else, container, false);
        Button button = (Button) waterView.findViewById(R.id.button);
        Button resetbutton = (Button) waterView.findViewById(R.id.resetbutton);
        waveLoadingView = (WaveLoadingView) waterView.findViewById(R.id.waveLoadingView);

        final TextView textView = (TextView) waterView.findViewById(R.id.textView);

        resetbutton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                index = 0;
                Activity myActivity=(Activity) container.getContext();
                SharedPreferences a = PreferenceManager.getDefaultSharedPreferences(myActivity);
                SharedPreferences.Editor editor = a.edit();
                editor.putInt("i", index);
                editor.apply();

                double percentage = index / 8 * 100;
                textView.setText(String.format("%.0f", percentage) + " %");
                waveLoadingView.setProgressValue((int) percentage);

                AppWidgetManager mgr = AppWidgetManager.getInstance(getContext());
                Intent update = new Intent(AppWidgetManager.ACTION_APPWIDGET_UPDATE);
                update.setClass(getContext(), ExampleAppWidgetProvider.class);
                update.putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS, mgr.getAppWidgetIds(new ComponentName(getContext(), ExampleAppWidgetProvider.class)));
                getContext().sendBroadcast(update);
            };
        });

        a = PreferenceManager.getDefaultSharedPreferences(this.getActivity());
        int i = a.getInt("i", 0);

        index = i;


        if (index >=8) {

            double percentage = 8.0 / 8.0 * 100;
            waveLoadingView.setProgressValue((int) percentage);
            textView.setText(String.format("%.0f", percentage) + " %");
        } else {
            double percentage = index / 8.0 * 100;
            waveLoadingView.setProgressValue((int) percentage);
            textView.setText(String.format("%.0f", percentage) + " %");
        }




        Timer timer = new Timer();
        // Creates a Calendar object that specifies a specific time of day
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(System.currentTimeMillis());
        cal.set(Calendar.HOUR_OF_DAY, 23);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 59);
        cal.set(Calendar.MILLISECOND, 99);

        Date date = cal.getTime();

        TimerTask tt = new TimerTask() {
            @Override
            public void run () {
                index = 0;
                Activity myActivity=(Activity) container.getContext();
                SharedPreferences a = PreferenceManager.getDefaultSharedPreferences(myActivity);
                SharedPreferences.Editor editor = a.edit();
                editor.putInt("i", index);
                editor.apply();
            }
        };

        timer.schedule(tt, date);

        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                k = 0;
                isThread = true;
                thread = new Thread() {
                    public void run() {
                        while(isThread){
                            button.setOnClickListener(new View.OnClickListener(){
                                @Override
                                public void onClick(View v){
                                    index += 1;

                                    Activity myActivity=(Activity)(view.getContext());
                                    SharedPreferences a = PreferenceManager.getDefaultSharedPreferences(myActivity);
                                    SharedPreferences.Editor editor = a.edit();
                                    editor.putInt("i", index);
                                    editor.apply();

                                    AppWidgetManager mgr = AppWidgetManager.getInstance(getContext());
                                    Intent update = new Intent(AppWidgetManager.ACTION_APPWIDGET_UPDATE);
                                    update.setClass(getContext(), ExampleAppWidgetProvider.class);
                                    update.putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS, mgr.getAppWidgetIds(new ComponentName(getContext(), ExampleAppWidgetProvider.class)));
                                    getContext().sendBroadcast(update);

                                    if (index >= 8) {
                                        Toast toast = Toast.makeText(getActivity(), "Congratulations! You drank 8 cups of Water!", Toast.LENGTH_SHORT);
                                        toast.setGravity(Gravity.CENTER, 0, 0);
                                        toast.show();
                                        double percentage = 8 / 8 * 100;
                                        textView.setText(String.format("%.0f", percentage) + " %");
                                        waveLoadingView.setProgressValue((int) percentage);
                                    } else {
                                        waveLoadingView.setProgressValue(index);
                                        double percentage = index / 8.0 * 100;
                                        textView.setText(String.format("%.0f", percentage) + " %");
                                        waveLoadingView.setProgressValue((int) percentage);
                                    }



                                    isThread=false;
                                    interrupt();
                                    k=0;

                                }
                            });
                            try {
                                sleep(5000);//5000->360000
                                k++;
                                handler.sendEmptyMessage(0);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            isThread=true;

                        }
                    }
                };
                index += 1;

                Activity myActivity=(Activity)(view.getContext());
                SharedPreferences a = PreferenceManager.getDefaultSharedPreferences(myActivity);
                SharedPreferences.Editor editor = a.edit();
                editor.putInt("i", index);
                editor.apply();
                thread.start();
////
////
////                count++;
//                index += 1;
//
//                Activity myActivity=(Activity)(view.getContext());
//                SharedPreferences a = PreferenceManager.getDefaultSharedPreferences(myActivity);
//                SharedPreferences.Editor editor = a.edit();
//                editor.putInt("i", index);
//                editor.apply();


                AppWidgetManager mgr = AppWidgetManager.getInstance(getContext());
                Intent update = new Intent(AppWidgetManager.ACTION_APPWIDGET_UPDATE);
                update.setClass(getContext(), ExampleAppWidgetProvider.class);
                update.putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS, mgr.getAppWidgetIds(new ComponentName(getContext(), ExampleAppWidgetProvider.class)));
                getContext().sendBroadcast(update);

                if (index >= 8) {
                    Toast toast = Toast.makeText(getActivity(), "Congratulations! You drank 8 cups of Water!", Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    toast.show();
                    double percentage = 8 / 8 * 100;
                    textView.setText(String.format("%.0f", percentage) + " %");
                  waveLoadingView.setProgressValue((int) percentage);
                } else {
                    waveLoadingView.setProgressValue(index);
                    double percentage = index / 8.0 * 100;
                    textView.setText(String.format("%.0f", percentage) + " %");
                    waveLoadingView.setProgressValue((int) percentage);
                }

            }
        });
        return waterView;
    }

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            NotificationSomethings();
        }
    };
    public void NotificationSomethings() {


        NotificationManager notificationManager = (NotificationManager)getActivity().getSystemService(NOTIFICATION_SERVICE);

        Intent notificationIntent = new Intent(getActivity(),MainActivity.class);
        notificationIntent.putExtra("notificationId", count); //전달할 값
        notificationIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK) ;
        PendingIntent pendingIntent = PendingIntent.getActivity(getActivity(), 0, notificationIntent,  PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(getActivity(), NOTIFICATION_CHANNEL_ID)
                .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.icon_bottle)) //BitMap 이미지 요구
                .setContentTitle(k+" hours have passed since you drank water!")
                .setContentText("Aim to drink 8 cups of water a day :)")
                // 더 많은 내용이라서 일부만 보여줘야 하는 경우 아래 주석을 제거하면 setContentText에 있는 문자열 대신 아래 문자열을 보여줌
                //.setStyle(new NotificationCompat.BigTextStyle().bigText("더 많은 내용을 보여줘야 하는 경우..."))
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setContentIntent(pendingIntent) // 사용자가 노티피케이션을 탭시 ResultActivity로 이동하도록 설정
                .setAutoCancel(true);

        //OREO API 26 이상에서는 채널 필요
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            builder.setSmallIcon(R.drawable.ic_launcher_foreground); //mipmap 사용시 Oreo 이상에서 시스템 UI 에러남
            CharSequence channelName  = "노티페케이션 채널";
            String description = "오레오 이상을 위한 것임";
            int importance = NotificationManager.IMPORTANCE_HIGH;

            NotificationChannel channel = new NotificationChannel(NOTIFICATION_CHANNEL_ID, channelName , importance);
            channel.setDescription(description);

            // 노티피케이션 채널을 시스템에 등록
            assert notificationManager != null;
            notificationManager.createNotificationChannel(channel);

        }else builder.setSmallIcon(R.mipmap.ic_launcher); // Oreo 이하에서 mipmap 사용하지 않으면 Couldn't create icon: StatusBarIcon 에러남

        assert notificationManager != null;
        notificationManager.notify(1234, builder.build()); // 고유숫자로 노티피케이션 동작시킴

    }


}
