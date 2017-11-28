package com.example.hcm_102_0006.android_project_m.local;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by hcm-102-0006 on 24/11/2017.
 */

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Movie.db";
    private final String SQL_CREATE_MOVIE =
            " CREATE TABLE "
                    + MovieTable.MovieEntry.TABLE_NAME
                    + " ("
                    + MovieTable.MovieEntry._ID
                    + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + MovieTable.MovieEntry.COLUMN_MOVIE_ID
                    + " INTEGER, "
                    + MovieTable.MovieEntry.COLUMN_TITLE
                    + " TEXT NOT NULL, "
                    + MovieTable.MovieEntry.COLUMN_VOTE_AVERAGE
                    + " REAL NOT NULL, "
                    + MovieTable.MovieEntry.COLUMN_POSTER_PATH
                    + " TEXT NOT NULL, "
                    + MovieTable.MovieEntry.COLUMN_OVERVIEW
                    + " TEXT NOT NULL "
                    + "); ";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(SQL_CREATE_MOVIE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + MovieTable.MovieEntry.TABLE_NAME);
        onCreate(sqLiteDatabase);
    }
}
