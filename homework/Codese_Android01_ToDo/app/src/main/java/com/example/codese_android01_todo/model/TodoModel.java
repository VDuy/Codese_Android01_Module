package com.example.codese_android01_todo.model;

import java.io.Serializable;

public class TodoModel implements Serializable {
    int id;
    String title;
    String content;
    String tag;
    String showtime;

    public TodoModel(int id, String title, String content, String tag, String showtime) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.tag = tag;
        this.showtime = showtime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getShowtime() {
        return showtime;
    }

    public void setShowtime(String showtime) {
        this.showtime = showtime;
    }


    public String toString() {
        return "model{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", tag='" + tag + '\'' +
                ", showtime='" + showtime + '\'' +
                '}';
    }
}
