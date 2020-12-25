package com.example.rest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Document;

public class InfoUser extends AppCompatActivity {
    TextView nom, corr, tel;
    FirebaseAuth auth;
    FirebaseDatabase data;
    String userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_user);
        nom = findViewById(R.id.nombre);
        corr = findViewById(R.id.correo);
        tel = findViewById(R.id.telef);

        auth = FirebaseAuth.getInstance();
        data = FirebaseDatabase.getInstance();

        userId = auth.getCurrentUser().getUid();

        DatabaseReference db = data.getReference("User").child(userId);
        db.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
               nom.setText(snapshot.child("name").getValue(String.class));
               corr.setText(snapshot.child("email").getValue(String.class));
               tel.setText(snapshot.child("phone").getValue(String.class));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getApplicationContext(),"Error!",Toast.LENGTH_SHORT).show();
            }
        });
    }
}