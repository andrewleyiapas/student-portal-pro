package com.prozer.studentportalpro.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.prozer.studentportalpro.R;
import com.prozer.studentportalpro.database.DatabaseHelper;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class DashboardActivity extends AppCompatActivity {

    private DatabaseHelper db;

    private TextView txtStudentCount;
    private TextView txtCampusCount;
    private TextView txtAttendanceCount;
    private TextView txtStatus;

    private TextView txtGreeting;
    private TextView txtDate;
    private TextView txtTime;
    private TextView txtWelcome;
    private TextView txtQuote;

    private TextView txtOverview;
    private TextView txtOverview2;

    private final Handler handler = new Handler();

    private final String[] quotes = {
            "Empowering Education Through Technology",
            "Learning Today, Leading Tomorrow",
            "Innovation Begins With Knowledge",
            "Success Starts With Discipline",
            "Education is the Passport to the Future",
            "Dream Big. Learn More. Achieve Greater.",
            "Technology Shapes Tomorrow's Leaders"
    };

    private int quoteIndex = 0;

    private final Runnable runnable = new Runnable() {
        @Override
        public void run() {
            updateDateTime();
            handler.postDelayed(this, 1000);
        }
    };

    private final Runnable quoteRunnable = new Runnable() {
        @Override
        public void run() {
            txtQuote.setText(quotes[quoteIndex]);
            quoteIndex++;
            if (quoteIndex >= quotes.length)
                quoteIndex = 0;
            handler.postDelayed(this, 6000);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_dashboard);

        db = new DatabaseHelper(this);

        // Header Views
        txtGreeting = findViewById(R.id.txtGreeting);
        txtDate = findViewById(R.id.txtDate);
        txtTime = findViewById(R.id.txtTime);
        txtWelcome = findViewById(R.id.txtWelcome);
        txtQuote = findViewById(R.id.txtQuote);

        // Stats Views
        txtStudentCount = findViewById(R.id.txtStudentCount);
        txtCampusCount = findViewById(R.id.txtCampusCount);
        txtAttendanceCount = findViewById(R.id.txtAttendanceCount);
        txtStatus = findViewById(R.id.txtStatus);

        txtOverview = findViewById(R.id.txtOverview);
        txtOverview2 = findViewById(R.id.txtOverview2);

        updateGreeting();
        handler.post(runnable);
        handler.post(quoteRunnable);
        loadDashboardStatistics();

        // ==========================
        // CAMPUS SERVICES
        // ==========================

        // Profile
        findViewById(R.id.btnProfile).setOnClickListener(v -> {
            animateCard(v);
            startActivity(new Intent(DashboardActivity.this, ProfileActivity.class));
        });

        // Results
        findViewById(R.id.btnResults).setOnClickListener(v -> {
            animateCard(v);
            startActivity(new Intent(DashboardActivity.this, ResultActivity.class));
        });

        // GPA Calculator
        findViewById(R.id.btnGpa).setOnClickListener(v -> {
            animateCard(v);
            startActivity(new Intent(DashboardActivity.this, GpacalculatorActivity.class));
        });

        // Timetable
        findViewById(R.id.btnTimetable).setOnClickListener(v -> {
            animateCard(v);
            startActivity(new Intent(DashboardActivity.this, TimetableActivity.class));
        });

        // Assignments
        findViewById(R.id.btnAssignments).setOnClickListener(v -> {
            animateCard(v);
            startActivity(new Intent(DashboardActivity.this, AssignmentActivity.class));
        });

        // Fees
        findViewById(R.id.btnFees).setOnClickListener(v -> {
            animateCard(v);
            startActivity(new Intent(DashboardActivity.this, FeesActivity.class));
        });

        // Notifications
        findViewById(R.id.btnNotifications).setOnClickListener(v -> {
            animateCard(v);
            startActivity(new Intent(DashboardActivity.this, NotificationActivity.class));
        });

        // Student ID Card
        findViewById(R.id.btnIdCard).setOnClickListener(v -> {
            animateCard(v);
            startActivity(new Intent(DashboardActivity.this, StudentIdCardActivity.class));
        });

        // ==========================
        // ADMINISTRATION
        // ==========================

        // Attendance
        findViewById(R.id.btnAttendance).setOnClickListener(v -> {
            animateCard(v);
            startActivity(new Intent(DashboardActivity.this, AttendanceActivity.class));
        });

        // Add Student
        findViewById(R.id.btnAddStudent).setOnClickListener(v -> {
            animateCard(v);
            startActivity(new Intent(DashboardActivity.this, AddStudentActivity.class));
        });

        // View Students
        findViewById(R.id.btnViewStudents).setOnClickListener(v -> {
            animateCard(v);
            startActivity(new Intent(DashboardActivity.this, ViewStudentsActivity.class));
        });

        // Student Records
        findViewById(R.id.btnStudentRecords).setOnClickListener(v -> {
            animateCard(v);
            startActivity(new Intent(DashboardActivity.this, StudentRecordsActivity.class));
        });

        // Campus News
        findViewById(R.id.btnNews).setOnClickListener(v -> {
            animateCard(v);
            startActivity(new Intent(DashboardActivity.this, NewsActivity.class));
        });

        // Settings
        findViewById(R.id.btnSettings).setOnClickListener(v -> {
            animateCard(v);
            startActivity(new Intent(DashboardActivity.this, SettingsActivity.class));
        });

        // Online Directory(API)
        findViewById(R.id.btnApiUsers).setOnClickListener(v -> {
            animateCard(v);
            startActivity(new Intent(DashboardActivity.this, ApiUsersActivity.class));
        });

        // ==========================
        // WEEK 9 FEATURE
        // ==========================

        // Smart Campus Explorer
        findViewById(R.id.btnCampusExplorer).setOnClickListener(v -> {
            animateCard(v);
            startActivity(new Intent(DashboardActivity.this, CampusExplorerActivity.class));
        });

    }

    private void updateGreeting() {

        int hour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);

        if (hour < 12) {
            txtGreeting.setText("Good Morning 👋");
        } else if (hour < 17) {
            txtGreeting.setText("Good Afternoon ☀");
        } else {
            txtGreeting.setText("Good Evening 🌙");
        }

        txtWelcome.setText("Welcome to Student Portal Pro");
        txtQuote.setText(quotes[0]);

    }

    private void updateDateTime() {

        Date date = new Date();

        SimpleDateFormat dateFormat =
                new SimpleDateFormat(
                        "EEEE, dd MMMM yyyy",
                        Locale.getDefault());

        SimpleDateFormat timeFormat =
                new SimpleDateFormat(
                        "hh:mm:ss a",
                        Locale.getDefault());

        txtDate.setText(dateFormat.format(date));

        txtTime.setText(timeFormat.format(date));

    }

    private void loadDashboardStatistics() {

        animateNumber(txtStudentCount, db.getStudentCount());

        int attendance = db.getPresentCount() + db.getAbsentCount();
        animateNumber(txtAttendanceCount, attendance);

        animateNumber(txtCampusCount, db.getCampusLocations().size());

        txtStatus.setText("System Online");

        txtOverview.setText("Welcome to Student Portal Pro");

        txtOverview2.setText(
                "Students: "
                        + db.getStudentCount()
                        + "   |   Campus Locations: "
                        + db.getCampusLocations().size()
                        + "   |   Status: Online");

    }

    private void animateNumber(TextView textView, int finalValue) {
        new Thread(() -> {
            for (int i = 0; i <= finalValue; i++) {
                int value = i;
                runOnUiThread(() -> textView.setText(String.valueOf(value)));
                try {
                    Thread.sleep(40);
                } catch (InterruptedException ignored) {
                }
            }
        }).start();
    }

    private void animateCard(View view) {
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.card_click);
        view.startAnimation(animation);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        handler.removeCallbacks(runnable);
        handler.removeCallbacks(quoteRunnable);
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadDashboardStatistics();
    }
}