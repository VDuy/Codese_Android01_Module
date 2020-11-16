package com.example.android01_module3_tmdb_mvp.feature.account;

import retrofit2.Response;

public interface AccountContract {
    interface View {
        void showAccountSection();

        void showLoginSection();

        void showLoadingIndicator();

        void hideLoadingIndicator();

        void showErrorFromServer(Response response);

        void showErrorWhenFailure(String error);
    }

    interface Presenter {
        void getSessionId();

        void signOut();

        void signIn(String username, String password);
    }
}
