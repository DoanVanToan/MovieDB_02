package com.example.hcm_102_0006.android_project_m.data.source.remote;

import com.example.hcm_102_0006.android_project_m.BuildConfig;
import com.example.hcm_102_0006.android_project_m.data.model.ResultResponse;
import com.example.hcm_102_0006.android_project_m.data.source.MovieDataSource;

import rx.Observable;

/**
 * Created by hcm-102-0006 on 30/11/2017.
 */

public class MovieRemoteDataSource extends BaseRemoteDataSource implements MovieDataSource{
    public MovieRemoteDataSource(MovieApi api) {
        super(api);
    }

    @Override
    public Observable<ResultResponse> getMovieCategory(String category) {
        return mApi.getMovie(category,BuildConfig.MOVIE_KEY);
    }

    @Override
    public Observable<ResultResponse> getMovieGenres(String genreId) {
        return mApi.getMovieGenres(genreId, BuildConfig.MOVIE_KEY, "en-US", false, "created_at.asc");
    }
}
