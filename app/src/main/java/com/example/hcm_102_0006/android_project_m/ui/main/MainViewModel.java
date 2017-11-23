package com.example.hcm_102_0006.android_project_m.ui.main;

import android.databinding.BaseObservable;

import com.example.hcm_102_0006.android_project_m.data.model.GenreResponse;
import com.example.hcm_102_0006.android_project_m.data.source.GenreRepository;

import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by hcm-102-0006 on 29/11/2017.
 */

public class MainViewModel extends BaseObservable {
    private GenreRepository mGenreRepository;

    public MainViewModel(GenreRepository genreRepository) {
        mGenreRepository = genreRepository;
    }

    public void onStart(){
        mGenreRepository.getGenres()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<GenreResponse>() {
                    @Override
                    public void call(GenreResponse genreResponse) {

                    }
                });
    }
}


