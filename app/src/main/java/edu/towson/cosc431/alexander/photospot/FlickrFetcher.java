package edu.towson.cosc431.alexander.photospot;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import android.net.Uri;
import android.nfc.Tag;
import android.util.Log;

import edu.towson.cosc431.alexander.photospot.models.Photo;

public class FlickrFetcher {

    public static final String TAG = "FlickrFetchr";
    private static final String ENDPOINT = "https://api.flickr.com/services/rest/";
    private static final String API_KEY = "ef78fe87ccc9f035142e7fc63640fcc4";
    private static final String METHOD_GET_RECENT = "flickr.photos.getRecent";
    private static final String METHOD_GET_INFO = "flickr.people.getInfo";
    private static final String METHOD_SEARCH = "flickr.photos.search";
    private static final String PARAM_EXTRAS = "extras";

    private static final String EXTRA_SMALL_URL = "url_s";

    private static final String XML_PHOTO = "photo";

    byte[] getUrlBytes(String urlSpec) throws IOException {
        URL url = new URL(urlSpec);
        HttpURLConnection connection = (HttpURLConnection)url.openConnection();

        try {
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            InputStream in = connection.getInputStream();

            if (connection.getResponseCode() != HttpURLConnection.HTTP_OK)
                return null;

            int bytesRead = 0;
            byte[] buffer = new byte[1024];
            while((bytesRead = in.read(buffer))> 0)
                out.write(buffer, 0, bytesRead);
            out.close();
            return out.toByteArray();
        } finally {
            connection.disconnect();
        }
    }

    public String getUrl(String urlSpec) throws IOException {
        return new String(getUrlBytes(urlSpec));
    }

    public ArrayList<Photo> getUser(int page, String id) {
        String url = Uri.parse(ENDPOINT).buildUpon()
                .appendQueryParameter("method", METHOD_GET_INFO)
                .appendQueryParameter("api_key", API_KEY)
                .appendQueryParameter("user_id ", id)
                .appendQueryParameter(PARAM_EXTRAS, EXTRA_SMALL_URL)
                .appendQueryParameter("page", Integer.toString(page))
                .build().toString();
        //Log.d(TAG, url);
        return downloadGalleryItems(url);
    }

    public ArrayList<Photo> fetchItems(int page) {
        String url = Uri.parse(ENDPOINT).buildUpon()
                .appendQueryParameter("method", METHOD_GET_RECENT)
                .appendQueryParameter("api_key", API_KEY)
                .appendQueryParameter(PARAM_EXTRAS, EXTRA_SMALL_URL)
                .appendQueryParameter("page", Integer.toString(page))
                .build().toString();
        return downloadGalleryItems(url);
    }

    public ArrayList<Photo> search(String city, String state/*, int page*/) {
        String url = Uri.parse(ENDPOINT).buildUpon()
                .appendQueryParameter("method", METHOD_SEARCH)
                .appendQueryParameter("api_key", API_KEY)
                //.appendQueryParameter("lon", "-76")
                //.appendQueryParameter("lat", "39")
                //.appendQueryParameter("radius", "15")
                //.appendQueryParameter("radius_units", "Miles")
                .appendQueryParameter("extras", "owner_name, description")
                .appendQueryParameter("tags", "Building, landmark, Forest, Creek, Lake, River, Statue, Monument, Architecture, Courthouse, Building Complex, Landscape, Skyline")
                .appendQueryParameter("text", city+" "+state)
                .appendQueryParameter("tag_mode", "any")
                .appendQueryParameter("safe_search", "1")
                //.appendQueryParameter("tags", city+", Maryland")
                .appendQueryParameter("sort", "interestingness-desc")
                .build().toString();
        url+="/";
        return downloadGalleryItems(url);
    }

    public ArrayList<Photo> downloadGalleryItems(String url) {

        ArrayList<Photo> items = new ArrayList<Photo>();
        try {
            String xmlString = getUrl(url);
            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            XmlPullParser parser = factory.newPullParser();
            parser.setInput(new StringReader(xmlString));

            parseItems(items, parser);
        } catch (IOException ioe) {
            Log.e(TAG, "Failed to fetch items", ioe);
        } catch (XmlPullParserException xppe) {
            Log.e(TAG, "Failed to parse items", xppe);
        }
        return items;
    }

    void parseItems(ArrayList<Photo> items, XmlPullParser parser) throws XmlPullParserException, IOException {
        int eventType = parser.next();
        while (eventType != XmlPullParser.END_DOCUMENT) {
            if (eventType == XmlPullParser.START_TAG && XML_PHOTO.equals(parser.getName())) {
                String id = parser.getAttributeValue(null, "id");
                String farm = parser.getAttributeValue(null, "farm");
                String server = parser.getAttributeValue(null, "server");
                String secret = parser.getAttributeValue(null, "secret");
                String title = parser.getAttributeValue(null, "title");
                String owner = parser.getAttributeValue(null, "owner");
                String smallUrl = parser.getAttributeValue(null, EXTRA_SMALL_URL);
                String imageUrl = "https://farm"+farm+".staticflickr.com/"+server+"/"+id+"_"+secret+".jpg";
                Photo photo = new Photo(title, "desc", imageUrl , owner);
                items.add(photo);
                Log.d(TAG, photo.getImageURL());
            }

            eventType = parser.next();
        }
    }
}
