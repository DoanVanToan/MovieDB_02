package com.example.hcm_102_0006.android_project_m.data.source.remote;

import com.example.hcm_102_0006.android_project_m.BuildConfig;
import com.example.hcm_102_0006.android_project_m.data.model.GenreResponse;
import com.example.hcm_102_0006.android_project_m.data.model.Movie;
import com.example.hcm_102_0006.android_project_m.data.model.MovieDetail;
import com.example.hcm_102_0006.android_project_m.data.model.ResultResponse;


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
    // https://api.themoviedb.org/3/genre/{genre_id}/movies?api_key=c733ac6aba3d86364a56d1145bc1d1f9&language=en-US&include_adult=false&sort_by=created_at.asc
    //@GET("/genre/{genre_id}/movies?api_key=" + BuildConfig.MOVIE_KEY + "&language=en-US&include_adult=false&sort_by=created_at.asc")
    @GET("/genre/{genre_id}/movies")
    Observable<ResultResponse> getMovieGenres(
            @Path("genre_id") String genreId,
            @Query("api_key") String apiKey,
            @Query("language") String language,
            @Query("include_adult") boolean includeAdult,
            @Query("sort_by") String sortBy);

    // get movie detail /movie/{movie_id}
    @GET("/movie/{movie_id}")
    Observable<MovieDetail> getMovieDetail(
            @Path("movie_id") String movieId,
            @Query("api_key") String apiKey,
            @Query("append_to_response") String videos);

    // Get videos
    //get /movie/{movie_id}/videos
    @GET("/movie/{movie_id}/videos")
    Observable<MovieDetail> getMovieVideo(@Path("movie_id") String movieId,@Query("api_key") String apiKey);

    // Get all movie of company https://api.themoviedb.org/3/company/{company_id}/movies?api_key=c733ac6aba3d86364a56d1145bc1d1f9&language=en-US
    // /company/{company_id}/movies
    @GET("/company/{company_id}/movies")
    Observable<Movie> getMovieCompany(
            @Path("company_id") String companyId,
            @Query("api_key") String apiKey ,
            @Query("language") String language);
}
