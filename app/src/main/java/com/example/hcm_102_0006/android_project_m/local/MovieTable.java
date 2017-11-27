package com.example.hcm_102_0006.android_project_m.local;

import android.provider.BaseColumns;

/**
 * Created by hcm-102-0006 on 24/11/2017.
 */

public class MovieTable  {
    public static class MovieEntry implements BaseColumns{
        public static final String TABLE_NAME = "tbl_movie_favorite";
        public static final String COLUMN_MOVIEID = "id";
        public static final String COLUM_POTER_PATH = "image_path";
        public static final String COLUM_OVERVIEW = "overview";
        public static final String COLUMN_TITLE = "title";
        public static final String COLUMN_VOTE_AVERAGE = "vote_average";
    }
}
