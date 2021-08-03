package com.samiei.exceptionhandling;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //sample
//                int i = 2/0;

        findViewById(R.id.helloWorld)
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                                        int i = 2/0;

                    }
                });
    }
}