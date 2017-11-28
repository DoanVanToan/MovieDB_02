package com.example.hcm_102_0006.android_project_m.view.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ObservableField;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.hcm_102_0006.android_project_m.R;
import com.example.hcm_102_0006.android_project_m.remote.model.Genres;
import com.example.hcm_102_0006.android_project_m.databinding.ItemGenresBinding;
import com.example.hcm_102_0006.android_project_m.remote.model.MovieDetail;

import java.util.List;

/**
 * Created by hcm-102-0006 on 22/11/2017.
 */

public class AdapterShowGenres extends RecyclerView.Adapter<AdapterShowGenres.GenresViewHolder> {
    private List<Genres> mGenres;
    private List<MovieDetail.Company> mCompanies;
    private Context mContext;

    public AdapterShowGenres(Context context, List<Genres> mGenres,List<MovieDetail.Company> companies) {
        this.mContext = context;
        this.mGenres = mGenres;
        this.mCompanies = companies;
    }

    @Override
    public GenresViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(GenresViewHolder holder, int position) {
    }

    @Override
    public int getItemCount() {
        return mGenres == null ? 0 : mGenres.size();
    }

    public class GenresViewHolder extends RecyclerView.ViewHolder {

        public GenresViewHolder(View itemView) {
            super(itemView);
        }
    }
}
