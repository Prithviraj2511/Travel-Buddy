package com.example.touristguide;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class HomeActivity extends AppCompatActivity {
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        ImageView topPicksImg = (ImageView) findViewById(R.id.topPicksImg);
        topPicksImg.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // your code here
                System.out.println("Clicked");
                topPicksImg.animate().rotationY(topPicksImg.getRotationY() + 360).setDuration(300).withEndAction(new Runnable() {
                    @Override
                    public void run() {
                        topPicksImg.animate().alpha(1);
                        getTopPicks();
                    }
                });
            }

        });
        button = (Button) findViewById(R.id.button2);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivity2();
            }


        });
    }



    private void getTopPicks(){
        Intent intent = new Intent(this, LocationListing.class);
        startActivity(intent);
    }
    private void openActivity2() {
        Intent intent = new Intent(this,currentloc.class);
        startActivity(intent);
    }
}

