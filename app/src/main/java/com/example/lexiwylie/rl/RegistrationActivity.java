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

public class RegistrationActivity extends AppCompatActivity
{
    private static final String TAG = RegistrationActivity.class.getSimpleName();
    private EditText usernameText, firstNameText, lastNameText, emailText, passwordText;
    private Button registerButton;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        mAuth = FirebaseAuth.getInstance();

        // initialize all views through ids defined above
        usernameText = findViewById(R.id.username);
        firstNameText = findViewById(R.id.firstName);
        lastNameText = findViewById(R.id.lastName);
        emailText = findViewById(R.id.emailRegister);
        passwordText = findViewById(R.id.passwordRegister);
        registerButton = findViewById(R.id.registerButton);

        registerButton.setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                registerAccount();
            }
        });
    }

    public void registerAccount()
    {
        String username, firstName, lastName, email, password;
        username = usernameText.getText().toString();
        firstName = firstNameText.getText().toString();
        lastName = lastNameText.getText().toString();
        email = emailText.getText().toString();
        password = passwordText.getText().toString();

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>()
                {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task)
                    {
                        // registration successful
                        if(task.isSuccessful())
                        {
                            Log.d(TAG, "createUserWithEmail: success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            //updateUI(user);
                        }

                        // registration  failed
                        else
                        {
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            //Toast.makeText(EmailPasswordActivity.this, "Authentication Failed.",
                                   //Toast.LENGTH_SHORT).show();
                            //updateUI(null);
                        }
                    }
                });

    }
}
