package com.example.hcm_102_0006.android_project_m.data.model;

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

    public static class Credit implements Parcelable{
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

        protected Credit(Parcel in) {
            mCastId = in.readInt();
            mCharacter = in.readString();
            mCreditId = in.readString();
            mGender = in.readInt();
            mId = in.readInt();
            mName = in.readString();
        }

        public static final Creator<Credit> CREATOR = new Creator<Credit>() {
            @Override
            public Credit createFromParcel(Parcel in) {
                return new Credit(in);
            }

            @Override
            public Credit[] newArray(int size) {
                return new Credit[size];
            }
        };

        public int getmCastId() {
            return mCastId;
        }

        public void setmCastId(int mCastId) {
            this.mCastId = mCastId;
        }

        public String getmCharacter() {
            return mCharacter;
        }

        public void setmCharacter(String mCharacter) {
            this.mCharacter = mCharacter;
        }

        public String getmCreditId() {
            return mCreditId;
        }

        public void setmCreditId(String mCreditId) {
            this.mCreditId = mCreditId;
        }

        public int getmGender() {
            return mGender;
        }

        public void setmGender(int mGender) {
            this.mGender = mGender;
        }

        public int getmId() {
            return mId;
        }

        public void setmId(int mId) {
            this.mId = mId;
        }

        public String getmName() {
            return mName;
        }

        public void setmName(String mName) {
            this.mName = mName;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeInt(mCastId);
            parcel.writeString(mCharacter);
            parcel.writeString(mCreditId);
            parcel.writeInt(mGender);
            parcel.writeInt(mId);
            parcel.writeString(mName);
        }
    }
}
