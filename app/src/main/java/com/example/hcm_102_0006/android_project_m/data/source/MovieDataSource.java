package com.example.hcm_102_0006.android_project_m.data.source;

import com.example.hcm_102_0006.android_project_m.data.model.MovieDetail;
import com.example.hcm_102_0006.android_project_m.data.model.ResultResponse;

import rx.Observable;

/**
 * Created by hcm-102-0006 on 30/11/2017.
 */

public interface MovieDataSource {
    Observable<ResultResponse> getMovieCategory(String category);
    Observable<ResultResponse> getMovieGenres(String genreId);
    Observable<MovieDetail> getMovieDetail(String movieId);
    Observable<ResultResponse> getMovieCompany(String companyId);
    Observable<ResultResponse> getMovieActor(String castId);
}
