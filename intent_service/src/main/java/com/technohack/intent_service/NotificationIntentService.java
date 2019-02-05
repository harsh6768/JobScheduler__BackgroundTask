package com.technohack.intent_service;

import android.app.IntentService;
import android.app.Notification;
import android.content.Intent;
import android.os.Build;
import android.os.PowerManager;
import android.os.SystemClock;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;

public class NotificationIntentService extends IntentService {

    public static final String TAG ="IntentService";
    private static final String CHANNEL_ID = "NOTIFICATION_CHANNEL_ID";

    private PowerManager.WakeLock wakeLock;
    public NotificationIntentService(String name) {
        super(name);

        setIntentRedelivery(true);

    }


    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate Section");

        //To manage the power
        PowerManager powerManager=(PowerManager) getSystemService(POWER_SERVICE);
        if(powerManager!=null)
        wakeLock=powerManager.newWakeLock(
          PowerManager.PARTIAL_WAKE_LOCK,   //we just want to wake up the cpu even if our device is off
          "IntentService:Wakelock"      //and this is just a naming convention
        );

        //todo  and this will keep the cpu on after 10 min it will automatically get off
        wakeLock.acquire(10*60*1000L /*10 minutes*/);

        Log.d(TAG, "Wakelock Acquired!!!");

        //checking the version of the Android
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.O){

            Notification notification=new NotificationCompat.Builder(this,CHANNEL_ID)
                    .setContentTitle("Notification Intent Service")
                    .setContentText("Running...")
                    .setSmallIcon(R.drawable.ic_notifications_black_24dp)
                    .build();

            //todo for starting the notification service in the foreground
            startForeground(1,notification);

        }
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        Log.d(TAG, "onHandleIntent ");

        assert intent != null;
        String input=intent.getStringExtra("input");

        for (int i = 0; i <10 ; i++) {
            Log.d(TAG, "Run "+i);

            SystemClock.sleep(1000);

        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy: ");

        //after completing the job we need to release the wakelock
        wakeLock.release();
        Log.d(TAG, "Wakelock released");

    }

}
