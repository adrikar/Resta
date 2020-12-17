package com.example.rest.Admin;
import android.content.*;
import android.view.*;
import android.widget.*;

import androidx.annotation.*;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.*;
import com.example.rest.Admin.Model.Product;
import com.example.rest.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

import java.util.*;

public class RecyclerAdapter extends FirebaseRecyclerAdapter<Product,RecyclerAdapter.myviewholder> {
    public RecyclerAdapter(@NonNull FirebaseRecyclerOptions<Product> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull myviewholder holder, int position, @NonNull Product model)
    {
        holder.namep.setText(model.getName());
        holder.des.setText("Descripcion: "+model.getDascri());
        holder.precio.setText("Precio: "+model.getPrice().toString()+" Bs.");
        Glide.with(holder.img.getContext()).load(model.getImage()).into(holder.img);
    }

    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.message_item, parent, false);
        return new myviewholder(view);
    }

    public class myviewholder extends RecyclerView.ViewHolder{
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
