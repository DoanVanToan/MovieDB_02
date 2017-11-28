package com.example.hcm_102_0006.android_project_m.viewmodel.impl;

import android.content.Context;
import android.view.View;

import com.example.hcm_102_0006.android_project_m.BuildConfig;
import com.example.hcm_102_0006.android_project_m.remote.model.GenreResponse;
import com.example.hcm_102_0006.android_project_m.remote.model.Genres;
import com.example.hcm_102_0006.android_project_m.remote.repository.MovieApi;
import com.example.hcm_102_0006.android_project_m.remote.repository.MovieFactory;
import com.example.hcm_102_0006.android_project_m.view.adapter.AdapterGenres;
import com.example.hcm_102_0006.android_project_m.viewmodel.GenresViewModel;

import java.util.List;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by hcm-102-0006 on 28/11/2017.
 */

public class MovieGenreViewModelImp implements GenresViewModel {

    private AdapterGenres mAdapterGenres;
    private List<Genres> mGenres;
    private Context mContext;

    public MovieGenreViewModelImp(Context context, List<Genres> genres, AdapterGenres adapterGenres) {
        this.mContext = context;
        this.mGenres = genres;
        this.mAdapterGenres = adapterGenres;
        getListGenres();
    }

    public void getListGenres() {
        MovieApi service =
                MovieFactory.createRetrofitService(MovieApi.class, MovieApi.SERVICE_URL);
        service.getGenres(BuildConfig.MOVIE_KEY)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<GenreResponse>() {
                    @Override
                    public void onCompleted() {
                        mAdapterGenres.notifyDataSetChanged();
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(GenreResponse genresMovie) {
                        mGenres.addAll(genresMovie.getGenres());
                    }
                });
    }
}
