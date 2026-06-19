package com.prozer.studentportalpro.activities;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.prozer.studentportalpro.database.DatabaseHelper;
import com.prozer.studentportalpro.R;

public class AddStudentActivity extends AppCompatActivity {

    DatabaseHelper db;
    EditText edtName, edtEmail, edtCourse, edtRegNo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_student);

        db = new DatabaseHelper(this);

        edtName = findViewById(R.id.edtName);
        edtEmail = findViewById(R.id.edtEmail);
        edtCourse = findViewById(R.id.edtCourse);
        edtRegNo = findViewById(R.id.edtRegNo);

        findViewById(R.id.btnSave).setOnClickListener(v -> {

            String name = edtName.getText().toString().trim();
            String email = edtEmail.getText().toString().trim();
            String course = edtCourse.getText().toString().trim();
            String regNo = edtRegNo.getText().toString().trim();

            if (name.isEmpty() || email.isEmpty() || course.isEmpty() || regNo.isEmpty()) {
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                return;
            }

            // Using helper method from DatabaseHelper for clean code and database safety
            boolean success = db.insertStudent(name, email, course, regNo);

            if (success) {
                Toast.makeText(this, "Student Added Successfully", Toast.LENGTH_SHORT).show();
                finish();
            } else {
                Toast.makeText(this, "Failed to Add Student", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
