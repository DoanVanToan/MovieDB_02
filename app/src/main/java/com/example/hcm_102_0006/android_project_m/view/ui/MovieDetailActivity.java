package com.example.hcm_102_0006.android_project_m.view.ui;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.hcm_102_0006.android_project_m.R;
import com.example.hcm_102_0006.android_project_m.data.MovieDataSource;
import com.example.hcm_102_0006.android_project_m.service.model.Movie;
import com.example.hcm_102_0006.android_project_m.service.model.MovieDetail;
import com.example.hcm_102_0006.android_project_m.service.repository.MovieApi;
import com.example.hcm_102_0006.android_project_m.service.repository.MovieFactory;
import com.example.hcm_102_0006.android_project_m.databinding.ActivityMovieDetailBinding;
import com.example.hcm_102_0006.android_project_m.view.adapter.AdapterShowMovie;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MovieDetailActivity extends AppCompatActivity {
    private ActivityMovieDetailBinding mActivityMovieDetailBinding;
    private Movie mMovie;
    private MovieDataSource mMovieDataSource;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivityMovieDetailBinding = DataBindingUtil.setContentView(this, R.layout.activity_movie_detail);
        mMovie = getIntent().getParcelableExtra(AdapterShowMovie.KEY_MOVIE);
        mActivityMovieDetailBinding.setMovieDatabaseBinding(this);
        mMovieDataSource = new MovieDataSource(this);
        getInformationMovieDetail(mMovie.getId());
    }

    public void getInformationMovieDetail(String movieId) {
        MovieApi movieApi = MovieFactory.createRetrofitService(MovieApi.class, MovieApi.SERVICE_URL);
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
                    }
                });
    }

    public void saveOrDeleteMovieFavorite(View view) {
        boolean isFavorite = mActivityMovieDetailBinding.btnSaveOrDeleteFavorite.isFavorite();
        if ( isFavorite ) {
            mMovieDataSource.deleteMovie(mMovie.getId());
            mActivityMovieDetailBinding.btnSaveOrDeleteFavorite.setFavorite(false);
            Toast.makeText(this, getResources().getString(R.string.msgDeleteMovie), Toast.LENGTH_SHORT).show();
        } else {
            mMovieDataSource.insertMovie(mMovie);
            mActivityMovieDetailBinding.btnSaveOrDeleteFavorite.setFavorite(true);
            Toast.makeText(this, getResources().getString(R.string.msgSaveMovie), Toast.LENGTH_SHORT).show();
        }
    }
}
