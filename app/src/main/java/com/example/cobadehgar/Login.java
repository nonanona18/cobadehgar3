package com.example.cobadehgar;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.EditText;
import android.widget.TextView;
import android.content.Intent;

public class Login extends AppCompatActivity {
    EditText emailuser;
    Button loginButton2;


    @Override
    protected void onCreate(Bundle savedInstancesState) {
        Bundle savedInstanceState = null;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        emailuser=findViewById(R.id.email);
        loginButton2 = findViewById(R.id.loginButton2);
        loginButton2.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent maju = new Intent(Login.this, home_fr.class);
                maju.putExtra("Dataemail", String.valueOf(Boolean.parseBoolean(String.valueOf(Boolean.parseBoolean(emailuser.getText().toString())))));
                startActivity(maju);
            }
        }));
    }}




