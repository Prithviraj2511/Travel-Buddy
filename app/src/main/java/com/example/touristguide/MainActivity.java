package com.example.touristguide;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {
    Button signInBtn,signUpBtn;
    EditText emailInput,passwordInput;

    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getSupportActionBar().hide();
        getWindow().setStatusBarColor(ContextCompat.getColor(MainActivity.this,R.color.colorAccent));
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();

        signInBtn=(Button) findViewById(R.id.signInBtn);
        signUpBtn=(Button)findViewById(R.id.signUpBtn);
        emailInput=(EditText) findViewById(R.id.signInEmail);
        passwordInput=(EditText) findViewById(R.id.signInPassword);

        signInBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v)
            {
                if(validateEmail(emailInput.getText()) && validatePassword(passwordInput.getText())){
                    String email=emailInput.getText().toString();
                    String password=passwordInput.getText().toString();
                    onSignIn(email,password);
                }
            }
        });
        signUpBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                onSignUp();
            }
        });
    }

    public boolean validateEmail(CharSequence target){
        if(TextUtils.isEmpty(target)){
            emailInput.setError("email is required");
            return false;
        }
        else if(!Patterns.EMAIL_ADDRESS.matcher(target).matches()){
            emailInput.setError("enter valid email");
            return false;
        }
        else{
            return true;
        }
    }

    public boolean validatePassword(CharSequence target){
        if(TextUtils.isEmpty(target)){
            passwordInput.setError("password is required");
            return false;
        }
        else if(!isValidPassword(target.toString())){
            passwordInput.setError("enter valid password");
            return false;
        }
        else{
            return true;
        }
    }

    public static boolean isValidPassword(final String password) {

        Pattern pattern;
        Matcher matcher;
        final String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{4,}$";
        pattern = Pattern.compile(PASSWORD_PATTERN);
        matcher = pattern.matcher(password);

        return matcher.matches();

    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser!=null){
            signInSuccessfull();
        }

    }

    private void onSignUp(){
        Intent intent = new Intent(this, SignUpActivity.class);
        startActivity(intent);
    }
    private void onSignIn(String email,String password){
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            FirebaseUser user = mAuth.getCurrentUser();
                            if(user!=null){
                                signInSuccessfull();
                                return;
                            }
                            Toast.makeText(MainActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(MainActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });

    }

    private  void signInSuccessfull(){
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
    }
}