package com.example.hcm_102_0006.android_project_m.utils;

import android.databinding.BindingAdapter;
import android.support.v7.widget.RecyclerView;

/**
 * Created by hcm-102-0006 on 29/11/2017.
 */

public class DataBindingUtils {
    @BindingAdapter("adapter")
    public static void setRecyclerAdapter(RecyclerView view, RecyclerView.Adapter adapter) {
        view.setAdapter(adapter);
    }
}
