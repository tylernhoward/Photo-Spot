package edu.towson.cosc431.alexander.photospot;

/**
 * Created by pkmna on 10/26/2017.
 */

public class Constants {
    private static final String PHOTO_EXTRA_TAG = "PHOTO";
    private static final String CAPTURED_PHOTO_TAG = "CAP_PHOTO";
    private static final int REQUEST_IMAGE_CAPTURE = 1;
    private static final int REQUEST_NEW_PHOTO = 77;

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
