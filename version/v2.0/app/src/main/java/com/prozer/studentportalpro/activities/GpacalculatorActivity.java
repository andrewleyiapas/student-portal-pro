package com.prozer.studentportalpro.activities;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.prozer.studentportalpro.R;

public class GpacalculatorActivity extends AppCompatActivity {

    private EditText etCat, etExam;
    private Button btnCalculate;
    private TextView txtResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_gpacalculator);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        etCat = findViewById(R.id.etCat);
        etExam = findViewById(R.id.etExam);
        btnCalculate = findViewById(R.id.btnCalculate);
        txtResult = findViewById(R.id.txtResult);

        btnCalculate.setOnClickListener(v -> calculateGpa());
    }

    private void calculateGpa() {
        String catStr = etCat.getText().toString();
        String examStr = etExam.getText().toString();

        if (catStr.isEmpty() || examStr.isEmpty()) {
            Toast.makeText(this, "Please enter both marks", Toast.LENGTH_SHORT).show();
            return;
        }

        try {
            double catMarks = Double.parseDouble(catStr);
            double examMarks = Double.parseDouble(examStr);

            if (catMarks > 30 || examMarks > 70) {
                Toast.makeText(this, "CAT marks <= 30 and Exam marks <= 70", Toast.LENGTH_LONG).show();
                return;
            }

            double total = catMarks + examMarks;
            double gpa = (total / 100.0) * 4.0;

            txtResult.setText(String.format("Total Marks: %.2f\nGPA: %.2f", total, gpa));

        } catch (NumberFormatException e) {
            Toast.makeText(this, "Invalid input", Toast.LENGTH_SHORT).show();
        }
    }
}