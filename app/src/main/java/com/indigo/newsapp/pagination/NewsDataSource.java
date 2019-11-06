package com.indigo.newsapp.pagination;


import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.PageKeyedDataSource;

import com.indigo.newsapp.core.remote.ResponseHandler;
import com.indigo.newsapp.model.Article;
import com.indigo.newsapp.model.NewsModel;
import com.indigo.newsapp.utils.NetworkState;
import com.indigo.newsapp.viewmodel.NewsListRepository;

import static com.indigo.newsapp.utils.Constants.FIRST_PAGE;
import static com.indigo.newsapp.utils.Constants.PAGE_SIZE;

public class NewsDataSource extends PageKeyedDataSource<Integer, Article> {
    private NewsListRepository repository;
    private String category;
    private MutableLiveData netWorkState;
    private MutableLiveData initialLoading;


    public MutableLiveData getNetWorkState() {
        return netWorkState;
    }

    public MutableLiveData getInitialLoading() {
        return initialLoading;
    }

    public NewsDataSource(String category) {
        repository = new NewsListRepository();
        netWorkState = new MutableLiveData();
        initialLoading = new MutableLiveData();
        this.category = category;
    }

    @Override
    public void loadInitial(@NonNull LoadInitialParams<Integer> params, @NonNull final LoadInitialCallback<Integer, Article> callback) {
        initialLoading.postValue(NetworkState.LOADING);
        netWorkState.postValue(NetworkState.LOADING);
        repository.fetchList(category, PAGE_SIZE, FIRST_PAGE, new ResponseHandler<NewsModel>() {
            @Override
            public void onRequestFailure(String errorMessage) {
                initialLoading.postValue(new NetworkState(NetworkState.Status.FAILED, errorMessage));
                netWorkState.postValue(new NetworkState(NetworkState.Status.FAILED, errorMessage));
            }

            @Override
            public void onRequestSuccess(NewsModel model) {
                callback.onResult((model.getArticles()), null, FIRST_PAGE + 1);
                initialLoading.postValue(NetworkState.LOADED);
                netWorkState.postValue(NetworkState.LOADED);
            }
        });
    }

    @Override
    public void loadBefore(@NonNull final LoadParams<Integer> params, @NonNull final LoadCallback<Integer, Article> callback) {
    }

    @Override
    public void loadAfter(@NonNull final LoadParams<Integer> params, @NonNull final LoadCallback<Integer, Article> callback) {
        int currentCount = params.key;

        netWorkState.postValue(NetworkState.LOADING);
        repository.fetchList(category, PAGE_SIZE, currentCount, new ResponseHandler<NewsModel>() {
            @Override
            public void onRequestFailure(String errorMessage) {
                netWorkState.postValue(new NetworkState(NetworkState.Status.FAILED, errorMessage));
            }

            @Override
            public void onRequestSuccess(NewsModel model) {
                int currentCount = params.key;
                Integer key = loadMore(model.getTotalResults(), currentCount) ? params.key + 1 : null;
                callback.onResult(model.getArticles(), key);
                netWorkState.postValue(NetworkState.LOADED);
            }
        });
    }

    private boolean loadMore(int totalRecord, int currentPage) {
        int totalPage = (int) (totalRecord / PAGE_SIZE);
        return totalPage > currentPage;
    }
}
