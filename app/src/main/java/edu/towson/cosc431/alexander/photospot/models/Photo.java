package edu.towson.cosc431.alexander.photospot.models;

/**
 * Created by pkmna on 10/22/2017.
 */

public class Photo {
    private String imageURL;

    public Photo(String title, String description, String imageUrl) {
        this.title = title;
        this.description = description;
        this.imageURL = imageUrl;
    }

    public String getImageURL() {
        return imageURL;
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

    private String title;
    private String description;

}
