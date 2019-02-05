package com.technohack.jobintentservice;

//import android.support.v7.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText inputText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inputText=findViewById(R.id.input_TextId);
    }

    public void enqueueWork(View view){

        String input=inputText.getText().toString().trim();

        Intent intent=new Intent(this,NotificationJobIntentService.class);
        intent.putExtra("input",input);

        //TODO to start the service
        // TODO this method won't use any condition like wifi checking or battery if you want to apply those condition this method won't work for you
        NotificationJobIntentService.enqueueWork(this,intent);

    }
}
