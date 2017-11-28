package com.example.hcm_102_0006.android_project_m.data.source.local;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.hcm_102_0006.android_project_m.data.model.Movie;
import com.example.hcm_102_0006.android_project_m.data.source.FavoriteDataSource;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.Subscriber;

/**
 * Created by hcm-102-0006 on 24/11/2017.
 */

public class FavoriteLocalDataSource extends DatabaseHelper implements FavoriteDataSource {
    public FavoriteLocalDataSource(Context context) {
        super(context);
    }

    @Override
    public Observable<List<Movie>> getAllMovieFavorite() {
        return Observable.create(new Observable.OnSubscribe<List<Movie>>() {
            @Override
            public void call(Subscriber<? super List<Movie>> subscriber) {
                List<Movie> movies = new ArrayList<>();
                SQLiteDatabase sqLiteDatabase = getReadableDatabase();
                Cursor cursor =
                    sqLiteDatabase.query(MovieTable.MovieEntry.TABLE_NAME, null, null, null, null,
                        null, null);
                if (cursor != null && cursor.moveToFirst()) {
                    do {
                        movies.add(new Movie(cursor));
                    } while (cursor.moveToNext());
                    if (cursor != null) {
                        cursor.close();
                    }
                }
                close();
                subscriber.onNext(movies);
                subscriber.onCompleted();
            }
        });
    }

    @Override
    public Observable<Boolean> isFavoriteMovie(final String id) {
        return Observable.create(new Observable.OnSubscribe<Boolean>() {
            @Override
            public void call(Subscriber<? super Boolean> subscriber) {
                boolean isFavorite = false;
                SQLiteDatabase sqLiteDatabase = getReadableDatabase();
                String selection = MovieTable.MovieEntry.COLUMN_MOVIE_ID + " =?";
                String[] selectionArgs = { String.valueOf(id) };
                Cursor cursor =
                    sqLiteDatabase.query(MovieTable.MovieEntry.TABLE_NAME, null, selection,
                        selectionArgs, null, null, null);
                if (cursor != null && cursor.moveToFirst()) {
                    isFavorite = true;
                }
                if (cursor != null) {
                    cursor.close();
                }

                close();
                subscriber.onNext(isFavorite);
            }
        });
    }

    @Override
    public Observable<Boolean> insertMovie(final Movie movie) {
        return Observable.create(new Observable.OnSubscribe<Boolean>() {
            @Override
            public void call(Subscriber<? super Boolean> subscriber) {
                if (movie == null) {
                    subscriber.onNext(false);
                    subscriber.onCompleted();
                    return;
                }
                SQLiteDatabase db = getWritableDatabase();
                long result =
                    db.insert(MovieTable.MovieEntry.TABLE_NAME, null, movie.getContentValues());
                close();
                subscriber.onNext(result != -1);
                subscriber.onCompleted();
            }
        });
    }

    @Override
    public Observable<Boolean> deleteMovie(final Movie movie) {
        return Observable.create(new Observable.OnSubscribe<Boolean>() {
            @Override
            public void call(Subscriber<? super Boolean> subscriber) {
                if (movie == null) {
                    subscriber.onNext(false);
                    subscriber.onCompleted();
                    return;
                }
                SQLiteDatabase db = getWritableDatabase();
                String whereClause = MovieTable.MovieEntry.COLUMN_MOVIE_ID + " =?";
                long result =
                    db.delete(MovieTable.MovieEntry.TABLE_NAME, whereClause, new String[] {
                        movie.getId()
                    });
                db.close();
                subscriber.onNext(result != -1);
                subscriber.onCompleted();
            }
        });
    }
}
