package com.ganesh.parkking;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;
import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

public class splash extends AppCompatActivity {
    TextView line,Name;
    LottieAnimationView lottieAnimationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        line=findViewById(R.id.slogen);
        lottieAnimationView=findViewById(R.id.splash);
        Name=findViewById(R.id.name);


        YoYo.with(Techniques.Bounce).duration(700).repeat(4).playOn(line);
      //  YoYo.with(Techniques.Flash).duration(700).repeat(4).playOn(Name);
        // line.animate().translationY(-1400).setDuration(2700).setStartDelay(0);
        lottieAnimationView.animate().translationX(2000).setDuration(2000).setStartDelay(2900);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                startActivity(new Intent(splash.this,WelcomeActivity.class));

            }
        },3000);



    }
}