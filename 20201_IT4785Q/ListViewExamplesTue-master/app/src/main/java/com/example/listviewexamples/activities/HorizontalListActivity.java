package com.example.listviewexamples.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.listviewexamples.models.ImageItemModel;
import com.example.listviewexamples.R;

import java.util.ArrayList;
import java.util.List;

public class HorizontalListActivity extends AppCompatActivity {

    List<ImageItemModel> items;
    ImageView imageLarge;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_horizontal_list);

        items = new ArrayList<>();
        items.add(new ImageItemModel("Image 0", R.drawable.thumb1, R.drawable.wall1));
        items.add(new ImageItemModel("Image 1", R.drawable.thumb2, R.drawable.wall2));
        items.add(new ImageItemModel("Image 2", R.drawable.thumb3, R.drawable.wall3));
        items.add(new ImageItemModel("Image 3", R.drawable.thumb4, R.drawable.wall4));
        items.add(new ImageItemModel("Image 4", R.drawable.thumb5, R.drawable.wall5));
        items.add(new ImageItemModel("Image 5", R.drawable.thumb6, R.drawable.wall6));
        items.add(new ImageItemModel("Image 6", R.drawable.thumb7, R.drawable.wall7));
        items.add(new ImageItemModel("Image 7", R.drawable.thumb8, R.drawable.wall8));
        items.add(new ImageItemModel("Image 8", R.drawable.thumb9, R.drawable.wall9));

        LinearLayout layoutContent = findViewById(R.id.layout_content);
        imageLarge = findViewById(R.id.image_large);

        for (int i = 0; i < items.size(); i++) {
            View view = LayoutInflater.from(this).inflate(R.layout.layout_image_item, layoutContent, false);
            view.setId(i);

            ImageView imageView = view.findViewById(R.id.image_view);
            TextView textView = view.findViewById(R.id.text_view);

            imageView.setImageResource(items.get(i).getThumbnailResource());
            textView.setText(items.get(i).getCaption());

            layoutContent.addView(view);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int i = view.getId();
                    imageLarge.setImageResource(items.get(i).getImageResouce());
                }
            });
        }
    }
}