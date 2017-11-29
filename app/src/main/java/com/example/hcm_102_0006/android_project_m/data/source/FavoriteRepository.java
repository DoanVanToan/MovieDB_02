package com.example.hcm_102_0006.android_project_m.data.source;

import com.example.hcm_102_0006.android_project_m.data.model.Movie;
import java.util.List;
import rx.Observable;

/**
 * Created by hcm-102-0006 on 29/11/2017.
 */

public class FavoriteRepository implements FavoriteDataSource {
    private FavoriteDataSource mLocalDataSource;

    public FavoriteRepository(FavoriteDataSource localDataSource) {
        mLocalDataSource = localDataSource;
    }

    @Override
    public Observable<List<Movie>> getAllMovieFavorite() {
        return mLocalDataSource.getAllMovieFavorite();
    }

    @Override
    public Observable<Boolean> isFavoriteMovie(String id) {
        return mLocalDataSource.isFavoriteMovie(id);
    }

    @Override
    public Observable<Boolean> insertMovie(Movie movie) {
        return mLocalDataSource.insertMovie(movie);
    }

    @Override
    public Observable<Boolean> deleteMovie(Movie movie) {
        return mLocalDataSource.deleteMovie(movie);
    }
}
