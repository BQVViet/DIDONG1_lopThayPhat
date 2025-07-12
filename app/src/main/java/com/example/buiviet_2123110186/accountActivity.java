package com.example.buiviet_2123110186;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class accountActivity extends AppCompatActivity {

    private TextView tvWelcome, tvUsername;
    private Button btnLogout;
    private Button btn_edit_profile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_account);

        // Áp dụng padding nếu layout có ID là "main"
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Ánh xạ view
        tvWelcome = findViewById(R.id.tv_welcome);
        tvUsername = findViewById(R.id.tv_username);
        btnLogout = findViewById(R.id.btn_logout);
        btn_edit_profile = findViewById(R.id.btn_edit_profile);

        btn_edit_profile.setOnClickListener(v -> startActivity(new Intent(accountActivity.this, activity_update_profile.class)));
        // Lấy dữ liệu username từ SharedPreferences
        SharedPreferences preferences = getSharedPreferences("user_data", MODE_PRIVATE);
        String username = preferences.getString("username", null);

        // Hiển thị thông tin người dùng
        if (username != null && !username.isEmpty()) {
            tvWelcome.setText("Xin chào!");
            tvUsername.setText("Tên tài khoản: " + username);
        } else {
            tvWelcome.setText("Xin chào!");
            tvUsername.setText("Tài khoản chưa đăng nhập.");
        }

        // Xử lý nút đăng xuất
        btnLogout.setOnClickListener(view -> {
            // Xoá dữ liệu user và quay về login
            preferences.edit().clear().apply();

            Intent intent = new Intent(accountActivity.this, loginActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK); // Xoá hết backstack
            startActivity(intent);
        });

    }
}
