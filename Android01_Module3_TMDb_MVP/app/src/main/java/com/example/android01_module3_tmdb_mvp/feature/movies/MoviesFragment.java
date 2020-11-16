package com.example.android01_module3_tmdb_mvp.feature.movies;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android01_module3_tmdb_mvp.BaseFragment;
import com.example.android01_module3_tmdb_mvp.R;
import com.example.android01_module3_tmdb_mvp.models.GetMoviesResponse;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class MoviesFragment extends BaseFragment implements MoviesContract.View {
    private static final String TAG = "MoviesFragment";
    @BindView(R.id.rv_movies)
    RecyclerView rvMovies;
    @BindView(R.id.ll_loading)
    LinearLayout llLoading;

    private MoviesAdapter moviesAdapter;
    private List<GetMoviesResponse.ResultsBean> movies = new ArrayList<>();

    private int page = 1;
    private int totalItemCount, lastVisibleItem;
    private int visibleThreshold = 5;
    private boolean isLoading;
    private MoviesPresenter presenter;

    public MoviesFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_movies, container, false);
        ButterKnife.bind(this, view);

        presenter = new MoviesPresenter(this);
        setupUI();
        loadData();

        return view;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_account;
    }

    @Override
    protected void onInit() {
        presenter = new MoviesPresenter(this);
        loadData();
        setupUI();
    }

    private void loadData() {
        presenter.getMovies(page);
    }

    private void setupUI() {
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 3);
        rvMovies.setLayoutManager(gridLayoutManager);

        moviesAdapter = new MoviesAdapter(movies);
        rvMovies.setAdapter(moviesAdapter);

        rvMovies.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                totalItemCount = gridLayoutManager.getItemCount();
                lastVisibleItem = gridLayoutManager.findLastVisibleItemPosition();

                Log.d(TAG, "onScrolled: totalItemCount " + totalItemCount);
                Log.d(TAG, "onScrolled: lastVisibleItem " + lastVisibleItem);

                if (!isLoading && lastVisibleItem >= totalItemCount - visibleThreshold) {
                    page++;
                    loadData();
                    isLoading = true;
                }
            }
        });
    }

    @Override
    public void setDataToRecycleView(List<GetMoviesResponse.ResultsBean> movies) {
        this.movies.addAll(movies);
        moviesAdapter.notifyDataSetChanged();
    }

    @Override
    public void showError(String error) {
        //  isLoading = 1==2;
        isLoading = false;
        Toast.makeText(getContext(), error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showLoadingIndicator() {
        llLoading.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoadingIndicator() {
        llLoading.setVisibility(View.INVISIBLE);
    }

}
