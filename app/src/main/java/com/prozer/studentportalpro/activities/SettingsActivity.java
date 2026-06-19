package com.prozer.studentportalpro.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import com.prozer.studentportalpro.R;
import com.prozer.studentportalpro.database.DatabaseHelper;

public class SettingsActivity extends AppCompatActivity {

    private DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        db = new DatabaseHelper(this);

        findViewById(R.id.btnProfileSettings).setOnClickListener(v -> {
            startActivity(new Intent(this, ProfileActivity.class));
        });

        findViewById(R.id.btnChangePassword).setOnClickListener(v -> showChangePasswordDialog());

        findViewById(R.id.btnDarkMode).setOnClickListener(v -> toggleDarkMode());

        findViewById(R.id.btnHelp).setOnClickListener(v -> showHelpDialog());

        findViewById(R.id.btnAbout).setOnClickListener(v -> showAboutDialog());
    }

    private void showChangePasswordDialog() {
        if (LoginActivity.loggedInStudent == null) {
            Toast.makeText(this, "Please login first", Toast.LENGTH_SHORT).show();
            return;
        }

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Change Password");

        final EditText input = new EditText(this);
        input.setHint("Enter new password");
        builder.setView(input);

        builder.setPositiveButton("Update", (dialog, which) -> {
            String newPass = input.getText().toString().trim();
            if (!newPass.isEmpty()) {
                boolean success = db.updatePassword(LoginActivity.loggedInStudent.getId(), newPass);
                if (success) {
                    Toast.makeText(this, "Password updated successfully!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "Failed to update password", Toast.LENGTH_SHORT).show();
                }
            }
        });
        builder.setNegativeButton("Cancel", (dialog, which) -> dialog.cancel());

        builder.show();
    }

    private void toggleDarkMode() {
        SharedPreferences sharedPreferences = getSharedPreferences("AppPrefs", MODE_PRIVATE);
        boolean isDarkMode = sharedPreferences.getBoolean("DarkMode", false);

        if (isDarkMode) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
            sharedPreferences.edit().putBoolean("DarkMode", false).apply();
            Toast.makeText(this, "Light Mode Enabled", Toast.LENGTH_SHORT).show();
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
            sharedPreferences.edit().putBoolean("DarkMode", true).apply();
            Toast.makeText(this, "Dark Mode Enabled", Toast.LENGTH_SHORT).show();
        }
    }

    private void showHelpDialog() {
        new AlertDialog.Builder(this)
                .setTitle("Help & Support")
                .setMessage("For any issues, please contact our support team at:\n\nEmail: support@studentportal.com\nPhone: +254 111579951")
                .setPositiveButton("OK", null)
                .show();
    }

    private void showAboutDialog() {
        new AlertDialog.Builder(this)
                .setTitle("About App")
                .setMessage("Student Portal Pro\nVersion 1.0.4\n\nDeveloped by Prozer\nPowered by EduKenya")
                .setPositiveButton("OK", null)
                .show();
    }
}
