package com.technohack.jobsechuler_background_task;

import androidx.appcompat.app.AppCompatActivity;

import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private static final int JOB_ID=101;
    private static final long REFRESH_INTERVAL  = 5 * 1000; // 5 seconds
    private static final String TAG="MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public  void scheduleJob(View view){

        ComponentName componentName=new ComponentName(this,MainJobService.class);

        JobInfo jobInfo=new JobInfo.Builder(JOB_ID,componentName)
                .setRequiresCharging(true)
                //task runs when device is connected with any wifi
                .setRequiredNetworkType(JobInfo.NETWORK_TYPE_CELLULAR)
                //TODO setPersisted(true) it will complete it works even if you boot the device
                .setPersisted(true)
                .setPeriodic(15 * 60 * 1000)
                .build();

        //getting the service so that we can perform any Job or task according to our requirement
        JobScheduler jobScheduler= (JobScheduler) getSystemService(JOB_SCHEDULER_SERVICE);

         if(jobScheduler!=null){
             int resultCode=jobScheduler.schedule(jobInfo);
             if(resultCode==JobScheduler.RESULT_SUCCESS){
                 Log.d(TAG, "scheduleJob!!!");
             }else{
                 Log.d(TAG, "Failed to scheduleJob!!!");
             }
         }


    }

    public void cancelJob(View view){
            JobScheduler jobScheduler=(JobScheduler) getSystemService(JOB_SCHEDULER_SERVICE);

            if(jobScheduler!=null){
                jobScheduler.cancel(JOB_ID);
            }
            Log.d(TAG, "cancelJob");
    }

}
