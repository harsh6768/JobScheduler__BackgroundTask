package com.technohack.intent_service;

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

    //for starting the service
    public void startNotificationIntentService(View view){
       String input=inputText.getText().toString().trim();

       Intent serviceIntent=new Intent(this,NotificationIntentService.class);
       serviceIntent.putExtra("input",input);

       //for managing the foreground job in every version of android
        ContextCompat.startForegroundService(this,serviceIntent);


    }
}
