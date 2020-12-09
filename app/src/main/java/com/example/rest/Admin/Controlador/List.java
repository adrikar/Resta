package com.example.rest.Admin.Controlador;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.rest.Admin.modelA.Product;
import com.example.rest.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.

 * create an instance of this fragment.
 */
public class List extends Fragment {
    private DatabaseReference databaseReference;
    private String state;
    private ArrayList<Product> products;
    private TextView textState;
    private ProgressBar progressBar;
    private boolean insert;
    private boolean existFood;

    public List() {

    }
    public List( DatabaseReference databaseReference, String state) {
        super();
        this.databaseReference = databaseReference;
        this.state = state;
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View root = inflater.inflate(R.layout.fragment_list, container, false);

        return root;
    }
}