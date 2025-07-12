package com.example.buiviet_2123110186;

import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class cartActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ProductAdapter adapter;
    private List<Product> cartList;
    private ImageButton btnBack;
    private TextView txtTotal;
    private Button btnClearCart, btnCheckout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_cart);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Ánh xạ View
        recyclerView = findViewById(R.id.recyclerCart);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        btnBack = findViewById(R.id.btnBack);
        txtTotal = findViewById(R.id.txtTotal);
        btnClearCart = findViewById(R.id.btnClearCart);
        btnCheckout = findViewById(R.id.btnCheckout);

        // Quay về trang chủ
        btnBack.setOnClickListener(v -> startActivity(new Intent(cartActivity.this, homeActivity.class)));

        // Load dữ liệu
        cartList = new ArrayList<>();
        loadCartFromPrefs();

        // Gắn adapter
        adapter = new ProductAdapter(this, cartList);
        recyclerView.setAdapter(adapter);

        // Tính tổng tiền
        calculateTotal();

        // Xoá toàn bộ
        btnClearCart.setOnClickListener(v -> {
            cartList.clear();
            adapter.notifyDataSetChanged();
            clearCartFromPrefs();
            calculateTotal();
            Toast.makeText(this, "Đã xoá toàn bộ giỏ hàng", Toast.LENGTH_SHORT).show();
        });

        // Thanh toán
        btnCheckout.setOnClickListener(v -> {
            if (cartList.isEmpty()) {
                Toast.makeText(this, "Không có sản phẩm để thanh toán", Toast.LENGTH_SHORT).show();
            } else {
                showPaymentMethods(); // Gọi chọn phương thức
            }
        });
    }

    private void loadCartFromPrefs() {
        SharedPreferences preferences = getSharedPreferences("cart", MODE_PRIVATE);
        int cartSize = preferences.getInt("cart_size", 0);

        for (int i = 0; i < cartSize; i++) {
            String name = preferences.getString("product_name_" + i, "");
            String category = preferences.getString("product_category_" + i, "");
            String colors = preferences.getString("product_colors_" + i, "");
            String price = preferences.getString("product_price_" + i, "");
            String discount = preferences.getString("product_discount_" + i, "");
            int imageResId = preferences.getInt("product_imageResId_" + i, R.drawable.nike1);

            Product product = new Product(name, category, colors, price, discount, imageResId);
            cartList.add(product);
        }

        if (cartSize == 0) {
            Toast.makeText(this, "Giỏ hàng trống", Toast.LENGTH_SHORT).show();
        }
    }

    private void clearCartFromPrefs() {
        SharedPreferences preferences = getSharedPreferences("cart", MODE_PRIVATE);
        preferences.edit().clear().apply();
    }

    private void calculateTotal() {
        int total = 0;
        for (Product product : cartList) {
            String priceStr = product.getPrice().replace("₫", "").replace(".", "").trim();
            try {
                total += Integer.parseInt(priceStr);
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }

        String formatted = String.format("%,d₫", total).replace(",", ".");
        txtTotal.setText("Tổng cộng: " + formatted);
    }

    // 👉 Hiển thị dialog chọn phương thức thanh toán
    private void showPaymentMethods() {
        String[] methods = {"Ví Momo", "ZaloPay", "Thẻ ATM", "Thanh toán khi nhận hàng (COD)"};

        new AlertDialog.Builder(this)
                .setTitle("Chọn phương thức thanh toán")
                .setItems(methods, (dialog, which) -> {
                    switch (which) {
                        case 0: payWithMomo(); break;
                        case 1: payWithZaloPay(); break;
                        case 2: payWithATM(); break;
                        case 3: payWithCOD(); break;
                    }
                })
                .setNegativeButton("Huỷ", null)
                .show();
    }

    // 👉 Các kiểu thanh toán (giả lập)
    private void payWithMomo() {
        Toast.makeText(this, "Đã chọn thanh toán qua Momo", Toast.LENGTH_SHORT).show();
        processAfterPayment();
    }

    private void payWithZaloPay() {
        Toast.makeText(this, "Đã chọn thanh toán qua ZaloPay", Toast.LENGTH_SHORT).show();
        processAfterPayment();
    }

    private void payWithATM() {
        Toast.makeText(this, "Đã chọn thanh toán qua Thẻ ATM", Toast.LENGTH_SHORT).show();
        processAfterPayment();
    }

    private void payWithCOD() {
        Toast.makeText(this, "Đã chọn thanh toán khi nhận hàng (COD)", Toast.LENGTH_SHORT).show();
        processAfterPayment();
    }

    // 👉 Sau khi thanh toán
    private void processAfterPayment() {
        Toast.makeText(this, "Thanh toán thành công!", Toast.LENGTH_LONG).show();
        clearCartFromPrefs();
        cartList.clear();
        adapter.notifyDataSetChanged();
        calculateTotal();

        // Tuỳ chọn: trở về Home
        // startActivity(new Intent(this, homeActivity.class));
        // finish();
    }
}
