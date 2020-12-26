package com.example.rest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.rest.Admin.Model.Product;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

public class RestMenu extends AppCompatActivity {
    RecyclerView recyclerView;
    private AdapterMenu adapterMenu;
    Button ver;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rest_menu);
        ver = findViewById(R.id.btnPedido);
        recyclerView =(RecyclerView)findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        FirebaseRecyclerOptions<Product> options =
                new FirebaseRecyclerOptions.Builder<Product>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Product"), Product.class)
                        .build();
        adapterMenu = new AdapterMenu(options);
        recyclerView.setAdapter(adapterMenu);

        ver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), ShowPedido.class));
            }
        });
    }
    @Override
    protected void onStart() {
        super.onStart();
        adapterMenu.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        adapterMenu.stopListening();
    }
}