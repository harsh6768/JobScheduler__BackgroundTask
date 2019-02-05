package com.technohack.jobscheduler_notification_channel;

import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;

public class NotificationApp  extends Application {

    public static final String NOTIFICATION_CHANNEL_ID="SERVICE_CHANNEL_ID";

    @Override
    public void onCreate() {
        super.onCreate();

        createNotificationChannel();

    }

    //after OREO we need to create channel to show the notification in our app
    private void createNotificationChannel() {

        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.O){

            NotificationChannel notificationChannel=new NotificationChannel(
                    NOTIFICATION_CHANNEL_ID,
                    "Service Channel",
                    NotificationManager.IMPORTANCE_DEFAULT
            );

            NotificationManager manager=getSystemService(NotificationManager.class);

            if(manager!=null)
            manager.createNotificationChannel(notificationChannel);

        }

     }
}
