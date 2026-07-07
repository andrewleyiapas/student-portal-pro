package com.prozer.studentportalpro.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.prozer.studentportalpro.R;
import com.prozer.studentportalpro.database.DatabaseHelper;
import com.prozer.studentportalpro.utils.NotificationHelper;

public class AddStudentActivity extends AppCompatActivity {

    EditText edtName, edtEmail, edtCourse, edtRegNo;
    Button btnSave;
    ProgressBar progressBar;
    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_student);

        edtName = findViewById(R.id.edtName);
        edtEmail = findViewById(R.id.edtEmail);
        edtCourse = findViewById(R.id.edtCourse);
        edtRegNo = findViewById(R.id.edtRegNo);
        btnSave = findViewById(R.id.btnSave);
        progressBar = findViewById(R.id.progressBar);

        db = new DatabaseHelper(this);

        btnSave.setOnClickListener(v -> saveStudent());
    }

    private void saveStudent() {

        String name = edtName.getText().toString().trim();
        String email = edtEmail.getText().toString().trim();
        String course = edtCourse.getText().toString().trim();
        String regNo = edtRegNo.getText().toString().trim();

        if (name.isEmpty()) {
            edtName.setError("Enter student name");
            edtName.requestFocus();
            return;
        }

        if (email.isEmpty()) {
            edtEmail.setError("Enter email");
            edtEmail.requestFocus();
            return;
        }

        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            edtEmail.setError("Invalid Email");
            edtEmail.requestFocus();
            return;
        }

        if (course.isEmpty()) {
            edtCourse.setError("Enter Course");
            edtCourse.requestFocus();
            return;
        }

        if (regNo.isEmpty()) {
            edtRegNo.setError("Enter Registration Number");
            edtRegNo.requestFocus();
            return;
        }

        if (db.isRegNoExists(regNo)) {
            edtRegNo.setError("Registration Number already exists");
            edtRegNo.requestFocus();
            return;
        }

        progressBar.setVisibility(View.VISIBLE);

        boolean saved = db.insertStudent(name, email, course, regNo);

        progressBar.setVisibility(View.GONE);

        if (saved) {

            Toast.makeText(
                    this,
                    "Student Registered Successfully",
                    Toast.LENGTH_SHORT
            ).show();

            NotificationHelper.showNotification(
                    this,
                    "Student Registration",
                    name + " has been successfully registered."
            );

            new androidx.appcompat.app.AlertDialog.Builder(this)
                    .setTitle("Success")
                    .setMessage("Student registered successfully.")
                    .setIcon(android.R.drawable.checkbox_on_background)
                    .setPositiveButton("OK", (dialogInterface, which) -> {

                        edtName.setText("");
                        edtEmail.setText("");
                        edtCourse.setText("");
                        edtRegNo.setText("");

                        finish();

                    })
                    .show();

        } else {

            new androidx.appcompat.app.AlertDialog.Builder(this)
                    .setTitle("Registration Failed")
                    .setMessage("Unable to save student. Please try again.")
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .setPositiveButton("OK", null)
                    .show();

        }

    }

}