package com.example.hcm_102_0006.android_project_m.view.ui;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;

import com.example.hcm_102_0006.android_project_m.R;
import com.example.hcm_102_0006.android_project_m.service.model.Genres;
import com.example.hcm_102_0006.android_project_m.service.model.GenreResponse;
import com.example.hcm_102_0006.android_project_m.service.repository.MovieApi;
import com.example.hcm_102_0006.android_project_m.service.repository.MovieFactory;
import com.example.hcm_102_0006.android_project_m.databinding.ActivityGenresBinding;
import com.example.hcm_102_0006.android_project_m.view.adapter.AdapterGenres;

import java.util.ArrayList;
import java.util.List;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class GenresActivity extends AppCompatActivity {

    private ActivityGenresBinding mActivityGenresBinding;
    private List<Genres> mGenresMovies;
    public AdapterGenres adapterGenres;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivityGenresBinding = DataBindingUtil.setContentView(this, R.layout.activity_genres);
        mGenresMovies = new ArrayList<>();
        adapterGenres = new AdapterGenres(this, mGenresMovies);
        mActivityGenresBinding.rcyShowGenres.setLayoutManager(new GridLayoutManager(this,2));
        mActivityGenresBinding.rcyShowGenres.setAdapter(adapterGenres);
        getListGenres();
    }

    public void getListGenres() {
        MovieApi service =
                MovieFactory.createRetrofitService(MovieApi.class, MovieApi.SERVICE_URL);
        service.getGenres()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<GenreResponse>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(GenreResponse genresMovie) {
                mGenresMovies.addAll(genresMovie.getGenres());
                adapterGenres.notifyDataSetChanged();
            }
        });
    }
}
