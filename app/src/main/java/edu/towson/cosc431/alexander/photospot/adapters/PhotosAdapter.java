package edu.towson.cosc431.alexander.photospot.adapters;

/**
 * Created by pkmna on 10/15/2017.
 */

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import edu.towson.cosc431.alexander.photospot.IController;
import edu.towson.cosc431.alexander.photospot.R;
import edu.towson.cosc431.alexander.photospot.models.Photo;


/**
 * Created by pkmna on 10/2/2017.
 */

public class PhotosAdapter extends RecyclerView.Adapter<PhotoViewHolder> {

    private ArrayList<Photo> photos;
    private Photo photo;
    private IController controller;
    private PhotoViewHolder holder;

    public PhotosAdapter(ArrayList<Photo> photos, IController controller) {
        this.photos = photos;
        this.controller = controller;
    }

    @Override
    public PhotoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.photo_view, parent, false);
        //view.setOnLongClickListener(controller.deleteTodo());
        PhotoViewHolder vh = new PhotoViewHolder(view, controller);
        return vh;
    }

    @Override
    public void onBindViewHolder(PhotoViewHolder holder, int position) {
        photo = photos.get(position);
        this.holder = holder;
        holder.bindTodo(photo);
    }

    @Override
    public int getItemCount() {
        return photos.size();
    }
}

