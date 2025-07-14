package com.example.buiviet_2123110186;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class product_listActivity extends AppCompatActivity {

    private ImageButton nav_home,nav_search,btnaccount;
    private RecyclerView recyclerProduct;
    private ProductlistAdapter adapter;
    private List<productlist> allProducts; // Danh sách tất cả sản phẩm

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_product_list);

        // Xử lý edge-to-edge layout
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        recyclerProduct = findViewById(R.id.recyclerProductDiscount);
        recyclerProduct.setLayoutManager(new GridLayoutManager(this, 2)); // 2 cột giống Shopee

        // Tạo danh sách sản phẩm
        allProducts = new ArrayList<>();
        allProducts.add(new productlist("Converse Chuck 70", "RUNNING", "+2 màu sắc", "950.000₫", "Giảm 8%", R.drawable.nike1));
        allProducts.add(new productlist("Nike Air Max", "SNEAKERS", "+3 màu sắc", "1.200.000₫", "Giảm 7%", R.drawable.nike6));
        allProducts.add(new productlist("Jordan Retro", "JORDANS", "+1 màu", "2.000.000₫", "Giảm 4%", R.drawable.nike7));
        allProducts.add(new productlist("Nike Tiempo", "FOOTBALL", "+2 màu sắc", "1.050.000₫", "Giảm 5%", R.drawable.nike8));
        allProducts.add(new productlist("Converse Classic", "RUNNING", "+4 màu", "850.000₫", "Giảm 6%", R.drawable.nike_6));
        allProducts.add(new productlist("Nike React", "NIKE", "+2 màu", "1.300.000₫", "Giảm 7%", R.drawable.nike3));
        allProducts.add(new productlist("Nike Phantom", "FOOTBALL", "+3 màu", "1.100.000₫", "Giảm 9%", R.drawable.nike4));
        allProducts.add(new productlist("Jordan High", "JORDANS", "+1 màu", "2.100.000₫", "Giảm 5%", R.drawable.jordan));
        allProducts.add(new productlist("Jordan High", "JORDANS", "+1 màu", "2.100.000₫", "Giảm 5%", R.drawable.nike_1));
        allProducts.add(new productlist("Converse Chuck 70", "RUNNING", "+2 màu sắc", "950.000₫", "Giảm 8%", R.drawable.giay1));
        allProducts.add(new productlist("Converse Chuck 70 High", "RUNNING", "+2 màu sắc", "960.000₫", "Giảm 7%", R.drawable.giay2));
        allProducts.add(new productlist(  "Jordan Retro 6", "JORDANS", "+1 màu", "2.050.000₫", "Giảm 4%", R.drawable.giay3));
        allProducts.add(new productlist("Nike Tiempo X", "FOOTBALL", "+2 màu sắc", "1.070.000₫", "Giảm 5%", R.drawable.giay4));
        allProducts.add(new productlist("Converse Classic Low", "RUNNING", "+4 màu", "830.000₫", "Giảm 6%", R.drawable.giay5));
        allProducts.add(new productlist( "Nike React Element", "NIKE", "+2 màu", "1.310.000₫", "Giảm 7%", R.drawable.giay6));
        allProducts.add(new productlist("Nike Phantom Venom", "FOOTBALL", "+3 màu", "1.090.000₫", "Giảm 9%", R.drawable.giay7));
        allProducts.add(new productlist("Jordan 1 Mid", "JORDANS", "+1 màu", "2.080.000₫", "Giảm 5%", R.drawable.giay8));
        allProducts.add(new productlist("Jordan 1 Low", "JORDANS", "+2 màu", "1.950.000₫", "Giảm 5%", R.drawable.giay9));
        // Khởi tạo adapter
        adapter = new ProductlistAdapter(this, allProducts);
        recyclerProduct.setAdapter(adapter);

        // Gắn nút lọc theo loại
        findViewById(R.id.button7).setOnClickListener(v -> filterByCategory("All"));
        findViewById(R.id.button8).setOnClickListener(v -> filterByCategory("Running"));
        findViewById(R.id.button9).setOnClickListener(v -> filterByCategory("Jordans"));
        findViewById(R.id.button10).setOnClickListener(v -> filterByCategory("Sneakers"));
        findViewById(R.id.button11).setOnClickListener(v -> filterByCategory("Football"));
        findViewById(R.id.button12).setOnClickListener(v -> filterByCategory("Nike"));

        // Gắn nút quay về Home
        nav_home = findViewById(R.id.nav_home);
        nav_home.setOnClickListener(v -> {
            Intent intent = new Intent(product_listActivity.this, homeActivity.class);
            startActivity(intent);
            finish();
        });
        nav_search = findViewById(R.id.nav_search);
        nav_search.setOnClickListener(v -> {
            Intent intent = new Intent(product_listActivity.this, notificationActivity.class);
            startActivity(intent);
            finish();
        });
        btnaccount = findViewById(R.id.btnaccount);
        btnaccount.setOnClickListener(v -> {
            Intent intent = new Intent(product_listActivity.this, accountActivity.class);
            startActivity(intent);
            finish();
        });
    }

    private void filterByCategory(String category) {
        List<productlist> filtered = new ArrayList<>();
        if (category.equalsIgnoreCase("All")) {
            filtered.addAll(allProducts);
        } else {
            for (productlist p : allProducts) {
                if (p.getCategory().equalsIgnoreCase(category)) {
                    filtered.add(p);
                }
            }
        }

        if (filtered.isEmpty()) {
            Toast.makeText(this, "Không có sản phẩm nào.", Toast.LENGTH_SHORT).show();
        }

        adapter.filterList(filtered);
    }
}
