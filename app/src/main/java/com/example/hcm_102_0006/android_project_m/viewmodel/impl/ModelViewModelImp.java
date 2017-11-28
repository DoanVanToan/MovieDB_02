package com.example.hcm_102_0006.android_project_m.viewmodel.impl;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.example.hcm_102_0006.android_project_m.BuildConfig;
import com.example.hcm_102_0006.android_project_m.R;
import com.example.hcm_102_0006.android_project_m.data.model.Genres;
import com.example.hcm_102_0006.android_project_m.data.model.Movie;
import com.example.hcm_102_0006.android_project_m.data.model.ResultResponse;
import com.example.hcm_102_0006.android_project_m.data.source.local.FavoriteLocalDataSource;
import com.example.hcm_102_0006.android_project_m.data.source.remote.MovieApi;
import com.example.hcm_102_0006.android_project_m.data.source.remote.MovieServiceClient;
import com.example.hcm_102_0006.android_project_m.ui.genre.AdapterGenres;
import com.example.hcm_102_0006.android_project_m.ui.genre.GenresActivity;
import com.example.hcm_102_0006.android_project_m.ui.main.AdapterShowMovie;
import com.example.hcm_102_0006.android_project_m.ui.main.MainActivity;
import com.example.hcm_102_0006.android_project_m.viewmodel.MovieViewModel;

import java.util.List;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by hcm-102-0006 on 28/11/2017.
 */

public class ModelViewModelImp implements MovieViewModel {
    private Context mContext;
    private List<Movie> mMovies;
    private List<String> mCategories;
    private AdapterShowMovie mAdapterShowMovie;
    private FavoriteLocalDataSource mMovieDataSource;

    public ModelViewModelImp(Context context, List<Movie> movies, AdapterShowMovie adapterShowMovie, List<String> categories,FavoriteLocalDataSource favoriteLocalDataSource) {
        this.mContext = context;
        this.mMovies = movies;
        this.mAdapterShowMovie = adapterShowMovie;
        this.mCategories = categories;
        this.mMovieDataSource = favoriteLocalDataSource;
        getInformationMovies(mCategories.get(0));
    }

    public void getInformationMovies(String category) {
        MovieApi service = MovieServiceClient.createRetrofitService(MovieApi.class, MovieApi.SERVICE_URL);
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
        MovieApi service = MovieServiceClient.createRetrofitService(MovieApi.class, MovieApi.SERVICE_URL);
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
                mMovieDataSource.getAllMovieFavorite().subscribe(new Action1<List<Movie>>() {
                    @Override
                    public void call(List<Movie> movies) {
                        mMovies.addAll(movies);
                    }
                });
                //mMoviesAgain.addAll(mMovies);
                mAdapterShowMovie.notifyDataSetChanged();
                break;
        }
    }

    public void handleActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == MainActivity.RESPONSE) {
            if (resultCode == Activity.RESULT_OK) {
                Genres genres = data.getParcelableExtra(AdapterGenres.BUNDLE_GENRES);
                getInformationMoviesGenre(String.valueOf(genres.getId()));
            }
        }
    }
}
