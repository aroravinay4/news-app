package com.indigo.newsapp.core;


import com.indigo.newsapp.core.remote.CloudConnector;
import com.indigo.newsapp.core.remote.CloudService;
import com.indigo.newsapp.core.remote.ServiceGenerator;
import com.indigo.newsapp.utils.Constants;

public class DataConnectorFactory {
    public final static int REST_REQUEST = 2;

    private CloudService service;

    public DataConnectorFactory() {
        this.service = ServiceGenerator.getRestService(Constants.BASE);
    }

    public DataConnector createCloudConnector(String baseUrl, int... test) {
        this.service = ServiceGenerator.getRestService(baseUrl);
        return new CloudConnector(service);
    }
}
