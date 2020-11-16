package com.example.lexiwylie.rl;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Button;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity
{
    private static final String TAG = LoginActivity.class.getSimpleName();
    private EditText emailText, passwordText;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Button getStartedButton = findViewById(R.id.getStartedButton);
        TextView createAccountButton = findViewById(R.id.createAccountButton);

        mAuth = FirebaseAuth.getInstance();

        getStartedButton.setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                //login();
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });


        createAccountButton.setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                createAccount();
            }
        });

    }

    @Override
    public void onStart()
    {
        super.onStart();

        // check if user is signed in and update UI accordingly
        FirebaseUser currentUser = mAuth.getCurrentUser();
        //updateUI(currentUser);
    }

    public void login()
    {
        String email, password;
        email = emailText.getText().toString();
        password = passwordText.getText().toString();

        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(
                        this, new OnCompleteListener<AuthResult>()
                        {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task)
                            {
                                // login successful
                                if(task.isSuccessful())
                                {
                                    Log.d(TAG, "signInWithEmail:success");
                                    FirebaseUser user = mAuth.getCurrentUser();
                                    //updateUI(user);
                                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                    startActivity(intent);
                                }

                                // login failed
                                else
                                {
                                    Log.w(TAG, "signInWithEmail:failure", task.getException());
                                    Toast.makeText(getApplicationContext(),
                                                    "Login Failed",
                                                    Toast.LENGTH_LONG).show();
                                }
                            }
                        });


    }

    public void createAccount()
    {
        Intent intent = new Intent(this, RegistrationActivity.class);
        startActivity(intent);
    }

    /*
    // change UI according to user data
    public void updateUI(FirebaseUser account)
    {

        if(account != null)
        {
            Toast.makeText(this,"U Signed In successfully",Toast.LENGTH_LONG).show();
            startActivity(new Intent(this,AnotherActivity.class));

        }

        else
        {
            Toast.makeText(this,"U Didnt signed in",Toast.LENGTH_LONG).show();
        }

    }**/
}
