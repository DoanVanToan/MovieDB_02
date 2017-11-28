package com.example.hcm_102_0006.android_project_m.local;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.example.hcm_102_0006.android_project_m.remote.model.Movie;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by hcm-102-0006 on 24/11/2017.
 */

public class MovieDataSource extends DatabaseHelper {
    public MovieDataSource(Context context) {
        super(context);
    }

    public List<Movie> getAllMovieFavorite() {
        List<Movie> movies = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        Cursor cursor = sqLiteDatabase.query(
                MovieTable.MovieEntry.TABLE_NAME, null, null, null, null, null, null);
        if (cursor != null && cursor.moveToFirst()) {
            do {
                movies.add(new Movie(cursor));
            } while (cursor.moveToNext());
            if (cursor != null) {
                cursor.close();
            }
        }
        return movies;
    }

    public boolean checkFavorite(String id) {
        boolean isFavorite = false;

        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        String selection = MovieTable.MovieEntry.COLUMN_MOVIE_ID + " =?";
        String[] selectionArgs = {String.valueOf(id)};
        Cursor cursor = sqLiteDatabase.query(MovieTable.MovieEntry.TABLE_NAME, null, selection, selectionArgs, null, null, null);
        if (cursor != null && cursor.moveToFirst()) {
            isFavorite = true;
        }
        if (cursor != null) {
            cursor.close();
        }
        return isFavorite;
    }

    public boolean insertMovie(Movie movie) {
        if (movie == null) return false;
        SQLiteDatabase db = getWritableDatabase();
        long result = db.insert(MovieTable.MovieEntry.TABLE_NAME, null,
                movie.getContentValues());
        db.close();
        return result != -1;
    }

    public boolean deleteMovie(String idMovie) {
        if (idMovie == null || idMovie == "") return false;
        SQLiteDatabase db = getWritableDatabase();
        String whereClause = MovieTable.MovieEntry.COLUMN_MOVIE_ID + " =?";
        long result = db.delete(MovieTable.MovieEntry.TABLE_NAME, whereClause, new String[]{idMovie});
        db.close();
        return result != -1;
    }
}
