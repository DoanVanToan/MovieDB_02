package com.example.hcm_102_0006.android_project_m.data.model;

import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.BindingAdapter;
import android.os.Parcel;
import android.os.Parcelable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.google.gson.annotations.SerializedName;
import java.util.List;

/**
 * Created by hcm-102-0006 on 24/11/2017.
 */

public class MovieDetail extends BaseObservable implements Parcelable {
    @SerializedName("id")
    private int mId;
    @SerializedName("poster_path")
    private String mPosterPath;
    @SerializedName("production_companies")
    private List<Company> mProductionCompanies;
    @SerializedName("vote_average")
    private float mVoteAverage;
    @SerializedName("overview")
    private String mOverview;
    @SerializedName("original_title")
    private String mOriginalTitle;

    public MovieDetail(
            int id, String poster_path, List<Company> production_companies,
            float vote_average, String overview, String original_title) {
        this.mId = id;
        this.mPosterPath = poster_path;
        this.mProductionCompanies = production_companies;
        this.mVoteAverage = vote_average;
        this.mOverview = overview;
        this.mOriginalTitle = original_title;
    }

    protected MovieDetail(Parcel in) {
        mId = in.readInt();
        mPosterPath = in.readString();
        mVoteAverage = in.readFloat();
        mOverview = in.readString();
        mOriginalTitle = in.readString();
    }

    public static final Creator<MovieDetail> CREATOR = new Creator<MovieDetail>() {
        @Override
        public MovieDetail createFromParcel(Parcel in) {
            return new MovieDetail(in);
        }

        @Override
        public MovieDetail[] newArray(int size) {
            return new MovieDetail[size];
        }
    };

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        this.mId = id;
    }

    public String getPoster_path() {
        return mPosterPath;
    }

    public void setPoster_path(String poster_path) {
        this.mPosterPath = poster_path;
    }

    public List<Company> getProduction_companies() {
        return mProductionCompanies;
    }

    public void setProduction_companies(List<Company> production_companies) {
        this.mProductionCompanies = production_companies;
    }

    public float getVote_average() {
        return mVoteAverage;
    }

    public void setVote_average(float vote_average) {
        this.mVoteAverage = vote_average;
    }

    public String getOverview() {
        return mOverview;
    }

    public void setOverview(String overview) {
        this.mOverview = overview;
    }

    public String getOriginal_title() {
        return mOriginalTitle;
    }

    public void setOriginal_title(String original_title) {
        this.mOriginalTitle = original_title;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(mId);
        parcel.writeString(mPosterPath);
        parcel.writeFloat(mVoteAverage);
        parcel.writeString(mOverview);
        parcel.writeString(mOriginalTitle);
    }

    public class Company {
        private int id;
        private String name;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    @BindingAdapter("imageUrl")
    public static void setImageUrl(ImageView imageView, String url) {
        Context context = imageView.getContext();
        String imagePath = "http://image.tmdb.org/t/p/w185/" + url;
        Glide.with(context)
                .load(imagePath)
                .fitCenter()
                .into(imageView);
    }


}
