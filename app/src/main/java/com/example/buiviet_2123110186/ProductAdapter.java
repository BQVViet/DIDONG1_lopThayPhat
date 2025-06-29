package com.example.buiviet_2123110186;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {

    private Context context;
    private List<Product> productList;

    // Constructor
    public ProductAdapter(Context context, List<Product> products) {
        this.context = context;
        this.productList = products;
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate layout item_product_card.xml
        View view = LayoutInflater.from(context).inflate(R.layout.activity_productdetail, parent, false);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        Product product = productList.get(position);

        // Set text and image
        holder.name.setText(product.getName());
        holder.category.setText(product.getCategory());
        holder.price.setText("Rs. " + product.getPrice());

        // Load image from drawable by name
        int imageResId = context.getResources().getIdentifier(product.getImage(), "drawable", context.getPackageName());
        holder.image.setImageResource(imageResId);

        // Khi click vào sản phẩm, mở chi tiết
        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, productdetailActivity.class);
            intent.putExtra("product", product); // Gửi đối tượng qua Intent
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    // ViewHolder class
    public static class ProductViewHolder extends RecyclerView.ViewHolder {
        TextView name, category, price;
        ImageView image;

        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.txtProductName);
            category = itemView.findViewById(R.id.txtCategory);
            price = itemView.findViewById(R.id.txtPrice);
            image = itemView.findViewById(R.id.imgShoe);
        }
    }
}
