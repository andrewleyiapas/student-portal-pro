package com.prozer.studentportalpro.activities;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.prozer.studentportalpro.R;
import com.prozer.studentportalpro.adapters.CampusAdapter;
import com.prozer.studentportalpro.database.DatabaseHelper;

public class CampusHistoryActivity extends AppCompatActivity {

    RecyclerView recyclerView;

    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_campus_history);

        recyclerView = findViewById(R.id.recyclerCampus);

        recyclerView.setLayoutManager(
                new LinearLayoutManager(this));

        databaseHelper = new DatabaseHelper(this);

        CampusAdapter adapter = new CampusAdapter(

                databaseHelper.getCampusLocations()

        );

        recyclerView.setAdapter(adapter);

    }

}