package edu.towson.cosc431.alexander.photospot;

/**
 * Created by pkmna on 10/26/2017.
 */

public class Constants {
    private static final String photoExtraTag = "PHOTO";
    private static final int REQUEST_IMAGE_CAPTURE = 1;


    public static int getRequestImageCapture() {
        return REQUEST_IMAGE_CAPTURE;
    }

    public static String getPhotoExtraTag() {
        return photoExtraTag;
    }
}
