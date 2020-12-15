package com.example.rest.Admin;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.rest.Admin.Model.Product;
import com.example.rest.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

import java.io.InputStream;
import java.util.Random;

public class AddProd extends AppCompatActivity {
    EditText name, descri, precio, cant, idprod;
    Uri filepath;
    ImageView img;
    Button btnimg, btnadd;
    Bitmap bitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_prod);

        img =(ImageView)findViewById(R.id.img);
        btnadd =(Button)findViewById(R.id.btnadd);
        btnimg =(Button)findViewById(R.id.btnImg);

            btnimg.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Dexter.withActivity(AddProd.this)
                            .withPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
                            .withListener(new PermissionListener() {
                                @Override
                                public void onPermissionGranted(PermissionGrantedResponse permissionGrantedResponse) {
                                    Intent intent = new Intent(Intent.ACTION_PICK);
                                    intent.setType("image/*");
                                    startActivityForResult(Intent.createChooser(intent,"Select image file"), 1);
                                }

                                @Override
                                public void onPermissionDenied(PermissionDeniedResponse permissionDeniedResponse) {

                                }

                                @Override
                                public void onPermissionRationaleShouldBeShown(PermissionRequest permissionRequest, PermissionToken permissionToken) {
                                    permissionToken.continuePermissionRequest();
                                }
                            }).check();
                }
            });

            btnadd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    uploadFirebase();
                }
            });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode== 1 && resultCode == RESULT_OK)
        {
            filepath= data.getData();
            try {
                InputStream inputStream = getContentResolver().openInputStream(filepath);
                bitmap = BitmapFactory.decodeStream(inputStream);
                img.setImageBitmap(bitmap);

            }catch (Exception e){

            }
        }

        super.onActivityResult(requestCode, resultCode, data);
    }

    private void uploadFirebase(){

        ProgressDialog dialog = new ProgressDialog(this);
        dialog.setTitle("file uploader");
        dialog.show();

        idprod = (EditText)findViewById(R.id.idprod);
        name = (EditText)findViewById(R.id.nameP);
        descri = (EditText)findViewById(R.id.descrip);
        cant = (EditText)findViewById(R.id.cant);
        precio = (EditText)findViewById(R.id.precio);

        FirebaseDatabase db = FirebaseDatabase.getInstance();
        DatabaseReference root = db.getReference();

        FirebaseStorage storage = FirebaseStorage.getInstance();
        StorageReference uploader = storage.getReference("image"+new Random().nextInt(50));

        uploader.putFile(filepath)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot)
                    {
                        uploader.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {
                                dialog.dismiss();
                                FirebaseDatabase db = FirebaseDatabase.getInstance();
                                DatabaseReference root =db.getReference("Product");

                                Product obj = new Product(name.getText().toString(),descri.getText().toString(),uri.toString(),Double.parseDouble(precio.getText().toString()),Integer.valueOf(cant.getText().toString()));
                                root.child(idprod.getText().toString()).setValue(obj);

                                name.setText("");
                                descri.setText("");
                                precio.setText("");
                                cant.setText("");
                                idprod.setText("");
                                img.setImageResource(R.drawable.ic_launcher_background);

                                Toast.makeText(getApplicationContext(), "Uploaded", Toast.LENGTH_LONG).show();
                            }
                        });

                    }
                }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onProgress(@NonNull UploadTask.TaskSnapshot taskSnapshot)
            {
                float percent = (100*taskSnapshot.getBytesTransferred())/taskSnapshot.getTotalByteCount();
                dialog.setMessage("uploaded :"+(int)percent+"%");
            }
        });
    }
}
