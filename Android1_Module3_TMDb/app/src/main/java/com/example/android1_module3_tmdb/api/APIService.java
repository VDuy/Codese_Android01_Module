package com.example.android1_module3_tmdb.api;

import com.example.android1_module3_tmdb.models.GetCreateRequestTokenResponse;
import com.example.android1_module3_tmdb.models.GetMovieDetailResponse;
import com.example.android1_module3_tmdb.models.GetMoviesResponse;
import com.example.android1_module3_tmdb.models.GetSearchMovies;
import com.example.android1_module3_tmdb.models.PostCreateSessionRequest;
import com.example.android1_module3_tmdb.models.PostCreateSessionResponse;
import com.example.android1_module3_tmdb.models.PostCreateSessionWithLoginRequest;
import com.example.android1_module3_tmdb.models.PostCreateSessionWithLoginResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface APIService {
    String apiKey = "2a772fb7cd2f46ea3c8d1b6f5db8270e";

    @GET("discover/movie?api_key=" + apiKey)
    Call<GetMoviesResponse> getMovies(@Query("page") int page);

    @GET("movie/{movie_id}?api_key=" + apiKey + "&append_to_response=videos")
    Call<GetMovieDetailResponse> getMovieDetail(@Path("movie_id") int movieId);

    @GET("authentication/token/new?api_key=" + apiKey)
    Call<GetCreateRequestTokenResponse> getCreateRequestToken();

    @POST("authentication/token/validate_with_login?api_key=" + apiKey)
    Call<PostCreateSessionWithLoginResponse> postCreateSessionWithLogin(@Body PostCreateSessionWithLoginRequest body);

    @POST("authentication/session/new?api_key" + apiKey)
    Call<PostCreateSessionResponse> postCreateSession(@Body PostCreateSessionRequest body);

    @GET("search/movie?api_key="+apiKey)
    Call<GetMoviesResponse>getMoviesSearch(
            @Query("query")String query,
            @Query("page") int page
    );

}