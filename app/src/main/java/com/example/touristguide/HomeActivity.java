package com.example.touristguide;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class HomeActivity extends AppCompatActivity {
    private Button getLocationBtn;

    private ImageView topPicksImg,shoppingImg,placesImg,foodImg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getSupportActionBar().hide();
        getWindow().setStatusBarColor(ContextCompat.getColor(HomeActivity.this,R.color.colorAccent));
        setContentView(R.layout.activity_home);
        topPicksImg = (ImageView) findViewById(R.id.topPicksImg);
        shoppingImg = (ImageView) findViewById(R.id.shoppingImg);
        placesImg = (ImageView) findViewById(R.id.placesImg);
        foodImg = (ImageView) findViewById(R.id.foodImg);

        topPicksImg.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                v.animate().rotationY(v.getRotationY()+360).setDuration(300).withEndAction(new Runnable() {
                    @Override
                    public void run() {
                        v.animate().alpha(1);
                        getTopPicks();
                    }
                });
            }

        });
        getLocationBtn = (Button) findViewById(R.id.getLocationBtn);
        getLocationBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getMap();
            }


        });

        shoppingImg.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                v.animate().rotationY(v.getRotationY()+360).setDuration(300).withEndAction(new Runnable() {
                    @Override
                    public void run() {
                        v.animate().alpha(1);
                        getShopping();
                    }
                });
            }
        });

        placesImg.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                v.animate().rotationY(v.getRotationY()+360).setDuration(300).withEndAction(new Runnable() {
                    @Override
                    public void run() {
                        v.animate().alpha(1);
                        getPlaces();
                    }
                });
            }
        });

        foodImg.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                v.animate().rotationY(v.getRotationY()+360).setDuration(300).withEndAction(new Runnable() {
                    @Override
                    public void run() {
                        v.animate().alpha(1);
                        getFood();
                    }
                });
            }
        });

    }



    private void getTopPicks(){
        Intent intent = new Intent(this, LocationListing.class);
        startActivity(intent);
    }
    private void getMap() {
        Intent intent = new Intent(this, LocationMap.class);
        startActivity(intent);
    }
    private void getShopping(){
        Intent intent = new Intent(this, LocationListing.class);
        startActivity(intent);
    }
    private void getPlaces(){
        Intent intent = new Intent(this, LocationListing.class);
        startActivity(intent);
    }
    private void getFood(){
        Intent intent = new Intent(this, LocationListing.class);
        startActivity(intent);
    }
}

