package edu.towson.cosc431.alexander.photospot;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import edu.towson.cosc431.alexander.photospot.models.Photo;

/**
 * Created by willi on 10/23/2017.
 */

public class SinglePhotoActivity extends AppCompatActivity {
    private ImageView imageView;
    private TextView title, author, desc;
    private Photo photo;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.full_screen_photo_view);
        Intent intent = getIntent();
        photo = (Photo) intent.getExtras().get(Constants.getPhotoExtraTag());
        bindView();
        title.setText(photo.getTitle());
        author.setText("Taken By: " + photo.getAuthor());
        desc.setText(photo.getDescription());
        Picasso.with(this).load(photo.getImageURL()).fit().into(imageView);
    }

    public void bindView() {
        title = (TextView) findViewById(R.id.photo_title);
        desc = (TextView) findViewById(R.id.photo_description);
        author = (TextView) findViewById(R.id.photo_author);
        imageView = (ImageView) findViewById(R.id.big_image_view);
    }
}
