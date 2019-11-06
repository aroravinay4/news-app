package com.indigo.newsapp.core.remote;

import com.indigo.newsapp.core.remote.model.Model;


public interface ResponseHandler<M extends Model> {

    void onRequestFailure(String errorMessage);

    void onRequestSuccess(M model);
}
