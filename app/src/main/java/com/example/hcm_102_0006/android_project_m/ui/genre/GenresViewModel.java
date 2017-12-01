package com.example.hcm_102_0006.android_project_m.ui.genre;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.widget.Toast;

import com.example.hcm_102_0006.android_project_m.R;
import com.example.hcm_102_0006.android_project_m.data.model.GenreResponse;
import com.example.hcm_102_0006.android_project_m.data.model.Genres;
import com.example.hcm_102_0006.android_project_m.data.source.GenreRepository;

import java.util.ArrayList;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by hcm-102-0006 on 29/11/2017.
 */

public class GenresViewModel extends BaseObservable {
    private GenreRepository mGenreRepository;
    private AdapterGenres mAdapter;
    private CompositeSubscription mCompositeSubscription;
    private Context mContext;

    public GenresViewModel(Context context, GenreRepository genreRepository) {
        mContext = context;
        mGenreRepository = genreRepository;
        mAdapter = new AdapterGenres(new ArrayList<Genres>());
        mAdapter.setViewModel(this);
        mCompositeSubscription = new CompositeSubscription();
    }

    public void onStart() {
        Subscription subscription = mGenreRepository.getGenres()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<GenreResponse>() {
                    @Override
                    public void call(GenreResponse genreResponse) {
                        mAdapter.addData(genreResponse.getGenres());
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        Toast.makeText(mContext,
                                mContext.getString(R.string.msg_connection_network),
                                Toast.LENGTH_SHORT).show();
                    }
                });
        mCompositeSubscription.add(subscription);
    }

    public void onItemGenreClicked(Genres genres){
        Toast.makeText(mContext, genres.getName(), Toast.LENGTH_SHORT).show();
        Intent intent = new Intent();
        intent.putExtra(AdapterGenres.BUNDLE_GENRES,genres);
        ((Activity)mContext).setResult(Activity.RESULT_OK,intent);
        ((Activity)mContext).finish();
    }

    public void onStop() {
        mCompositeSubscription.clear();
    }

    @Bindable
    public AdapterGenres getAdapter() {
        return mAdapter;
    }

    public void setAdapter(AdapterGenres adapter) {
        mAdapter = adapter;
    }
}
