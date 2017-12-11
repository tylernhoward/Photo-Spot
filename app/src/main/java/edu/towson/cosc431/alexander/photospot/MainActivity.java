package edu.towson.cosc431.alexander.photospot;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.content.AsyncTaskLoader;
import android.support.v4.content.FileProvider;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import edu.towson.cosc431.alexander.photospot.adapters.PhotosAdapter;
import edu.towson.cosc431.alexander.photospot.database.PhotoDataSource;
import edu.towson.cosc431.alexander.photospot.interfaces.ASyncResponse;
import edu.towson.cosc431.alexander.photospot.interfaces.IController;
import edu.towson.cosc431.alexander.photospot.interfaces.IModel;
import edu.towson.cosc431.alexander.photospot.models.LocationModel;
import edu.towson.cosc431.alexander.photospot.models.Photo;
import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.EasyPermissions;

public class MainActivity extends AppCompatActivity
        implements LocationListener, EasyPermissions.PermissionCallbacks, NavigationView.OnNavigationItemSelectedListener, IController, ASyncResponse {

    private static final int RC_LOCATION = 124;

    private ArrayList<Photo> photos;
    private ArrayList<Photo> tempHolder;
    private FloatingActionButton fab;
    private DrawerLayout drawer;
    private Toolbar toolbar;
    private ActionBarDrawerToggle toggle;
    private PhotosAdapter adapter;
    private NavigationView navigationView;
    private RecyclerView recyclerView;
    private Photo photo;
    private Location location;
    private String currentPhotoPath;
    private static IController controller;
    private IModel photoModel = new PhotoModel(PhotoDataSource.getInstance(this));


    protected LocationManager locationManager;
    protected LocationListener locationListener;
    protected boolean gps_enabled,network_enabled;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        askForPermission();
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        photos = new ArrayList<Photo>();
        tempHolder = new ArrayList<Photo>();
        controller = this;
        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Snackbar.make(view, "TODO: Take / Upload Photo", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
               dispatchTakePictureIntent();
            }
        });
         drawer =(DrawerLayout) findViewById(R.id.drawer_layout);
        toggle =new ActionBarDrawerToggle(this,drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        navigationView =(NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        recyclerView =(RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new PhotosAdapter(photos, this);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();


    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        // Forward results to EasyPermissions
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }
    @AfterPermissionGranted(RC_LOCATION)
    private void askForPermission() {
        String[] perms = { Manifest.permission.ACCESS_FINE_LOCATION};
        if (EasyPermissions.hasPermissions(this, perms)) {
            locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);
            location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            if(location != null){
                LocationModel locationModel = new LocationModel().getInstance();
                locationModel.setLatitude(location.getLatitude());
                locationModel.setLongitude(location.getLongitude());
            }

            Log.d("permissions", "Granted");
        } else {
            EasyPermissions.requestPermissions(this, getString(R.string.camera_and_location_rationale), RC_LOCATION, perms);
            Log.d("permissions", "Requested");

        }
    }
    @Override
    public void onPermissionsGranted(int requestCode, List<String> perms) {
        Log.d("permissions", "onPermissionsGranted:" + requestCode + ":" + perms.size());
    }
    @Override
    public void onPermissionsDenied(int requestCode, List<String> perms) {
        Log.d("permissions", "onPermissionsDenied:" + requestCode + ":" + perms.size());
    }
    @Override
    public void onLocationChanged(Location loc) {
        location = loc;
    }
    @Override
    public void onProviderDisabled(String provider) {
        Log.d("Provider","disable");
    }

    @Override
    public void onProviderEnabled(String provider) {
        Log.d("Provider","enable");
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
        Log.d("Status","status");
    }






    @Override
    public void onBackPressed() {
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id) {
            case R.id.nav_camera:
                dispatchTakePictureIntent();
                break;
            case R.id.nav_saved:
                viewSaved();
                break;
            case R.id.nav_gallery:
                viewGallery();
                break;
            case R.id.nav_slideshow:
                dispatchSlideshowIntent();
                break;
            //case R.id.nav_manage:
               // break;
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void displayFullScreenImage(Photo photo) {
        Intent intent = new Intent(getBaseContext(), SinglePhotoActivity.class);
        intent.putExtra(Constants.getPhotoExtraTag(), photo);
        startActivity(intent);
    }

    public void dispatchSlideshowIntent() {
        Intent intent = new Intent(getBaseContext(), SlideshowActivity.class);
        intent.putExtra(Constants.PHOTOARRAY_EXTRA_TAG, photos);
        startActivity(intent);
    }
    public void viewSaved() {
        tempHolder.addAll(photos);
        photos.clear();
        photos.addAll(photoModel.getPhotos());
        refresh();
    }
    public void viewGallery(){
        photos.clear();
        photos.addAll(tempHolder);
        refresh();
    }

    @Override
    public void addPhoto(Photo photo) {
        photos.add(photo);
    }

    @Override
    public void updatePhoto(Photo input) {
        Photo photoToUpdate = new Photo();
        for(Photo p : photos) {
            if(p.getId().equals(input.getId())){
                photoToUpdate = p;
            }
        }
        photos.set(0,photoToUpdate);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == Constants.getRequestImageCapture() && resultCode == RESULT_OK) {
            Intent intent = new Intent(getBaseContext(), NewPhotoActivity.class);
            intent.putExtra(Constants.getCapturedPhotoTag(), currentPhotoPath);
            startActivityForResult(intent, Constants.getRequestNewPhoto());
        }
    }

    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // Ensure that there's a camera activity to handle the intent
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            // Create the File where the photo should go
            File photoFile = null;
            try {
                photoFile = createImageFile();
            } catch (IOException ex) {
                // Error occurred while creating the File
            }
            // Continue only if the File was successfully created
            if (photoFile != null) {
                Uri photoURI = FileProvider.getUriForFile(this,
                        "com.example.android.fileprovider",
                        photoFile);
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                startActivityForResult(takePictureIntent, Constants.getRequestImageCapture());
            }
        }
    }

    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );

        // Save a file: path for use with ACTION_VIEW intents
        currentPhotoPath = image.getAbsolutePath();
        return image;
    }

    public static IController getController() {
        return controller;
    }

    public void refresh() {

        adapter.notifyDataSetChanged();
    }

    @Override
    public void retrieveList(ArrayList<Photo> list) {
        photos.clear();
        for(int i =0; i<list.size(); i++)
        {
            photos.add(list.get(i));
        }
    }
}
