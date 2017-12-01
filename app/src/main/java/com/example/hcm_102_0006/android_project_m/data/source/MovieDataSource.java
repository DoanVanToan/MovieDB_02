package com.example.hcm_102_0006.android_project_m.data.source;

import com.example.hcm_102_0006.android_project_m.data.model.Movie;

import java.util.List;

import rx.Observable;

/**
 * Created by hcm-102-0006 on 29/11/2017.
 */

public interface MovieDataSource {
    Observable<List<Movie>> getAllMovieFavorite();

    Observable<Boolean> isFavoriteMovie(String id);

    Observable<Boolean> insertMovie(Movie movie);

    Observable<Boolean> deleteMovie(Movie movie);
}
