package com.example.listviewexamples;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import com.example.listviewexamples.adapters.ChatAdapter;
import com.example.listviewexamples.models.ChatModel;

import java.util.ArrayList;
import java.util.List;

public class ChatListActivity extends AppCompatActivity {

    List<ChatModel> messages;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_list);

        messages = new ArrayList<>();
        messages.add(new ChatModel("user1", "Hello", R.drawable.thumb1));
        messages.add(new ChatModel("user1", "How are you?", R.drawable.thumb1));
        messages.add(new ChatModel("me", "Fine. Thanks.", R.drawable.thumb2));
        messages.add(new ChatModel("me", "What's your name?", R.drawable.thumb2));
        messages.add(new ChatModel("user1", "Peter.", R.drawable.thumb1));

        ChatAdapter adapter = new ChatAdapter(messages, this);
        ListView listView = findViewById(R.id.list_view);
        listView.setAdapter(adapter);
    }
}