package com.example.touristguide;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class SignUpActivity extends AppCompatActivity {
    TextView signInNav;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getSupportActionBar().hide();
        getWindow().setStatusBarColor(ContextCompat.getColor(SignUpActivity.this,R.color.colorAccent));
        setContentView(R.layout.activity_sign_up);

        signInNav=(TextView)findViewById(R.id.signInNav);
        signInNav.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                finish();
            }
        });
    }
}