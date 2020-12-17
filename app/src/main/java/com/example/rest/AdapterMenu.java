package com.example.rest;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.rest.Admin.Model.Product;
import com.example.rest.Admin.RecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

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


        public myviewholder(@NonNull View itemView) {
            super(itemView);

            img = (ImageView)itemView.findViewById(R.id.imageview);
            namep= (TextView)itemView.findViewById(R.id.nametxt);
            des= (TextView)itemView.findViewById(R.id.descritxt);
            precio = (TextView)itemView.findViewById(R.id.preciotxt);
        }
    }
}
