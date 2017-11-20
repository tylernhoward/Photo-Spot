package edu.towson.cosc431.alexander.photospot.models;

import java.io.Serializable;

/**
 * Created by pkmna on 10/22/2017.
 */

public class Photo implements Serializable {
    private String title;
    private String description;
    private String author;
    private String imageURL;

    public Photo(String title, String description, String imageUrl, String author) {
        this.title = title;
        this.description = description;
        this.imageURL = imageUrl;
        this.author = author;
    }

    public Photo(String imageUrl)
    {
        imageURL = imageUrl;
    }

    public String getImageURL() {
        return imageURL;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
