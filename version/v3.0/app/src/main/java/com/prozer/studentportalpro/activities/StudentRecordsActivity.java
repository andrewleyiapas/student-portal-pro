package com.prozer.studentportalpro.activities;

import android.database.Cursor;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.prozer.studentportalpro.R;
import com.prozer.studentportalpro.database.DatabaseHelper;

public class StudentRecordsActivity extends AppCompatActivity {

    private DatabaseHelper databaseHelper;
    private TextView txtStudents;
    private TextView txtCount;
    private EditText etSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_records);

        txtStudents = findViewById(R.id.txtStudents);
        txtCount = findViewById(R.id.txtCount);
        etSearch = findViewById(R.id.etSearch);

        databaseHelper = new DatabaseHelper(this);

        etSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                searchStudents(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });

        loadStudents();
    }

    private void searchStudents(String keyword) {
        Cursor cursor = databaseHelper.searchStudents(keyword);
        displayData(cursor);
    }

    private void loadStudents() {
        Cursor cursor = databaseHelper.getAllStudentsCursor();
        displayData(cursor);
        txtCount.setText("Total Students: " + databaseHelper.getStudentCount());
    }

    private void displayData(Cursor cursor) {
        StringBuilder builder = new StringBuilder();
        int andrewCount = 0;

        while (cursor.moveToNext()) {
            String name = cursor.getString(1);
            String regNo = cursor.getString(5);
            String course = cursor.getString(4);

            // Manual override for specific records as requested
            if (name.equalsIgnoreCase("Celestine Gitau") || name.equalsIgnoreCase("Celestine")) {
                name = "Pauline";
                regNo = "BIT/2024/001";
            } else if (name.equalsIgnoreCase("Andrew Leyiapas")) {
                andrewCount++;
                if (andrewCount == 1) {
                    name = "John Peter";
                    regNo = "BDS/2024/088";
                } else if (andrewCount == 2) {
                    name = "Paul";
                    regNo = "BSE/2024/015";
                }
            }

            builder.append("NAME: ").append(name.toUpperCase()).append("\n");
            builder.append("REG NO: ").append(regNo).append("\n");
            builder.append("COURSE: ").append(course).append("\n");
            builder.append("----------------------------------\n\n");
        }

        if (builder.length() == 0) {
            txtStudents.setText("No records found.");
        } else {
            txtStudents.setText(builder.toString());
        }
        cursor.close();
    }
}
