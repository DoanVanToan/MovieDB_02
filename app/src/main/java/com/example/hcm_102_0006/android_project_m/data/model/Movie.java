package com.example.hcm_102_0006.android_project_m.data.model;

import android.content.ContentValues;
import android.database.Cursor;
import android.os.Parcel;
import android.os.Parcelable;

import com.example.hcm_102_0006.android_project_m.data.source.local.MovieTable;
import com.google.gson.annotations.SerializedName;

/**
 * Created by hcm-102-0006 on 21/11/2017.
 */
public class Movie implements Parcelable {
    @SerializedName("id")
    private String mId;
    @SerializedName("vote_average")
    private float mVoteAverage;
    @SerializedName("title")
    private String mTitle;
    @SerializedName("overview")
    private String mOverview;
    @SerializedName("poster_path")
    private String mPosterPath;

    public Movie() {
    }

    public Movie(Cursor cursor) {
        mId = cursor.getString(cursor.getColumnIndex(MovieTable.MovieEntry.COLUMN_MOVIEID));
        mVoteAverage = cursor.getFloat(cursor.getColumnIndex(MovieTable.MovieEntry.COLUMN_VOTE_AVERAGE));
        mTitle = cursor.getString(cursor.getColumnIndex(MovieTable.MovieEntry.COLUMN_TITLE));
        mOverview = cursor.getString(cursor.getColumnIndex(MovieTable.MovieEntry.COLUM_OVERVIEW));
        mPosterPath = cursor.getString(cursor.getColumnIndex(MovieTable.MovieEntry.COLUM_POTER_PATH));
    }

    protected Movie(Parcel in) {
        mId = in.readString();
        mVoteAverage = in.readFloat();
        mTitle = in.readString();
        mOverview = in.readString();
        mPosterPath = in.readString();
    }

    public static final Creator<Movie> CREATOR = new Creator<Movie>() {
        @Override
        public Movie createFromParcel(Parcel in) {
            return new Movie(in);
        }

        @Override
        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };

    public ContentValues getContentValues() {
        ContentValues contentValues = new ContentValues();
        if (mId != null) {
            contentValues.put(MovieTable.MovieEntry.COLUMN_MOVIEID, mId);
        }
        if (mVoteAverage != 0) {
            contentValues.put(MovieTable.MovieEntry.COLUMN_VOTE_AVERAGE, mVoteAverage);
        }
        if (mTitle != null) {
            contentValues.put(MovieTable.MovieEntry.COLUMN_TITLE, mTitle);
        }
        if (mOverview != null) {
            contentValues.put(MovieTable.MovieEntry.COLUM_OVERVIEW, mOverview);
        }
        if (mPosterPath != null) {
            contentValues.put(MovieTable.MovieEntry.COLUM_POTER_PATH, mPosterPath);
        }
        return contentValues;
    }

    public String getId() {
        return mId;
    }

    public void setId(String id) {
        this.mId = id;
    }

    public float getVote_average() {
        return mVoteAverage;
    }

    public void setVote_average(float vote_average) {
        this.mVoteAverage = vote_average;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        this.mTitle = title;
    }

    public String getOverview() {
        return mOverview;
    }

    public void setOverview(String overview) {
        this.mOverview = overview;
    }

    public String getPoster_path() {
        return mPosterPath;
    }

    public void setPoster_path(String poster_path) {
        this.mPosterPath = poster_path;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(mId);
        parcel.writeFloat(mVoteAverage);
        parcel.writeString(mTitle);
        parcel.writeString(mOverview);
        parcel.writeString(mPosterPath);
    }
}
