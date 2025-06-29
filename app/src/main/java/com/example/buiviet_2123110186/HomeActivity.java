package com.example.buiviet_2123110186;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    List<Product> productList;
    ProductAdapter productAdapter;
    Button btnChiTiet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_home);

        // Áp dụng padding hệ thống (status bar...)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Liên kết View
        recyclerView = findViewById(R.id.recyclerView);
        btnChiTiet = findViewById(R.id.chitiet); // Button trong layout
        Button btncart = findViewById(R.id.cart);
        // Danh sách sản phẩm mẫu
        productList = new ArrayList<>();
        productList.add(new Product(
                "Nike Air Jordan X",
                "Men's Shoes",
                "air_jordan_main",
                12067,
                4.9,
                "Giày thể thao thời trang, phù hợp chơi bóng rổ và đi hằng ngày."
        ));

        // Adapter
        productAdapter = new ProductAdapter(this, productList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(productAdapter);

        btnChiTiet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), productdetailActivity.class);
                startActivity(intent);
            }
        });
        btncart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), CartActivity.class);
                startActivity(intent);
            }
        });
        // Nút chuyển sang màn chi tiết sản phẩm
        btnChiTiet.setOnClickListener(v -> {
            Product p = productList.get(0); // Lấy sản phẩm đầu tiên
            Intent intent = new Intent(HomeActivity.this, productdetailActivity.class); // Tên class đúng chuẩn
            intent.putExtra("product", p);
            startActivity(intent);
        });
    }
}
