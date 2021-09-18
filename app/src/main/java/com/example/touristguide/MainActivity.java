package com.example.touristguide;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    Button signInBtn,signUpBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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