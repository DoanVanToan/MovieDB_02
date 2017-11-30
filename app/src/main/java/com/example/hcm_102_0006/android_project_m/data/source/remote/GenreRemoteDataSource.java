package com.example.hcm_102_0006.android_project_m.data.source.remote;

import com.example.hcm_102_0006.android_project_m.BuildConfig;
import com.example.hcm_102_0006.android_project_m.data.model.GenreResponse;
import com.example.hcm_102_0006.android_project_m.data.model.ResultResponse;
import com.example.hcm_102_0006.android_project_m.data.source.GenreDataSource;
import rx.Observable;

/**
 * Created by hcm-102-0006 on 29/11/2017.
 */

public class GenreRemoteDataSource extends BaseRemoteDataSource implements GenreDataSource {

    public GenreRemoteDataSource(MovieApi api) {
        super(api);
    }

    @Override
    public Observable<GenreResponse> getGenres() {
        return mApi.getGenres(BuildConfig.MOVIE_KEY);
    }

}
