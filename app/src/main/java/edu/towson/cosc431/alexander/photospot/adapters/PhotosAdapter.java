package edu.towson.cosc431.alexander.photospot.adapters;

/**
 * Created by Josh on 10/15/2017.
 */

import android.app.Activity;
import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.Toast;

import java.util.ArrayList;

import edu.towson.cosc431.alexander.photospot.ASyncResponse;
import edu.towson.cosc431.alexander.photospot.FlickrFetcher;
import edu.towson.cosc431.alexander.photospot.IController;
import edu.towson.cosc431.alexander.photospot.R;
import edu.towson.cosc431.alexander.photospot.models.Photo;

public class PhotosAdapter extends RecyclerView.Adapter<PhotoViewHolder> {

    private ArrayList<Photo> photosList;
    private Photo photo;
    private IController controller;
    private PhotoViewHolder holder;
    private FlickrTask task;

    public PhotosAdapter(ArrayList<Photo> photos, IController controller) {
        this.photosList = photos;
        updateList();
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
        photo = photosList.get(position);
        //THIS NEEDS TO GO AWAY
       // if(photo.isVisible()) {
            this.holder = holder;
            holder.bindTodo(photo);
       // }
    }

    @Override
    public int getItemCount() {
        return photosList.size();
    }

    public void addItemsToList(ArrayList<Photo> list)
    {
        photosList.addAll(list);
     }

    public void updateList()
    {
        task = new FlickrTask();
        task.execute();
        //task.wait(50);
        //new FlickrTask().execute();
    }

    private class FlickrTask extends AsyncTask<Void, Void, ArrayList<Photo>>
    {
        ASyncResponse temp = null;
        FlickrFetcher flickr = new FlickrFetcher();
        Activity activity;

        @Override
        protected void onPreExecute()
        {
            super.onPreExecute();
        }

        protected void onPostExecute(ArrayList<Photo> photos){
            //photosList = photos;
            for(int i =0; i<photos.size(); i++)
            {
                photosList.add(photos.get(i));
            }
            controller.refresh();
        }

        @Override
        protected ArrayList<Photo> doInBackground(Void... params) {
            return flickr.search("Towson");
        }
    }
}

