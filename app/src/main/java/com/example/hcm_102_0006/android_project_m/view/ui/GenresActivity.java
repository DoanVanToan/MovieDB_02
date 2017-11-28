package com.example.hcm_102_0006.android_project_m.view.ui;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;

import com.example.hcm_102_0006.android_project_m.BuildConfig;
import com.example.hcm_102_0006.android_project_m.R;
import com.example.hcm_102_0006.android_project_m.remote.model.Genres;
import com.example.hcm_102_0006.android_project_m.remote.model.GenreResponse;
import com.example.hcm_102_0006.android_project_m.remote.repository.MovieApi;
import com.example.hcm_102_0006.android_project_m.remote.repository.MovieFactory;
import com.example.hcm_102_0006.android_project_m.databinding.ActivityGenresBinding;
import com.example.hcm_102_0006.android_project_m.view.adapter.AdapterGenres;
import com.example.hcm_102_0006.android_project_m.viewmodel.impl.MovieGenreViewModelImp;

import java.util.ArrayList;
import java.util.List;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class GenresActivity extends AppCompatActivity {

    private ActivityGenresBinding mActivityGenresBinding;
    private List<Genres> mGenresMovies;
    private AdapterGenres mAdapterGenres;
    private MovieGenreViewModelImp mMovieGenreViewModelImp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivityGenresBinding = DataBindingUtil.setContentView(this, R.layout.activity_genres);
        mGenresMovies = new ArrayList<>();
        mAdapterGenres = new AdapterGenres(this, mGenresMovies);
        mActivityGenresBinding.rcyShowGenres.setLayoutManager(new GridLayoutManager(this,2));
        mActivityGenresBinding.rcyShowGenres.setAdapter(mAdapterGenres);
        mMovieGenreViewModelImp = new MovieGenreViewModelImp(this, mGenresMovies, mAdapterGenres);
    }
}
