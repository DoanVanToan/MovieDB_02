package com.example.hcm_102_0006.android_project_m.remote.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by hcm-102-0006 on 29/11/2017.
 */

public class CreditsResponse implements Parcelable {
    @SerializedName("cast")
    private List<Credit> mCast;

    public List<Credit> getmCast() {
        return mCast;
    }

    public void setmCast(List<Credit> mCast) {
        this.mCast = mCast;
    }

    public static Creator<CreditsResponse> getCREATOR() {
        return CREATOR;
    }

    protected CreditsResponse(Parcel in) {
    }

    public static final Creator<CreditsResponse> CREATOR = new Creator<CreditsResponse>() {
        @Override
        public CreditsResponse createFromParcel(Parcel in) {
            return new CreditsResponse(in);
        }

        @Override
        public CreditsResponse[] newArray(int size) {
            return new CreditsResponse[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
    }

    public class Credit {
        @SerializedName("cast_id")
        private int mCastId;
        @SerializedName("character")
        private String mCharacter;
        @SerializedName("credit_id")
        private String mCreditId;
        @SerializedName("gender")
        private int mGender;
        @SerializedName("id")
        private int mId;
        @SerializedName("name")
        private String mName;
    }
}
