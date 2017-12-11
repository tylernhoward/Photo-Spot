package edu.towson.cosc431.alexander.photospot.interfaces;

import java.util.ArrayList;

import edu.towson.cosc431.alexander.photospot.models.Photo;

/**
 * Created by tylerhoward on 11/13/17.
 */

public interface IDataSource {
    ArrayList<Photo> getAllPhotos();
    void addPhoto(Photo photo);
    void removePhoto(Photo photo);
    Photo getPhoto(String _id);
    void updatePhoto(Photo photo);
}
