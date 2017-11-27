package com.example.hcm_102_0006.android_project_m.ui.moviedetail;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.hcm_102_0006.android_project_m.R;
import com.example.hcm_102_0006.android_project_m.data.MovieDataSource;

import com.example.hcm_102_0006.android_project_m.data.model.Movie;
import com.example.hcm_102_0006.android_project_m.data.model.MovieDetail;
import com.example.hcm_102_0006.android_project_m.data.source.remote.MovieApi;
import com.example.hcm_102_0006.android_project_m.data.source.remote.MovieServiceClient;

import com.example.hcm_102_0006.android_project_m.databinding.ActivityMovieDetailBinding;
import com.example.hcm_102_0006.android_project_m.service.repository.DeveloperKey;
import com.example.hcm_102_0006.android_project_m.ui.main.AdapterShowMovie;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MovieDetailActivity extends YouTubeBaseActivity implements YouTubePlayer.OnInitializedListener {
    private static final int RECOVERY_DIALOG_REQUEST = 1;
    private ActivityMovieDetailBinding mActivityMovieDetailBinding;
    private Movie mMovie;
    private MovieDataSource mMovieDataSource;
    private MovieDetail mMovieDetail;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivityMovieDetailBinding = DataBindingUtil
                .setContentView(this, R.layout.activity_movie_detail);
        mMovie = getIntent().getParcelableExtra(AdapterShowMovie.KEY_MOVIE);
        mActivityMovieDetailBinding.setMovieDatabaseBinding(this);
        mMovieDataSource = new MovieDataSource(this);
        getInformationMovieDetail(mMovie.getId());
        // Youtube API


    }

    public void getInformationMovieDetail(String movieId){
        MovieApi movieApi = MovieServiceClient.createRetrofitService(MovieApi.class, MovieApi.SERVICE_URL);

        movieApi.getMovieDetail(movieId).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<MovieDetail>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(MovieDetail movieDetail) {
                        mActivityMovieDetailBinding.setMovieDetailBinding(movieDetail);
                        if (mMovieDataSource.checkFavorite(mMovie.getId())) {
                            mActivityMovieDetailBinding.btnSaveOrDeleteFavorite.setFavorite(true);
                        } else {
                            mActivityMovieDetailBinding.btnSaveOrDeleteFavorite.setFavorite(false);
                        }
                        mMovieDetail = movieDetail;
                        mActivityMovieDetailBinding.youTubeShowVideo.initialize(DeveloperKey.DEVELOP_KEY,MovieDetailActivity.this);
                    }
                });
    }

    public void saveOrDeleteMovieFavorite(View view) {
        boolean isFavorite = mActivityMovieDetailBinding.btnSaveOrDeleteFavorite.isFavorite();
        if ( isFavorite ) {
            mMovieDataSource.deleteMovie(mMovie.getId());
            mActivityMovieDetailBinding.btnSaveOrDeleteFavorite.setFavorite(false);
            Toast.makeText(this, getResources().getString(R.string.msg_delete_movie), Toast.LENGTH_SHORT).show();
        } else {
            mMovieDataSource.insertMovie(mMovie);
            mActivityMovieDetailBinding.btnSaveOrDeleteFavorite.setFavorite(true);
            Toast.makeText(this, getResources().getString(R.string.msg_save_movie), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
        if (!b) {
            youTubePlayer.cueVideo(mMovieDetail.getmVideos().getmResults().get(0).getmKey());
        }
    }

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
        if (youTubeInitializationResult.isUserRecoverableError()) {
            youTubeInitializationResult.getErrorDialog(this,RECOVERY_DIALOG_REQUEST).show();
        } else {
            String errorMessage = String.format("ABC", youTubeInitializationResult.toString());
            Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RECOVERY_DIALOG_REQUEST) {
            mActivityMovieDetailBinding.youTubeShowVideo.initialize(DeveloperKey.DEVELOP_KEY,this);
        }
    }
}
