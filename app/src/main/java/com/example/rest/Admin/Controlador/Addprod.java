package com.example.rest.Admin.Controlador;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.rest.Admin.modelA.Product;
import com.example.rest.R;
import com.example.rest.Register;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.UUID;


public class Addprod extends Fragment {
    EditText aName, aDes, aPrice, aCant;
    Button aResgiterpBtn;
    ProgressBar aProgressBar;
    ImageView imageView;
    Uri imageuri = null;
    Context context;
    StorageReference reference;

    private static final int GALLERY_REQUEST=1;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    public Addprod() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View root = inflater.inflate(R.layout.fragment_addprod, container, false);
        aName = root.findViewById(R.id.fullNamep);
        aDes = root.findViewById(R.id.des);
        aPrice = root.findViewById(R.id.price);
        aCant = root.findViewById(R.id.cant);
        aResgiterpBtn = root.findViewById(R.id.registerpBtn);
        imageView = root.findViewById(R.id.imageView3);
        aProgressBar = root.findViewById(R.id.progressBar);

        inicializarFirebase();
        reference = FirebaseStorage.getInstance().getReference();
        openGallery();

        aResgiterpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String name = aName.getText().toString().trim();
                final String des = aDes.getText().toString().trim();
                final String price = aPrice.getText().toString();
                final String cant = aCant.getText().toString().trim();


                if (TextUtils.isEmpty(name)) {
                    aName.setError("Name product is Required.");
                    return;
                }
                if (TextUtils.isEmpty(des)) {
                    aDes.setError("Description product is Required.");
                    return;
                }
                if (TextUtils.isEmpty(price)) {
                    aPrice.setError("Price is Required.");
                    return;
                }
                if (TextUtils.isEmpty(cant)) {
                    aCant.setError("Quantity is Required.");
                    return;
                }
                else if(!TextUtils.isEmpty(name)&&!TextUtils.isEmpty(price)&&imageuri!=null){

                    StorageReference filepath = reference.child("producto_image").child(imageuri.getLastPathSegment());
                    filepath.putFile(imageuri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            filepath.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                @Override
                                public void onSuccess(Uri uri) {
                                    Uri downloaduri = uri;
                                    final String img = downloaduri.toString();


                                    Product pro = new Product();
                                    pro.setProductId(UUID.randomUUID().toString());
                                    pro.setName(name);
                                    pro.setDescri(des);
                                    pro.setPrice(price);
                                    pro.setCant(cant);
                                    pro.setImag(img);

                                    databaseReference.child("Product").child(pro.getProductId()).setValue(pro)
                                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                @Override
                                                public void onComplete(@NonNull Task<Void> task) {
                                                    Toast.makeText(context,"Success",Toast.LENGTH_SHORT).show();
                                                }
                                            }).addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception e) {
                                            Toast.makeText(context, "Error ! " + e.getMessage(), Toast.LENGTH_SHORT).show();
                                            aProgressBar.setVisibility(View.GONE);
                                        }
                                    });

                                }
                            });
                        }
                    });
                }

                aProgressBar.setVisibility(View.VISIBLE);

            }
        });

        return root;
    }

    private void inicializarFirebase() {

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference= firebaseDatabase.getReference();
    }
    private void openGallery(){
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                startActivityForResult(intent,GALLERY_REQUEST);
            }
        });
    }
}