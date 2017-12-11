package edu.towson.cosc431.alexander.photospot;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import edu.towson.cosc431.alexander.photospot.database.PhotoDataSource;
import edu.towson.cosc431.alexander.photospot.interfaces.IController;
import edu.towson.cosc431.alexander.photospot.interfaces.IModel;
import edu.towson.cosc431.alexander.photospot.models.Photo;

/**
 * Created by willi on 10/23/2017.
 */

public class SinglePhotoActivity extends AppCompatActivity {
    private ImageView imageView;
    private TextView title, author, desc;
    private CheckBox fav;
    private Photo photo;
    private IModel photoModel = new PhotoModel(PhotoDataSource.getInstance(this));
    private IController controller;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_photo);
        photo = (Photo) getIntent().getExtras().get(Constants.getPhotoExtraTag());
        bindView();
    }

    private void bindView() {
        title = (TextView) findViewById(R.id.photo_title);
        desc = (TextView) findViewById(R.id.photo_description);
        author = (TextView) findViewById(R.id.photo_author);
        imageView = (ImageView) findViewById(R.id.big_image_view);
        fav = (CheckBox)findViewById(R.id.favoriteBtn);
        title.setText(photo.getTitle());
        author.setText("Taken By: " + photo.getAuthor());
        desc.setText(photo.getDescription());
        fav.setChecked(photo.isFavorite());
        controller = MainActivity.getController();
        fav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onToggleStar();
            }});
        Picasso.with(this).load(photo.getImageURL()).fit().into(imageView);
    }
    public void onToggleStar(){
        photo.toggleFavorite();
        controller.updatePhoto(photo);  //NEED UPDATE MODEL NOT DS
        if(photo.isFavorite()){
            photoModel.addPhoto(photo);
        }
        else{
            photoModel.removePhoto(photo);
        }
        controller.refresh();
    }
}
