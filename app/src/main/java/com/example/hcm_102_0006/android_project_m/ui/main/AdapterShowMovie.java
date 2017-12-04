package com.example.hcm_102_0006.android_project_m.ui.main;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.hcm_102_0006.android_project_m.R;
import com.example.hcm_102_0006.android_project_m.data.model.Movie;
import com.example.hcm_102_0006.android_project_m.data.source.remote.MovieApi;
import com.example.hcm_102_0006.android_project_m.databinding.ItemMovieBinding;

import java.util.List;

/**
 * Created by hcm-102-0006 on 21/11/2017.
 */

public class AdapterShowMovie extends RecyclerView.Adapter<AdapterShowMovie.MyViewHolder> {

    public static final int KEY_DETAIL = 321;
    public static final String BUNDLE_MOVIE = "BUNDLE_MOVIE";
    private Context mContext;
    private List<Movie> mMovies;
    private MainViewModel mMainViewModel;

    public AdapterShowMovie(Context context, List<Movie> mMovies) {
        this.mContext = context;
        this.mMovies = mMovies;
    }

    public void addData(List<Movie> movies) {
        if (movies != null) {
            mMovies.clear();
            mMovies.addAll(movies);
            notifyDataSetChanged();
        }
    }

    public void setMainViewModel(MainViewModel mainViewModel) {
        mMainViewModel = mainViewModel;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemMovieBinding movieBinding =
                DataBindingUtil.inflate(LayoutInflater.from(
                        parent.getContext()), R.layout.item_movie, parent, false);
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

        public ItemMovieBinding mItemMovieBinding;

        public MyViewHolder(ItemMovieBinding itemMovieBinding) {
            super(itemMovieBinding.getRoot());
            mItemMovieBinding = itemMovieBinding;
        }

        public void setBinding(Movie movie) {
            if (!movie.getPosterPath().contains(MovieApi.IMAGE_URL)){
                movie.setPosterPath(MovieApi.IMAGE_URL + movie.getPosterPath());
            }
            mItemMovieBinding.setMovie(movie);
            mItemMovieBinding.setViewModel(mMainViewModel);
            mItemMovieBinding.executePendingBindings();
        }
    }
}