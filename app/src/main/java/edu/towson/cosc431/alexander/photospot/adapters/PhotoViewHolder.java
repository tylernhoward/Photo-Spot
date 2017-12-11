package edu.towson.cosc431.alexander.photospot.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import edu.towson.cosc431.alexander.photospot.interfaces.IController;
import edu.towson.cosc431.alexander.photospot.R;
import edu.towson.cosc431.alexander.photospot.models.Photo;

/**
 * Created by Josh on 10/15/2017.
 */

public class PhotoViewHolder extends RecyclerView.ViewHolder {

    private Photo photo;
    private TextView photoTitle, photoDescription;
    private ImageView imageView;

    public PhotoViewHolder(View itemView, final IController controller) {
        super(itemView);
        itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                return true;
            }
        });

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                controller.displayFullScreenImage(photo);
            }
        });
        photoTitle = (TextView) itemView.findViewById(R.id.photoTitle);
        photoDescription = (TextView) itemView.findViewById(R.id.photoDescription);
        imageView = (ImageView) itemView.findViewById(R.id.imageView);
    }

    public void bindTodo(Photo photo) {
        this.photo = photo;
        photoTitle.setText(photo.getTitle());
        photoDescription.setText(photo.getDescription());
        Picasso.with(itemView.getContext()).load(photo.getImageURL()).fit().into(imageView);
    }
}