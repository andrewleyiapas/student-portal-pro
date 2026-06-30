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

public class AttendanceActivity extends AppCompatActivity {

    EditText etName;
    EditText etCourse;
    Spinner spStatus;
    Button btnSaveAttendance;

    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendance);

        etName = findViewById(R.id.etName);
        etCourse = findViewById(R.id.etCourse);
        spStatus = findViewById(R.id.spStatus);
        btnSaveAttendance = findViewById(R.id.btnSaveAttendance);

        databaseHelper = new DatabaseHelper(this);

        String[] status = {
                "Present",
                "Absent"
        };

        ArrayAdapter<String> adapter =
                new ArrayAdapter<>(
                        this,
                        android.R.layout.simple_spinner_item,
                        status);

        adapter.setDropDownViewResource(
                android.R.layout.simple_spinner_dropdown_item);

        spStatus.setAdapter(adapter);

        btnSaveAttendance.setOnClickListener(v -> {

            String name =
                    etName.getText().toString().trim();

            String course =
                    etCourse.getText().toString().trim();

            String attendanceStatus =
                    spStatus.getSelectedItem().toString();

            if(name.isEmpty() || course.isEmpty()) {

                Toast.makeText(
                        this,
                        "Fill all fields",
                        Toast.LENGTH_SHORT
                ).show();

                return;
            }

            boolean result =
                    databaseHelper.addAttendance(
                            name,
                            course,
                            attendanceStatus);

            if(result) {

                Toast.makeText(
                        this,
                        "Attendance Saved Successfully",
                        Toast.LENGTH_SHORT
                ).show();

                etName.setText("");
                etCourse.setText("");

            } else {

                Toast.makeText(
                        this,
                        "Failed To Save",
                        Toast.LENGTH_SHORT
                ).show();
            }
        });
    }
}