package com.example.hcm_102_0006.android_project_m.ui.moviedetail;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import com.example.hcm_102_0006.android_project_m.R;
import com.example.hcm_102_0006.android_project_m.data.model.Movie;
import com.example.hcm_102_0006.android_project_m.data.source.MovieRepository;
import com.example.hcm_102_0006.android_project_m.data.source.local.FavoriteLocalDataSource;

import com.example.hcm_102_0006.android_project_m.data.source.remote.MovieRemoteDataSource;
import com.example.hcm_102_0006.android_project_m.data.source.remote.MovieServiceClient;
import com.example.hcm_102_0006.android_project_m.databinding.ActivityMovieDetailBinding;
import com.example.hcm_102_0006.android_project_m.ui.main.AdapterShowMovie;
import com.google.android.youtube.player.YouTubeBaseActivity;

public class MovieDetailActivity extends YouTubeBaseActivity  {
    private Movie mMovie;
    private FavoriteLocalDataSource mFavoriteDataSource;
    private MovieDetailViewModel mMovieDetailViewModel;
    private ActivityMovieDetailBinding mActivityMovieDetailBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivityMovieDetailBinding = DataBindingUtil
                .setContentView(this, R.layout.activity_movie_detail);
        mFavoriteDataSource = new FavoriteLocalDataSource(this);
        mMovie = getIntent().getParcelableExtra(AdapterShowMovie.BUNDLE_MOVIE);

        MovieRepository mMovieRepository = new MovieRepository(
                new MovieRemoteDataSource(MovieServiceClient.getInstance()));
        mMovieDetailViewModel = new MovieDetailViewModel(
                this, mFavoriteDataSource,mMovieRepository,mMovie);
        mActivityMovieDetailBinding.setViewModel(mMovieDetailViewModel);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        mMovieDetailViewModel.handlerActivityResult(requestCode, resultCode, data);
    }
}