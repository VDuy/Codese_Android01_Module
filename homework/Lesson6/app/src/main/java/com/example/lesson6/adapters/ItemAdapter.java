package com.example.lesson6.adapters;

import android.annotation.SuppressLint;
import android.content.Context;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.example.lesson6.R;
import com.example.lesson6.databases.DatabaseUtils;
import com.example.lesson6.models.ItemModel;


import java.util.List;

public class ItemAdapter extends ArrayAdapter<ItemModel> {

    List<ItemModel> arrItem;
    private int resource;

    public ItemAdapter(@NonNull Context context, int resource, @NonNull List<ItemModel> objects) {
        super(context, resource, objects);
        this.arrItem = objects;
        this.resource = resource;
    }

    @SuppressLint("CheckResult")
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_books_list, parent, false);
            viewHolder.ivImage = convertView.findViewById(R.id.iv_image);
            viewHolder.tvTitle = convertView.findViewById(R.id.tv_title);
            viewHolder.tvAuthor = convertView.findViewById(R.id.tv_author);

            convertView.setTag(viewHolder);

        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        ItemModel itemModel = arrItem.get(position);
        Glide.with(parent.getContext()).load(
                DatabaseUtils.getInstance(parent.getContext()).getListTopic().get(position).getImage()
        ).into(viewHolder.ivImage);
        viewHolder.tvTitle.setText(DatabaseUtils.getInstance(parent.getContext()).getListTopic().get(position).getTitle());
        viewHolder.tvAuthor.setText(DatabaseUtils.getInstance(parent.getContext()).getListTopic().get(position).getAuthor());
        return convertView;
    }

    public class ViewHolder {
        ImageView ivImage;
        TextView tvTitle;
        TextView tvAuthor;
    }
}