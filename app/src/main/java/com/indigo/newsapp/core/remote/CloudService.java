package com.indigo.newsapp.core.remote;


import com.indigo.newsapp.model.NewsModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface CloudService {

    @GET("top-headlines?")
    Call<NewsModel> getNewsList(
            @Query("pageSize") int pageSize,
            @Query("page") int page,
            @Query("country") String country,
            @Query("category") String category,
            @Query("apiKey") String apiKey


    );



}