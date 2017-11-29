package com.example.hcm_102_0006.android_project_m.view.ui;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.hcm_102_0006.android_project_m.R;
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
    private String mIdMovie;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivityMovieDetailBinding = DataBindingUtil.setContentView(this,R.layout.activity_movie_detail);
        mIdMovie = getIntent().getStringExtra(AdapterShowMovie.KEY_MOVIE);
        getInformationMovieDetail(mIdMovie);
    }

    public void getInformationMovieDetail(String movieId){
        MovieApi movieApi = MovieFactory.createRetrofitService(MovieApi.class, MovieApi.SERVICE_URL);
        movieApi.getMovieDetail(movieId).subscribeOn(Schedulers.io())
                . observeOn(AndroidSchedulers.mainThread())
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
                    }
                });
    }
}
