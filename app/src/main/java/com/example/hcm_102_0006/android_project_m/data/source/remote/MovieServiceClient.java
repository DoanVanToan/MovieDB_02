package com.example.hcm_102_0006.android_project_m.data.source.remote;


import com.example.hcm_102_0006.android_project_m.data.model.Movie;

import retrofit.RestAdapter;

/**
 * Created by hcm-102-0006 on 21/11/2017.
 */

public class MovieServiceClient {
    public static <T> T createRetrofitService(final Class<T> classZZ, final String endPoint){
        final RestAdapter restAdapter = new RestAdapter.Builder().setEndpoint(endPoint).build();
        T service = restAdapter.create(classZZ);
        return service;
    }

    public static MovieApi getInstance(){
        return createRetrofitService(MovieApi.class, MovieApi.SERVICE_URL);
    }
}
