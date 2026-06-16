package com.prozer.studentportalpro.api;

import com.prozer.studentportalpro.models.News;
import com.prozer.studentportalpro.models.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {

    @GET("users")
    Call<List<User>> getUsers();

    @GET("posts")
    Call<List<News>> getNews();
}