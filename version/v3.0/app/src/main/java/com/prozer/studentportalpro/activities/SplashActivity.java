package com.prozer.studentportalpro.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.prozer.studentportalpro.R;

public class SplashActivity extends AppCompatActivity {

    ProgressBar progressBar;
    TextView txtLoading;

    int progress = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        progressBar = findViewById(R.id.progressBar);
        txtLoading = findViewById(R.id.txtLoading);

        Handler handler = new Handler();

        Runnable runnable = new Runnable() {
            @Override
            public void run() {

                progress += 5;

                progressBar.setProgress(progress);

                txtLoading.setText("Loading... " + progress + "%");

                if(progress < 100){

                    handler.postDelayed(this, 150);

                }else{

                    SharedPreferences prefs = getSharedPreferences("StudentPortal", MODE_PRIVATE);
                    boolean loggedIn = prefs.getBoolean("loggedIn", false);

                    if (loggedIn) {
                        startActivity(new Intent(SplashActivity.this, DashboardActivity.class));
                    } else {
                        startActivity(new Intent(SplashActivity.this, LoginActivity.class));
                    }

                    finish();

                }

            }
        };

        handler.post(runnable);

    }
}