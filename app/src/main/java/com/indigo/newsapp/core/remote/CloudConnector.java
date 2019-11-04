package com.indigo.newsapp.core.remote;


import com.indigo.newsapp.core.DataConnector;
import com.indigo.newsapp.model.NewsModel;
import com.indigo.newsapp.utils.Constants;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;



public class CloudConnector implements DataConnector {

    private static final String TAG = CloudConnector.class.getName();
    private CloudService mServiceEndpoint;

    public CloudConnector(CloudService service) {
        this.mServiceEndpoint = service;
    }



    @Override
    public void getNewsList(int pageSize, int page,String category,final ResponseHandler<NewsModel> responseHandler) {
        mServiceEndpoint.getNewsList(pageSize,page, Constants.COUNTRY,category,Constants.API_KEY).enqueue(new Callback<NewsModel>() {
            @Override
            public void onResponse(Call<NewsModel> call, Response<NewsModel> response) {
                if (response != null && response.isSuccessful()) {
                    responseHandler.onRequestSuccess(response.body());
                } else {
                    responseHandler.onRequestFailure();
                }
            }

            @Override
            public void onFailure(Call<NewsModel> call, Throwable t) {
                responseHandler.onRequestFailure();
            }
        });
    }


}