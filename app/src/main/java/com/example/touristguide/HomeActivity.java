package com.example.touristguide;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ImageView topPicksImg = (ImageView) findViewById(R.id.topPicksImg);
        topPicksImg.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // your code here
                System.out.println("Clicked");
                topPicksImg.animate().rotationY(topPicksImg.getRotationY()+360).setDuration(300).withEndAction(new Runnable() {
                    @Override
                    public void run() {
                        topPicksImg.animate().alpha(1);
                        getTopPicks();
                    }
                });
            }
        });
    }

    private void getTopPicks(){
        Intent intent = new Intent(this, LocationListing.class);
        startActivity(intent);
    }
}