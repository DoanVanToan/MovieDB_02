package com.example.hcm_102_0006.android_project_m.remote.model;

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
    @SerializedName("genres")
    private List<Genres> mGenres;
    @SerializedName("videos")
    private VideoResponse mVideos;

    public MovieDetail(
            int id, String posterPath, List<Company> productionCompanies,
            float voteAverage, String overview, String originalTitle) {
        this.mId = id;
        this.mPosterPath = posterPath;
        this.mProductionCompanies = productionCompanies;
        this.mVoteAverage = voteAverage;
        this.mOverview = overview;
        this.mOriginalTitle = originalTitle;
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

    public String getPosterPath() {
        return mPosterPath;
    }

    public void setPosterPath(String posterPath) {
        this.mPosterPath = posterPath;
    }

    public List<Company> getProductionPompanies() {
        return mProductionCompanies;
    }

    public void setProductionCompanies(List<Company> productionCompanies) {
        this.mProductionCompanies = productionCompanies;
    }

    public float getVoteAverage() {
        return mVoteAverage;
    }

    public void setVoteAverage(float voteAverage) {
        this.mVoteAverage = voteAverage;
    }

    public String getOverview() {
        return mOverview;
    }

    public void setOverview(String overview) {
        this.mOverview = overview;
    }

    public String getOriginalTitle() {
        return mOriginalTitle;
    }

    public void setOriginalTitle(String originalTitle) {
        this.mOriginalTitle = originalTitle;
    }

    public List<Genres> getmGenres() {
        return mGenres;
    }

    public void setmGenres(List<Genres> mGenres) {
        this.mGenres = mGenres;
    }

    public VideoResponse getmVideos() {
        return mVideos;
    }

    public void setmVideos(VideoResponse mVideos) {
        this.mVideos = mVideos;
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

    public String getAllProduction() {
        String allCompany = "Productions : ";
        for (Company company : mProductionCompanies) {
            allCompany += company.getName() + ", ";
        }
        if (allCompany.length() > 2) {
            allCompany = allCompany.substring(0, allCompany.length() - 2);
        }
        return allCompany;
    }

    public String getAllGenres() {
        String genres = "Genres : ";
        for (Genres genre : mGenres) {
            genres += genre.getName() + ", ";
        }
        if (genres.length() > 2) {
            genres = genres.substring(0, genres.length() - 2);
        }
        return genres;
    }
}
