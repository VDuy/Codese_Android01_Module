package com.example.android01_module3_tmdb_mvp.feature.movies;

import com.example.android01_module3_tmdb_mvp.models.GetMoviesResponse;

import retrofit2.Response;

public class MoviesPresenter implements MoviesContract.Presenter, MoviesContract.Model.OnFinishGetMovies {
    MoviesContract.View view;
    MoviesContract.Model model;


    public MoviesPresenter(MoviesContract.View view) {
        this.view = view;
        this.model = new MoviesModel();
    }

    @Override
    public void getMovies(int page) {
        view.showLoadingIndicator();
        model.getMovies(this, page);
    }
        @Override
        public void onResponse (Response response){
            view.hideLoadingIndicator();
            if (response.code() == 200) {
                view.setDataToRecycleView(((GetMoviesResponse)response.body()).getResults());
            }
        }

        @Override
        public void onFailure (String error){
            view.hideLoadingIndicator();
            view.showError(error);
        }
    }




