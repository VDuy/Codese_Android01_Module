package com.example.listviewexamples.adapters;

import android.app.Activity;
import android.content.Context;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import java.util.List;

public class ImageAdapter extends BaseAdapter {

    Context context;
    List<Integer> items;
    int size;

    public ImageAdapter(Context context, List<Integer> items) {
        this.context = context;
        this.items = items;

        DisplayMetrics metrics = new DisplayMetrics();
        ((Activity)context).getWindowManager().getDefaultDisplay().getMetrics(metrics);
        size = metrics.widthPixels / 3;
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int i) {
        return items.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ImageView imageView;

        if (view == null) {
            imageView = new ImageView(context);
            imageView.setLayoutParams(new GridView.LayoutParams(size, size));
            imageView.setPadding(2, 2, 2, 2);
        }
        else
            imageView = (ImageView) view;
        
        imageView.setImageResource(items.get(i));

        return imageView;
    }
}
