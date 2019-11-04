
package com.indigo.newsapp.core;

import com.indigo.newsapp.core.remote.ResponseHandler;
import com.indigo.newsapp.model.NewsModel;


public interface DataConnector {
    void getNewsList(int pageSize, int page,String category, ResponseHandler<NewsModel> responseHandler);
}