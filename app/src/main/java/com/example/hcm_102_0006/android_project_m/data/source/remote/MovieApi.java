package com.example.hcm_102_0006.android_project_m.data.source.remote;

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

    @GET("/movie/{category}")
    Observable<ResultResponse> getMovie(@Path("category") String category, @Query("api_key") String apiKey);

    @GET("/genre/movie/list")
    Observable<GenreResponse> getGenres(@Query("api_key") String apiKey);

    @GET("/genre/{genre_id}/movies")
    Observable<ResultResponse> getMovieGenres(
            @Path("genre_id") String genreId,
            @Query("api_key") String apiKey,
            @Query("language") String language,
            @Query("include_adult") boolean includeAdult,
            @Query("sort_by") String sortBy);

    @GET("/movie/{movie_id}")
    Observable<MovieDetail> getMovieDetail(
            @Path("movie_id") String movieId,
            @Query("api_key") String apiKey,
            @Query("append_to_response") String videos);

    @GET("/movie/{movie_id}/videos")
    Observable<MovieDetail> getMovieVideo(@Path("movie_id") String movieId,@Query("api_key") String apiKey);

    @GET("/company/{company_id}/movies")
    Observable<Movie> getMovieCompany(
            @Path("company_id") String companyId,
            @Query("api_key") String apiKey,
            @Query("language") String language);
}