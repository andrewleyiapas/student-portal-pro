package com.prozer.studentportalpro.activities;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import com.prozer.studentportalpro.R;

public class FeesActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fees);
        findViewById(R.id.feesBackBtn).setOnClickListener(v -> finish());
    }
}