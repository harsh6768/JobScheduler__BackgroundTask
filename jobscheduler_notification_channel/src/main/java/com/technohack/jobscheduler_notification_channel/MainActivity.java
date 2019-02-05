package com.technohack.jobscheduler_notification_channel;

//import android.support.v7.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

public class MainActivity extends AppCompatActivity {

    private EditText inputText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inputText=findViewById(R.id.input_TextId);

    }

    //start the service by clicking the button
    public void startNotificationService(View view){
        String inText=inputText.getText().toString().trim();

        Intent notificationIntent=new Intent(this,NotificationService.class);
        notificationIntent.putExtra("input",inText);
        //to start the service when we click the button
        //if don't start service in 5 second it will automatically stops the job
        ContextCompat.startForegroundService(this,notificationIntent);

    }

    //for stopping the service by clicking the button
    public void stopNotificationService(View view){
        Intent notificationsIntent=new Intent(MainActivity.this,NotificationService.class);
        //stop service when we click the button
        stopService(notificationsIntent);
    }


}
