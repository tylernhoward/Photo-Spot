package edu.towson.cosc431.alexander.photospot;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import edu.towson.cosc431.alexander.photospot.models.Photo;

import static edu.towson.cosc431.alexander.photospot.R.id.imageView;

/**
 * Created by willi on 10/23/2017.
 */

public class single_photo_activity extends AppCompatActivity
{
    private static final String TAG = MainActivity.class.getSimpleName();
    ImageView image;
    TextView title;
    TextView author;
    TextView desc;
    Photo photo;
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.full_screen_photo_view);
        Intent intent = getIntent();
        photo = (Photo) intent.getExtras().get("PHOTO");
        Log.d(TAG, photo.getTitle());
        bindView();
        title.setText(photo.getTitle());
        author.setText("Taken By: "+photo.getAuthor());
        desc.setText(photo.getDescription());
        Picasso.with(this).load(photo.getImageURL()).fit().into(image);

    }

    public void bindView()
    {
        title = (TextView) findViewById(R.id.photo_title);
        desc = (TextView) findViewById(R.id.photo_description);
        author = (TextView) findViewById(R.id.photo_author);
        image = (ImageView) findViewById(R.id.big_image_view);
    }
}
