package com.example.rest.Admin.Controlador;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.rest.Admin.modelA.Product;
import com.example.rest.R;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.CDATASection;

import java.util.ArrayList;
import java.util.Objects;

/**
 * A simple {@link Fragment} subclass.

 * create an instance of this fragment.
 */
public class List extends Fragment {

    private ArrayList<Product> listPro = new ArrayList<Product>();
    ArrayAdapter<Product>arrayAdapterpro;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    ListView list;



    public List() {

    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View root = inflater.inflate(R.layout.fragment_list, container, false);
        list = root.findViewById(R.id.list_products);
        inicializarFirebase();
        listarDatos();
        return root;
    }

    private void listarDatos() {
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                listPro.clear();
                for(DataSnapshot objSnapshot : dataSnapshot.getChildren()){
                    Product p = objSnapshot.getValue(Product.class);
                    listPro.add(p);

                    arrayAdapterpro = new ArrayAdapter<Product>(getContext(), android.R.layout.simple_list_item_1, listPro);
                    list.setAdapter(arrayAdapterpro);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void inicializarFirebase() {
        FirebaseApp.initializeApp(getContext());
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
    }
}