package com.example.hcm_102_0006.android_project_m.ui.main;

import android.content.Context;
import android.databinding.BaseObservable;

import com.example.hcm_102_0006.android_project_m.R;
import com.example.hcm_102_0006.android_project_m.data.model.GenreResponse;
import com.example.hcm_102_0006.android_project_m.data.model.Movie;
import com.example.hcm_102_0006.android_project_m.data.source.GenreRepository;
import com.example.hcm_102_0006.android_project_m.data.source.MovieRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by hcm-102-0006 on 29/11/2017.
 */

public class MainViewModel extends BaseObservable {
    private AdapterShowMovie mAdapterShowMovie;
    private Context mContext;
    private MovieRepository mMovieRepository;
    private CompositeSubscription mCompositeSubscription;
    private List<String> mCategories;

    public MainViewModel(AdapterShowMovie adapterShowMovie, Context context,
                         MovieRepository movieRepository,
                         CompositeSubscription compositeSubscription) {
        mAdapterShowMovie = adapterShowMovie;
        mContext = context;
        mMovieRepository = movieRepository;
        mCompositeSubscription = compositeSubscription;
        mAdapterShowMovie.setMainViewModel(this);
        mCategories = new ArrayList<>();
        mCategories.addAll(Arrays.asList(mContext.getResources().getStringArray(R.array.categories)));
    }

    public void onStart(){
        Subscription subscription = mMovieRepository.getMovieCategory(
                mCategories.get(0)).subscribeOn(

                        Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(
                new Action1<Movie>() {
                    @Override
                    public void call(Movie movie) {

                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {

                    }
                });
    }

    public void onStop(){

    }


}


