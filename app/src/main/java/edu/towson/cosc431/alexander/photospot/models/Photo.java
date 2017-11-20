package edu.towson.cosc431.alexander.photospot.models;

import java.io.Serializable;
import java.util.UUID;

/**
 * Created by pkmna on 10/22/2017.
 */

public class Photo implements Serializable {
    private String title;
    private String description;
    private String author;
    private String imageURL;
    private String _id;
    private boolean isVisible;
    private boolean isFavorite;


    public Photo(String title, String description, String imageUrl, String author) {
        this.title = title;
        this.description = description;
        this.imageURL = imageUrl;
        this.author = author;
        this._id = UUID.randomUUID().toString();
        this.isVisible = true;
        this.isFavorite = false;
    }

    public Photo(){
        this.title = "";
        this.description = "";
        this.imageURL = "";
        this.author = "";
        this._id = UUID.randomUUID().toString();
        this.isVisible = true;
        this.isFavorite = false;

    }
    public boolean isVisible() {
        return isVisible;
    }

    public void setVisible(boolean visible) {
        isVisible = visible;
    }
    public boolean isFavorite() {
        return isFavorite;
    }

    public void toggleFavorite() {
        isFavorite = !isFavorite;
    }

    public Photo(String imageUrl)
    {
        this.imageURL = imageUrl;
    }
    public String getId() {return _id;}

    public void setId(String _id) {this._id = _id;}

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
