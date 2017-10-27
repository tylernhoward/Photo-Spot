package edu.towson.cosc431.alexander.photospot;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import edu.towson.cosc431.alexander.photospot.models.Photo;

/**
 * Created by Josh on 10/26/2017.
 */

public class NewPhotoActivity extends AppCompatActivity {

    private ImageView imageView;
    private EditText titleEt, authorEt, descEt;
    private Button saveBtn;
    private IController controller;
    private String photoPath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_photo);
        bindView();
        controller = MainActivity.getController();

        photoPath = (String) getIntent().getExtras().get(Constants.getCapturedPhotoTag());
        Bitmap b = BitmapFactory.decodeFile(photoPath);
        imageView.setImageBitmap(b);
    }

    private void bindView() {
        imageView = (ImageView) findViewById(R.id.image_view);
        titleEt = (EditText) findViewById(R.id.titleEt);
        authorEt = (EditText) findViewById(R.id.authorEt);
        descEt = (EditText) findViewById(R.id.descEt);
        saveBtn = (Button) findViewById(R.id.saveBtn);
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                controller.addPhoto(new Photo(titleEt.getText().toString(), descEt.getText().toString(), photoPath, authorEt.getText().toString()));
                controller.refresh();
                finish();
            }
        });
    }
}
