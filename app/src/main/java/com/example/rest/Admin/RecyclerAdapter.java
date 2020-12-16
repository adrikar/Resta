package com.example.rest.Admin;
import android.content.*;
import android.view.*;
import android.widget.*;

import androidx.annotation.*;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.*;
import com.example.rest.Admin.Model.Product;
import com.example.rest.R;

import java.util.*;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder>{

    private static final String Tag = "RecyclerView";
    private Context mContext;
    private ArrayList<Product> productList;

    public RecyclerAdapter(Context mContext, ArrayList<Product> productList) {
        this.mContext = mContext;
        this.productList = productList;
    }

    @NonNull
    @Override
    public RecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.message_item,parent,false);

        return new ViewHolder(view);




    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.textView.setText(productList.get(position).getName());
        holder.textView1.setText(productList.get(position).getDascri());

        Glide.with(mContext)
                .load(productList.get(position).getImage())
                .into(holder.imageView);

    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imageView;
        TextView textView;
        TextView textView1;
        TextView textView2;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView);
            textView = itemView.findViewById(R.id.textView);
            textView1 = itemView.findViewById(R.id.textView1);
            textView2 =itemView.findViewById(R.id.textView2);
        }
    }
}
