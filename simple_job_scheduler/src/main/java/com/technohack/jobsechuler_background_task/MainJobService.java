package com.technohack.jobsechuler_background_task;

import android.app.job.JobParameters;
import android.app.job.JobService;
import android.util.Log;

public class MainJobService extends JobService {

    private  static final String TAG="MainJobService";
    private static final boolean jobCancelled=false;

    @Override
    public boolean onStartJob(JobParameters params) {
//  work that you want to do in background thread
      doInBackground(params);
      //TODO return true if you have any job to complete before cancelled the job
        return true;
    }

    //TODO--> job whatever you want to do
    private void doInBackground(final JobParameters params) {

        //run job in background thread
        new Thread(new Runnable() {
            @Override
            public void run() {

                for(int i=0;i<10;i++){

                    Log.d(TAG, "run:"+i);

                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    Log.d(TAG, "Job finished");

                    //after completing the job we need to finished the job
                    jobFinished(params,false);

                }
            }
        }).start();

    }

    @Override
    public boolean onStopJob(JobParameters params) {
        Log.d(TAG, "onStopJob: Job cancelled without completing!!!");
        return true;
    }

}
