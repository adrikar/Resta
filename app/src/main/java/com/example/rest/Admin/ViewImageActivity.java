package com.example.rest.Admin;


import androidx.annotation.*;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.*;

import android.os.Bundle;
import android.content.Context;


import com.example.rest.Admin.*;
import com.example.rest.R;
import com.google.firebase.database.*;

import java.util.*;

public class ViewImageActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    private DatabaseReference myRef;
    private ArrayList<Messages> messagesList;
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
        messagesList= new ArrayList<>();


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

                    Messages messages = new Messages();
                    messages.setImageUrl(snapshot.child("image").getValue().toString());
                    messages.setName(snapshot.child("name").getValue().toString());

                    messagesList.add(messages);

                }
                recyclerAdapter= new RecyclerAdapter(getApplicationContext(), messagesList);
                recyclerView.setAdapter(recyclerAdapter);
                recyclerAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });




    }

    private  void ClearAll(){
        if(messagesList!= null){
            messagesList.clear();

            if(recyclerAdapter!=null){
                recyclerAdapter.notifyDataSetChanged();


            }
        }
        messagesList= new ArrayList<>();


    }


}
