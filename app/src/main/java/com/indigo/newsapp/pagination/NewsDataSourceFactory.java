package com.indigo.newsapp.pagination;


import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;

public class NewsDataSourceFactory extends DataSource.Factory {

    private MutableLiveData<NewsDataSource> newsLiveDataSource = new MutableLiveData<NewsDataSource>();

    private String category;
    public NewsDataSourceFactory(String category){
        this.category=category;
    }
    @Override
    public DataSource create() {
        NewsDataSource itemDataSource = new NewsDataSource(category);
        newsLiveDataSource.postValue(itemDataSource);
        return itemDataSource;
    }

    public MutableLiveData<NewsDataSource> getItemLiveDataSource() {
        return newsLiveDataSource;
    }
}
