package com.prozer.studentportalpro.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.prozer.studentportalpro.R;
import com.prozer.studentportalpro.database.DatabaseHelper;

public class RegisterActivity extends AppCompatActivity {

    private EditText etName, etEmail, etPassword, etCourse, etRegNo;
    private Button btnRegister;
    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        etName = findViewById(R.id.etName);
        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        etCourse = findViewById(R.id.etCourse);
        etRegNo = findViewById(R.id.etRegNo);
        btnRegister = findViewById(R.id.btnRegister);

        databaseHelper = new DatabaseHelper(this);

        btnRegister.setOnClickListener(v -> {
            String name = etName.getText().toString().trim();
            String email = etEmail.getText().toString().trim();
            String password = etPassword.getText().toString().trim();
            String course = etCourse.getText().toString().trim();
            String regNo = etRegNo.getText().toString().trim();

            if (name.isEmpty() || email.isEmpty() || password.isEmpty() || course.isEmpty() || regNo.isEmpty()) {
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                return;
            }

            boolean isRegistered = databaseHelper.registerStudent(name, email, password, course, regNo);

            if (isRegistered) {
                Toast.makeText(this, "Registration Successful", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(this, LoginActivity.class));
                finish();
            } else {
                Toast.makeText(this, "Registration Failed or User already exists", Toast.LENGTH_SHORT).show();
            }
        });
    }
}