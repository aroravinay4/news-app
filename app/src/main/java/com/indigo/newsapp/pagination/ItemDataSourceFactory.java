package com.indigo.newsapp.pagination;


import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;
import androidx.paging.PageKeyedDataSource;

import com.indigo.newsapp.model.Article;

public class ItemDataSourceFactory extends DataSource.Factory {

    private MutableLiveData<PageKeyedDataSource<Integer, Article>> itemLiveDataSource = new MutableLiveData<>();

    private String category;
    public ItemDataSourceFactory(String catry){
        this.category=catry;
    }
    @Override
    public DataSource create() {
        ItemDataSource itemDataSource = new ItemDataSource(category);
        itemLiveDataSource.postValue(itemDataSource);
        return itemDataSource;
    }

    public MutableLiveData<PageKeyedDataSource<Integer, Article>> getItemLiveDataSource() {
        return itemLiveDataSource;
    }
}
