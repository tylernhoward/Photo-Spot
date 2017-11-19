package edu.towson.cosc431.alexander.photospot.database;

import android.provider.BaseColumns;

/**
 * Created by tylerhoward on 10/30/17.
 */

public class DatabaseContract implements BaseColumns {

    public static final String TABLE_NAME = "Photo";
    public static final String TITLE_COLUMN_NAME = "title";
    public static final String AUTHOR_COLUMN_NAME = "author";
    public static final String DESCRIPTION_COLUMN_NAME = "description";
    public static final String IMAGEURL_COLUMN_NAME = "image_url";
    public static final String VISIBLE_COLUMN_NAME = "is_visible";



}
