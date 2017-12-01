package com.example.hcm_102_0006.android_project_m.ui.moviedetail;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.databinding.DataBindingUtil;
import android.view.View;
import android.widget.Toast;

import com.example.hcm_102_0006.android_project_m.BuildConfig;
import com.example.hcm_102_0006.android_project_m.R;
import com.example.hcm_102_0006.android_project_m.data.model.CreditsResponse;
import com.example.hcm_102_0006.android_project_m.data.model.Genres;
import com.example.hcm_102_0006.android_project_m.data.model.Movie;
import com.example.hcm_102_0006.android_project_m.data.model.MovieDetail;
import com.example.hcm_102_0006.android_project_m.data.source.MovieRepository;
import com.example.hcm_102_0006.android_project_m.data.source.local.FavoriteLocalDataSource;
import com.example.hcm_102_0006.android_project_m.databinding.ActivityMovieDetailBinding;
import com.example.hcm_102_0006.android_project_m.view.adapter.AdapterShowCompany;
import com.example.hcm_102_0006.android_project_m.view.adapter.AdapterShowCreditDetail;
import com.example.hcm_102_0006.android_project_m.view.adapter.AdapterShowGenresDetail;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;

import java.util.ArrayList;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by hcm-102-0006 on 30/11/2017.
 */

public class MovieDetailViewModel extends BaseObservable implements YouTubePlayer.OnInitializedListener {
    private Context mContext;
    private CompositeSubscription mCompositeSubscription;
    private FavoriteLocalDataSource mFavoriteLocalDataSource;
    private MovieRepository mMovieRepository;
    private Movie mMovie;
    private MovieDetail mMovieDetail;
    private ActivityMovieDetailBinding mActivityMovieDetailBinding;

    public AdapterShowCompany mAdapterShowCompany;
    public AdapterShowCreditDetail mAdapterShowCreditDetail;
    public AdapterShowGenresDetail mAdapterShowGenresDetail;

    public MovieDetailViewModel(Context context, FavoriteLocalDataSource favoriteLocalDataSource, MovieRepository movieRepository, Movie movie) {
        mContext = context;
        mFavoriteLocalDataSource = favoriteLocalDataSource;
        mMovieRepository = movieRepository;
        mCompositeSubscription = new CompositeSubscription();
        mMovie = movie;
        mAdapterShowCompany = new AdapterShowCompany(context, new ArrayList<MovieDetail.Company>());
        mAdapterShowCompany.setMovieDetailViewModel(this);
        mAdapterShowCreditDetail = new AdapterShowCreditDetail(context, new ArrayList<CreditsResponse.Credit>());
        mAdapterShowCreditDetail.setMovieDetailViewModel(this);
        mAdapterShowGenresDetail = new AdapterShowGenresDetail(context, new ArrayList<Genres>());
        mAdapterShowGenresDetail.setMovieDetailViewModel(this);
        getInformationDetailMovie(String.valueOf(movie.getId()));
        mActivityMovieDetailBinding = DataBindingUtil
                .setContentView((Activity) context, R.layout.activity_movie_detail);
        mActivityMovieDetailBinding.setViewModel(this);
    }

    public void getInformationDetailMovie(final String movieId) {
        Subscription subscription = mMovieRepository.getMovieDetail(movieId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<MovieDetail>() {
                    @Override
                    public void call(MovieDetail movieDetail) {
                        mMovieDetail = movieDetail;
                        mAdapterShowCompany.addData(movieDetail.getProductionPompanies());
                        mAdapterShowCreditDetail.addData(movieDetail.getmCredits().getmCast());
                        mAdapterShowGenresDetail.addData(movieDetail.getmGenres());
                        mActivityMovieDetailBinding.setMovieDetailBinding(mMovieDetail);
                        mFavoriteLocalDataSource.isFavoriteMovie(movieId).subscribe(new Action1<Boolean>() {
                            @Override
                            public void call(Boolean aBoolean) {
                                if (aBoolean) {
                                    mActivityMovieDetailBinding.btnSaveOrDeleteFavorite.setFavorite(true);
                                } else {
                                    mActivityMovieDetailBinding.btnSaveOrDeleteFavorite.setFavorite(false);
                                }
                            }
                        }, new Action1<Throwable>() {
                            @Override
                            public void call(Throwable throwable) {
                                Toast.makeText(mContext, mContext.getResources().getString(R.string.msg_connection_network), Toast.LENGTH_SHORT).show();
                            }
                        });
                        mActivityMovieDetailBinding.youTubeShowVideo.initialize(
                                BuildConfig.YOUTUBE_KEY, MovieDetailViewModel.this);

                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {

                    }
                });
        mCompositeSubscription.add(subscription);
    }

    public Movie getMovie() {
        return mMovie;
    }


    public void saveOrDeleteMovieFavorite() {
        boolean isFavorite = mActivityMovieDetailBinding.btnSaveOrDeleteFavorite.isFavorite();
        if (isFavorite) {
            mFavoriteLocalDataSource.deleteMovie(mMovie).subscribe(new Action1<Boolean>() {
                @Override
                public void call(Boolean aBoolean) {
                    if (aBoolean) {
                        mActivityMovieDetailBinding.btnSaveOrDeleteFavorite.setFavorite(false);
                        Toast.makeText(mContext, mContext.getResources().getString(R.string.msg_delete_movie), Toast.LENGTH_SHORT).show();
                    }
                }
            });

        } else {
            mFavoriteLocalDataSource.insertMovie(mMovie).subscribe(new Action1<Boolean>() {
                @Override
                public void call(Boolean aBoolean) {
                    if (aBoolean) {
                        mActivityMovieDetailBinding.btnSaveOrDeleteFavorite.setFavorite(true);
                        Toast.makeText(mContext, mContext.getResources().getString(R.string.msg_save_movie), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    @Bindable
    public AdapterShowCompany getAdapterShowCompany() {
        return mAdapterShowCompany;
    }

    public AdapterShowCreditDetail getAdapterShowCreditDetail() {
        return mAdapterShowCreditDetail;
    }

    public AdapterShowGenresDetail getAdapterShowGenresDetail() {
        return mAdapterShowGenresDetail;
    }

    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
        if (!b) {
            youTubePlayer.loadVideo(mMovieDetail.getmVideos().getmResults().get(0).getmKey());
        }
    }

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
        if (youTubeInitializationResult.isUserRecoverableError()) {
            youTubeInitializationResult.getErrorDialog((Activity) mContext, 1).show();
        } else {
            String errorMessage = String.format("ABC", youTubeInitializationResult.toString());
            Toast.makeText(mContext, errorMessage, Toast.LENGTH_SHORT).show();
        }
    }

    public void handlerActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1) {
            mActivityMovieDetailBinding.youTubeShowVideo.initialize(BuildConfig.YOUTUBE_KEY, this);
        }
    }

    public void onResultCompany(MovieDetail.Company company) {
        Intent intent = new Intent();
        intent.putExtra(AdapterShowCompany.BUNDLE_COMPANY, company);
        ((Activity) mContext).setResult(AdapterShowCompany.KEY_COMPANY_DETAIL, intent);
        ((Activity) mContext).finish();
    }

    public void onResultGenres(Genres genres) {
        Intent intent = new Intent();
        intent.putExtra(AdapterShowGenresDetail.BUNDLE_GENRES_DETAIL, genres);
        ((Activity) mContext).setResult(AdapterShowGenresDetail.KEY_GENRES_DETAIL, intent);
        ((Activity) mContext).finish();
    }

    public void onResultCredit(CreditsResponse.Credit credit) {
        Intent intent = new Intent();
        intent.putExtra(AdapterShowCreditDetail.BUNDLE_CREDIT_DETAIL, credit);
        ((Activity) mContext).setResult(AdapterShowCreditDetail.KEY_CREDIT_DETAIL, intent);
        ((Activity) mContext).finish();
    }
}
