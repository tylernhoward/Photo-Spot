package edu.towson.cosc431.alexander.photospot;

import java.util.ArrayList;

import edu.towson.cosc431.alexander.photospot.database.IDataSource;
import edu.towson.cosc431.alexander.photospot.interfaces.IModel;
import edu.towson.cosc431.alexander.photospot.models.Photo;

/**
 * Created by tylerhoward on 11/19/17.
 */

public class PhotoModel implements IModel {

        private IDataSource dataSource = null;
        public PhotoModel(IDataSource ds) {
            dataSource = ds;
        }

        @Override
        public ArrayList<Photo> getPhotos(){
            return dataSource.getAllPhotos();
        }

        @Override
        public void removePhoto(Photo photo) {
            dataSource.removePhoto(photo);
        }

        @Override
        public void addPhoto(Photo photo) {
            dataSource.addPhoto(photo);
        }
        public void updatePhoto(Photo photo){
            dataSource.updatePhoto(photo);
        }

    }
