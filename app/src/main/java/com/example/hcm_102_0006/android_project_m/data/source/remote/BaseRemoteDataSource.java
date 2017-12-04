package com.example.hcm_102_0006.android_project_m.data.source.remote;

/**
 * Created by hcm-102-0006 on 29/11/2017.
 */

public abstract class BaseRemoteDataSource {
    protected MovieApi mApi;

    public BaseRemoteDataSource(MovieApi api) {
        mApi = api;
    }
}
