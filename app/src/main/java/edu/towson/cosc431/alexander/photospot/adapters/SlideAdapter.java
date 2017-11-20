package edu.towson.cosc431.alexander.photospot.adapters;

import android.content.Context;
import android.net.Uri;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.net.URI;
import java.util.ArrayList;

import edu.towson.cosc431.alexander.photospot.R;
import edu.towson.cosc431.alexander.photospot.models.Photo;

/**
 * Created by tylerhoward on 11/20/17.
 */

public class SlideAdapter extends PagerAdapter {
    private ArrayList<Photo> images;
    private LayoutInflater inflater;
    private Context context;

    public SlideAdapter(Context context, ArrayList<Photo> images) {
        this.context = context;
        this.images = images;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public int getCount() {
        return images.size();
    }

    @Override
    public Object instantiateItem(ViewGroup view, int position) {
        View myImageLayout = inflater.inflate(R.layout.slide, view, false);
        ImageView myImage = (ImageView) myImageLayout.findViewById(R.id.image);
        Picasso.with(context).load(images.get(position).getImageURL()).fit().into(myImage);
        view.addView(myImageLayout, 0);
        return myImageLayout;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view.equals(object);
    }
}
