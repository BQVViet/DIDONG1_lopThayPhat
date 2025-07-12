package com.example.buiviet_2123110186;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

public class loginActivity extends AppCompatActivity {

    EditText txtUsername, txtPassword;
    Button btnLogin, btnRegister;
    ImageView imageView4; // 👈 Thêm dòng này

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);

        // Đảm bảo layout chính có ID là "main"
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Ánh xạ view từ layout
        txtUsername = findViewById(R.id.txtUsename);
        txtPassword = findViewById(R.id.txtPassword);
        btnLogin = findViewById(R.id.btnLogin);
        btnRegister = findViewById(R.id.btnregister);
        imageView4 = findViewById(R.id.imageView4); // 👈 Ánh xạ nút mắt

        // 👁 Xử lý hiện/ẩn mật khẩu
        final boolean[] isPasswordVisible = {false};
        imageView4.setOnClickListener(v -> {
            if (isPasswordVisible[0]) {
                txtPassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                imageView4.setImageResource(R.drawable.view_eye_icon); // mắt đóng
                isPasswordVisible[0] = false;
            } else {
                txtPassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                imageView4.setImageResource(R.drawable.view_eye_icon); // mắt mở
                isPasswordVisible[0] = true;
            }
            txtPassword.setSelection(txtPassword.getText().length()); // giữ con trỏ ở cuối
        });

        // Nhận dữ liệu từ trang đăng ký (nếu có)
        Intent intent = getIntent();
        String receivedUsername = intent.getStringExtra("username");
        String receivedPassword = intent.getStringExtra("password");

        if (receivedUsername != null && receivedPassword != null) {
            txtUsername.setText(receivedUsername);
            txtPassword.setText(receivedPassword);
        }

        // Xử lý nút Đăng nhập
        btnLogin.setOnClickListener(v -> {
            String username = txtUsername.getText().toString().trim();
            String password = txtPassword.getText().toString().trim();
            handleLogin(username, password);
        });

        // Chuyển sang màn đăng ký
        btnRegister.setOnClickListener(v -> {
            Intent i = new Intent(loginActivity.this, registerActivity.class);
            startActivity(i);
        });
    }

    // Hàm xử lý đăng nhập qua FakeStore API
    private void handleLogin(String username, String password) {
        if (username.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Vui lòng nhập đầy đủ tên đăng nhập và mật khẩu.", Toast.LENGTH_SHORT).show();
            return;
        }

        String url = "https://fakestoreapi.com/auth/login";
        RequestQueue queue = Volley.newRequestQueue(this);

        try {
            JSONObject jsonBody = new JSONObject();
            jsonBody.put("username", username);
            jsonBody.put("password", password);

            JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, jsonBody,
                    response -> {
                        try {
                            String token = response.getString("token");
                            Toast.makeText(this, "Đăng nhập thành công!", Toast.LENGTH_SHORT).show();

                            // Có thể lưu token nếu cần
                            Intent intent = new Intent(loginActivity.this, homeActivity.class);
                            startActivity(intent);
                            finish();
                        } catch (Exception e) {
                            e.printStackTrace();
                            Toast.makeText(this, "Đăng nhập thất bại (lỗi phản hồi).", Toast.LENGTH_SHORT).show();
                        }
                    },
                    error -> {
                        Toast.makeText(this, "Sai tên đăng nhập hoặc mật khẩu!", Toast.LENGTH_SHORT).show();
                    });

            queue.add(request);
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this, "Lỗi tạo dữ liệu đăng nhập.", Toast.LENGTH_SHORT).show();
        }
    }
}
