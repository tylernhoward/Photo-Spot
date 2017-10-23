package edu.towson.cosc431.alexander.photospot.adapters;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import edu.towson.cosc431.alexander.photospot.MainActivity;
import edu.towson.cosc431.alexander.photospot.R;
import edu.towson.cosc431.alexander.photospot.models.Photo;
import edu.towson.cosc431.alexander.photospot.IController;

/**
 * Created by pkmna on 10/15/2017.
 */

public class PhotoViewHolder extends RecyclerView.ViewHolder {

    private Photo photo;
    private IController controller;
    private TextView photoTitle, photoDescription;
    private ImageView imageView;

    public PhotoViewHolder(View itemView, final IController controller) {
        super(itemView);
        this.controller = controller;
        itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                //controller.deletePhoto(photo);
                return false;
            }
        });
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                controller.fullScreenImage(photo);
         //       controller.launchEditTodoActivity(Constants.EDIT_REQUEST, todo);
            }
        });
        photoTitle = (TextView) itemView.findViewById(R.id.photoTitle);
        photoDescription = (TextView) itemView.findViewById(R.id.photoDescription);
        imageView = (ImageView) itemView.findViewById(R.id.imageView);
    }

    public void bindTodo(Photo photo) {
        photoTitle.setText(photo.getTitle());
        photoDescription.setText(photo.getDescription());
        Picasso.with(itemView.getContext()).load(photo.getImageURL()).fit().into(imageView);
        this.photo = photo;
    }
}