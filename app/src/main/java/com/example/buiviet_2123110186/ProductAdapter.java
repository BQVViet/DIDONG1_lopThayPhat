package com.example.buiviet_2123110186;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {

    private final Context context;
    private final List<Product> productList;

    public ProductAdapter(Context context, List<Product> products) {
        this.context = context;
        this.productList = products;
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Gắn layout item_product.xml
        View view = LayoutInflater.from(context).inflate(R.layout.activity_item_product, parent, false);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        Product product = productList.get(position);

        // Gán dữ liệu vào View
        holder.name.setText(product.getName());
        holder.category.setText(product.getCategory());
        holder.colors.setText(product.getColors());
        holder.price.setText(product.getPrice());
        holder.discount.setText(product.getDiscount());
        holder.image.setImageResource(product.getImageResId());

        // Sự kiện xem chi tiết: chuyển sang product_detailActivity, truyền product
        holder.btnDetail.setOnClickListener(v -> {
            Intent intent = new Intent(context, detailActivity.class);
            intent.putExtra("product", product); // product phải implements Serializable
            context.startActivity(intent);
        });

        // Sự kiện thêm vào giỏ hàng: lưu SharedPreferences
        holder.btnAddToCart.setOnClickListener(v -> {
            SharedPreferences preferences = context.getSharedPreferences("cart", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = preferences.edit();

            int cartSize = preferences.getInt("cart_size", 0);

            editor.putString("product_name_" + cartSize, product.getName());
            editor.putString("product_category_" + cartSize, product.getCategory());
            editor.putString("product_colors_" + cartSize, product.getColors());
            editor.putString("product_price_" + cartSize, product.getPrice());
            editor.putString("product_discount_" + cartSize, product.getDiscount());
            editor.putInt("product_imageResId_" + cartSize, product.getImageResId());

            editor.putInt("cart_size", cartSize + 1);
            editor.apply();

            Toast.makeText(context, "Đã thêm vào giỏ hàng", Toast.LENGTH_SHORT).show();
        });
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    // ViewHolder
    public static class ProductViewHolder extends RecyclerView.ViewHolder {
        TextView name, category, colors, price, discount;
        ImageView image;
        Button btnAddToCart, btnDetail;

        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.productName);
            category = itemView.findViewById(R.id.productCategory);
            colors = itemView.findViewById(R.id.productColors);
            price = itemView.findViewById(R.id.productPrice);
            discount = itemView.findViewById(R.id.productDiscount);
            image = itemView.findViewById(R.id.productImage);
            btnAddToCart = itemView.findViewById(R.id.btnCart);
            btnDetail = itemView.findViewById(R.id.btnDetail);
        }
    }
}
