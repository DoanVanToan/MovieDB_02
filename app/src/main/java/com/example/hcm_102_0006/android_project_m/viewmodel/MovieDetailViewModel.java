package com.example.hcm_102_0006.android_project_m.viewmodel;

import android.view.View;

import com.example.hcm_102_0006.android_project_m.data.model.Movie;


/**
 * Created by hcm-102-0006 on 28/11/2017.
 */

public interface MovieDetailViewModel {
    void getInformationMovieDetail(View view, String movieId);
    void saveOrDeleteMovieFavorite(View view, Movie movie);
}
