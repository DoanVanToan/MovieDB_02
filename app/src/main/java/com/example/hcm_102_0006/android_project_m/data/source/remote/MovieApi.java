package com.example.hcm_102_0006.android_project_m.data.source.remote;

import com.example.hcm_102_0006.android_project_m.BuildConfig;
import com.example.hcm_102_0006.android_project_m.data.model.GenreResponse;
import com.example.hcm_102_0006.android_project_m.data.model.Movie;
import com.example.hcm_102_0006.android_project_m.data.model.MovieDetail;
import com.example.hcm_102_0006.android_project_m.service.model.ResultResponse;


import retrofit.http.GET;
import retrofit.http.Path;
import rx.Observable;

/**
 * Created by hcm-102-0006 on 21/11/2017.
 */

public interface MovieApi {
    String SERVICE_URL = "https://api.themoviedb.org/3";

    //Get information category: https://api.themoviedb.org/3/movie/upcoming?api_key=c733ac6aba3d86364a56d1145bc1d1f9
    @GET("/movie/{category}?api_key=" + BuildConfig.MOVIE_KEY)
    Observable<ResultResponse> getMovie(@Path("category") String category);

    //https://api.themoviedb.org/3/genre/movie/list?api_key=c733ac6aba3d86364a56d1145bc1d1f9&language=en-US
    @GET("/genre/movie/list?api_key=" + BuildConfig.MOVIE_KEY)
    Observable<GenreResponse> getGenres();

    // get movie genres
    @GET("/genre/{genre_id}/movies?api_key=" + BuildConfig.MOVIE_KEY + "&language=en-US&include_adult=false&sort_by=created_at.asc")
    Observable<ResultResponse> getMovieGenres(@Path("genre_id") String genreId);

    // get movie detail /movie/{movie_id}
    @GET("/movie/{movie_id}?api_key=" + BuildConfig.MOVIE_KEY+"&append_to_response=videos")
    Observable<MovieDetail> getMovieDetail(@Path("movie_id") String movieId);

    // Get videos
    //get /movie/{movie_id}/videos
    @GET("/movie/{movie_id}/videos?api_key=" + BuildConfig.MOVIE_KEY)
    Observable<MovieDetail> getMovieVideo(@Path("movie_id") String movieId);
    // then get key movie. https://www.youtube.com/watch?v=SUXWAEX2jlg

    // Get all movie of company https://api.themoviedb.org/3/company/{company_id}/movies?api_key=c733ac6aba3d86364a56d1145bc1d1f9&language=en-US
    // /company/{company_id}/movies
    @GET("/company/{company_id}/movies?api_key=" + BuildConfig.MOVIE_KEY + "&language=en-US")
    Observable<Movie> getMovieCompany(@Path("company_id") String companyId);
}
