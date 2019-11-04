package com.indigo.newsapp.core.remote;



import okhttp3.OkHttpClient;

import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class ServiceGenerator {
    private static Retrofit retrofit;
    private static Retrofit.Builder restBuilder;

    // No need to instantiate this class.
    private ServiceGenerator() {
    }

    public static CloudService getRestService(String newApiBaseUrl) {

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        httpClient.addInterceptor(logging);

        restBuilder = new Retrofit.Builder()
                .baseUrl(newApiBaseUrl)
                .client(httpClient.build())
                .addConverterFactory(GsonConverterFactory.create());

        retrofit = restBuilder.build();
        return retrofit.create(CloudService.class);
    }

}