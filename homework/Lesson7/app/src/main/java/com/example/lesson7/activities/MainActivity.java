package com.example.lesson7.activities;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.lesson7.R;
import com.example.lesson7.api.APIService;
import com.example.lesson7.models.GetWeathersResponse;
import com.example.lesson7.models.RetrofitConfiguration;

import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.et_enter_city_name)
    EditText etEnterCityName;
    @BindView(R.id.bt_get_data)
    Button btGetData;
    @BindView(R.id.tv_show_date)
    TextView tvShowDate;
    private static final String TAG = "MainActivity";
    String apiKey = "cbeed1a1a9dc4f8ff167312a55c80bb0";
    String temperatureType = "metric";
    @BindView(R.id.tv_show_country)
    TextView tvShowCountry;
    @BindView(R.id.tv_show_wind)
    TextView tvShowWind;
    @BindView(R.id.tv_show_temperature)
    TextView tvShowTemperature;
    @BindView(R.id.tv_show_description)
    TextView tvShowDescription;
    @BindView(R.id.iv_icon)
    ImageView ivIcon;
    @BindView(R.id.tv_status)
    TextView tvStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }


    @OnClick(R.id.bt_get_data)
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bt_get_data:
                APIService service = RetrofitConfiguration.getInstance().create(APIService.class);
                Call<GetWeathersResponse> call = service.getWeather(
                        etEnterCityName.getText().toString(),
                        temperatureType, apiKey);
                call.enqueue(new Callback<GetWeathersResponse>() {
                    @Override
                    public void onResponse(Call<GetWeathersResponse> call, Response<GetWeathersResponse> response) {
                        if (response.code() == 200) {
                            Log.d(TAG, "onResponse: " + response.body().getTimezone());
                            GetWeathersResponse getWeathersResponse = response.body();

                            String country = getWeathersResponse.getSys().getCountry();
                            tvShowCountry.setText("Country: " + country);
                            int date1 = getWeathersResponse.getDt();
                            Date date2 = new Date(date1 * 1000L);
                            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEEE d-MMM-yyyy");
                            String day = simpleDateFormat.format(date2);
                            tvShowDate.setText(day);

                            GetWeathersResponse.WeatherBean weatherBean = response.body().getWeather().get(0);
                            String status = weatherBean.getMain();
                            Log.d(TAG, "onResponse: " + status);
                            tvStatus.setText(status);

                            String icon = weatherBean.getIcon();
                            Glide.with(MainActivity.this)
                                    .load("http://openweathermap.org/img/w/" + icon + ".png").into(ivIcon);
                            String description = weatherBean.getDescription();
                            tvShowDescription.setText("Description: " + description);

                            GetWeathersResponse.MainBean mainBean = response.body().getMain();
                            int temperature = (int) mainBean.getTemp();
                            tvShowTemperature.setText("Temperature: " + temperature);

                            double wind = response.body().getWind().getSpeed();
                            tvShowWind.setText("WindSpeed: " + wind);
                        }else if(response.code() == 404){
                            Toast.makeText(MainActivity.this,"City not found",Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<GetWeathersResponse> call, Throwable t) {
                        Toast.makeText(MainActivity.this, "Error", Toast.LENGTH_SHORT).show();
                    }
                });
                break;
        }
    }
}