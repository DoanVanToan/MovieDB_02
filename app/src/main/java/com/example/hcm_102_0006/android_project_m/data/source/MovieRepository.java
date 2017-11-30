package com.example.hcm_102_0006.android_project_m.data.source;

import com.example.hcm_102_0006.android_project_m.data.model.ResultResponse;

import rx.Observable;

/**
 * Created by hcm-102-0006 on 30/11/2017.
 */

public class MovieRepository implements MovieDataSource {
    private MovieDataSource mMovieDataSource;

    public MovieRepository(MovieDataSource movieDataSource) {
        mMovieDataSource = movieDataSource;
    }

    @Override
    public Observable<ResultResponse> getMovieCategory(String category) {
        return mMovieDataSource.getMovieCategory(category);
    }

    @Override
    public Observable<ResultResponse> getMovieGenres(String genreId) {
        return mMovieDataSource.getMovieGenres(genreId);
    }
}
