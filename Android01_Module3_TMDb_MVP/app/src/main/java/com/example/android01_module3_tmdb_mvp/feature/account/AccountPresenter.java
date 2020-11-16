package com.example.android01_module3_tmdb_mvp.feature.account;

import android.util.Log;

import com.example.android01_module3_tmdb_mvp.api.APIService;
import com.example.android01_module3_tmdb_mvp.api.RetrofitConfiguration;
import com.example.android01_module3_tmdb_mvp.models.DeleteSessionIdRequest;
import com.example.android01_module3_tmdb_mvp.models.GetCreateRequestTokenResponse;
import com.example.android01_module3_tmdb_mvp.models.PostCreateSessionRequest;
import com.example.android01_module3_tmdb_mvp.models.PostCreateSessionResponse;
import com.example.android01_module3_tmdb_mvp.models.PostCreateSessionWithLoginRequest;
import com.example.android01_module3_tmdb_mvp.models.PostCreateSessionWithLoginResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class AccountPresenter implements AccountContract.Presenter {
    AccountContract.View view;
    AccountSharePref accountSharePref;
    private APIService service;

    public AccountPresenter(AccountContract.View view, AccountSharePref accountSharePref) {
        this.view = view;
        this.accountSharePref = accountSharePref;
        service = RetrofitConfiguration.getInstance().create(APIService.class);
    }


    @Override
    public void getSessionId() {
        if (accountSharePref.getSessionId() == null) {
            view.showLoginSection();
        } else {
            view.showAccountSection();
        }
    }

    @Override
    public void signIn(String username, String password) {
        createRequestToken(username, password);
    }

    private void createRequestToken(String username, String password) {
        view.showLoadingIndicator();
        Call<GetCreateRequestTokenResponse> call = service.getCreateRequestToken();
        call.enqueue(new Callback<GetCreateRequestTokenResponse>() {
            @Override
            public void onResponse(Call<GetCreateRequestTokenResponse> call, Response<GetCreateRequestTokenResponse> response) {
                if (response.code() == 200) {
                    createSessionWithLogin(response.body().getRequest_token(), username, password);
                } else {
                    view.hideLoadingIndicator();
                    view.showErrorFromServer(response);
                }
            }

            @Override
            public void onFailure(Call<GetCreateRequestTokenResponse> call, Throwable t) {
                view.hideLoadingIndicator();
                view.showErrorWhenFailure(t.toString());
                Log.d(TAG, "onFailure: ");

            }
        });
    }

    private void createSessionWithLogin(String token, String username, String password) {
        PostCreateSessionWithLoginRequest body = new PostCreateSessionWithLoginRequest();
        body.setUsername(username);
        body.setPassword(password);
        body.setRequest_token(token);

        Call<PostCreateSessionWithLoginResponse> call = service.postCreateSessionWithLogin(body);
        call.enqueue(new Callback<PostCreateSessionWithLoginResponse>() {
            @Override
            public void onResponse(Call<PostCreateSessionWithLoginResponse> call, Response<PostCreateSessionWithLoginResponse> response) {
                if (response.code() == 200) {
                    createSession(response.body().getRequest_token());
                } else {
                    view.hideLoadingIndicator();
                    view.showErrorFromServer(response);
                }
            }

            @Override
            public void onFailure(Call<PostCreateSessionWithLoginResponse> call, Throwable t) {
                view.hideLoadingIndicator();
                view.showErrorWhenFailure(t.toString());
            }
        });
    }

    private void createSession(String token) {
        PostCreateSessionRequest body = new PostCreateSessionRequest();
        body.setRequest_token(token);

        Call<PostCreateSessionResponse> call = service.postCreateSession(body);
        call.enqueue(new Callback<PostCreateSessionResponse>() {
            @Override
            public void onResponse(Call<PostCreateSessionResponse> call, Response<PostCreateSessionResponse> response) {
                if (response.code() == 200) {
                    view.hideLoadingIndicator();

                    accountSharePref.saveSessionId(response.body().getSession_id());
                } else {
                    view.hideLoadingIndicator();
                    view.showErrorFromServer(response);
                }
            }

            @Override
            public void onFailure(Call<PostCreateSessionResponse> call, Throwable t) {
                view.hideLoadingIndicator();
                view.showErrorWhenFailure(t.toString());
            }
        });
    }

    @Override
    public void signOut() {
        view.showLoadingIndicator();
        DeleteSessionIdRequest body = new DeleteSessionIdRequest();
        body.setSession_id(accountSharePref.getSessionId());
        Call<DeleteSessionIdRequest> call = service.deleteSessionId(body);
        call.enqueue(new Callback<DeleteSessionIdRequest>() {
            @Override
            public void onResponse(Call<DeleteSessionIdRequest> call, Response<DeleteSessionIdRequest> response) {
                view.hideLoadingIndicator();
                if (response.code() == 200) {
                    view.showLoginSection();
                    accountSharePref.saveSessionId(null);


                } else {
                    view.hideLoadingIndicator();
                    view.showErrorFromServer(response);
                }
            }

            @Override
            public void onFailure(Call<DeleteSessionIdRequest> call, Throwable t) {
                view.hideLoadingIndicator();
                view.showErrorWhenFailure(t.toString());
            }
        });
    }

}
