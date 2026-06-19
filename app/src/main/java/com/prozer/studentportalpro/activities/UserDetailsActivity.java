package com.prozer.studentportalpro.activities;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.prozer.studentportalpro.R;

public class UserDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_details);

        TextView txtName = findViewById(R.id.txtName);
        TextView txtEmail = findViewById(R.id.txtEmail);
        TextView txtPhone = findViewById(R.id.txtPhone);
        TextView txtWebsite = findViewById(R.id.txtWebsite);

        txtName.setText(
                "Name: " +
                        getIntent().getStringExtra("name"));

        txtEmail.setText(
                "Email: " +
                        getIntent().getStringExtra("email"));

        txtPhone.setText(
                "Phone: " +
                        getIntent().getStringExtra("phone"));

        txtWebsite.setText(
                "Website: " +
                        getIntent().getStringExtra("website"));
    }
}