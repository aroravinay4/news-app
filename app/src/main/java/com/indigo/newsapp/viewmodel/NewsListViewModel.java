package com.indigo.newsapp.viewmodel;

import androidx.databinding.ObservableInt;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PageKeyedDataSource;
import androidx.paging.PagedList;

import com.indigo.newsapp.pagination.ItemDataSource;
import com.indigo.newsapp.pagination.ItemDataSourceFactory;
import com.indigo.newsapp.model.Article;


public class NewsListViewModel extends ViewModel {

    public ObservableInt loading;
    public ObservableInt showEmpty;

    public LiveData<PagedList<Article>> itemPagedList;
    public LiveData<PageKeyedDataSource<Integer, Article>> liveDataSource;

    public void fetchList(String category) {

        ItemDataSourceFactory itemDataSourceFactory = new ItemDataSourceFactory(category);
        liveDataSource = itemDataSourceFactory.getItemLiveDataSource();

        PagedList.Config config =
                (new PagedList.Config.Builder())
                        .setEnablePlaceholders(false)
                        .setPageSize(ItemDataSource.PAGE_SIZE)
                        .build();

        itemPagedList = (new LivePagedListBuilder(itemDataSourceFactory, config)).build();
    }

}
