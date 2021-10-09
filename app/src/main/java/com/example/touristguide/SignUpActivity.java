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
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SignUpActivity extends AppCompatActivity {
    TextView signInNav;
    EditText nameInput,emailInput,passwordInput,confirmPasswordInput;
    Button signupButton;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getSupportActionBar().hide();
        getWindow().setStatusBarColor(ContextCompat.getColor(SignUpActivity.this,R.color.colorAccent));
        setContentView(R.layout.activity_sign_up);

        mAuth = FirebaseAuth.getInstance();

        signupButton=(Button)findViewById(R.id.signupButton);
        nameInput=(EditText)findViewById(R.id.signUpName);
        emailInput=(EditText)findViewById(R.id.signUpEmail);
        passwordInput=(EditText)findViewById(R.id.signUpPassword);
        confirmPasswordInput=(EditText)findViewById(R.id.signUpCPassword);

        signupButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(validateName(nameInput.getText()) && validateEmail(emailInput.getText()) && validatePassword(passwordInput.getText())){
                    if(confirmPasswordInput.getText().toString().equals(passwordInput.getText().toString())){
                        String email=emailInput.getText().toString();
                        String password=passwordInput.getText().toString();
                        onSignUP(email,password);
                    }
                    else{
                        confirmPasswordInput.setError("Password does not match");
                    }
                }
            }
        });

        signInNav=(TextView)findViewById(R.id.signInNav);
        signInNav.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                finish();
            }
        });
    }

    public boolean validateName(CharSequence target){
        if(TextUtils.isEmpty(target)){
            nameInput.setError("name is required");
            return false;
        }
        else if(!(target.toString().length()>=4)){
            nameInput.setError("atleast 4 characters required");
            return false;
        }
        else{
            return true;
        }
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

    private void onSignUP(String email,String password){
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            FirebaseUser user = mAuth.getCurrentUser();
                            if(user!=null){
                                Toast.makeText(SignUpActivity.this, "Authentication successful.",
                                        Toast.LENGTH_SHORT).show();
                                signUpSuccessfull();
                            }
                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(SignUpActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();

                        }
                    }
                });
    }

    private  void signUpSuccessfull(){
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
    }
}