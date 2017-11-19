package edu.towson.cosc431.alexander.photospot.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by tylerhoward on 10/30/17.
 */

public class PhotosDBHelper extends SQLiteOpenHelper {
    private IDataSource dataSource;

    private static final String DATABASE_NAME ="photos.db";
    private static final int DB_VERSION =1;
    private static final String CREATE_TABLE = "CREATE TABLE " + DatabaseContract.TABLE_NAME + "("
            + DatabaseContract._ID +" text , " + DatabaseContract.TITLE_COLUMN_NAME + " text,"
            + DatabaseContract.AUTHOR_COLUMN_NAME +" text,"+ DatabaseContract.DESCRIPTION_COLUMN_NAME +" text,"
            + DatabaseContract.IMAGEURL_COLUMN_NAME +" text," + DatabaseContract.VISIBLE_COLUMN_NAME +" integer);";
    public PhotosDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
