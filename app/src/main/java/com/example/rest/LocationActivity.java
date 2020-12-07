package com.example.rest;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.*;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rest.Admin.LoginAdmin;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import androidx.appcompat.app.*;

public class LocationActivity extends AppCompatActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ubicacion);
    }

    public void goToMap (View view) {
        goToUrl ( "https://www.google.com/maps/place/Universidad+Mayor+de+San+Simon/@-17.3988536,-66.179853,14z/data=!4m19!1m13!4m12!1m4!2m2!1d-66.177221!2d-17.404416!4e1!1m6!1m2!1s0x93e373fef347572b:0xc642d3fd93a6f7d2!2suniversidad+mayor+de+san+simon+cochabamba!2m2!1d-66.1463787!2d-17.3936779!3m4!1s0x93e373fef347572b:0xc642d3fd93a6f7d2!8m2!3d-17.3936779!4d-66.1463787");
    }

    private void goToUrl (String url) {
        Uri uriUrl = Uri.parse(url);
        Intent launchBrowser = new Intent(Intent.ACTION_VIEW, uriUrl);
        startActivity(launchBrowser);
    }




}
