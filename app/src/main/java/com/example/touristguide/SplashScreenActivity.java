package com.example.touristguide;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

import com.airbnb.lottie.LottieAnimationView;

public class SplashScreenActivity extends AppCompatActivity {
    ImageView appLogo,appName,splashImg;
    LottieAnimationView lottieAnimationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        this.getSupportActionBar().hide();
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        appLogo=(ImageView) findViewById(R.id.appLogo);
        appName=(ImageView) findViewById(R.id.appName);
        splashImg=(ImageView) findViewById(R.id.splashImg);
        lottieAnimationView=(LottieAnimationView)findViewById(R.id.lottie);

        splashImg.animate().alpha(0).setDuration(500).setStartDelay(5000);
        appLogo.animate().translationY(-2000).setDuration(1000).setStartDelay(5000);
        appName.animate().translationY(2000).setDuration(1000).setStartDelay(5000);
        lottieAnimationView.animate().translationY(2000).setDuration(1000).setStartDelay(5000);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                finish();
            }
        },6000);

    }
}