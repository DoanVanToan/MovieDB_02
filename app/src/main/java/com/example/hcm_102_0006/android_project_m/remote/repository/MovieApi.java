package com.example.hcm_102_0006.android_project_m.remote.repository;

import com.example.hcm_102_0006.android_project_m.BuildConfig;
import com.example.hcm_102_0006.android_project_m.remote.model.GenreResponse;
import com.example.hcm_102_0006.android_project_m.remote.model.Movie;
import com.example.hcm_102_0006.android_project_m.remote.model.MovieDetail;
import com.example.hcm_102_0006.android_project_m.remote.model.ResultResponse;

import retrofit.http.GET;
import retrofit.http.Path;
import retrofit.http.Query;
import rx.Observable;

/**
 * Created by hcm-102-0006 on 21/11/2017.
 */

public interface MovieApi {
    String SERVICE_URL = "https://api.themoviedb.org/3";

    //Get information category: https://api.themoviedb.org/3/movie/upcoming?api_key=c733ac6aba3d86364a56d1145bc1d1f9
    @GET("/movie/{category}")
    Observable<ResultResponse> getMovie(@Path("category") String category, @Query("api_key") String apiKey);

    //https://api.themoviedb.org/3/genre/movie/list?api_key=c733ac6aba3d86364a56d1145bc1d1f9&language=en-US
    @GET("/genre/movie/list")
    Observable<GenreResponse> getGenres(@Query("api_key") String apiKey);

    // get movie genres
    @GET("/genre/{genre_id}/movies?api_key=" + BuildConfig.MOVIE_KEY + "&language=en-US&include_adult=false&sort_by=created_at.asc")
    Observable<ResultResponse> getMovieGenres(@Path("genre_id") String genreId);

    // get movie detail /movie/{movie_id}
    @GET("/movie/{movie_id}")
    Observable<MovieDetail> getMovieDetail(
            @Path("movie_id") String movieId,
            @Query("api_key") String apiKey,
            @Query("append_to_response") String videos);

    // Get videos
    //get /movie/{movie_id}/videos
    @GET("/movie/{movie_id}/videos?api_key=" + BuildConfig.MOVIE_KEY)
    Observable<MovieDetail> getMovieVideo(@Path("movie_id") String movieId);

    // Get all movie of company https://api.themoviedb.org/3/company/{company_id}/movies?api_key=c733ac6aba3d86364a56d1145bc1d1f9&language=en-US
    // /company/{company_id}/movies
    @GET("/company/{company_id}/movies?api_key=" + BuildConfig.MOVIE_KEY + "&language=en-US")
    Observable<Movie> getMovieCompany(@Path("company_id") String companyId);
}
