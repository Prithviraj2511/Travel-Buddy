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
import android.webkit.WebView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import static android.content.ContentValues.TAG;

public class Details extends AppCompatActivity {

    SupportMapFragment supportMapFragment;
    FusedLocationProviderClient client;
    double lat=0.0;
    double lng=0.0;
    private TextView location_name,location_desc,location_address;
    private RatingBar ratingBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getSupportActionBar().setTitle("Details");
        getWindow().setStatusBarColor(ContextCompat.getColor(Details.this,R.color.colorAccent));
        setContentView(R.layout.activity_details);

        Intent intent = getIntent();
        String place_id = intent.getStringExtra("place_id");
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        location_name=(TextView) findViewById(R.id.nameTV);
        location_address=(TextView) findViewById(R.id.addressTV);
        location_desc=(TextView) findViewById(R.id.descriptionTV);
        ratingBar=(RatingBar) findViewById(R.id.ratingBar);

        supportMapFragment=(SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.google_map2);
        client= LocationServices.getFusedLocationProviderClient(this);


        if(ActivityCompat.checkSelfPermission(Details.this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED){

        }else {
            ActivityCompat.requestPermissions(Details.this,new String[]{Manifest.permission.ACCESS_FINE_LOCATION},44);
        }

        DocumentReference docRef = db.collection("TouristBuddy").document(place_id);
        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();

                    if (document.exists()) {

                        String[] latlongArr = document.getString("latlong").split(",");
                        lat=Double.parseDouble(latlongArr[0]);
                        lng=Double.parseDouble(latlongArr[1]);
                        location_name.setText(document.getString("name"));
                        location_address.setText(document.getString("address"));
                        location_desc.setText(document.getString("description"));
                        ratingBar.setRating(Float.parseFloat(document.getString("rating")));

                        supportMapFragment.getMapAsync(new OnMapReadyCallback() {
                            @Override
                            public void onMapReady(@NonNull GoogleMap googleMap) {
                                //Initialize lat lng
                                LatLng latLng = new LatLng(lat,lng);
                                //Create marker options
                                MarkerOptions options = new MarkerOptions().position(latLng).title("Place is here");
                                //Zoom map
                                googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng,15));
                                //Add marker on map
                                googleMap.addMarker(options);
                            }
                        });
                    }
                } else {
                    Log.d(TAG, "get failed with ", task.getException());
                }
            }
        });

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 44) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                supportMapFragment.getMapAsync(new OnMapReadyCallback() {
                    @Override
                    public void onMapReady(@NonNull GoogleMap googleMap) {
                        //Initialize lat lng
                        LatLng latLng = new LatLng(lat,lng);
                        //Create marker options
                        MarkerOptions options = new MarkerOptions().position(latLng).title("Place is here");
                        //Zoom map
                        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng,15));
                        //Add marker on map
                        googleMap.addMarker(options);
                    }
                });
            }
        }
    }
}
