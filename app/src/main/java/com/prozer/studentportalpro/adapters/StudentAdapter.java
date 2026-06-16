package com.prozer.studentportalpro.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.prozer.studentportalpro.R;
import com.prozer.studentportalpro.models.Student;

import java.util.List;

public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.ViewHolder> {

    List<Student> studentList;
    private OnStudentActionListener listener;

    public interface OnStudentActionListener {
        void onStudentDelete(Student student, int position);
        void onStudentUpdate(Student student, int position);
    }

    public StudentAdapter(List<Student> studentList, OnStudentActionListener listener) {
        this.studentList = studentList;
        this.listener = listener;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView name, regNo, course, email, btnDelete, btnUpdate;

        public ViewHolder(View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.txtName);
            regNo = itemView.findViewById(R.id.txtRegNo);
            course = itemView.findViewById(R.id.txtCourse);
            email = itemView.findViewById(R.id.txtEmail);
            btnDelete = itemView.findViewById(R.id.btnDelete);
            btnUpdate = itemView.findViewById(R.id.btnUpdate);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.student_item, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        Student student = studentList.get(position);

        holder.name.setText(student.getName());
        holder.regNo.setText(student.getRegNo());
        holder.course.setText(student.getCourse());
        holder.email.setText(student.getEmail());

        holder.btnDelete.setOnClickListener(v -> {
            if (listener != null) {
                listener.onStudentDelete(student, position);
            }
        });

        holder.btnUpdate.setOnClickListener(v -> {
            if (listener != null) {
                listener.onStudentUpdate(student, position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return studentList.size();
    }
}
