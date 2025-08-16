package com.example.recyclerview_example.models;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.recyclerview_example.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class LaptopAdapter extends RecyclerView.Adapter<LaptopAdapter.LaptopViewHolder> {

    Context context;
    List<Laptop> laptopList;
    OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(Laptop laptop);
    }

    public LaptopAdapter(Context context, List<Laptop> laptopList, OnItemClickListener listener) {
        this.context = context;
        this.laptopList = laptopList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public LaptopViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_laptop, parent, false);
        return new LaptopViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LaptopViewHolder holder, int position) {
        Laptop laptop = laptopList.get(position);

        Picasso.get()
                .load(laptop.imageUrl)
                .into(holder.laptopImage);


        holder.laptopName.setText(laptop.laptopName);
        holder.laptopDescription.setText(laptop.laptopDescription);
        holder.laptopPrice.setText(String.valueOf(laptop.laptopPrice));
        holder.laptopRating.setRating((float) laptop.laptopRating);

        holder.itemView.setOnClickListener(v -> listener.onItemClick(laptop));
    }

    @Override
    public int getItemCount() {
        return laptopList.size();
    }

    public static class LaptopViewHolder extends RecyclerView.ViewHolder {
        ImageView laptopImage;
        TextView laptopName, laptopDescription, laptopPrice;
        RatingBar laptopRating;

        public LaptopViewHolder(@NonNull View itemView) {
            super(itemView);
            laptopImage = itemView.findViewById(R.id.laptopImage);
            laptopName = itemView.findViewById(R.id.laptopName);
            laptopDescription = itemView.findViewById(R.id.laptopDescription);
            laptopPrice = itemView.findViewById(R.id.laptopPrice);
            laptopRating = itemView.findViewById(R.id.laptopRating);
        }
    }
}
