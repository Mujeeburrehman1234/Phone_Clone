package com.example.phone_clone;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class SplashScreen extends AppCompatActivity {
    int SPLASH_TIME = 3000;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_splashscreen );
        //Splash Screen  Handler
        new Handler ().postDelayed ( new Runnable () {
            @Override
            public void run() {
                Intent intent = new Intent (SplashScreen.this,Home_activity.class);
                startActivity ( intent );
                finish ();
            }
        },SPLASH_TIME );

    }
}
