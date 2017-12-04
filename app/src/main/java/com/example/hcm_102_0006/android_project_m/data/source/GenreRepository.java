package com.example.hcm_102_0006.android_project_m.data.source;

import com.example.hcm_102_0006.android_project_m.data.model.GenreResponse;
import com.example.hcm_102_0006.android_project_m.data.model.ResultResponse;

import rx.Observable;

/**
 * Created by hcm-102-0006 on 29/11/2017.
 */

public class GenreRepository implements GenreDataSource{
    private GenreDataSource mRemoteDataSource;

    public GenreRepository(GenreDataSource remoteDataSource) {
        mRemoteDataSource = remoteDataSource;
    }

    @Override
    public Observable<GenreResponse> getGenres() {
        return mRemoteDataSource.getGenres();
    }
}
