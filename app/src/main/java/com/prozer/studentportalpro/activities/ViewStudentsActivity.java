package com.prozer.studentportalpro.activities;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.prozer.studentportalpro.R;
import com.prozer.studentportalpro.adapters.StudentAdapter;
import com.prozer.studentportalpro.database.DatabaseHelper;
import com.prozer.studentportalpro.models.Student;

import java.util.ArrayList;
import java.util.List;

public class ViewStudentsActivity extends AppCompatActivity implements StudentAdapter.OnStudentActionListener {

    RecyclerView recyclerView;
    EditText edtSearch;
    TextView txtEmpty;

    DatabaseHelper db;
    StudentAdapter adapter;

    List<Student> studentList;
    List<Student> filteredList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_students);

        recyclerView = findViewById(R.id.recyclerStudents);
        edtSearch = findViewById(R.id.edtSearch);
        txtEmpty = findViewById(R.id.txtEmpty);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        db = new DatabaseHelper(this);

        loadData();

        edtSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                filter(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });
    }

    private void loadData() {
        studentList = db.getAllStudents();
        filteredList = new ArrayList<>(studentList);

        adapter = new StudentAdapter(filteredList, this);
        recyclerView.setAdapter(adapter);

        checkEmptyState();
    }

    private void filter(String text) {
        filteredList.clear();

        if (text.trim().isEmpty()) {
            filteredList.addAll(studentList);
        } else {
            for (Student student : studentList) {
                if (student.getName().toLowerCase().contains(text.toLowerCase()) ||
                        student.getRegNo().toLowerCase().contains(text.toLowerCase())) {
                    filteredList.add(student);
                }
            }
        }

        adapter.notifyDataSetChanged();
        checkEmptyState();
    }

    private void checkEmptyState() {
        if (filteredList.isEmpty()) {
            txtEmpty.setVisibility(TextView.VISIBLE);
            recyclerView.setVisibility(RecyclerView.GONE);
        } else {
            txtEmpty.setVisibility(TextView.GONE);
            recyclerView.setVisibility(RecyclerView.VISIBLE);
        }
    }

    @Override
    public void onStudentDelete(Student student, int position) {
        new AlertDialog.Builder(this)
                .setTitle("Delete Student")
                .setMessage("Are you sure you want to delete " + student.getName() + "?")
                .setPositiveButton("Yes", (dialog, which) -> {
                    if (db.deleteStudent(student.getId())) {
                        Toast.makeText(this, "Student deleted", Toast.LENGTH_SHORT).show();
                        loadData(); // Refresh list
                    } else {
                        Toast.makeText(this, "Failed to delete student", Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton("No", null)
                .show();
    }

    @Override
    public void onStudentUpdate(Student student, int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Update Student");

        View view = LayoutInflater.from(this).inflate(R.layout.activity_add_student, null);
        builder.setView(view);

        EditText edtName = view.findViewById(R.id.edtName);
        EditText edtEmail = view.findViewById(R.id.edtEmail);
        EditText edtCourse = view.findViewById(R.id.edtCourse);
        EditText edtRegNo = view.findViewById(R.id.edtRegNo);
        view.findViewById(R.id.btnSave).setVisibility(View.GONE);

        // Pre-fill data
        edtName.setText(student.getName());
        edtEmail.setText(student.getEmail());
        edtCourse.setText(student.getCourse());
        edtRegNo.setText(student.getRegNo());

        builder.setPositiveButton("Update", (dialog, which) -> {
            String name = edtName.getText().toString().trim();
            String email = edtEmail.getText().toString().trim();
            String course = edtCourse.getText().toString().trim();
            String regNo = edtRegNo.getText().toString().trim();

            if (db.updateStudent(student.getId(), name, email, course, regNo)) {
                Toast.makeText(this, "Student updated successfully", Toast.LENGTH_SHORT).show();
                loadData();
            } else {
                Toast.makeText(this, "Failed to update student", Toast.LENGTH_SHORT).show();
            }
        });

        builder.setNegativeButton("Cancel", null);
        builder.show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadData();
    }
}
