package com.example.hcm_102_0006.android_project_m.view.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.databinding.BindingAdapter;
import android.databinding.DataBindingUtil;
import android.databinding.ObservableField;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.hcm_102_0006.android_project_m.R;
import com.example.hcm_102_0006.android_project_m.service.model.Movie;
import com.example.hcm_102_0006.android_project_m.databinding.ItemMovieBinding;
import com.example.hcm_102_0006.android_project_m.view.ui.MovieDetailActivity;

import java.util.List;

/**
 * Created by hcm-102-0006 on 21/11/2017.
 */

public class AdapterShowMovie extends RecyclerView.Adapter<AdapterShowMovie.MyViewHolder> {

    public static final int KEY_DETAIL = 321;
    public static final String KEY_MOVIE = "ID_MOVIE";
    private static Activity mContext;
    private static List<Movie> sMovies;
    public AdapterShowMovie(Activity context, List<Movie> mMovies) {
        this.mContext = context;
        this.sMovies = mMovies;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemMovieBinding movieBinding =
                DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_movie, parent, false);
        return new MyViewHolder(movieBinding);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.setBinding(sMovies.get(position));
    }

    @Override
    public int getItemCount() {
        return sMovies == null ? 0 : sMovies.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        public ObservableField<String> title = new ObservableField<>();
        public ObservableField<String> voteAverage = new ObservableField<>();
        public ObservableField<String> profileImage = new ObservableField<>();

        public ItemMovieBinding mItemMovieBinding;

        public MyViewHolder(ItemMovieBinding itemMovieBinding) {
            super(itemMovieBinding.getRoot());
            mItemMovieBinding = itemMovieBinding;
        }

        public void setBinding(Movie movie) {
            if (mItemMovieBinding.getItemView() == null) mItemMovieBinding.setItemView(this);
            title.set(movie.getTitle());
            voteAverage.set(movie.getVoteAverage() + "");
            profileImage.set(movie.getPosterPath());
        }

        @BindingAdapter("imageUrl")
        public static void setImageUrl(ImageView imageView, String url) {
            Context context = imageView.getContext();
            String imagePath = "http://image.tmdb.org/t/p/w185/"+ url;
            Glide.with(context).load(imagePath).into(imageView);
        }

        public void onClickMovieDetail(View view){
            Intent intent = new Intent(mContext, MovieDetailActivity.class);
            intent.putExtra(KEY_MOVIE,sMovies.get(getAdapterPosition()));
            mContext.startActivityForResult(intent,KEY_DETAIL);
        }

    }
}
