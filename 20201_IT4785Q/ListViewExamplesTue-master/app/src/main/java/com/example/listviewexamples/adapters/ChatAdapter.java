package com.example.listviewexamples.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.listviewexamples.R;
import com.example.listviewexamples.models.ChatModel;

import java.util.List;

public class ChatAdapter extends BaseAdapter {

    List<ChatModel> messages;
    Context context;

    public ChatAdapter(List<ChatModel> messages, Context context) {
        this.messages = messages;
        this.context = context;
    }

    @Override
    public int getCount() {
        return messages.size();
    }

    @Override
    public Object getItem(int i) {
        return messages.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public int getItemViewType(int position) {
        ChatModel message = messages.get(position);
        if (message.getUsername().equals("me"))
            return 1;
        else
            return 0;
    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            if (getItemViewType(i) == 0)
                view = LayoutInflater.from(context).inflate(R.layout.layout_item_left, viewGroup, false);
            else
                view = LayoutInflater.from(context).inflate(R.layout.layout_item_right, viewGroup, false);
        }

        ImageView imageAvatar = view.findViewById(R.id.image_avatar);
        TextView textUser = view.findViewById(R.id.text_user);
        TextView textContent = view.findViewById(R.id.text_content);

        ChatModel message = messages.get(i);
        imageAvatar.setImageResource(message.getAvatarResource());
        textUser.setText(message.getUsername());
        textContent.setText(message.getContent());

        return view;
    }
}
