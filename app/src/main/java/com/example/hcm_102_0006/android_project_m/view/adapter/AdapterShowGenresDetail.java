package com.example.hcm_102_0006.android_project_m.view.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.hcm_102_0006.android_project_m.R;
import com.example.hcm_102_0006.android_project_m.data.model.Genres;
import com.example.hcm_102_0006.android_project_m.databinding.ItemGenreDetailBinding;
import com.example.hcm_102_0006.android_project_m.ui.moviedetail.MovieDetailViewModel;

import java.util.List;

/**
 * Created by hcm-102-0006 on 29/11/2017.
 */

public class AdapterShowGenresDetail extends RecyclerView.Adapter<AdapterShowGenresDetail.GenresViewHolder> {
    private List<Genres> mGenres;
    private Context mContext;
    public static final String BUNDLE_GENRES_DETAIL = "BUNDLE_GENRES_DETAIL";
    public static final int KEY_GENRES_DETAIL = 123;
    public MovieDetailViewModel mMovieDetailViewModel;

    public AdapterShowGenresDetail(Context context, List<Genres> genres) {
        this.mGenres = genres;
        this.mContext = context;
    }

    public void addData(List<Genres> genres) {
        if (genres != null) {
            mGenres.clear();
            mGenres.addAll(genres);
            notifyDataSetChanged();
        }
    }

    public void setMovieDetailViewModel(MovieDetailViewModel movieDetailViewModel) {
        mMovieDetailViewModel = movieDetailViewModel;
    }

    @Override
    public GenresViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemGenreDetailBinding itemGenresBinding =
                DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_genre_detail, parent, false);
        return new GenresViewHolder(itemGenresBinding);
    }

    @Override
    public void onBindViewHolder(GenresViewHolder holder, int position) {
        holder.binding(mGenres.get(position));
    }

    @Override
    public int getItemCount() {
        return mGenres == null ? 0 : mGenres.size();
    }

    public class GenresViewHolder extends RecyclerView.ViewHolder {
        public ItemGenreDetailBinding mItemGenresBinding;

        public GenresViewHolder(ItemGenreDetailBinding itemGenresBinding) {
            super(itemGenresBinding.getRoot());
            this.mItemGenresBinding = itemGenresBinding;
        }

        public void binding(Genres genre) {
            mItemGenresBinding.setGenre(genre);
            mItemGenresBinding.setItemView(mMovieDetailViewModel);
            mItemGenresBinding.executePendingBindings();
        }

    }
}