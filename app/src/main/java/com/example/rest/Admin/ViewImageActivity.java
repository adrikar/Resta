package com.example.rest.Admin;


import androidx.annotation.*;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.*;

import android.os.Bundle;
import android.content.Context;


import com.example.rest.Admin.*;
import com.example.rest.Admin.Model.Product;
import com.example.rest.R;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.*;

import java.util.*;

public class ViewImageActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    private RecyclerAdapter recyclerAdapter;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_image);

        recyclerView =(RecyclerView)findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        FirebaseRecyclerOptions<Product> options =
                new FirebaseRecyclerOptions.Builder<Product>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Product"), Product.class)
                        .build();
        recyclerAdapter = new RecyclerAdapter(options);
        recyclerView.setAdapter(recyclerAdapter);


    }

    @Override
    protected void onStart() {
        super.onStart();
        recyclerAdapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        recyclerAdapter.stopListening();
    }
}
