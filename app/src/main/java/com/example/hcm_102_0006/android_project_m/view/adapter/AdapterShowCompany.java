package com.example.hcm_102_0006.android_project_m.view.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.hcm_102_0006.android_project_m.R;
import com.example.hcm_102_0006.android_project_m.data.model.MovieDetail;
import com.example.hcm_102_0006.android_project_m.databinding.ItemCompanyBinding;
import com.example.hcm_102_0006.android_project_m.ui.moviedetail.MovieDetailViewModel;

import java.util.List;

/**
 * Created by hcm-102-0006 on 22/11/2017.
 */

public class AdapterShowCompany extends RecyclerView.Adapter<AdapterShowCompany.CompanyViewHolder> {
    private List<MovieDetail.Company> mCompanies;
    private Context mContext;
    public static final String BUNDLE_COMPANY = "BUNDLE_COMPANY";
    public static final int KEY_COMPANY_DETAIL = 789;
    private MovieDetailViewModel mMovieDetailViewModel;

    public AdapterShowCompany(Context context, List<MovieDetail.Company> companies) {
        this.mContext = context;
        this.mCompanies = companies;
    }

    public void addData(List<MovieDetail.Company> companies) {
        if (companies != null) {
            mCompanies.clear();
            mCompanies.addAll(companies);
            notifyDataSetChanged();
        }
    }

    public void setMovieDetailViewModel(MovieDetailViewModel movieDetailViewModel) {
        mMovieDetailViewModel = movieDetailViewModel;
    }

    @Override
    public CompanyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemCompanyBinding itemCompanyBinding =
                DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_company, parent, false);
        return new CompanyViewHolder(itemCompanyBinding);
    }

    @Override
    public void onBindViewHolder(CompanyViewHolder holder, int position) {
        holder.binding(mCompanies.get(position));
    }

    @Override
    public int getItemCount() {
        return mCompanies == null ? 0 : mCompanies.size();
    }

    public class CompanyViewHolder extends RecyclerView.ViewHolder {
        public ItemCompanyBinding mItemCompanyBinding;
        public CompanyViewHolder(ItemCompanyBinding itemCompanyBinding) {
            super(itemCompanyBinding.getRoot());
            this.mItemCompanyBinding = itemCompanyBinding;
        }

        public void binding(MovieDetail.Company company){
            mItemCompanyBinding.setCompany(company);
            mItemCompanyBinding.setViewModel(mMovieDetailViewModel);
            mItemCompanyBinding.executePendingBindings();
        }
    }
}