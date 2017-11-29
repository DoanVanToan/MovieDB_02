package com.example.hcm_102_0006.android_project_m.view.adapter;

import android.content.Context;
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
 * Created by hcm-102-0006 on 29/11/2017.
 */

public class AdapterShowGenresDetail extends RecyclerView.Adapter<AdapterShowGenresDetail.GenresViewHolder> {
    private List<Genres> mGenres;
    private Context mContext;
    public static final String BUNDLE_GENRES_DETAIL = "BUNDLE_GENRES_DETAIL";

    public AdapterShowGenresDetail(Context context, List<Genres> genres) {
        this.mGenres = genres;
        this.mContext = context;
    }

    @Override
    public GenresViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemGenresBinding itemGenresBinding =
                DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_genres, parent, false);
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
        public ObservableField<Genres> mGenre = new ObservableField<>();
        public ItemGenresBinding mItemGenresBinding;

        public GenresViewHolder(ItemGenresBinding itemGenresBinding) {
            super(itemGenresBinding.getRoot());
            this.mItemGenresBinding = itemGenresBinding;
        }

        public void binding(Genres genre) {
            /*if (mItemGenresBinding.getViewModel() == null)
                mItemGenresBinding.setViewModel(this);
            mGenre.set(genre);*/
        }
    }
}