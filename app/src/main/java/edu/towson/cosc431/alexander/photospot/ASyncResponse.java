package edu.towson.cosc431.alexander.photospot;

import java.util.ArrayList;

import edu.towson.cosc431.alexander.photospot.models.Photo;

/**
 * Created by willi on 11/18/2017.
 */

public interface ASyncResponse
{
    public void retrieveList(ArrayList<Photo> list);
}
