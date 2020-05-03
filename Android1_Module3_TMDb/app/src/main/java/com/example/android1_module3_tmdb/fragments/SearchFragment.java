package com.example.android1_module3_tmdb.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android1_module3_tmdb.R;
import com.example.android1_module3_tmdb.adapters.MoviesAdapter;
import com.example.android1_module3_tmdb.api.APIService;
import com.example.android1_module3_tmdb.api.RetrofitConfiguration;
import com.example.android1_module3_tmdb.models.GetMoviesResponse;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class SearchFragment extends Fragment {

    @BindView(R.id.spn_search)
    Spinner spnSearch;
    @BindView(R.id.tv_add_spinner)
    TextView tvAddSpinner;
    @BindView(R.id.et_search)
    EditText etSearch;
    @BindView(R.id.imgSearch)
    ImageView imgSearch;
    @BindView(R.id.tv_result_search)
    TextView tvResultSearch;
    @BindView(R.id.rcv_movies_search)
    RecyclerView rcvMoviesSearch;

    private MoviesAdapter movieAdapter;
    private List<GetMoviesResponse.ResultsBean> moviesSearch = new ArrayList<>();
    private int page = 1;
    private int visibleThreshold = 5;
    private int totalItemCount, lastVisibleItem;
    private boolean isLoading;
    private String query;

    private static final String TAG = "SearchFragment";


    public SearchFragment() {
        // Required empty public constructor

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_search, container, false);
        ButterKnife.bind(this, view);
        setupSpinner();
        setupUI();
        query = etSearch.getText().toString();
        loadData(query);

        imgSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String et = etSearch.getText().toString();
                if (et != query) {
                    moviesSearch.clear();
                    loadData(et);
                }

            }
        });
        return view;
    }

    private void setupUI() {
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 3);
        rcvMoviesSearch.setLayoutManager(gridLayoutManager);
        movieAdapter = new MoviesAdapter(moviesSearch);
        rcvMoviesSearch.setAdapter(movieAdapter);

        rcvMoviesSearch.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                totalItemCount = gridLayoutManager.getItemCount();
                lastVisibleItem = gridLayoutManager.findLastVisibleItemPosition();
                Log.d(TAG, "onScrolled: totalItem" + totalItemCount);
                Log.d(TAG, "onScrolled: lastVisibleItem" + lastVisibleItem);
                if (!isLoading && lastVisibleItem >= totalItemCount - visibleThreshold) {
                    Log.d(TAG, "onScrolled: LoadMore");
                    page++;
                    loadData(query);
                    isLoading = true;
                }
            }
        });
    }

    private void loadData(String msg) {
        APIService service = RetrofitConfiguration.getInstance().create(APIService.class);
        Call<GetMoviesResponse> call = service.getMoviesSearch(msg, page);
        call.enqueue(new Callback<GetMoviesResponse>() {
            @Override
            public void onResponse(Call<GetMoviesResponse> call, Response<GetMoviesResponse> response) {
                isLoading = false;
                if (response.code() == 200) {
                    tvResultSearch.setText(response.body().getTotal_results() + "result in relate to" + "\" " +
                            etSearch.getText().toString() + "\" ");
                    moviesSearch.addAll(response.body().getResults());
                    movieAdapter.notifyDataSetChanged();

                    Log.d(TAG, "onResponse: connected" + response.body() + response.body().getTotal_results());
                } else {
                    Log.d(TAG, "onResponse: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<GetMoviesResponse> call, Throwable t) {

                isLoading = false;
                Toast.makeText(getContext(), "NetWork Fail", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void setupSpinner() {

        final ArrayList<String> item = new ArrayList<String>();

        item.add("Movies");
        item.add("People");
        item.add("TvShows");


        ArrayAdapter arrayAdapter = new ArrayAdapter(getContext(), android.R.layout.simple_spinner_item, item);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnSearch.setAdapter(arrayAdapter);
        spnSearch.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String text = spnSearch.getSelectedItem().toString();
                //     tvAddSpinner.setText(text);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
}
