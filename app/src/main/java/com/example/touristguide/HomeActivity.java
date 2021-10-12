package com.example.touristguide;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class HomeActivity extends AppCompatActivity {
    private Button getLocationBtn,logoutBtn;
    private ImageView topPicksImg,shoppingImg,placesImg,foodImg;
    Double location_lat=0.0;
    Double location_lng=0.0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getSupportActionBar().hide();
        getWindow().setStatusBarColor(ContextCompat.getColor(HomeActivity.this,R.color.colorAccent));
        setContentView(R.layout.activity_home);

        Intent intent = getIntent();
        location_lat = intent.getDoubleExtra("location_lat",0.0);
        location_lng=intent.getDoubleExtra("location_lng",0.0);


        logoutBtn=(Button) findViewById(R.id.logoutBtn);
        topPicksImg = (ImageView) findViewById(R.id.topPicksImg);
        shoppingImg = (ImageView) findViewById(R.id.shoppingImg);
        placesImg = (ImageView) findViewById(R.id.natureImg);
        foodImg = (ImageView) findViewById(R.id.foodImg);

        topPicksImg.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                v.animate().rotationY(v.getRotationY()+360).setDuration(300).withEndAction(new Runnable() {
                    @Override
                    public void run() {
                        v.animate().alpha(1);
                        if(location_lat==0.0 && location_lng==0.0){
                            Toast.makeText(HomeActivity.this,"Select location first",Toast.LENGTH_SHORT).show();
                        }
                        else {
                            getTopPicks();
                        }

                    }
                });
            }

        });
        getLocationBtn = (Button) findViewById(R.id.getLocationBtn);

        logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(HomeActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
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
                        if(location_lat==0.0 && location_lng==0.0){
                            Toast.makeText(HomeActivity.this,"Select location first",Toast.LENGTH_SHORT).show();
                        }
                        else {
                            getShopping();
                        }
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
                        if(location_lat==0.0 && location_lng==0.0){
                            Toast.makeText(HomeActivity.this,"Select location first",Toast.LENGTH_SHORT).show();
                        }
                        else {
                            getPlace();
                        }

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
                        if(location_lat==0.0 && location_lng==0.0){
                            Toast.makeText(HomeActivity.this,"Select location first",Toast.LENGTH_SHORT).show();
                        }
                        else {
                            getFood();
                        }

                    }
                });
            }
        });

    }



    private void getTopPicks(){
        Intent intent = new Intent(this, LocationListing.class);
        intent.putExtra("location_type", "top");
        intent.putExtra("location_latitude", location_lat);
        intent.putExtra("location_longitude",location_lng);
        startActivity(intent);
    }
    private void getMap() {
        Intent intent = new Intent(this, LocationMap.class);
        startActivity(intent);
    }
    private void getShopping(){
        Intent intent = new Intent(this, LocationListing.class);
        intent.putExtra("location_type", "shopping");
        intent.putExtra("location_latitude", location_lat);
        intent.putExtra("location_longitude",location_lng);
        startActivity(intent);
    }
    private void getPlace(){
        Intent intent = new Intent(this, LocationListing.class);
        intent.putExtra("location_latitude", location_lat);
        intent.putExtra("location_longitude",location_lng);
        intent.putExtra("location_type", "natural place");
        startActivity(intent);
    }
    private void getFood(){
        Intent intent = new Intent(this, LocationListing.class);
        intent.putExtra("location_latitude", location_lat);
        intent.putExtra("location_longitude",location_lng);
        intent.putExtra("location_type", "food");
        startActivity(intent);
    }
}

