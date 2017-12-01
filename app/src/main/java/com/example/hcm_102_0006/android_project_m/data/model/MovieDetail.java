package com.example.hcm_102_0006.android_project_m.data.model;

import android.databinding.BaseObservable;
import android.os.Parcel;
import android.os.Parcelable;
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
    @SerializedName("credits")
    private CreditsResponse mCredits;

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

    public CreditsResponse getmCredits() {
        return mCredits;
    }

    public void setmCredits(CreditsResponse mCredits) {
        this.mCredits = mCredits;
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

    public static class Company implements Parcelable{
        private int id;
        private String name;

        protected Company(Parcel in) {
            id = in.readInt();
            name = in.readString();
        }

        public static final Creator<Company> CREATOR = new Creator<Company>() {
            @Override
            public Company createFromParcel(Parcel in) {
                return new Company(in);
            }

            @Override
            public Company[] newArray(int size) {
                return new Company[size];
            }
        };

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

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeInt(id);
            parcel.writeString(name);
        }
    }
}