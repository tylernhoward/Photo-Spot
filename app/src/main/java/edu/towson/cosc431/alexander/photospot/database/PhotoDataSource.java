package edu.towson.cosc431.alexander.photospot.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import edu.towson.cosc431.alexander.photospot.models.Photo;


/**
 * Created by tylerhoward on 10/30/17.
 */

public class PhotoDataSource implements IDataSource {
    private static PhotoDataSource instance;
    private static PhotosDBHelper dbHelper;
    private PhotoDataSource(Context ctx){
        dbHelper = new PhotosDBHelper(ctx);
    }
    public static PhotoDataSource getInstance(Context ctx){
        if (instance == null) instance = new PhotoDataSource(ctx);
            return instance;
    }

    private ContentValues PhotoToContentValues(Photo photo) {
        ContentValues cv = new ContentValues();
        cv.put(DatabaseContract._ID, photo.getId());
        cv.put(DatabaseContract.TITLE_COLUMN_NAME, photo.getTitle());
        cv.put(DatabaseContract.AUTHOR_COLUMN_NAME, photo.getAuthor());
        cv.put(DatabaseContract.DESCRIPTION_COLUMN_NAME, photo.getDescription());
        cv.put(DatabaseContract.IMAGEURL_COLUMN_NAME, photo.getImageURL());
        cv.put(DatabaseContract.VISIBLE_COLUMN_NAME, photo.isVisible());
        cv.put(DatabaseContract.FAVORITE_COLUMN_NAME, photo.isFavorite());


        return cv;
    }


    @Override
    public ArrayList<Photo> getAllPhotos() {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + DatabaseContract.TABLE_NAME, null);
        ArrayList<Photo> photos = new ArrayList<>();
        while (cursor.moveToNext()){
            Photo photo = new Photo();
            String id = cursor.getString(cursor.getColumnIndex(DatabaseContract._ID));
            String title = cursor.getString(cursor.getColumnIndex(DatabaseContract.TITLE_COLUMN_NAME));
            String author = cursor.getString(cursor.getColumnIndex(DatabaseContract.AUTHOR_COLUMN_NAME));
            String description = cursor.getString(cursor.getColumnIndex(DatabaseContract.DESCRIPTION_COLUMN_NAME));
            String imageURL = cursor.getString(cursor.getColumnIndex(DatabaseContract.IMAGEURL_COLUMN_NAME));
            boolean isVisible = cursor.getInt(cursor.getColumnIndex(DatabaseContract.VISIBLE_COLUMN_NAME)) == 1;
            boolean isFavorite = cursor.getInt(cursor.getColumnIndex(DatabaseContract.FAVORITE_COLUMN_NAME)) == 1;

            photo.setFavorite(isFavorite);
            photo.setId(id);
            photo.setTitle(title);
            photo.setAuthor(author);
            photo.setDescription(description);
            photo.setImageURL(imageURL);
            if(isVisible){ photos.add(photo);}
        }
        return photos;
    }

    @Override
    public void addPhoto(Photo photo) {
        ContentValues cv = PhotoToContentValues(photo);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.insert(DatabaseContract.TABLE_NAME, null, cv);
    }

    @Override
    public void removePhoto(Photo photo) {
        photo.setVisible(false);
        ContentValues cv = PhotoToContentValues(photo);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.update(DatabaseContract.TABLE_NAME, cv, DatabaseContract._ID + " = ?", new String[]{ String.valueOf(photo.getId()) });
    }

    @Override
    public Photo getPhoto(String _id) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + DatabaseContract.TABLE_NAME + " WHERE " + DatabaseContract._ID + " = \"" + _id + "\"", null); //NEEDS TO BE SPECIALIZED
        Photo photo = new Photo();
        String id = cursor.getString(cursor.getColumnIndex(DatabaseContract._ID));
        String title = cursor.getString(cursor.getColumnIndex(DatabaseContract.TITLE_COLUMN_NAME));
        String author = cursor.getString(cursor.getColumnIndex(DatabaseContract.AUTHOR_COLUMN_NAME));
        String description = cursor.getString(cursor.getColumnIndex(DatabaseContract.DESCRIPTION_COLUMN_NAME));
        String imageURL = cursor.getString(cursor.getColumnIndex(DatabaseContract.IMAGEURL_COLUMN_NAME));
        boolean isVisible = cursor.getInt(cursor.getColumnIndex(DatabaseContract.VISIBLE_COLUMN_NAME)) == 1;
        boolean isFavorite = cursor.getInt(cursor.getColumnIndex(DatabaseContract.FAVORITE_COLUMN_NAME)) == 1;

        photo.setFavorite(isFavorite);
        photo.setId(id);
        photo.setTitle(title);
        photo.setAuthor(author);
        photo.setDescription(description);
        photo.setImageURL(imageURL);

        return photo;
    }

    @Override
    public void updatePhoto(Photo photo) {
        ContentValues cv = PhotoToContentValues(photo);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.update(DatabaseContract.TABLE_NAME, cv, DatabaseContract._ID + " = ?", new String[]{ String.valueOf(photo.getId()) });
    }

}
