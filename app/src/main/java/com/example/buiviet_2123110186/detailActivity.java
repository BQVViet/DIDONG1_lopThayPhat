package com.example.buiviet_2123110186;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class detailActivity extends AppCompatActivity {

    private TextView name, category, colors, discount, price;
    private ImageView image;
    private ImageButton btnBack;
    private Button btnBuy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_detail);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Ánh xạ view
        name = findViewById(R.id.txtProductName);
        category = findViewById(R.id.txtCategory);
        colors = findViewById(R.id.txtColors);
        discount = findViewById(R.id.txtDiscount);
        price = findViewById(R.id.txtPrice);
        image = findViewById(R.id.imgShoe);
        btnBack = findViewById(R.id.btnBack);
        btnBuy = findViewById(R.id.btnBuy);

        // Nhận dữ liệu từ Intent
        Product product = (Product) getIntent().getSerializableExtra("product");

        if (product != null) {
            name.setText(product.getName());
            category.setText(product.getCategory());
            colors.setText(product.getColors());
            discount.setText(product.getDiscount());
            price.setText(product.getPrice());
            image.setImageResource(product.getImageResId());
        }

        // Nút quay lại
        btnBack.setOnClickListener(v -> {
            Intent i = new Intent(detailActivity.this, homeActivity.class);
            startActivity(i);
        });

        // Nút thêm vào giỏ hàng
        btnBuy.setOnClickListener(v -> {
            SharedPreferences preferences = getSharedPreferences("cart", MODE_PRIVATE);
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

            Toast.makeText(this, "Đã thêm vào giỏ hàng", Toast.LENGTH_SHORT).show();
        });
    }
}
