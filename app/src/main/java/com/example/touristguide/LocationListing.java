package com.example.touristguide;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import java.util.ArrayList;
import android.util.Log;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import static android.content.ContentValues.TAG;

public class LocationListing extends AppCompatActivity implements LocationAdapter.OnLocationListener {

    private RecyclerView locationRV;
    private ArrayList<LocationModel> locationModelArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getWindow().setStatusBarColor(ContextCompat.getColor(LocationListing.this,R.color.colorAccent));
        setContentView(R.layout.activity_location_listing);

        Intent intent = getIntent();
        String location_type = intent.getStringExtra("location_type");
        this.getSupportActionBar().setTitle(location_type);
        double userLat=18.99588389385923;
        double userLng=73.12464409663677;

        locationRV = findViewById(R.id.idRVLocation);
        locationModelArrayList = new ArrayList<>();
        LocationAdapter locationAdapter = new LocationAdapter(LocationListing.this, locationModelArrayList,LocationListing.this);

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("TouristBuddy")
                .whereEqualTo("type",location_type)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {

                                String[] latlongArr = document.getString("latlong").split(",");
                                double lat=Double.parseDouble(latlongArr[0]);
                                double lng=Double.parseDouble(latlongArr[1]);
                                if(caldistance(userLat,lat,userLng,lng,0.0,0.0)<3000){
                                    String location_id=document.getId();
                                    String location_name=document.getString("name");
                                    float location_rating=Float.parseFloat(document.get("rating").toString());
                                    String location_desc=document.getString("description");
                                    LocationModel lm=new LocationModel(location_id,location_name,location_rating,location_desc);
                                    locationModelArrayList.add(lm);
                                    locationAdapter.notifyDataSetChanged();
                                }

                            }
                        } else {
                            Log.w(TAG, "Error getting documents.", task.getException());
                        }
                    }
                });


        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(LocationListing.this, LinearLayoutManager.VERTICAL, false);
        locationRV.setLayoutManager(linearLayoutManager);
        locationRV.setAdapter(locationAdapter);
    }


    @Override
    public void onLocationClick(int pos) {
        Toast.makeText(this,"You are at "+locationModelArrayList.get(pos).getLocation_name(),Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, Details.class);
        intent.putExtra("place_id",locationModelArrayList.get(pos).getLocation_id());
        startActivity(intent);
    }


    public static double caldistance(double lat1, double lat2, double lon1,
                                  double lon2, double el1, double el2) {

        final int R = 6371; // Radius of the earth

        double latDistance = Math.toRadians(lat2 - lat1);
        double lonDistance = Math.toRadians(lon2 - lon1);
        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
                * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double distance = R * c * 1000; // convert to meters

        double height = el1 - el2;

        distance = Math.pow(distance, 2) + Math.pow(height, 2);

        return Math.sqrt(distance);
    }

}