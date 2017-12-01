package com.example.hcm_102_0006.android_project_m.ui.genre;

import android.databinding.DataBindingUtil;
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
public class AdapterGenres extends RecyclerView.Adapter<AdapterGenres.MyViewHolder> {
    public static final String  BUNDLE_GENRES = "BUNDLE_GENRES";
    private List<Genres> mGenres;
    private GenresViewModel mViewModel;

    public AdapterGenres(List<Genres> genres) {
        mGenres = genres;
    }

    public void addData(List<Genres> genres) {
        if (genres == null) {
            return;
        }
        mGenres.addAll(genres);
        notifyDataSetChanged();
    }

    public void setViewModel(GenresViewModel viewModel) {
        mViewModel = viewModel;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemGenresBinding itemGenres2Binding =
                DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                        R.layout.item_genres, parent, false);
        return new MyViewHolder(itemGenres2Binding);
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

        private ItemGenresBinding mBinding;

        public MyViewHolder(ItemGenresBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
        }

        public void setBinding(Genres genres) {
            mBinding.setGenre(genres);
            mBinding.setViewModel(mViewModel);
            mBinding.executePendingBindings();
        }
    }
}