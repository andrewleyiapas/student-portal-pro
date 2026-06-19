package com.prozer.studentportalpro.activities;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.prozer.studentportalpro.R;
import com.prozer.studentportalpro.database.DatabaseHelper;

public class AttendanceActivity
        extends AppCompatActivity {

    EditText etName;
    EditText etCourse;
    Spinner spStatus;
    Button btnSave;

    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(
            Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(
                R.layout.activity_attendance);

        etName =
                findViewById(R.id.etName);

        etCourse =
                findViewById(R.id.etCourse);

        spStatus =
                findViewById(R.id.spStatus);

        btnSave =
                findViewById(
                        R.id.btnSaveAttendance);

        databaseHelper =
                new DatabaseHelper(this);

        String[] status = {
                "Present",
                "Absent"
        };

        ArrayAdapter<String> adapter =
                new ArrayAdapter<>(
                        this,
                        android.R.layout.simple_spinner_item,
                        status);

        spStatus.setAdapter(adapter);

        btnSave.setOnClickListener(v -> {

            boolean inserted =
                    databaseHelper.addAttendance(

                            etName.getText()
                                    .toString(),

                            etCourse.getText()
                                    .toString(),

                            spStatus.getSelectedItem()
                                    .toString()
                    );

            if (inserted) {

                Toast.makeText(
                        this,
                        "Attendance Saved",
                        Toast.LENGTH_SHORT
                ).show();

            } else {

                Toast.makeText(
                        this,
                        "Failed",
                        Toast.LENGTH_SHORT
                ).show();
            }
        });
    }
}