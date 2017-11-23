package com.example.hcm_102_0006.android_project_m.data.source.remote;


import retrofit.RestAdapter;

/**
 * Created by hcm-102-0006 on 21/11/2017.
 */

public class MovieFactory {
    public static <T> T createRetrofitService(final Class<T> cla, final String endpoint){
        final RestAdapter restAdapter = new RestAdapter.Builder().setEndpoint(endpoint).build();
        T service = restAdapter.create(cla);
        return service;
    }
}
