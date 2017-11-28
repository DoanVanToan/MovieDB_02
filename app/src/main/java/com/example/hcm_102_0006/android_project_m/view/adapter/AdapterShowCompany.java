package com.example.hcm_102_0006.android_project_m.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.example.hcm_102_0006.android_project_m.data.model.MovieDetail;

import java.util.List;

/**
 * Created by hcm-102-0006 on 22/11/2017.
 */

public class AdapterShowCompany extends RecyclerView.Adapter<AdapterShowCompany.CompanyViewHolder> {
    private List<MovieDetail.Company> mCompany;
    private Context mContext;

    public AdapterShowCompany(Context context, List<MovieDetail.Company> company) {
        this.mContext = context;
        this.mCompany = company;
    }

    @Override
    public CompanyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(CompanyViewHolder holder, int position) {
    }

    @Override
    public int getItemCount() {
        return mCompany == null ? 0 : mCompany.size();
    }

    public class CompanyViewHolder extends RecyclerView.ViewHolder {
        public CompanyViewHolder(View itemView) {
            super(itemView);
        }
    }
}
