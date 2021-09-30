package com.example.touristguide;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    Button signInBtn,signUpBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getSupportActionBar().hide();
        getWindow().setStatusBarColor(ContextCompat.getColor(MainActivity.this,R.color.colorAccent));
        setContentView(R.layout.activity_main);
        signInBtn=(Button) findViewById(R.id.signInBtn);
        signUpBtn=(Button)findViewById(R.id.signUpBtn);
        signInBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                onSignIn();
            }
        });
        signUpBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                onSignUp();
            }
        });
    }
    private void onSignUp(){
        Intent intent = new Intent(this, SignUpActivity.class);
        startActivity(intent);
    }
    private void onSignIn(){
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
    }
}