package edu.towson.cosc431.alexander.photospot;

import android.util.Log;

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
        static String API = "http://locationiq.org/v1/reverse.php?format=json";
        static String Lat ="&lat=";
        static String Long = "&lon=";
        static String Key = "&key=96254c5f4eb1f1";

        public static String downloadJson(double latitude, double longitude) {
            URL url = null;
            try {
                Lat= Lat + String.valueOf(latitude);
                Long = Long + String.valueOf(longitude);
                url = new URL(API + Key + Lat + Long);
                Log.d("__________",url.toString());
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
