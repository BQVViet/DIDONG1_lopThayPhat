package com.example.buiviet_2123110186;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class loginActivity extends AppCompatActivity {

    EditText objUse, objPass;
    Button btnbutton, btnregister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        objUse = findViewById(R.id.txtUsename);
        objPass = findViewById(R.id.txtPassword);
        btnbutton = findViewById(R.id.btnLogin);
        btnregister = findViewById(R.id.btnregister);

        // Nhận dữ liệu từ đăng ký gửi sang (nếu có)
        Intent intent = getIntent();
        String receivedUsername = intent.getStringExtra("username");
        String receivedPassword = intent.getStringExtra("password");

        if (receivedUsername != null && receivedPassword != null) {
            objUse.setText(receivedUsername);
            objPass.setText(receivedPassword);

            // Đăng nhập luôn
            handleLogin(receivedUsername, receivedPassword);
        }

        // Xử lý nút Đăng nhập
        btnbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = objUse.getText().toString().trim();
                String password = objPass.getText().toString();
                handleLogin(username, password);
            }
        });

        // Chuyển sang đăng ký
        btnregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), registerActivity.class);
                startActivity(intent);
            }
        });
    }

    // Hàm kiểm tra đăng nhập
    private void handleLogin(String username, String password) {
        if (username.isEmpty() || password.isEmpty()) {
            Toast.makeText(getApplicationContext(), "Vui lòng nhập đầy đủ thông tin.", Toast.LENGTH_SHORT).show();
            return;
        }

        // Lấy dữ liệu tài khoản đã lưu
        SharedPreferences sharedPreferences = getSharedPreferences("UserPrefs", MODE_PRIVATE);
        String savedUsername = sharedPreferences.getString("username", "");
        String savedPassword = sharedPreferences.getString("password", "");

        if (username.equals(savedUsername) && password.equals(savedPassword)) {
            Toast.makeText(getApplicationContext(), "Đăng nhập thành công.", Toast.LENGTH_SHORT).show();
            Intent it = new Intent(getApplicationContext(), HomeActivity.class);
            startActivity(it);
            finish(); // Đóng màn login
        } else {
            Toast.makeText(getApplicationContext(), "Sai tài khoản hoặc mật khẩu.", Toast.LENGTH_SHORT).show();
        }
    }
}
