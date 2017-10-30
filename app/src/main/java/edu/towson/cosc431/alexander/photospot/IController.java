package edu.towson.cosc431.alexander.photospot;

import edu.towson.cosc431.alexander.photospot.models.Photo;

/**
 * Created by Josh on 10/22/2017.
 */

public interface IController {
    void displayFullScreenImage(Photo photo);
    void addPhoto(Photo photo);
    void refresh();
}
