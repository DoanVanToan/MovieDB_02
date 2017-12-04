package com.example.hcm_102_0006.android_project_m.view.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.hcm_102_0006.android_project_m.R;
import com.example.hcm_102_0006.android_project_m.databinding.ItemCreditsBinding;
import com.example.hcm_102_0006.android_project_m.data.model.CreditsResponse;
import com.example.hcm_102_0006.android_project_m.ui.moviedetail.MovieDetailViewModel;

import java.util.List;

/**
 * Created by hcm-102-0006 on 29/11/2017.
 */

public class AdapterShowCreditDetail extends RecyclerView.Adapter<AdapterShowCreditDetail.CreditViewHolder> {
    public static final String BUNDLE_CREDIT_DETAIL = "BUNDLE_CREDIT_DETAIL";
    public static final int KEY_CREDIT_DETAIL = 456;
    private List<CreditsResponse.Credit> mCredits;
    private Context mContext;
    public MovieDetailViewModel mMovieDetailViewModel;

    public AdapterShowCreditDetail(Context context, List<CreditsResponse.Credit> credits) {
        this.mCredits = credits;
        this.mContext = context;
    }

    public void addData(List<CreditsResponse.Credit> credits) {
        if (credits != null) {
            mCredits.clear();
            mCredits.addAll(credits);
            notifyDataSetChanged();
        }
    }

    public void setMovieDetailViewModel(MovieDetailViewModel movieDetailViewModel) {
        mMovieDetailViewModel = movieDetailViewModel;
    }

    @Override
    public CreditViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemCreditsBinding itemCreditsBinding =
                DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_credits, parent, false);
        return new CreditViewHolder(itemCreditsBinding);
    }

    @Override
    public void onBindViewHolder(CreditViewHolder holder, int position) {
        holder.binding(mCredits.get(position));
    }

    @Override
    public int getItemCount() {
        return mCredits == null ? 0 : mCredits.size();
    }

    public class CreditViewHolder extends RecyclerView.ViewHolder {
        public ItemCreditsBinding mItemCreditsBinding;

        public CreditViewHolder(ItemCreditsBinding itemCreditsBinding) {
            super(itemCreditsBinding.getRoot());
            this.mItemCreditsBinding = itemCreditsBinding;
        }

        public void binding(CreditsResponse.Credit credit) {
            mItemCreditsBinding.setCredit(credit);
            mItemCreditsBinding.setViewModel(mMovieDetailViewModel);
            mItemCreditsBinding.executePendingBindings();
        }
    }
}