package com.example.hcm_102_0006.android_project_m.remote.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;


/**
 * Created by hcm-102-0006 on 22/11/2017.
 */

public class Genres implements Parcelable {
    @SerializedName("id")
    private int mId;

    @SerializedName("name")
    private String mName;

    protected Genres(Parcel in) {
        mId = in.readInt();
        mName = in.readString();
    }

    public static final Creator<Genres> CREATOR = new Creator<Genres>() {
        @Override
        public Genres createFromParcel(Parcel in) {
            return new Genres(in);
        }

        @Override
        public Genres[] newArray(int size) {
            return new Genres[size];
        }
    };

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        this.mId = id;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        this.mName = name;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(mId);
        parcel.writeString(mName);
    }
}
