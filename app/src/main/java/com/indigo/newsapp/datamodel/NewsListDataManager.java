package com.indigo.newsapp.datamodel;

import com.indigo.newsapp.core.DataConnector;
import com.indigo.newsapp.core.DataConnectorFactory;
import com.indigo.newsapp.core.remote.ResponseHandler;
import com.indigo.newsapp.model.NewsModel;
import com.indigo.newsapp.utils.Constants;


public class NewsListDataManager {
    private DataConnector cloudConnector;

    public NewsListDataManager(DataConnectorFactory dataConnectorFactory) {
        cloudConnector = dataConnectorFactory.createCloudConnector(Constants.BASE, DataConnectorFactory.REST_REQUEST);
    }

    public void getNewsList(int pageSize, int page,String category,final ResponseHandler<NewsModel> responseHandler) {
        cloudConnector.getNewsList(pageSize,page,category, responseHandler);
    }
}