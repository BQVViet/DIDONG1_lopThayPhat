package com.example.buiviet_2123110186;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class productdetailActivity extends AppCompatActivity {

    TextView name, category, price, description;
    ImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_productdetail); // đảm bảo layout file đúng tên

        // Áp dụng padding cho EdgeToEdge
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Liên kết view
        name = findViewById(R.id.txtProductName);
        category = findViewById(R.id.txtCategory);
        price = findViewById(R.id.txtPrice);
        description = findViewById(R.id.txtDescription);
        image = findViewById(R.id.imgShoe);

        // Nhận object từ Intent
        Product product = (Product) getIntent().getSerializableExtra("product");

        if (product != null) {
            name.setText(product.getName());
            category.setText(product.getCategory());
            price.setText("Rs. " + product.getPrice());
            description.setText(product.getDescription());

            int imgResId = getResources().getIdentifier(product.getImage(), "drawable", getPackageName());
            image.setImageResource(imgResId);
        }
        ImageButton btnmuiten= findViewById(R.id.btnBack);
        btnmuiten.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                startActivity(intent);
            }
        });
    }
}
