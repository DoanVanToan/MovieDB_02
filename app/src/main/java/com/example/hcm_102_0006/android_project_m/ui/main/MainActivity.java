package com.example.hcm_102_0006.android_project_m.view.ui.main;

import android.app.Activity;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;

import com.example.hcm_102_0006.android_project_m.R;
import com.example.hcm_102_0006.android_project_m.service.model.Genres;
import com.example.hcm_102_0006.android_project_m.service.model.Movie;
import com.example.hcm_102_0006.android_project_m.service.model.Result;
import com.example.hcm_102_0006.android_project_m.service.repository.MovieApi;
import com.example.hcm_102_0006.android_project_m.service.repository.MovieFactory;
import com.example.hcm_102_0006.android_project_m.data.MovieDataSource;
import com.example.hcm_102_0006.android_project_m.databinding.ActivityHomeBinding;
import com.example.hcm_102_0006.android_project_m.view.ui.genre.AdapterGenres;

import com.example.hcm_102_0006.android_project_m.view.ui.genre.GenresActivity;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


public class MainActivity extends AppCompatActivity {

    public static final int RESPONSE = 810;
    private List<String> mCategories;
    private List<Movie> mMovies;
    private List<Movie> mMoviesAgain;
    private AdapterShowMovie mAdapterShowMovie;
    private ActivityHomeBinding mActivityHomeBinding;
    private MovieDataSource mMovieDataSource;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mMovies = new ArrayList<>();
        mActivityHomeBinding =
                DataBindingUtil.setContentView(this, R.layout.activity_home);

        mMovies = new ArrayList<>();
        mCategories = new ArrayList<>();
        mMoviesAgain = new ArrayList<>();
        mMovieDataSource = new MovieDataSource(this);

        mCategories.addAll(Arrays.asList(getResources().getStringArray(R.array.categories)));
        mAdapterShowMovie = new AdapterShowMovie(mMovies, this);
        mActivityHomeBinding.rcyShowMovies.setLayoutManager(new GridLayoutManager(this, 2));
        mActivityHomeBinding.rcyShowMovies.setAdapter(mAdapterShowMovie);
        mActivityHomeBinding.setHomeDataBinding(this);
        getInformationMovies(mCategories.get(0));

    }

    private void getInformationMovies(String category) {
        MovieApi service = MovieFactory.createRetrofitService(MovieApi.class, MovieApi.SERVICE_URL);
        service.getMovie(category)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Result>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                    }

                    @Override
                    public void onNext(Result result) {
                        mMovies.clear();
                        mMoviesAgain.clear();
                        mMovies.addAll(result.getResults());
                        mAdapterShowMovie.notifyDataSetChanged();
                        mMoviesAgain.addAll(mMovies);
                        int cour = 0;
                        for (Movie movie : mMovies) {
                            mMovieDataSource.insertMovie(movie);
                            if (cour == 4) {
                                break;
                            }
                            cour++;
                        }
                    }
                });
    }

    private void getInformationMoviesGenre(String category) {
        MovieApi service = MovieFactory.createRetrofitService(MovieApi.class, MovieApi.SERVICE_URL);
        service.getMovieGenres(category)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Result>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                    }

                    @Override
                    public void onNext(Result result) {
                        mMovies.clear();
                        mMoviesAgain.clear();
                        mMovies.addAll(result.getResults());
                        mAdapterShowMovie.notifyDataSetChanged();
                        mMoviesAgain.addAll(mMovies);
                    }
                });
    }

    public void getMoviesFollowCategory(View view) {
        switch (view.getId()) {
            case R.id.button_Popular:
                getInformationMovies(mCategories.get(2));
                break;
            case R.id.button_NowPlaying:
                getInformationMovies(mCategories.get(1));
                break;
            case R.id.button_TopRate:
                getInformationMovies(mCategories.get(0));
                break;
            case R.id.button_Genres:
                startActivityForResult(new Intent(this, GenresActivity.class), RESPONSE);
                break;
            case R.id.button_Upcoming:
                getInformationMovies(mCategories.get(3));
                break;
            case R.id.button_favorite:
                mMovies.clear();
                mMoviesAgain.clear();
                mMovies.addAll(mMovieDataSource.getAllMovieFavorite());
                mMoviesAgain.addAll(mMovies);
                mAdapterShowMovie.notifyDataSetChanged();
                break;
        }
    }

    public TextWatcher nameWatcher() {
        return new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                mMovies.clear();
                String enterCharacter = charSequence.toString().toUpperCase().trim();
                if (enterCharacter == "") {
                    mMovies.addAll(mMoviesAgain);
                } else {
                    for (Movie movie : mMoviesAgain) {
                        String templateSearch = movie.getTitle().toUpperCase();
                        if (templateSearch.contains(enterCharacter)) {
                            mMovies.add(movie);
                        }
                    }
                }
                mAdapterShowMovie.notifyDataSetChanged();
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        };
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RESPONSE) {
            if (resultCode == Activity.RESULT_OK) {
                Genres genres = (Genres) data.getSerializableExtra(AdapterGenres.KEY_RESULT);
                getInformationMoviesGenre(String.valueOf(genres.getId()));
            }
        }

    }
}
