package com.example.rest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.rest.Admin.Model.Product;
import com.example.rest.Admin.RecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

public class RestMenu extends AppCompatActivity {
    RecyclerView recyclerView;
    private AdapterMenu adapterMenu;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rest_menu);
        recyclerView =(RecyclerView)findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        FirebaseRecyclerOptions<Product> options =
                new FirebaseRecyclerOptions.Builder<Product>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Product"), Product.class)
                        .build();
        adapterMenu = new AdapterMenu(options);
        recyclerView.setAdapter(adapterMenu);
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