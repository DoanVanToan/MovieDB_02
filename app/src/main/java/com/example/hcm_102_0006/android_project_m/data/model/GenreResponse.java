package com.example.hcm_102_0006.android_project_m.data.model;

import com.google.gson.annotations.SerializedName;
import java.util.List;

/**
 * Created by hcm-102-0006 on 22/11/2017.
 */

public class GenreResponse {

    @SerializedName("genres")
    private List<Genres> mGenres;

    public List<Genres> getGenres() {
        return mGenres;
    }

    public void setGenres(List<Genres> genres) {
        this.mGenres = genres;
    }
}
