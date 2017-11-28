package com.example.hcm_102_0006.android_project_m.viewmodel;

import android.content.Context;
import android.databinding.BindingAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

/**
 * Created by hcm-102-0006 on 28/11/2017.
 */

public class BindingUtils {
    @BindingAdapter({"bind:imageUrl"})
    public static void setImageUrl(ImageView imageView, String url) {
        Context context = imageView.getContext();
        String imagePath = "http://image.tmdb.org/t/p/w185/"+ url;
        Glide.with(context).load(imagePath).into(imageView);
    }
}
