package com.example.listviewexamples.models;

public class ItemModel {
    private String title;
    private String subtitle;
    private int avatarResource;
    private boolean selected;

    public ItemModel(String title, String subtitle, int avatarResource) {
        this.title = title;
        this.subtitle = subtitle;
        this.avatarResource = avatarResource;
        this.selected = false;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public int getAvatarResource() {
        return avatarResource;
    }

    public void setAvatarResource(int avatarResource) {
        this.avatarResource = avatarResource;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }
}
