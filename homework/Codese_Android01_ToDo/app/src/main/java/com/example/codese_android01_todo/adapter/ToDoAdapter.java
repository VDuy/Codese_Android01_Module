package com.example.codese_android01_todo.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.codese_android01_todo.R;
import com.example.codese_android01_todo.model.TodoModel;

import java.util.List;


public class ToDoAdapter extends ArrayAdapter<TodoModel> {
    private static final String TAG = "getView";
    List<TodoModel> arrTodo;
    private int resource;

    public ToDoAdapter(@NonNull Context context, int resource, @NonNull List<TodoModel> objects) {
        super(context, resource, objects);
        this.arrTodo = objects;
        this.resource = resource;
    }

    @SuppressLint("CheckResult")
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
            viewHolder.tvDate = convertView.findViewById(R.id.tv_time);
            viewHolder.tvTitle = convertView.findViewById(R.id.tv_title);
            viewHolder.tvTag = convertView.findViewById(R.id.tv_tag);
            viewHolder.tvContent = convertView.findViewById(R.id.tv_content);

            convertView.setTag(viewHolder);

        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        try {
            viewHolder.tvDate.setText(arrTodo.get(position).getShowtime());
            viewHolder.tvTitle.setText(arrTodo.get(position).getTitle());
            viewHolder.tvTag.setText(arrTodo.get(position).getTag());
            viewHolder.tvContent.setText(arrTodo.get(position).getContent() + ".");

        } catch (Throwable t) {
            Log.d(TAG, "getView: " + t);
        }
        return convertView;
    }

    public class ViewHolder {
        TextView tvDate;
        TextView tvTitle;
        TextView tvTag;
        TextView tvContent;
    }


}