package edu.towson.cosc431.alexander.photospot;

/**
 * Created by pkmna on 10/26/2017.
 */

public class Constants {
    private static final String PHOTO_EXTRA_TAG = "PHOTO";
    public static final String PHOTOARRAY_EXTRA_TAG = "PHOTO_ARRAY";

    private static final String CAPTURED_PHOTO_TAG = "CAP_PHOTO";
    private static final int REQUEST_IMAGE_CAPTURE = 1;
    private static final int REQUEST_NEW_PHOTO = 77;
    protected static final String ENDPOINT = "https://api.flickr.com/services/rest/";
    protected static final String API_KEY = "ef78fe87ccc9f035142e7fc63640fcc4";
    protected static final String METHOD_SEARCH = "flickr.photos.search";
    protected static final String XML_PHOTO = "photo";


    public static String getCapturedPhotoTag() {
        return CAPTURED_PHOTO_TAG;
    }

    public static int getRequestNewPhoto() {
        return REQUEST_NEW_PHOTO;
    }

    public static int getRequestImageCapture() {
        return REQUEST_IMAGE_CAPTURE;
    }

    public static String getPhotoExtraTag() {
        return PHOTO_EXTRA_TAG;
    }
}
