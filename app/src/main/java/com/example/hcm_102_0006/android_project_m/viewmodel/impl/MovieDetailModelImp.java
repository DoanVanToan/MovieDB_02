package com.example.hcm_102_0006.android_project_m.viewmodel.impl;

import android.content.Context;
import android.view.View;
import android.widget.Toast;

import com.example.hcm_102_0006.android_project_m.R;
import com.example.hcm_102_0006.android_project_m.data.model.Genres;
import com.example.hcm_102_0006.android_project_m.data.model.Movie;
import com.example.hcm_102_0006.android_project_m.data.source.local.FavoriteLocalDataSource;
import com.example.hcm_102_0006.android_project_m.databinding.ActivityMovieDetailBinding;
import com.example.hcm_102_0006.android_project_m.ui.genre.AdapterGenres;

import com.example.hcm_102_0006.android_project_m.viewmodel.MovieDetailViewModel;

import java.util.List;

import rx.functions.Action1;

/**
 * Created by hcm-102-0006 on 28/11/2017.
 */

public class MovieDetailModelImp implements MovieDetailViewModel {

    private ActivityMovieDetailBinding mActivityMovieDetailBinding;
    private AdapterGenres mAdapterGenres;
    private List<Genres> mGenres;
    private Context mContext;
    private FavoriteLocalDataSource mMovieDataSource;

    public MovieDetailModelImp(Context context, List<Genres> genres, AdapterGenres adapterGenres) {
        this.mContext = context;
        this.mGenres = genres;
        this.mAdapterGenres = adapterGenres;
        mMovieDataSource = new FavoriteLocalDataSource(context);
    }
    @Override
    public void getInformationMovieDetail(View view, String movieId) {

    }

    @Override
    public void saveOrDeleteMovieFavorite(View view, Movie movie) {
        boolean isFavorite = mActivityMovieDetailBinding.btnSaveOrDeleteFavorite.isFavorite();
        if ( isFavorite ) {
            mMovieDataSource.deleteMovie(movie).subscribe(new Action1<Boolean>() {
                @Override
                public void call(Boolean aBoolean) {

                }
            });
            mActivityMovieDetailBinding.btnSaveOrDeleteFavorite.setFavorite(false);
            Toast.makeText(mContext, mContext.getResources().getString(R.string.msg_delete_movie), Toast.LENGTH_SHORT).show();
        } else {
            mMovieDataSource.insertMovie(movie);
            mActivityMovieDetailBinding.btnSaveOrDeleteFavorite.setFavorite(true);
            Toast.makeText(mContext, mContext.getResources().getString(R.string.msg_save_movie), Toast.LENGTH_SHORT).show();
        }
    }

}
