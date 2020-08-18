package com.example.myapplication;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import java.util.Calendar;

import static android.app.PendingIntent.FLAG_ONE_SHOT;

public class MainActivity extends AppCompatActivity {

    Button show;
    private Handler mHandler = new Handler();

    public static String MY_PREFS_NAME= "nameOfSharedPreferences";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        show = (Button)findViewById(R.id.btn_show);
        mToastRunnable.run();

       show.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent intent = new Intent(MainActivity.this,Main3Activity.class);
               startActivity(intent);
           }
       });


    }



    private Runnable mToastRunnable = new Runnable() {
        @Override
        public void run() {
           // Toast.makeText(MainActivity.this, "This is a delayed toast", Toast.LENGTH_SHORT).show();
            startAlarm(true,true);
            mHandler.postDelayed(this, 15000);
        }
    };

    private void startAlarm(boolean isNotification, boolean isRepeat) {
        AlarmManager manager = (AlarmManager)getSystemService(Context.ALARM_SERVICE);


        // SET TIME HERE
        Calendar calendar= Calendar.getInstance();


     int hour = calendar.get(Calendar.HOUR);
     int min = calendar.get(Calendar.MINUTE);
     int sec = calendar.get(Calendar.SECOND);
     if(hour>=2 && min >=4 && sec >=0)
     {
       //  Toast.makeText(MainActivity.this, " Noti fication de", Toast.LENGTH_SHORT).show();
         NotificationCompat.Builder builder = new NotificationCompat.Builder(MainActivity.this);

         Intent myIntent = new Intent(MainActivity.this, Main2Activity.class);
         PendingIntent pendingIntent = PendingIntent.getActivity(
                 MainActivity.this,
                 0,
                 myIntent,
                 FLAG_ONE_SHOT );

         builder.setAutoCancel(true)
                 .setDefaults(Notification.DEFAULT_ALL)
                 .setWhen(System.currentTimeMillis())
                 .setSmallIcon(R.mipmap.ic_launcher)
                 .setContentTitle(" buddy")
                 .setContentIntent(pendingIntent)
                 .setContentText("Let's  for quiz ")
                 .setDefaults(Notification.DEFAULT_LIGHTS | Notification.DEFAULT_SOUND)
                 .setContentInfo("Info");

         NotificationManager notificationManager = (NotificationManager)MainActivity.this.getSystemService(Context.NOTIFICATION_SERVICE);
         notificationManager.notify(1,builder.build());

     }



    }

}
