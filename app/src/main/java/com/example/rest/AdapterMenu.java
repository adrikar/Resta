package com.example.rest;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.rest.Admin.Model.Product;
import com.example.rest.Admin.RecyclerAdapter;
import com.example.rest.model.PedidoU;
import com.example.rest.model.User;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import static com.firebase.ui.auth.AuthUI.getApplicationContext;

public class AdapterMenu extends FirebaseRecyclerAdapter<Product,AdapterMenu.myviewholder> {
    public AdapterMenu(@NonNull FirebaseRecyclerOptions<Product> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull myviewholder holder, int position, @NonNull Product model) {
        holder.namep.setText(model.getName());
        holder.des.setText(model.getDascri());
        holder.precio.setText(model.getPrice().toString()+" Bs.");
        Glide.with(holder.img.getContext()).load(model.getImage()).into(holder.img);
    }

    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_card, parent, false);
        return new AdapterMenu.myviewholder(view);
    }

    class myviewholder extends RecyclerView.ViewHolder{
        ImageView img ;
        TextView namep, des, precio;
        EditText cant;
        Button btnc;


        public myviewholder(@NonNull View itemView) {
            super(itemView);

            img = (ImageView)itemView.findViewById(R.id.imageview);
            namep= (TextView)itemView.findViewById(R.id.nametxt);
            des= (TextView)itemView.findViewById(R.id.descritxt);
            precio = (TextView)itemView.findViewById(R.id.preciotxt);
            cant = (EditText)itemView.findViewById(R.id.cantidad);
            btnc =(Button)itemView.findViewById(R.id.btncarrito);


            btnc.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String userId;
                    FirebaseAuth fAuth ;
                    fAuth = FirebaseAuth.getInstance();
                    userId = fAuth.getCurrentUser().getUid();
                    FirebaseDatabase db = FirebaseDatabase.getInstance();
                    DatabaseReference data = (DatabaseReference) db.getReference("User").child(userId);
                    User us = new User();
                    PedidoU ped = new PedidoU(namep.getText().toString(),us.getName(),Integer.valueOf(cant.getText().toString()));
                    data.child("Pedido").push().setValue(ped);

                    cant.setText("");



                }
            });


        }
    }
}
