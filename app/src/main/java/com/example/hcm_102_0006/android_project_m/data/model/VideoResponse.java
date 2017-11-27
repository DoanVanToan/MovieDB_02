package com.example.hcm_102_0006.android_project_m.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by hcm-102-0006 on 27/11/2017.
 */

public class VideoResponse implements Parcelable{
    @SerializedName("results")
    private List<Video> mResults;

    public VideoResponse(List<Video> mResults) {
        this.mResults = mResults;
    }

    protected VideoResponse(Parcel in) {
    }

    public static final Creator<VideoResponse> CREATOR = new Creator<VideoResponse>() {
        @Override
        public VideoResponse createFromParcel(Parcel in) {
            return new VideoResponse(in);
        }

        @Override
        public VideoResponse[] newArray(int size) {
            return new VideoResponse[size];
        }
    };

    public List<Video> getmResults() {
        return mResults;
    }

    public void setmResults(List<Video> mResults) {
        this.mResults = mResults;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
    }

    public class Video {
        @SerializedName("id")
        private String mId;
        @SerializedName("key")
        private String mKey;
        @SerializedName("type")
        private String mType;
        @SerializedName("name")
        private String mName;

        public Video(String mId, String mKey, String mType, String mName) {
            this.mId = mId;
            this.mKey = mKey;
            this.mType = mType;
            this.mName = mName;
        }

        protected Video(Parcel in) {
            mId = in.readString();
            mKey = in.readString();
            mType = in.readString();
            mName = in.readString();
        }


        public String getmId() {
            return mId;
        }

        public void setmId(String mId) {
            this.mId = mId;
        }

        public String getmKey() {
            return mKey;
        }

        public void setmKey(String mKey) {
            this.mKey = mKey;
        }

        public String getmType() {
            return mType;
        }

        public void setmType(String mType) {
            this.mType = mType;
        }

        public String getmName() {
            return mName;
        }

        public void setmName(String mName) {
            this.mName = mName;
        }
    }

}
