package com.technohack.jobintentservice;

import android.content.Context;
import android.content.Intent;
import android.os.SystemClock;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.core.app.JobIntentService;


public class NotificationJobIntentService extends JobIntentService {

    public static final String TAG="NotifiJobIntentService";

    // we have to start service by own so for that we will
    static void enqueueWork(Context context, Intent work){

        enqueueWork(context,NotificationJobIntentService.class,123,work);

    }

    /*
    TODO we don't need to implement logic for running the cpu because JObScheduler will take care of It.
     */
    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate");
    }


    @Override
    protected void onHandleWork(@NonNull Intent intent) {

        Log.d(TAG, "onHandleWork");

        String input=intent.getStringExtra("input");

        for (int i = 0; i <10 ; i++) {

            Log.d(TAG, input +"-"+i);
            //if job is completed we need to stopped by ourSelf otherwise cpu will kill the process
            if(isStopped()){
                return ;
            }
            //time we will sleep the job
            SystemClock.sleep(1000);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy");
    }

    //TODO this method will trigger when current work will stop if it's using JOB SCHEDULER after api level 26
    @Override
    public boolean onStopCurrentWork() {

        Log.d(TAG, "onStopCurrentWork");

        return super.onStopCurrentWork();
    }
}
