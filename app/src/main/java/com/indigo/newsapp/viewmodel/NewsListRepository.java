package com.indigo.newsapp.viewmodel;

import androidx.databinding.BaseObservable;
import androidx.lifecycle.MutableLiveData;

import com.indigo.newsapp.core.DataConnectorFactory;
import com.indigo.newsapp.core.remote.ResponseHandler;
import com.indigo.newsapp.datamodel.NewsListDataManager;
import com.indigo.newsapp.model.Article;
import com.indigo.newsapp.model.NewsModel;

import java.util.ArrayList;
import java.util.List;

public class NewsListRepository {

    private NewsListDataManager productSearchDataManager;

    public void fetchList(String category, int pageSize, int currentPage, final ResponseHandler<NewsModel> responseHandler) {

        DataConnectorFactory pdaDataConnectorFactory = new DataConnectorFactory();
        productSearchDataManager = new NewsListDataManager(pdaDataConnectorFactory);

        productSearchDataManager.getNewsList(pageSize, currentPage, category, new ResponseHandler<NewsModel>() {
            @Override
            public void onRequestFailure(String errorMessage) {
                responseHandler.onRequestFailure(errorMessage);
            }

            @Override
            public void onRequestSuccess(NewsModel model) {
                responseHandler.onRequestSuccess(model);
            }
        });

    }


}
