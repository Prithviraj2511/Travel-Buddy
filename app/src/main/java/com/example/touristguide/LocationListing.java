package com.example.touristguide;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import java.util.ArrayList;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class LocationListing extends AppCompatActivity implements LocationAdapter.OnLocationListener {

    private RecyclerView locationRV;

    // Arraylist for storing data
    private ArrayList<LocationModel> locationModelArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getSupportActionBar().setTitle("Places");
        this.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getWindow().setStatusBarColor(ContextCompat.getColor(LocationListing.this,R.color.colorAccent));
        setContentView(R.layout.activity_location_listing);
        locationRV = findViewById(R.id.idRVLocation);

        // here we have created new array list and added data to it.
        // https://developers.google.com/maps/documentation/places/web-service/search-nearby
        locationModelArrayList = new ArrayList<>();
        locationModelArrayList.add(new LocationModel("PVR Cinema", 4.5f,"48 Pirrama Rd, Pyrmont NSW 2009, Australia"));
        locationModelArrayList.add(new LocationModel("PVR Cinema", 3.5f,"48 Pirrama Rd, Pyrmont NSW 2009, Australia"));
        locationModelArrayList.add(new LocationModel("PVR Cinema", 4.5f,"48 Pirrama Rd, Pyrmont NSW 2009, Australia"));
        locationModelArrayList.add(new LocationModel("PVR Cinema", 5.0f,"48 Pirrama Rd, Pyrmont NSW 2009, Australia"));
        locationModelArrayList.add(new LocationModel("PVR Cinema", 4.9f,"48 Pirrama Rd, Pyrmont NSW 2009, Australia"));
        locationModelArrayList.add(new LocationModel("PVR Cinema", 4.5f,"48 Pirrama Rd, Pyrmont NSW 2009, Australia"));
        locationModelArrayList.add(new LocationModel("PVR Cinema", 4.5f,"48 Pirrama Rd, Pyrmont NSW 2009, Australia"));
        locationModelArrayList.add(new LocationModel("PVR Cinema", 4.5f,"48 Pirrama Rd, Pyrmont NSW 2009, Australia"));

        // we are initializing our adapter class and passing our arraylist to it.
        LocationAdapter locationAdapter = new LocationAdapter(this, locationModelArrayList,this);

        // below line is for setting a layout manager for our recycler view.
        // here we are creating vertical list so we will provide orientation as vertical
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

        // in below two lines we are setting layoutmanager and adapter to our recycler view.
        locationRV.setLayoutManager(linearLayoutManager);
        locationRV.setAdapter(locationAdapter);
    }

    @Override
    public void onLocationClick(int pos) {

        Toast.makeText(this,"You are at "+locationModelArrayList.get(pos).getLocation_name(),Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, Details.class);
        startActivity(intent);
    }
}

//tourist_attraction
//cafe
//movie_theater
//park
//restaurant
//shopping_mall