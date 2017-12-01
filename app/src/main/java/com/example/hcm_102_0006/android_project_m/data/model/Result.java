package com.example.hcm_102_0006.android_project_m.data.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by hcm-102-0006 on 21/11/2017.
 */

public class Result {
    @SerializedName("results")
    private List<Movie> mResults ;

    public List<Movie> getResults() {
        return mResults;
    }

    public void setResults(List<Movie> results) {
        this.mResults = results;
    }
}
