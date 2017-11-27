package com.example.hcm_102_0006.android_project_m.view.adapter;

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
import com.example.hcm_102_0006.android_project_m.databinding.ItemGenresMovieBinding;
import com.example.hcm_102_0006.android_project_m.remote.model.Genres;

import java.util.List;

/**
 * Created by hcm-102-0006 on 22/11/2017.
 */

public class AdapterGenres extends RecyclerView.Adapter<AdapterGenres.MyViewHolder> {
    public static final String KEY_RESULT = "GENRES";
    private Context mContext;
    private List<Genres> mGenres;

    public AdapterGenres(Context context, List<Genres> mGenres) {
        this.mContext = context;
        this.mGenres = mGenres;
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemGenresMovieBinding itemGenresMovieBinding =
                DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_genres_movie, parent, false);
        return new MyViewHolder(itemGenresMovieBinding);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.setBinding(mGenres.get(position));
    }

    @Override
    public int getItemCount() {
        return mGenres == null ? 0 : mGenres.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public ObservableField<String> title = new ObservableField<>();

        public ItemGenresMovieBinding itemGenresMovieBinding;

        public MyViewHolder(ItemGenresMovieBinding itemGenresMovieBinding) {
            super(itemGenresMovieBinding.getRoot());
            this.itemGenresMovieBinding = itemGenresMovieBinding;
        }

        public void setBinding(Genres genres) {
            if (itemGenresMovieBinding.getItemGenres() == null) itemGenresMovieBinding.setItemGenres(this);
            title.set(genres.getName());
        }

        public void onResultGenre(View view) {
            Intent intent = new Intent();
            intent.putExtra(KEY_RESULT, mGenres.get(getAdapterPosition()));
            ((Activity)mContext).setResult(Activity.RESULT_OK, intent);
            ((Activity)mContext).finish();
        }

    }
}
