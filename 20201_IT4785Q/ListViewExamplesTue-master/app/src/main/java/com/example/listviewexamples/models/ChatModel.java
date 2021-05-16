package com.example.listviewexamples.models;

public class ChatModel {
    private String username;
    private String content;
    private int avatarResource;

    public ChatModel(String username, String content, int avatarResource) {
        this.username = username;
        this.content = content;
        this.avatarResource = avatarResource;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getAvatarResource() {
        return avatarResource;
    }

    public void setAvatarResource(int avatarResource) {
        this.avatarResource = avatarResource;
    }
}
