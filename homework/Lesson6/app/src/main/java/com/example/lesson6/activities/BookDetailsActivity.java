package com.example.lesson6.activities;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.lesson6.R;
import com.example.lesson6.databases.DatabaseUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class BookDetailsActivity extends AppCompatActivity {
    private static final String TAG = "check";
    public static String KEY_STORY = "key_story";

    @BindView(R.id.textView2)
    TextView tvTitleRead;
    @BindView(R.id.textView3)
    TextView tvAuthorRead;
    @BindView(R.id.textView4)
    TextView tvDescriptionRead;
    @BindView(R.id.iv_main)
    ImageView imageMain;
    @BindView(R.id.iv_back)
    ImageView imageLeft;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_details);
        ButterKnife.bind(this);
        getSupportActionBar().hide();
        Intent intent = getIntent();
        int position = intent.getIntExtra("position", 0);
        getData(position);
        Log.d(TAG, "onCreate: " + position);
        }
    public void getData(int position) {
        Glide.with(this).load(DatabaseUtils.getInstance(this).getListTopic().get(position).getImage()).into(imageMain);
        tvTitleRead.setText(DatabaseUtils.getInstance(this).getListTopic().get(position).getTitle());
        tvAuthorRead.setText(DatabaseUtils.getInstance(this).getListTopic().get(position).getAuthor());
        tvDescriptionRead.setText(DatabaseUtils.getInstance(this).getListTopic().get(position).getDescription());
    }

    @OnClick(R.id.iv_back)
    public void onViewClicked() {
        onBackPressed();
    }
    }


