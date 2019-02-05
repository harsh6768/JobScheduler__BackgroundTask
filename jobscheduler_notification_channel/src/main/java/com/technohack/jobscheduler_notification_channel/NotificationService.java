package com.technohack.jobscheduler_notification_channel;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;

import static com.technohack.jobscheduler_notification_channel.NotificationApp.NOTIFICATION_CHANNEL_ID;

public class NotificationService extends Service {

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        String inputText=intent.getStringExtra("input");

        Intent notificationIntent=new Intent(this,MainActivity.class);

        PendingIntent pendingIntent=PendingIntent.getActivity(
          this,
                0
          ,notificationIntent,
                0
        );

        Notification notification=new NotificationCompat.Builder(this,NOTIFICATION_CHANNEL_ID)
                .setContentTitle("Notification Service")
                .setContentText(inputText)
                .setSmallIcon(R.drawable.ic_notifications_black_24dp)
                .setContentIntent(pendingIntent)
                .build();

        //if we don't start service in foreground service will automatically stopped after 1 minute
        startForeground(1, notification);

        //we don't start this job again when we our job is either cancel or completed
        return START_NOT_STICKY;

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

}
