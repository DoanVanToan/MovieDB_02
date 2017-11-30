package com.example.hcm_102_0006.android_project_m.ui.main;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;

import com.example.hcm_102_0006.android_project_m.R;
import com.example.hcm_102_0006.android_project_m.data.model.CreditsResponse;
import com.example.hcm_102_0006.android_project_m.data.model.Genres;
import com.example.hcm_102_0006.android_project_m.data.model.Movie;
import com.example.hcm_102_0006.android_project_m.data.model.MovieDetail;
import com.example.hcm_102_0006.android_project_m.data.model.ResultResponse;
import com.example.hcm_102_0006.android_project_m.data.source.MovieRepository;
import com.example.hcm_102_0006.android_project_m.data.source.local.FavoriteLocalDataSource;
import com.example.hcm_102_0006.android_project_m.ui.genre.AdapterGenres;
import com.example.hcm_102_0006.android_project_m.ui.genre.GenresActivity;
import com.example.hcm_102_0006.android_project_m.ui.moviedetail.MovieDetailActivity;
import com.example.hcm_102_0006.android_project_m.view.adapter.AdapterShowCompany;
import com.example.hcm_102_0006.android_project_m.view.adapter.AdapterShowCreditDetail;
import com.example.hcm_102_0006.android_project_m.view.adapter.AdapterShowGenresDetail;

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
    private List<Movie> mMovies;
    private FavoriteLocalDataSource mFavoriteLocalDataSource;

    public MainViewModel(Context context,
                         MovieRepository movieRepository) {
        mContext = context;
        mAdapterShowMovie = new AdapterShowMovie(mContext, new ArrayList<Movie>());
        mMovieRepository = movieRepository;
        mCompositeSubscription = new CompositeSubscription();
        mAdapterShowMovie.setMainViewModel(this);
        mCategories = new ArrayList<>();
        mMovies = new ArrayList<>();
        mCategories.addAll(Arrays.asList(mContext.getResources().getStringArray(R.array.categories)));
        mFavoriteLocalDataSource = new FavoriteLocalDataSource(mContext);
        getInformationMoviesGenres(mCategories.get(0));
    }

    public void getInformationMoviesGenres(String category) {
        Subscription subscription = mMovieRepository.getMovieCategory(category)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<ResultResponse>() {
                    @Override
                    public void call(ResultResponse resultResponse) {
                        mMovies.clear();
                        mAdapterShowMovie.addData(resultResponse.getResults());
                        mMovies.addAll(resultResponse.getResults());
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {

                    }
                });
        mCompositeSubscription.add(subscription);
    }

    public void getInformationMovieGenres(String genreId) {
        Subscription subscription = mMovieRepository.getMovieGenres(genreId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<ResultResponse>() {
                    @Override
                    public void call(ResultResponse resultResponse) {
                        mMovies.clear();
                        mAdapterShowMovie.addData(resultResponse.getResults());
                        mMovies.addAll(resultResponse.getResults());
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                    }
                });
        mCompositeSubscription.add(subscription);
    }


    public void onClinkMovie(View view) {
        mMovies.clear();
        switch (view.getId()) {
            case R.id.button_popular:
                getInformationMoviesGenres(mCategories.get(2));
                break;
            case R.id.button_now_playing:
                getInformationMoviesGenres(mCategories.get(1));
                break;
            case R.id.button_top_rate:
                getInformationMoviesGenres(mCategories.get(0));
                break;
            case R.id.button_genres:
                ((Activity) mContext).startActivityForResult(new Intent(mContext, GenresActivity.class), MainActivity.RESPONSE);
                break;
            case R.id.button_upcoming:
                getInformationMoviesGenres(mCategories.get(3));
                break;
            case R.id.button_favorite:
                mFavoriteLocalDataSource.getAllMovieFavorite().subscribe(new Action1<List<Movie>>() {
                    @Override
                    public void call(List<Movie> movies) {
                        mAdapterShowMovie.addData(movies);
                        mMovies.addAll(movies);
                    }
                });
                break;
        }
    }

    public void onStop() {
        mCompositeSubscription.clear();
    }

    @Bindable
    public AdapterShowMovie getAdapterShowMovie() {
        return mAdapterShowMovie;
    }

    public void onClickMovieDetail(Movie movie) {
        Intent intent = new Intent(mContext, MovieDetailActivity.class);
        intent.putExtra(AdapterShowMovie.BUNDLE_MOVIE, movie);
        ((Activity) mContext).startActivityForResult(intent, AdapterShowMovie.KEY_DETAIL);
    }

    public void handleActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == MainActivity.RESPONSE) {
            if (resultCode == Activity.RESULT_OK) {
                Genres genres = data.getParcelableExtra(AdapterGenres.BUNDLE_GENRES);
                getInformationMovieGenres(String.valueOf(genres.getId()));
            }
        } else if (requestCode == AdapterShowMovie.KEY_DETAIL) {
            if (resultCode == AdapterShowGenresDetail.KEY_GENRES_DETAIL) {
                Genres genres = data.getParcelableExtra(AdapterShowGenresDetail.BUNDLE_GENRES_DETAIL);
                getInformationMovieGenres(String.valueOf(genres.getId()));
            } else if (resultCode == AdapterShowCreditDetail.KEY_CREDIT_DETAIL) {
                CreditsResponse.Credit credit = data.getParcelableExtra(AdapterShowCreditDetail.BUNDLE_CREDIT_DETAIL);
                getCastMovieGenres(String.valueOf(credit.getmCastId()));
            } else if (resultCode == AdapterShowCompany.KEY_COMPANY_DETAIL) {
                MovieDetail.Company company = data.getParcelableExtra(AdapterShowCompany.BUNDLE_COMPANY);
                getCompanyMovieGenres(String.valueOf(company.getId()));
            }
        }
    }

    public void getCompanyMovieGenres(String companyId) {
        Subscription subscription = mMovieRepository.getMovieCompany(companyId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<ResultResponse>() {
                    @Override
                    public void call(ResultResponse resultResponse) {
                        mMovies.clear();
                        mAdapterShowMovie.addData(resultResponse.getResults());
                        mMovies.addAll(resultResponse.getResults());
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {

                    }
                });
        mCompositeSubscription.add(subscription);
    }

    public void getCastMovieGenres(String castId) {
        Subscription subscription = mMovieRepository.getMovieCompany(castId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<ResultResponse>() {
                    @Override
                    public void call(ResultResponse resultResponse) {
                        mMovies.clear();
                        mAdapterShowMovie.addData(resultResponse.getResults());
                        mMovies.addAll(resultResponse.getResults());
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {

                    }
                });
        mCompositeSubscription.add(subscription);
    }

    public TextWatcher nameWatcher() {
        return new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String enterCharacter = charSequence.toString().toUpperCase().trim();
                if (enterCharacter == "") {
                    mAdapterShowMovie.addData(mMovies);
                } else {
                    List<Movie> moviesTemp = new ArrayList<>();
                    for (Movie movie : mMovies) {
                        String templateSearch = movie.getTitle().toUpperCase();
                        if (templateSearch.contains(enterCharacter)) {
                            moviesTemp.add(movie);
                        }
                    }
                    mAdapterShowMovie.addData(moviesTemp);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        };
    }
}


