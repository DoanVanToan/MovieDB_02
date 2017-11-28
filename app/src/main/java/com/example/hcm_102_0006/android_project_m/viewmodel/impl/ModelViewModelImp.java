package com.example.hcm_102_0006.android_project_m.viewmodel.impl;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.example.hcm_102_0006.android_project_m.BuildConfig;
import com.example.hcm_102_0006.android_project_m.R;
import com.example.hcm_102_0006.android_project_m.local.MovieDataSource;
import com.example.hcm_102_0006.android_project_m.remote.model.Genres;
import com.example.hcm_102_0006.android_project_m.remote.model.Movie;
import com.example.hcm_102_0006.android_project_m.remote.model.ResultResponse;
import com.example.hcm_102_0006.android_project_m.remote.repository.MovieApi;
import com.example.hcm_102_0006.android_project_m.remote.repository.MovieFactory;
import com.example.hcm_102_0006.android_project_m.view.adapter.AdapterGenres;
import com.example.hcm_102_0006.android_project_m.view.adapter.AdapterShowMovie;
import com.example.hcm_102_0006.android_project_m.view.ui.GenresActivity;
import com.example.hcm_102_0006.android_project_m.view.ui.MainActivity;
import com.example.hcm_102_0006.android_project_m.viewmodel.MovieViewModel;

import java.util.List;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by hcm-102-0006 on 28/11/2017.
 */

public class ModelViewModelImp implements MovieViewModel {
    private Context mContext;
    private List<Movie> mMovies;
    private List<String> mCategories;
    private AdapterShowMovie mAdapterShowMovie;
    private MovieDataSource mMovieDataSource;

    public ModelViewModelImp(Context context, List<Movie> movies, AdapterShowMovie adapterShowMovie, List<String> categories) {
        this.mContext = context;
        this.mMovies = movies;
        this.mAdapterShowMovie = adapterShowMovie;
        this.mCategories = categories;
        getInformationMovies(mCategories.get(0));
    }

    public void getInformationMovies(String category) {
        MovieApi service = MovieFactory.createRetrofitService(MovieApi.class, MovieApi.SERVICE_URL);
        service.getMovie(category, BuildConfig.MOVIE_KEY)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<ResultResponse>() {
                    @Override
                    public void onCompleted() {
                        mAdapterShowMovie.notifyDataSetChanged();
                    }

                    @Override
                    public void onError(Throwable e) {
                        mAdapterShowMovie.notifyDataSetChanged();
                    }

                    @Override
                    public void onNext(ResultResponse result) {
                        mMovies.clear();
                        mMovies.addAll(result.getResults());
                    }
                });
    }

    private void getInformationMoviesGenre(String category) {
        MovieApi service = MovieFactory.createRetrofitService(MovieApi.class, MovieApi.SERVICE_URL);
        service.getMovieGenres(
                category, BuildConfig.MOVIE_KEY, "en-US", false, "created_at.asc")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<ResultResponse>() {
                    @Override
                    public void onCompleted() {
                        mAdapterShowMovie.notifyDataSetChanged();
                    }

                    @Override
                    public void onError(Throwable e) {
                        mAdapterShowMovie.notifyDataSetChanged();
                    }

                    @Override
                    public void onNext(ResultResponse result) {
                        mMovies.clear();
                        mMovies.addAll(result.getResults());

                    }
                });
    }

    @Override
    public void onClinkMovie(View view) {
        switch (view.getId()) {
            case R.id.button_popular:
                getInformationMovies(mCategories.get(2));
                break;
            case R.id.button_now_playing:
                getInformationMovies(mCategories.get(1));
                break;
            case R.id.button_top_rate:
                getInformationMovies(mCategories.get(0));
                break;
            case R.id.button_genres:
                ((Activity) mContext).startActivityForResult(new Intent(mContext, GenresActivity.class), MainActivity.RESPONSE);
                break;
            case R.id.button_upcoming:
                getInformationMovies(mCategories.get(3));
                break;
            case R.id.button_favorite:
                mMovies.clear();
                //mMoviesAgain.clear();
                mMovies.addAll(mMovieDataSource.getAllMovieFavorite());
                //mMoviesAgain.addAll(mMovies);
                mAdapterShowMovie.notifyDataSetChanged();
                break;
        }
    }

    public void handleActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == MainActivity.RESPONSE) {
            if (resultCode == Activity.RESULT_OK) {
                Genres genres = data.getParcelableExtra(AdapterGenres.KEY_RESULT);
                getInformationMoviesGenre(String.valueOf(genres.getId()));
            }
        }
    }
}
