package com.example.hcm_102_0006.android_project_m.ui.genre;

import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.widget.Toast;

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
                        // e tu xu ly doan nay nhe
                    }
                });
        mCompositeSubscription.add(subscription);
    }

    public void onItemGenreClicked(Genres genres){
        Toast.makeText(mContext, genres.getName(), Toast.LENGTH_SHORT).show();
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
    // da hieu roi a. hay ghe a
    // a thu lam e phan onclich vs
    // ko e lam a xem thu nha

    // dạ,vậy là mình xử lý khi trong onstart khi quay lại app nó tụ refesh , khi thoat nó clear het du lieu luon phia ko
}
