package com.example.hcm_102_0006.android_project_m.data.source.local;

import android.provider.BaseColumns;

/**
 * Created by hcm-102-0006 on 24/11/2017.
 */

public class MovieTable  {
    public static class MovieEntry implements BaseColumns{
        public static final String TABLE_NAME = "tbl_movie_favorite";
        public static final String COLUMN_MOVIE_ID = "id";
        public static final String COLUMN_POSTER_PATH = "image_path";
        public static final String COLUMN_OVERVIEW = "overview";
        public static final String COLUMN_TITLE = "title";
        public static final String COLUMN_VOTE_AVERAGE = "vote_average";
    }
}