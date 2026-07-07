package com.prozer.studentportalpro.activities;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.prozer.studentportalpro.R;

public class AssignmentActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assignment);

        Spinner spinner = findViewById(R.id.spinnerSubjects);
        String[] subjects = {"Mobile App Dev", "Database Systems", "Internet Programming", "Discrete Mathematics"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, subjects);
        spinner.setAdapter(adapter);

        findViewById(R.id.btnChooseFile).setOnClickListener(v -> 
            Toast.makeText(this, "File Picker opened", Toast.LENGTH_SHORT).show()
        );

        findViewById(R.id.btnUpload).setOnClickListener(v -> 
            Toast.makeText(this, "Assignment Submitted Successfully!", Toast.LENGTH_LONG).show()
        );

        findViewById(R.id.assignmentBackBtn).setOnClickListener(v -> finish());
    }
}