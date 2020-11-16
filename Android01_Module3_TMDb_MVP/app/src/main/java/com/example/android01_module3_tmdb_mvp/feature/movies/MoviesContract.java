package com.example.android01_module3_tmdb_mvp.feature.movies;

import com.example.android01_module3_tmdb_mvp.models.GetMoviesResponse;

import java.util.List;

import retrofit2.Response;

public interface MoviesContract {


    interface View {
        void setDataToRecycleView(List<GetMoviesResponse.ResultsBean> movies);

        void showError(String error);

        void showLoadingIndicator();

        void hideLoadingIndicator();
    }

    interface Presenter {
        void getMovies(int face);
    }

    interface Model {
        interface OnFinishGetMovies {
            void onResponse(Response response);

            void onFailure(String error);
        }

        void getMovies(OnFinishGetMovies onFinishGetMovies, int page);
    }
}
