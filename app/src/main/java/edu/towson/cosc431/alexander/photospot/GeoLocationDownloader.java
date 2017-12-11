package edu.towson.cosc431.alexander.photospot;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

/**
 * Created by tylerhoward on 12/11/17.
 */

public class GeoLocationDownloader {
        static String API = "https://maps.googleapis.com/maps/api/geocode/json?";
        static String Coords ="latlng=";
        static String Key = "&key=AIzaSyCych-jrfZ2bkGMyQbKcq6Yl6S6bNPHStI";

        public static String downloadJson(double latitude, double longitude) {
            URL url = null;
            try {
                Coords = Coords + String.valueOf(latitude)+","+String.valueOf(longitude);
                url = new URL(API + Coords + Key);
                HttpURLConnection connection = (HttpURLConnection)url.openConnection();
                connection.setRequestMethod("GET");
                InputStream stream = connection.getInputStream();
                InputStreamReader reader = new InputStreamReader(stream, "UTF-8");
                char[] bytes = new char[1024];
                StringBuilder builder = new StringBuilder();
                int byteCount = reader.read(bytes);
                while(byteCount > 0) {
                    builder.append(bytes, 0, byteCount);
                    byteCount = reader.read(bytes);
                }
                return builder.toString();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            } catch (ProtocolException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return "";
        }

}
