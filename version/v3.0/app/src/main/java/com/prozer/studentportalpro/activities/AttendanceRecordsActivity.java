package com.prozer.studentportalpro.activities;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.prozer.studentportalpro.R;
import com.prozer.studentportalpro.database.DatabaseHelper;

import java.util.ArrayList;

public class AttendanceRecordsActivity
        extends AppCompatActivity {

    ListView listAttendance;

    @Override
    protected void onCreate(
            Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(
                R.layout.activity_attendance_records);

        listAttendance =
                findViewById(R.id.listAttendance);

        DatabaseHelper db =
                new DatabaseHelper(this);

        Cursor cursor =
                db.getAllAttendance();

        ArrayList<String> records =
                new ArrayList<>();

        while(cursor.moveToNext()) {

            records.add(
                    cursor.getString(1)
                            + " - "
                            + cursor.getString(2)
                            + " - "
                            + cursor.getString(3));
        }

        ArrayAdapter<String> adapter =
                new ArrayAdapter<>(
                        this,
                        android.R.layout.simple_list_item_1,
                        records);

        listAttendance.setAdapter(adapter);
    }
}