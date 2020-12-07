package com.example.rest.Admin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rest.Login;
import com.example.rest.R;

public class LoginAdmin extends AppCompatActivity {
    EditText aEmail,aPassword;
    Button aLoginBtn;
    ProgressBar progressBar;
    TextView acreateText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_admin);


        aEmail = findViewById(R.id.des);
        aPassword = findViewById(R.id.price);
        aLoginBtn = findViewById(R.id.loginBtn);
        progressBar = findViewById(R.id.progressBar);
        acreateText = findViewById(R.id.createText);

        aLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = aEmail.getText().toString().trim();
                String password = aPassword.getText().toString().trim();

                if(TextUtils.isEmpty(email)){
                    aEmail.setError("Email is Required.");
                    return;
                }

                if(TextUtils.isEmpty(password)){
                    aPassword.setError("Password is Required.");
                    return;
                }

                if(password.length() < 6){
                    aPassword.setError("Password Must be >= 6 Characters");
                    return;
                }

                progressBar.setVisibility(View.VISIBLE);
                if(email.equals("admin") || password.equals("password")) {
                    Toast.makeText(LoginAdmin.this, "Logged in Successfully", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getApplicationContext(), MenuAdmin.class));

                }else {
                    Toast.makeText(LoginAdmin.this, "Error" , Toast.LENGTH_SHORT).show();
                }
            }
        });acreateText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), Login.class));
            }
        });
    }
}