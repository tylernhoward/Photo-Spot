package edu.towson.cosc431.alexander.photospot.interfaces;

import java.util.ArrayList;

import edu.towson.cosc431.alexander.photospot.models.Photo;

/**
 * Created by tylerhoward on 11/19/17.
 */

public interface IModel {
    ArrayList<Photo> getPhotos();
    void removePhoto(Photo photo);
    void addPhoto(Photo photo);
    void updatePhoto(Photo photo);
}
