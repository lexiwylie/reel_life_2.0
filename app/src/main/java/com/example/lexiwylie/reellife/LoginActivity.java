package com.example.lexiwylie.reellife;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.AuthResult;
import android.view.View.OnClickListener;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

public class LoginActivity extends AppCompatActivity
{

    private EditText emailText, passwordText;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Button loginButton = findViewById(R.id.login);
        Button createAccountButton = findViewById(R.id.register);

        // get instance of FirebaseAuth
        mAuth = FirebaseAuth.getInstance();

        // initialize all views through ids defined above
        emailText = findViewById(R.id.email);
        passwordText = findViewById(R.id.password);

        loginButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                loginUserAccount();
            }
        });

        createAccountButton.setOnClickListener(new View.OnClickListener()
        {
             @Override
            public void onClick(View v)
             {
                 Intent intent = new Intent(LoginActivity.this, RegistrationActivity.class);
                 startActivity(intent);
             }
        });
    }

    private void loginUserAccount()
    {
        String email, password;
        email = emailText.getText().toString();
        password = passwordText.getText().toString();

        // validations for input email and password
        if(TextUtils.isEmpty(email))
        {
            Toast.makeText(getApplicationContext(),
                    "Please enter email",
                    Toast.LENGTH_LONG).show();
            return;
        }

        if(TextUtils.isEmpty(password))
        {
            Toast.makeText(getApplicationContext(),
                    "Please enter password",
                    Toast.LENGTH_LONG).show();
            return;
        }

        // sign in existing user
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(
                        new OnCompleteListener<AuthResult>()
                        {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task)
                            {
                                // if sign-in is successful intent to home activity
                                if(task.isSuccessful())
                                {
                                    Intent intent = new Intent(LoginActivity.this,
                                                                MainActivity.class);
                                    startActivity(intent);
                                }

                                // sign-in failed
                                else
                                {
                                    Toast.makeText(getApplicationContext(),
                                                "Login Failed",
                                                Toast.LENGTH_LONG).show();
                                }
                            }

                        });
    }

}