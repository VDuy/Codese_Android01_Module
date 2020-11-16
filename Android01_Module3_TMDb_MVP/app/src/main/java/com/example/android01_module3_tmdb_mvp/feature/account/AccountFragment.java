package com.example.android01_module3_tmdb_mvp.feature.account;

import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.android01_module3_tmdb_mvp.BaseFragment;
import com.example.android01_module3_tmdb_mvp.R;
import com.example.android01_module3_tmdb_mvp.utils.Utils;

import butterknife.BindView;
import butterknife.OnClick;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */

public class AccountFragment extends BaseFragment implements AccountContract.View {

    @BindView(R.id.et_username)
    EditText etUsername;
    @BindView(R.id.et_password)
    EditText etPassword;
    @BindView(R.id.ll_sign_in)
    LinearLayout llSignIn;
    @BindView(R.id.tv_sign_out)
    TextView tvSignOut;
    @BindView(R.id.ll_account)
    LinearLayout llAccount;
    @BindView(R.id.ll_loading)
    LinearLayout llLoading;



    public AccountFragment() {
        // Required empty public constructor
    }

    AccountContract.Presenter presenter;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_account;
    }

    @Override
    protected void onInit() {
        presenter = new AccountPresenter(this, new AccountSharePref(getContext()));
        presenter.getSessionId();
    }

    @OnClick({R.id.tv_sign_in, R.id.tv_sign_out})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_sign_in:
                presenter.signIn(
                        etUsername.getText().toString(),
                        etPassword.getText().toString()
                );
                break;
            case R.id.tv_sign_out:
                break;
        }
    }

    @Override
    public void showAccountSection() {
        llAccount.setVisibility(View.VISIBLE);
        llSignIn.setVisibility(View.INVISIBLE);
    }

    @Override
    public void showLoginSection() {
        llAccount.setVisibility(View.INVISIBLE);
        llSignIn.setVisibility(View.VISIBLE);
    }

    @Override
    public void showLoadingIndicator() {
        llLoading.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoadingIndicator() {
        llLoading.setVisibility(View.INVISIBLE);
    }

    @Override
    public void showErrorFromServer(Response response) {
        Utils.showErrorFromServer(response, getContext());
    }

    @Override
    public void showErrorWhenFailure(String error) {
        Toast.makeText(getContext(), toString(), Toast.LENGTH_SHORT).show();
    }
}