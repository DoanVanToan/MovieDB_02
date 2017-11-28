package com.example.hcm_102_0006.android_project_m.viewmodel.impl;

import android.content.Context;
import android.view.View;

import com.example.hcm_102_0006.android_project_m.data.model.Genres;
import com.example.hcm_102_0006.android_project_m.ui.genre.AdapterGenres;
import com.example.hcm_102_0006.android_project_m.viewmodel.MovieDetailViewModel;

import java.util.List;

/**
 * Created by hcm-102-0006 on 28/11/2017.
 */

public class MovieDetailModelImp implements MovieDetailViewModel {

    private AdapterGenres mAdapterGenres;
    private List<Genres> mGenres;
    private Context mContext;

    public MovieDetailModelImp(Context context, List<Genres> genres, AdapterGenres adapterGenres) {
        this.mContext = context;
        this.mGenres = genres;
        this.mAdapterGenres = adapterGenres;
    }
    @Override
    public void getInformationMovieDetail(View view, String movieId) {

    }

    @Override
    public void saveOrDeleteMovieFavorite(View view) {

    }
}
