package com.prozer.studentportalpro.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.prozer.studentportalpro.R;
import com.prozer.studentportalpro.adapters.NewsAdapter;
import com.prozer.studentportalpro.models.News;

import java.util.ArrayList;
import java.util.List;

public class NewsActivity extends AppCompatActivity {

    private ProgressBar progressBar;
    private RecyclerView recyclerNews;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);

        progressBar = findViewById(R.id.progressBar);
        recyclerNews = findViewById(R.id.recyclerNews);

        recyclerNews.setLayoutManager(new LinearLayoutManager(this));
        
        loadEnglishNews();
    }

    private void loadEnglishNews() {
        progressBar.setVisibility(View.VISIBLE);

        // High Quality English News for a professional, marketable appearance
        List<News> newsList = new ArrayList<>();
        
        newsList.add(new News("Semester Registration Open", "All students are advised that the registration for the upcoming semester is now open. Please complete your registration through the student portal before the deadline."));
        newsList.add(new News("Examination Timetable Released", "The official examination timetable for the current session has been released. Students can download their specific schedules from the downloads section."));
        newsList.add(new News("New Library Resources", "The University Library has added over 500 new e-books and research journals to the digital repository. Access them using your student credentials."));
        newsList.add(new News("Campus Career Fair 2024", "Join us this Friday for the annual Career Fair. Over 20 leading companies will be on campus for recruitment and internship interviews."));
        newsList.add(new News("Inter-University Sports Competition", "Our university teams will be participating in the regional sports competition next week. Come out and support our athletes in soccer, basketball and rugby."));

        NewsAdapter adapter = new NewsAdapter(newsList);
        recyclerNews.setAdapter(adapter);
        
        progressBar.setVisibility(View.GONE);
    }
}
