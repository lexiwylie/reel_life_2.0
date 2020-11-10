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
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

public class RegistrationActivity extends AppCompatActivity
{

    private EditText emailText, passwordText;
    private Button registerButton;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        // get FirebaseAuth instance
        mAuth = FirebaseAuth.getInstance();

        // initialize all views through ids defined above
        emailText = findViewById(R.id.email);
        passwordText = findViewById(R.id.password);
        registerButton = findViewById(R.id.register);

        registerButton.setOnClickListener(new View.OnClickListener()
        {
                @Override
                public void onClick(View v)
                {
                    registerNewUser();
                }
        });
    }

    private void registerNewUser()
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

        // register new user
        mAuth
                .createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>()
                {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task)
                    {
                        // registration successful
                        if(task.isSuccessful())
                        {
                            Toast.makeText(getApplicationContext(),
                                        "Registration Successful",
                                         Toast.LENGTH_LONG).show();

                            Intent intent = new Intent(RegistrationActivity.this,
                                                        MainActivity.class);

                            startActivity(intent);
                        }

                        // registration failed
                        else
                        {
                            Toast.makeText(
                                    getApplicationContext(),
                                    "Registration Failed",
                                    Toast.LENGTH_LONG).show();
                        }
                    }
                });


    }
}
