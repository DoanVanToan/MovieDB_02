package com.example.hcm_102_0006.android_project_m.view.ui.genre;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import com.example.hcm_102_0006.android_project_m.R;
import com.example.hcm_102_0006.android_project_m.data.model.Genres;
import com.example.hcm_102_0006.android_project_m.databinding.ActivityGenresBinding;
import java.util.ArrayList;
import java.util.List;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class GenresActivity extends AppCompatActivity {

    private ActivityGenresBinding mActivityGenresBinding;
    private List<Genres> mGenresMovies;
    public AdaptegrGenres adapterGenres;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivityGenresBinding = DataBindingUtil.setContentView(this, R.layout.activity_genres);
        mGenresMovies = new ArrayList<>();
        adapterGenres = new AdapterGenres(mGenresMovies,this);
        mActivityGenresBinding.rcyShowGenres.setLayoutManager(new GridLayoutManager(this,2));
        mActivityGenresBinding.rcyShowGenres.setAdapter(adapterGenres);
        getListGenres();
    }

    public void getListGenres() {
        // REFACTOR LẠI CODE.
        // NÊN GET DÂT THÔNG QUA REPOSSITORY
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
