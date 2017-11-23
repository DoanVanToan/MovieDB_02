package com.example.hcm_102_0006.android_project_m.data.source.remote;

import com.example.hcm_102_0006.android_project_m.data.model.GenreResponse;
import com.example.hcm_102_0006.android_project_m.data.model.MovieDetail;
import com.example.hcm_102_0006.android_project_m.data.model.Result;

import retrofit.http.GET;
import retrofit.http.Path;
import rx.Observable;

/**
 * Created by hcm-102-0006 on 21/11/2017.
 */

public interface MovieApi {
    String SERVICE_URL = "https://api.themoviedb.org/3";
    String KEY_API = "c733ac6aba3d86364a56d1145bc1d1f9";

    //Get information Category: https://api.themoviedb.org/3/movie/upcoming?api_key=c733ac6aba3d86364a56d1145bc1d1f9
    @GET("/movie/{category}?api_key=c733ac6aba3d86364a56d1145bc1d1f9")
    Observable<Result> getMovie (@Path("category") String category);

    //https://api.themoviedb.org/3/genre/movie/list?api_key=c733ac6aba3d86364a56d1145bc1d1f9&language=en-US
    @GET("/genre/movie/list?api_key=" + KEY_API)
    Observable<GenreResponse> getGenres ();

    // get Movie Genres
    @GET("/genre/{genre_id}/movies?api_key=" + KEY_API +"&language=en-US&include_adult=false&sort_by=created_at.asc")
    Observable<Result> getMovieGenres (@Path("genre_id") String genreId);

    // get Movie Detail /movie/{movie_id}
    @GET("/movie/{movie_id}?api_key=" + KEY_API)
    Observable<MovieDetail> getMovieDetail(@Path("movie_id") String movieId);

    // Get Videos
    //get /movie/{movie_id}/videos
    @GET("/movie/{movie_id}/videos?api_key=" + KEY_API)
    Observable<MovieDetail> getMovieVideo(@Path("movie_id") String movieId);
    ///

}
