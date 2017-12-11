package edu.towson.cosc431.alexander.photospot;

import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import edu.towson.cosc431.alexander.photospot.adapters.SlideAdapter;
import edu.towson.cosc431.alexander.photospot.models.Photo;

public class SlideshowActivity extends AppCompatActivity {

    private static ViewPager mPager;
    private static int currentPage = 0;
    private ArrayList<Photo> photos = new ArrayList<Photo>();
    private ArrayList<Photo> slideArray = new ArrayList<Photo>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        photos = (ArrayList<Photo>)getIntent().getExtras().get(Constants.PHOTOARRAY_EXTRA_TAG);
        setContentView(R.layout.activity_slideshow);
        init();
    }
    private void init() {
        slideArray = new ArrayList<>();
        slideArray.addAll(photos);

        mPager = (ViewPager)findViewById(R.id.pager);

        mPager.setAdapter(new SlideAdapter(SlideshowActivity.this,slideArray));

        // Auto start of viewpager
        final Handler handler = new Handler();
        final Runnable Update = new Runnable() {
            public void run() {
                if (currentPage == photos.size()) {
                    currentPage = 0;
                }
                mPager.setCurrentItem(currentPage++, true);
            }
        };
        Timer swipeTimer = new Timer();
        swipeTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(Update);
            }
        }, 2500, 2500);
    }
}
