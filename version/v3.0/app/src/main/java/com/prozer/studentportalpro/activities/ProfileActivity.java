package com.prozer.studentportalpro.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.prozer.studentportalpro.R;
import com.prozer.studentportalpro.models.Student;

public class ProfileActivity extends AppCompatActivity {

    private TextView tvName, tvEmail, tvCourse, tvRegNo;
    private Button logoutBtn, backBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_profile);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        tvName = findViewById(R.id.tvName);
        tvEmail = findViewById(R.id.tvEmail);
        tvCourse = findViewById(R.id.tvCourse);
        tvRegNo = findViewById(R.id.tvRegNo);
        logoutBtn = findViewById(R.id.logoutBtn);
        backBtn = findViewById(R.id.backBtn);

        Student student = LoginActivity.loggedInStudent;

        if (student != null) {
            tvName.setText("Name: " + student.getName());
            tvEmail.setText("Email: " + student.getEmail());
            tvCourse.setText("Course: " + student.getCourse());
            tvRegNo.setText("Reg No: " + student.getRegNo());
        }

        backBtn.setOnClickListener(v -> finish());

        logoutBtn.setOnClickListener(v -> {
            LoginActivity.loggedInStudent = null;
            Intent intent = new Intent(ProfileActivity.this, LoginActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            finish();
        });
    }
}