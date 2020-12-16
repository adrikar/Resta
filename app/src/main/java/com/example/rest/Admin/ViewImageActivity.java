package com.example.rest.Admin;


import androidx.annotation.*;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.*;

import android.os.Bundle;
import android.content.Context;


import com.example.rest.Admin.*;
import com.example.rest.Admin.Model.Product;
import com.example.rest.R;
import com.google.firebase.database.*;

import java.util.*;

public class ViewImageActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    private DatabaseReference myRef;
    private ArrayList<Product> productList;
    private RecyclerAdapter recyclerAdapter;
    private Context mContext;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_image);

        recyclerView =findViewById(R.id.recyclerView);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);

        myRef= FirebaseDatabase.getInstance().getReference();
        productList= new ArrayList<>();


        ClearAll();
        GetDataFromFirebase();


    }

    private void GetDataFromFirebase() {
        Query query = myRef.child("Product");
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot datasnapshot) {
                ClearAll();
                for (DataSnapshot snapshot: datasnapshot.getChildren()){

                    Product pro = new Product();
                    pro.setImage(snapshot.child("image").getValue().toString());
                    pro.setName(snapshot.child("name").getValue().toString());
                    pro.setDascri(snapshot.child("dascri").getValue().toString());


                   productList.add(pro);

                }
                recyclerAdapter= new RecyclerAdapter(getApplicationContext(), productList);
                recyclerView.setAdapter(recyclerAdapter);
                recyclerAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });




    }

    private  void ClearAll(){
        if(productList!= null){
            productList.clear();

            if(recyclerAdapter!=null){
                recyclerAdapter.notifyDataSetChanged();


            }
        }
        productList= new ArrayList<>();


    }


}
