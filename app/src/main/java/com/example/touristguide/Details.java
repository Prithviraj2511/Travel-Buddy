package com.example.touristguide;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.os.Bundle;

public class Details extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getSupportActionBar().setTitle("Details");
        this.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getWindow().setStatusBarColor(ContextCompat.getColor(Details.this,R.color.colorAccent));
        setContentView(R.layout.activity_details);
    }
}
// formatted_address
//formatted_phone_number
//icon
//name
//photos
//rating
//reviews
//geometry