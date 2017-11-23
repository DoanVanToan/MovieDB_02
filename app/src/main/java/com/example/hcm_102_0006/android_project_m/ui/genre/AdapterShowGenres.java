package com.example.hcm_102_0006.android_project_m.ui.genre;

import android.databinding.DataBindingUtil;
import android.databinding.ObservableField;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.hcm_102_0006.android_project_m.R;
import com.example.hcm_102_0006.android_project_m.data.model.Genres;
import com.example.hcm_102_0006.android_project_m.databinding.ItemGenresBinding;

import java.util.List;

/**
 * Created by hcm-102-0006 on 22/11/2017.
 */

public class AdapterShowGenres extends RecyclerView.Adapter<AdapterShowGenres.GenresViewHolder> {
    private List<Genres> mGenres;

    public AdapterShowGenres(List<Genres> mGenres) {
        this.mGenres = mGenres;
    }

    @Override
    public GenresViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemGenresBinding itemGenresBinding =
                DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_genres, parent, false);
        return new GenresViewHolder(itemGenresBinding);
    }

    @Override
    public void onBindViewHolder(GenresViewHolder holder, int position) {
        holder.setBinding(mGenres.get(position));
    }

    @Override
    public int getItemCount() {
        return mGenres == null ? 0 : mGenres.size();
    }

    public class GenresViewHolder extends RecyclerView.ViewHolder {
        public ObservableField<String> mGenresTitle = new ObservableField<>("");
        public ItemGenresBinding mItemGenresBinding;

        public GenresViewHolder(ItemGenresBinding itemGenresBinding) {
            super(itemGenresBinding.getRoot());
            mItemGenresBinding = itemGenresBinding;
        }

        public void setBinding(Genres genres) {
            if (mItemGenresBinding == null) mItemGenresBinding.setItemView(this);
            mGenresTitle.set(genres.getName());
        }
    }
}
