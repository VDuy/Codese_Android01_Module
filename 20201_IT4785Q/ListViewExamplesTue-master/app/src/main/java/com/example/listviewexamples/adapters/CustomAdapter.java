package com.example.listviewexamples.adapters;

import android.content.ClipData;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.listviewexamples.R;
import com.example.listviewexamples.models.ItemModel;

import java.util.List;

public class CustomAdapter extends BaseAdapter {

    List<ItemModel> items;
    Context context;
    int count = 0;

    public CustomAdapter(List<ItemModel> items, Context context) {
        this.items = items;
        this.context = context;
        count = 0;
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

        ViewHolder viewHolder;

        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.layout_item_view, viewGroup, false);
            Log.v("TAG", "Count: " + (count++));

            viewHolder = new ViewHolder();
            viewHolder.imageAvatar = view.findViewById(R.id.image_avatar);
            viewHolder.textTitle = view.findViewById(R.id.text_title);
            viewHolder.textSubtitle = view.findViewById(R.id.text_subtitle);
            viewHolder.checkSelected = view.findViewById(R.id.check_selected);

            view.setTag(viewHolder);
        } else
            viewHolder = (ViewHolder) view.getTag();

        ItemModel item = items.get(i);

        viewHolder.imageAvatar.setImageResource(item.getAvatarResource());
        viewHolder.textTitle.setText(item.getTitle());
        viewHolder.textSubtitle.setText(item.getSubtitle());
        viewHolder.checkSelected.setChecked(item.isSelected());

        viewHolder.checkSelected.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                item.setSelected(((CheckBox)view).isChecked());
                notifyDataSetChanged();
            }
        });

        return view;
    }

    private class ViewHolder {
        ImageView imageAvatar;
        TextView textTitle;
        TextView textSubtitle;
        CheckBox checkSelected;
    }
}
