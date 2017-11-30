package com.example.hcm_102_0006.android_project_m.ui.main;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.databinding.ObservableField;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.hcm_102_0006.android_project_m.R;
import com.example.hcm_102_0006.android_project_m.data.model.Movie;
import com.example.hcm_102_0006.android_project_m.databinding.ItemMovieBinding;
import com.example.hcm_102_0006.android_project_m.ui.moviedetail.MovieDetailActivity;


import java.util.List;

/**
 * Created by hcm-102-0006 on 21/11/2017.
 */

public class AdapterShowMovie extends RecyclerView.Adapter<AdapterShowMovie.MyViewHolder> {

    public static final int KEY_DETAIL = 321;
    public static final String BUNDLE_MOVIE = "BUNDLE_MOVIE";
    private Context mContext;
    public List<Movie> mMovies;
    public AdapterShowMovie(Context context, List<Movie> mMovies) {
        this.mContext = context;
        this.mMovies = mMovies;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemMovieBinding movieBinding =
                DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_movie, parent, false);
        return new MyViewHolder(movieBinding);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.setBinding(mMovies.get(position));
    }

    @Override
    public int getItemCount() {
        return mMovies == null ? 0 : mMovies.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public ObservableField<Movie> mMovie = new ObservableField<>();

        public ItemMovieBinding mItemMovieBinding;

        public MyViewHolder(ItemMovieBinding itemMovieBinding) {
            super(itemMovieBinding.getRoot());
            mItemMovieBinding = itemMovieBinding;
        }

        public void setBinding(Movie movie) {
            if (mItemMovieBinding.getItemView() == null) mItemMovieBinding.setItemView(this);
            mMovie.set(movie);
        }

        public void onClickMovieDetail(View view) {
            Intent intent = new Intent(mContext, MovieDetailActivity.class);
            intent.putExtra(BUNDLE_MOVIE, mMovies.get(getAdapterPosition()));
            ((Activity)mContext).startActivityForResult(intent,KEY_DETAIL);
        }
    }
}