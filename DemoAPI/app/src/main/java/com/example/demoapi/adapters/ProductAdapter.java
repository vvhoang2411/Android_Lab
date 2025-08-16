package com.example.demoapi.adapters;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.demoapi.R;
import com.example.demoapi.models.Product;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {

    private Context context;
    private List<Product> productList;

    // Constructor
    public ProductAdapter(Context context, List<Product> productList) {
        this.context = context;
        this.productList = productList;
    }

    // ViewHolder
    public class ProductViewHolder extends RecyclerView.ViewHolder {
        TextView tvName, tvDescription, tvPrice;

        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvName);
            tvDescription = itemView.findViewById(R.id.tvDescription);
            tvPrice = itemView.findViewById(R.id.tvPrice);

            // Xử lý click để sửa
            itemView.setOnClickListener(v -> {
                int position = getAdapterPosition();
                if (position != RecyclerView.NO_POSITION) {
                    Product clickedProduct = productList.get(position);
                    Toast.makeText(context, "Chỉnh sửa: " + clickedProduct.getName(), Toast.LENGTH_SHORT).show();

                    // TODO: Gửi Intent sang EditProductActivity và truyền dữ liệu
                }
            });

            // Xử lý long-click để xóa
            itemView.setOnLongClickListener(v -> {
                int position = getAdapterPosition();
                if (position != RecyclerView.NO_POSITION) {
                    Product productToDelete = productList.get(position);
                    Toast.makeText(context, "Xóa: " + productToDelete.getName(), Toast.LENGTH_SHORT).show();

                    // TODO: Gọi API hoặc thông báo xác nhận xóa
                    return true;
                }
                return false;
            });
        }
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_product, parent, false);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        Product p = productList.get(position);
        holder.tvName.setText(p.getName());
        holder.tvDescription.setText(p.getDescription());
        holder.tvPrice.setText(String.format("%.2f đ", p.getPrice()));
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    // Optional: cập nhật dữ liệu
    public void setProducts(List<Product> products) {
        this.productList = products;
        notifyDataSetChanged();
    }
}

