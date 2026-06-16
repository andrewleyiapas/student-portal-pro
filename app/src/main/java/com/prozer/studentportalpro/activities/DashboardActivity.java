package com.prozer.studentportalpro.activities;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.prozer.studentportalpro.R;

public class DashboardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_dashboard);

        // Profile
        findViewById(R.id.btnProfile)
                .setOnClickListener(v ->
                        startActivity(new Intent(this, ProfileActivity.class)));

        // Results
        findViewById(R.id.btnResults)
                .setOnClickListener(v ->
                        startActivity(new Intent(this, ResultActivity.class)));

        // GPA Calculator
        findViewById(R.id.btnGpa)
                .setOnClickListener(v ->
                        startActivity(new Intent(this, GpacalculatorActivity.class)));

        // Timetable
        findViewById(R.id.btnTimetable)
                .setOnClickListener(v ->
                        startActivity(new Intent(this, TimetableActivity.class)));

        // Add Student
        findViewById(R.id.btnAddStudent)
                .setOnClickListener(v ->
                        startActivity(new Intent(this, AddStudentActivity.class)));

        // View Students
        findViewById(R.id.btnViewStudents)
                .setOnClickListener(v ->
                        startActivity(new Intent(this, ViewStudentsActivity.class)));

        // Student Records
        findViewById(R.id.btnStudentRecords)
                .setOnClickListener(v ->
                        startActivity(new Intent(this, StudentRecordsActivity.class)));

        // Assignments
        findViewById(R.id.btnAssignments)
                .setOnClickListener(v ->
                        startActivity(new Intent(this, AssignmentActivity.class)));

        // News
        findViewById(R.id.btnNews)
                .setOnClickListener(v ->
                        startActivity(new Intent(this, NewsActivity.class)));

        // Fees
        findViewById(R.id.btnFees)
                .setOnClickListener(v ->
                        startActivity(new Intent(this, FeesActivity.class)));

        // Notifications
        findViewById(R.id.btnNotifications)
                .setOnClickListener(v ->
                        startActivity(new Intent(this, NotificationActivity.class)));

        // Settings
        findViewById(R.id.btnSettings)
                .setOnClickListener(v ->
                        startActivity(new Intent(this, SettingsActivity.class)));
        findViewById(R.id.btnApiUsers)
                .setOnClickListener(v ->
                        startActivity(
                                new Intent(
                                        this,
                                        ApiUsersActivity.class
                                )
                        ));

        // Student ID Card
        findViewById(R.id.btnIdCard)
                .setOnClickListener(v ->
                        startActivity(new Intent(this, StudentIdCardActivity.class)));
    }
}