package com.example.uytai.farmersp.mvp.onboarding;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.uytai.farmersp.R;
import com.example.uytai.farmersp.mvp.login.LoginActivity;

public class SplashScreenActivity extends AppCompatActivity {

    private ImageView mImageView;
    private TextView mTextView;
    private Thread mThread;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        mImageView = (ImageView) findViewById(R.id.image);
        mTextView = (TextView) findViewById(R.id.text);
        startAnimation();
        sharedPreferences = getSharedPreferences("onboarding", MODE_PRIVATE);
    }

    private void startAnimation() {
        Animation rotate = AnimationUtils.loadAnimation(this, R.anim.rotate);
        rotate.reset();
        mImageView.setAnimation(rotate);

        mThread = new Thread() {
            @Override
            public void run() {
                super.run();
                int waited = 0;
                while (waited < 2500) {
                    try {
                        sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    waited += 100;
                }
                SplashScreenActivity.this.finish();
                if(sharedPreferences.getBoolean("onboar", false)){
                    Intent intent = new Intent(SplashScreenActivity.this, LoginActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                    startActivity(intent);
                }else{
                    Intent intent = new Intent(SplashScreenActivity.this, OnboardingActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                    startActivity(intent);
                }

            }
        };
        mThread.start();
    }
}
