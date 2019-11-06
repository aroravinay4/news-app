package com.indigo.newsapp.pagination;


import androidx.annotation.NonNull;
import androidx.paging.PageKeyedDataSource;

import com.indigo.newsapp.core.remote.ResponseHandler;
import com.indigo.newsapp.model.Article;
import com.indigo.newsapp.model.NewsModel;
import com.indigo.newsapp.viewmodel.NewsListRepository;

import static com.indigo.newsapp.utils.Constants.FIRST_PAGE;
import static com.indigo.newsapp.utils.Constants.PAGE_SIZE;

public class ItemDataSource extends PageKeyedDataSource<Integer, Article> {
    NewsListRepository repository = new NewsListRepository();
    private String category;

    ItemDataSource(String category) {
        this.category = category;
    }

    @Override
    public void loadInitial(@NonNull LoadInitialParams<Integer> params, @NonNull final LoadInitialCallback<Integer, Article> callback) {
        repository.fetchList(category, PAGE_SIZE, FIRST_PAGE, new ResponseHandler<NewsModel>() {
            @Override
            public void onRequestFailure() {
            }

            @Override
            public void onRequestSuccess(NewsModel model) {
                callback.onResult((model.getArticles()), null, FIRST_PAGE + 1);
            }
        });
    }

    @Override
    public void loadBefore(@NonNull final LoadParams<Integer> params, @NonNull final LoadCallback<Integer, Article> callback) {
        int currentPage = params.key;
        repository.fetchList(category, PAGE_SIZE, currentPage, new ResponseHandler<NewsModel>() {
            @Override
            public void onRequestFailure() {
            }

            @Override
            public void onRequestSuccess(NewsModel model) {
                Integer key = (params.key > 1) ? params.key - 1 : null;
                callback.onResult(model.getArticles(), key);
            }
        });
    }

    @Override
    public void loadAfter(@NonNull final LoadParams<Integer> params, @NonNull final LoadCallback<Integer, Article> callback) {
        int currentCount = params.key;
        repository.fetchList(category, PAGE_SIZE, currentCount, new ResponseHandler<NewsModel>() {
            @Override
            public void onRequestFailure() {
            }

            @Override
            public void onRequestSuccess(NewsModel model) {
                int currentCount = params.key;
                Integer key = loadMore(model.getTotalResults(), currentCount) ? params.key + 1 : null;
                callback.onResult(model.getArticles(), key);
            }
        });
    }

    private boolean loadMore(int totalRecord, int currentPage) {
        int totalPage = (int) (totalRecord / PAGE_SIZE);
        return totalPage > currentPage;
    }
}
