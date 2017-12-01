package com.example.hcm_102_0006.android_project_m.utils;

import android.content.Context;
import android.databinding.BindingAdapter;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

/**
 * Created by hcm-102-0006 on 29/11/2017.
 */

public class DataBindingUtils {
    @BindingAdapter("adapter")
    public static void setRecyclerAdapter(RecyclerView view, RecyclerView.Adapter adapter) {
        view.setAdapter(adapter);
    }

    @BindingAdapter({"bind:imageUrl"})
    public static void setImageUrl(ImageView imageView, String url) {
        Context context = imageView.getContext();
        String imagePath = "http://image.tmdb.org/t/p/w185/"+ url;
        Glide.with(context).load(imagePath).into(imageView);
    }
}
