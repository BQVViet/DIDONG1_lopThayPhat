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

public class ProductlistAdapter extends RecyclerView.Adapter<ProductlistAdapter.ViewHolder> {

    private Context context;
    private List<productlist> productList;

    public ProductlistAdapter(Context context, List<productlist> productList) {
        this.context = context;
        this.productList = productList;
    }

    public void filterList(List<productlist> filteredList) {
        this.productList = filteredList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.activity_item_product_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        productlist product = productList.get(position);
        holder.nameText.setText(product.getName());
        holder.CategoryText.setText(product.getCategory());
        holder.priceText.setText(product.getPrice());
        holder.discountText.setText(product.getDiscount());
        holder.imageView.setImageResource(product.imageResId());

        // Sự kiện xem chi tiết
        holder.btnDetail.setOnClickListener(v -> {
            Intent intent = new Intent(context, detailActivity.class);
            intent.putExtra("product", product); // Đối tượng product cần implements Serializable
            context.startActivity(intent);
        });

        // Sự kiện thêm vào giỏ hàng
        holder.btnCart.setOnClickListener(v -> {
            SharedPreferences preferences = context.getSharedPreferences("cart", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = preferences.edit();

            int cartSize = preferences.getInt("cart_size", 0);

            editor.putString("product_name_" + cartSize, product.getName());
            editor.putString("product_category_" + cartSize, product.getCategory());
            editor.putString("product_colors_" + cartSize, product.getColors());
            editor.putString("product_price_" + cartSize, product.getPrice());
            editor.putString("product_discount_" + cartSize, product.getDiscount());
            editor.putInt("product_imageResId_" + cartSize, product.imageResId());

            editor.putInt("cart_size", cartSize + 1);
            editor.apply();

            Toast.makeText(context, "Đã thêm vào giỏ hàng", Toast.LENGTH_SHORT).show();
        });
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView nameText, CategoryText, priceText, discountText;
        ImageView imageView;
        Button btnCart, btnDetail;

        public ViewHolder(View itemView) {
            super(itemView);
            nameText = itemView.findViewById(R.id.productName);
            CategoryText = itemView.findViewById(R.id.productCategory);
            priceText = itemView.findViewById(R.id.productPrice);
            discountText = itemView.findViewById(R.id.productDiscount);
            imageView = itemView.findViewById(R.id.productImage);
            btnCart = itemView.findViewById(R.id.btnCart);
            btnDetail = itemView.findViewById(R.id.btnDetail);
        }
    }
}
