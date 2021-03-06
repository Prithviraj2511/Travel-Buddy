package com.example.touristguide;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

import static android.content.ContentValues.TAG;

public class LocationMap extends AppCompatActivity {

    //Initialize variable
    SupportMapFragment supportMapFragment;
    FusedLocationProviderClient client;
    double lat=0.0;
    double lng=0.0;
    Button pickLocationBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getSupportActionBar().setTitle("Mark Location");
        this.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getWindow().setStatusBarColor(ContextCompat.getColor(LocationMap.this,R.color.colorAccent));
        setContentView(R.layout.activity_currentloc);


        //Assign variable
        supportMapFragment=(SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.google_map);
        pickLocationBtn=(Button) findViewById(R.id.pickLocationBtn);

        //Initialize fused location
        client= LocationServices.getFusedLocationProviderClient(this);


        if(ActivityCompat.checkSelfPermission(LocationMap.this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED){
            //When Permission granted
            //Call method
            getCurrentLocation();

        }else {
            //When permission denied
            //Request permission
            ActivityCompat.requestPermissions(LocationMap.this,new String[]{Manifest.permission.ACCESS_FINE_LOCATION},44);
        }

        pickLocationBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LocationMap.this, HomeActivity.class);
                intent.putExtra("location_lat", lat);
                intent.putExtra("location_lng",lng);
                startActivity(intent);
            }
        });

    }

    private void getCurrentLocation() {
        //Initialize task location
        Task<Location> task = client.getLastLocation();
        task.addOnSuccessListener(new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                //When success
                if(location != null){
                    //Sync map
                    supportMapFragment.getMapAsync(new OnMapReadyCallback() {
                        @Override
                        public void onMapReady(@NonNull GoogleMap googleMap) {
                            googleMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
                                @Override
                                public void onMapClick(@NonNull LatLng latLng) {
//                                    android.util.Log.i("onMapClick", "Horray!");
                                    lat=latLng.latitude;
                                    lng=latLng.longitude;
                                    googleMap.clear();
                                    MarkerOptions options = new MarkerOptions().position(latLng).title("I am here");
                                    //Zoom map
                                    googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng,15));
                                    //Add marker on map
                                    googleMap.addMarker(options);
                                }
                            });
                            //Initialize lat lng
                            LatLng latLng = new LatLng(location.getLatitude(),location.getLongitude());
                            lat=latLng.latitude;
                            lng=latLng.longitude;
                            //Create marker options
                            MarkerOptions options = new MarkerOptions().position(latLng).title("I am here");
                            //Zoom map
                            googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng,15));
                            //Add marker on map
                            googleMap.addMarker(options);
                        }
                    });
                }
            }
        });

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 44) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                //When permission granted
                //Call method
                getCurrentLocation();
            }
        }
    }
}


