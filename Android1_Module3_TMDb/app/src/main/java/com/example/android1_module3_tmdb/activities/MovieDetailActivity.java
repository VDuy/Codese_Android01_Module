package com.example.android1_module3_tmdb.activities;



import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.NestedScrollView;

import com.bumptech.glide.Glide;
import com.example.android1_module3_tmdb.R;
import com.example.android1_module3_tmdb.api.APIService;
import com.example.android1_module3_tmdb.api.RetrofitConfiguration;
import com.example.android1_module3_tmdb.models.GetMovieDetailResponse;

import java.text.DecimalFormat;
import java.text.NumberFormat;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieDetailActivity extends AppCompatActivity {
    private static final String TAG = "MovieDetailActivity";

    @BindView(R.id.iv_background)
    ImageView ivBackground;
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.iv_favourite)
    ImageView ivFavourite;
    @BindView(R.id.ns_detail)
    NestedScrollView nsDetail;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_genres)
    TextView tvGenres;
    @BindView(R.id.ll_watch_trailer)
    LinearLayout llWatchTrailer;
    GetMovieDetailResponse movie;
    @BindView(R.id.rb_movie_rated)
    RatingBar rbRated;
    @BindView(R.id.tv_movie_voted)
    TextView tvVoted;
    @BindView(R.id.tv__movie_runtime)
    TextView tvRunTime;
    @BindView(R.id.tv_movie_result_date)
    TextView tvResultDate;
    @BindView(R.id.tv_movie_result_overview)
    TextView tvResultOverview;
    @BindView(R.id.tv_movie_result_revenue)
    TextView tvResultRevenue;
    @BindView(R.id.tv_movie_result_company)
    TextView tvResultCompany;
    @BindView(R.id.tv_movie_result_country)
    TextView tvResultCountry;
    @BindView(R.id.tv_movie_rated)
    TextView tvRated;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);
        ButterKnife.bind(this);

        setupUI();
        loadData();
    }

    private void loadData() {
        int id = getIntent().getIntExtra("movie_id", -1);

        APIService apiService = RetrofitConfiguration.getInstance().create(APIService.class);
        Call<GetMovieDetailResponse> call = apiService.getMovieDetail(id);
        call.enqueue(new Callback<GetMovieDetailResponse>() {
            @Override
            public void onResponse(Call<GetMovieDetailResponse> call, Response<GetMovieDetailResponse> response) {
                movie = response.body();
                setupUIWithData();
            }

            @Override
            public void onFailure(Call<GetMovieDetailResponse> call, Throwable t) {
                Toast.makeText(MovieDetailActivity.this, t.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setupUIWithData() {
        Glide.with(this)
                .load(RetrofitConfiguration.getImageBaseUrlThumbnail() + movie.getPoster_path())
                .centerCrop()
                .into(ivBackground);

        tvTitle.setText(movie.getTitle());

        StringBuilder genres = new StringBuilder();
        for (int i = 0; i < movie.getGenres().size(); i++) {
            genres.append(movie.getGenres().get(i).getName());
            if (i != movie.getGenres().size() - 1) {
                genres.append(", ");
            }
        }
        tvGenres.setText(genres);
        String rated = String.valueOf(movie.getVote_average());
        tvRated.setText(rated);
        rbRated.setRating((float) (movie.getVote_average() / 2));
        tvVoted.setText(String.valueOf(movie.getRelease_date()));
        tvRunTime.setText(movie.getRuntime() + "m");
        tvResultDate.setText(String.valueOf(movie.getRelease_date()));
        tvResultOverview.setText(String.valueOf(movie.getOverview()));

        NumberFormat format = new DecimalFormat("#,###.##");

        tvResultRevenue.setText("$" + format.format(movie.getRevenue()) + ",00");

        StringBuilder company = new StringBuilder();
        for (int i = 0; i < movie.getProduction_companies().size(); i++) {
            company.append(movie.getProduction_companies().get(i).getName());
            if (i != movie.getProduction_companies().size() - 1) {
                company.append(", ");
            }
        }
        tvResultCompany.setText(company);
        StringBuilder country = new StringBuilder();
        for (int i = 0; i < movie.getProduction_countries().size(); i++) {
            country.append(movie.getProduction_countries().get(i).getName());
            if (i != movie.getProduction_countries().size() - 1) {
                country.append(", ");
            }
        }
        tvResultCountry.setText(country);
    }

    private void setupUI() {
        //1. get screen size
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int screenHeight = displayMetrics.heightPixels;

        //2. set max height for NestedScrollView
        ViewGroup.LayoutParams params = nsDetail.getLayoutParams();
        params.height = (int) (screenHeight * 0.85);
        nsDetail.setLayoutParams(params);
    }

    @OnClick({R.id.iv_back, R.id.iv_favourite, R.id.ll_watch_trailer})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                onBackPressed();
                break;
            case R.id.iv_favourite:
                break;
            case R.id.ll_watch_trailer:
                watchTrailer();
                break;
        }
    }

    private void watchTrailer() {
        if (movie.getVideos().getResults().size() == 0) {
            Toast.makeText(this, "No trailers", Toast.LENGTH_SHORT).show();
        } else {
            String trailerKey = movie.getVideos().getResults().get(0).getKey();
            Intent appIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("vnd.youtube:" + trailerKey));
            Intent webIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=" + trailerKey));
            try {
                startActivity(appIntent);
            } catch (Exception e) {
                startActivity(webIntent);
            }
        }
    }
}