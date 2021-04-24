package com.wheic.cleanurge.OtherActivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;
import android.widget.TextView;

import com.wheic.cleanurge.MainActivity;
import com.wheic.cleanurge.R;
import com.wheic.cleanurge.SharedPrefManager.SharedPrefManager;

public class SplashScreenActivity extends AppCompatActivity {

    private ImageView appSplashImage;
    private TextView appSplashText;
    private int SPLASH_TIME_OUT = 2000;

    private SharedPrefManager sharedPrefManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        appSplashImage = findViewById(R.id.appSplashImage);
        appSplashText = findViewById(R.id.appSplashText);
        sharedPrefManager = new SharedPrefManager(this);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                Intent nextIntent;

                if(sharedPrefManager.isLoggedIn()){
                    nextIntent = new Intent(SplashScreenActivity.this, MainActivity.class);
                }else{
                    nextIntent = new Intent(SplashScreenActivity.this, GatewayActivity.class);
                }

                nextIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(nextIntent);
                finish();

            }
        }, SPLASH_TIME_OUT);
    }
}