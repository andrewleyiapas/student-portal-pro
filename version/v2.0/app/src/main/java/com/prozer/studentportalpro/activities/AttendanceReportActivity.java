package com.prozer.studentportalpro.activities;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.prozer.studentportalpro.R;
import com.prozer.studentportalpro.database.DatabaseHelper;

public class AttendanceReportActivity
        extends AppCompatActivity {

    @Override
    protected void onCreate(
            Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(
                R.layout.activity_attendance_report);

        TextView present =
                findViewById(R.id.txtPresent);

        TextView absent =
                findViewById(R.id.txtAbsent);

        DatabaseHelper db =
                new DatabaseHelper(this);

        present.setText(
                "Present: "
                        + db.getPresentCount());

        absent.setText(
                "Absent: "
                        + db.getAbsentCount());
    }
}