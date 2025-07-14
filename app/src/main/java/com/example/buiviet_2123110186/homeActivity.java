package com.example.buiviet_2123110186;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class homeActivity extends AppCompatActivity {

    private ImageButton btnaccount, btnCart,nav_search,Buttongiay;
    private RecyclerView recyclerView, recyclerProductDiscount;
    private ProductAdapter productAdapter;
    private ProductAdapter discountAdapter;
    private List<Product> allProducts = new ArrayList<>();
    private List<Product> filteredList = new ArrayList<>();
    private List<Product> productDiscountList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_home);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Ánh xạ View
        btnCart = findViewById(R.id.cart);
        btnaccount = findViewById(R.id.btnaccount);
        recyclerView = findViewById(R.id.recyclerViewNewProducts);
        recyclerProductDiscount = findViewById(R.id.recyclerProductDiscount);
        EditText searchEditText = findViewById(R.id.searchEditText);
        nav_search = findViewById(R.id.nav_search);
        Buttongiay = findViewById(R.id.Buttongiay);

        // Gán dữ liệu
        allProducts.add(new Product("Converse Chuck 70", "Running", "+2 màu sắc", "950.000₫", "Giảm 8%", R.drawable.nike5));
        allProducts.add(new Product("Nike Air Max", "Sneakers", "+3 màu sắc", "1.200.000₫", "Giảm 7%", R.drawable.nike6));
        allProducts.add(new Product("Jordan Retro", "Jordans", "+1 màu", "2.000.000₫", "Giảm 4%", R.drawable.nike7));
        allProducts.add(new Product("Nike Tiempo", "Football", "+2 màu sắc", "1.050.000₫", "Giảm 5%", R.drawable.nike8));
        allProducts.add(new Product("Converse Classic", "Running", "+4 màu", "850.000₫", "Giảm 6%", R.drawable.nike1));
        allProducts.add(new Product("Nike React", "Nike", "+2 màu", "1.300.000₫", "Giảm 7%", R.drawable.nike3));
        allProducts.add(new Product("Nike Phantom", "Football", "+3 màu", "1.100.000₫", "Giảm 9%", R.drawable.nike4));
        allProducts.add(new Product("Jordan High", "Jordans", "+1 màu", "2.100.000₫", "Giảm 5%", R.drawable.jordan));

        // Gán cho danh sách đang hiển thị
        filteredList.addAll(allProducts);

        // Thiết lập RecyclerView sản phẩm mới (cuộn ngang)
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        productAdapter = new ProductAdapter(this, filteredList);
        recyclerView.setAdapter(productAdapter);

        // Thiết lập RecyclerView sản phẩm khuyến mãi
        recyclerProductDiscount.setLayoutManager(new GridLayoutManager(this, 2));
        productDiscountList = new ArrayList<>(allProducts); // Tạm dùng cùng danh sách
        discountAdapter = new ProductAdapter(this, productDiscountList);
        recyclerProductDiscount.setAdapter(discountAdapter);

        // Chuyển trang
        btnCart.setOnClickListener(v -> startActivity(new Intent(homeActivity.this, cartActivity.class)));
        btnaccount.setOnClickListener(v -> startActivity(new Intent(homeActivity.this, accountActivity.class)));
        nav_search.setOnClickListener(v -> startActivity(new Intent(homeActivity.this, notificationActivity.class)));
        Buttongiay.setOnClickListener(v -> startActivity(new Intent(homeActivity.this, product_listActivity.class)));
        // Nút lọc danh mục
        Button btnAll = findViewById(R.id.button7);
        Button btnRunning = findViewById(R.id.button8);
        Button btnJordans = findViewById(R.id.button9);
        Button btnSneakers = findViewById(R.id.button10);
        Button btnFootball = findViewById(R.id.button11);
        Button btnNike = findViewById(R.id.button12);

        btnAll.setOnClickListener(v -> filterByCategory("All"));
        btnRunning.setOnClickListener(v -> filterByCategory("Running"));
        btnJordans.setOnClickListener(v -> filterByCategory("Jordans"));
        btnSneakers.setOnClickListener(v -> filterByCategory("Sneakers"));
        btnFootball.setOnClickListener(v -> filterByCategory("Football"));
        btnNike.setOnClickListener(v -> filterByCategory("Nike"));

        // Tìm kiếm sản phẩm
        searchEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                filterByName(s.toString().trim());
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });
    }

    // Hàm lọc danh mục
    private void filterByCategory(String category) {
        filteredList.clear();
        if (category.equals("All")) {
            filteredList.addAll(allProducts);
        } else {
            for (Product p : allProducts) {
                if (p.getCategory().equalsIgnoreCase(category)) {
                    filteredList.add(p);
                }
            }
        }
        productAdapter.notifyDataSetChanged();
    }

    // Hàm tìm kiếm theo tên sản phẩm
    private void filterByName(String keyword) {
        filteredList.clear();
        if (keyword.isEmpty()) {
            filteredList.addAll(allProducts);
        } else {
            for (Product p : allProducts) {
                if (p.getName().toLowerCase().contains(keyword.toLowerCase())) {
                    filteredList.add(p);
                }
            }
        }
        productAdapter.notifyDataSetChanged();
    }
}
