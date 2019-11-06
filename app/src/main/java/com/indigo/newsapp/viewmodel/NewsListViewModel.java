package com.indigo.newsapp.viewmodel;

import androidx.databinding.ObservableInt;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PageKeyedDataSource;
import androidx.paging.PagedList;

import com.indigo.newsapp.pagination.NewsDataSourceFactory;
import com.indigo.newsapp.model.Article;
import com.indigo.newsapp.utils.NetworkState;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import static com.indigo.newsapp.utils.Constants.PAGE_SIZE;


public class NewsListViewModel extends ViewModel {
    private LiveData<NetworkState> networkStateLiveData;
    private LiveData<PagedList<Article>> newsLiveData;
    private Executor executor;
    private NewsDataSourceFactory newsDataSourceFactory;

    public void fetchList(String category) {
        executor = Executors.newFixedThreadPool(5);
        newsDataSourceFactory = new NewsDataSourceFactory(category);
        networkStateLiveData = Transformations.switchMap(newsDataSourceFactory.getItemLiveDataSource(), dataSource ->
                dataSource.getNetWorkState());

        PagedList.Config pagedListConfig =
                (new PagedList.Config.Builder())
                        .setEnablePlaceholders(false)
                        .setPageSize(PAGE_SIZE).build();

        newsLiveData = (new LivePagedListBuilder(newsDataSourceFactory, pagedListConfig))
                .setFetchExecutor(executor)
                .build();
    }


    public LiveData<NetworkState> getNetworkStateLiveData() {
        return networkStateLiveData;
    }

    public LiveData<PagedList<Article>> getNewsLiveData() {
        return newsLiveData;
    }
}
