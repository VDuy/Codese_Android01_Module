package com.example.listviewexamples.models;

public class ImageItemModel {
    private String caption;
    private int thumbnailResource;
    private int imageResouce;

    public ImageItemModel(String caption, int thumbnailResource, int imageResouce) {
        this.caption = caption;
        this.thumbnailResource = thumbnailResource;
        this.imageResouce = imageResouce;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public int getThumbnailResource() {
        return thumbnailResource;
    }

    public void setThumbnailResource(int thumbnailResource) {
        this.thumbnailResource = thumbnailResource;
    }

    public int getImageResouce() {
        return imageResouce;
    }

    String jsonString =
            "[{\"name\":\"John\", \"age\":20, \"gender\":\"male\"}," +
                    " {\"name\":\"Peter\", \"age\":21, \"gender\":\"male\"}, " +
                    "{\"name\":\"July\", \"age\":19, \"gender\":\"female\"}]";

    public void setImageResouce(int imageResouce) {
        this.imageResouce = imageResouce;
    }
}
