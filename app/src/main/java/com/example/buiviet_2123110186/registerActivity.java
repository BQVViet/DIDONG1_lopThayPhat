//package com.example.buiviet_2123110186;
//
//import android.content.Intent;
//import android.content.SharedPreferences;
//import android.os.Bundle;
//import android.view.View;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.Toast;
//
//import androidx.activity.EdgeToEdge;
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.core.graphics.Insets;
//import androidx.core.view.ViewCompat;
//import androidx.core.view.WindowInsetsCompat;
//
//public class registerActivity extends AppCompatActivity {
//
//    EditText edtEmail, edtUsername, edtPassword, edtConfirmPassword;
//    Button btnSignup;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        EdgeToEdge.enable(this);
//        setContentView(R.layout.activity_register);
//
//        // Áp dụng insets để tránh che view bởi status/navigation bar
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
//            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
//            return insets;
//        });
//
//        // Ánh xạ các thành phần giao diện
//        edtEmail = findViewById(R.id.txtEmail);
//        edtUsername = findViewById(R.id.txtUse);          // Lưu ý: ID = txtUse
//        edtPassword = findViewById(R.id.txtPasswordd);    // Lưu ý: ID = txtPasswordd
//        edtConfirmPassword = findViewById(R.id.txtNBPassword);
//        btnSignup = findViewById(R.id.btnSignup);
//
//        // Bắt sự kiện nút Đăng ký
//        btnSignup.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String email = edtEmail.getText().toString().trim();
//                String username = edtUsername.getText().toString().trim();
//                String password = edtPassword.getText().toString();
//                String confirmPassword = edtConfirmPassword.getText().toString();
//
//                // Kiểm tra dữ liệu nhập
//                if (email.isEmpty() || username.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
//                    Toast.makeText(registerActivity.this, "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
//                    return;
//                }
//
//                if (!password.equals(confirmPassword)) {
//                    Toast.makeText(registerActivity.this, "Mật khẩu không khớp", Toast.LENGTH_SHORT).show();
//                    return;
//                }
//
//                // Lưu username và password vào SharedPreferences
//                SharedPreferences sharedPreferences = getSharedPreferences("UserPrefs", MODE_PRIVATE);
//                SharedPreferences.Editor editor = sharedPreferences.edit();
//                editor.putString("username", username);
//                editor.putString("password", password);
//                editor.apply();
//
//                Toast.makeText(registerActivity.this, "Đăng ký thành công!", Toast.LENGTH_SHORT).show();
//
//                // Truyền dữ liệu sang login để điền sẵn
//                Intent intent = new Intent(registerActivity.this, loginActivity.class);
//                intent.putExtra("username", username);
//                intent.putExtra("password", password);
//                startActivity(intent);
//                finish();
//            }
//        });
//    }
//}
package com.example.buiviet_2123110186;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class registerActivity extends AppCompatActivity {

    EditText edtEmail, edtUsername, edtPassword;
    Button btnSignup;

    // Thay endpoint dưới đây nếu bạn dùng mock khác
    private static final String REGISTER_URL = "https://68726d5e76a5723aacd4a9e8.mockapi.io/buiviet/api/v1/users";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        edtEmail = findViewById(R.id.txtEmail);
        edtUsername = findViewById(R.id.txtUse);
        edtPassword = findViewById(R.id.txtPasswordd);
        btnSignup = findViewById(R.id.btnSignup);

        btnSignup.setOnClickListener(v -> {
            String email = edtEmail.getText().toString().trim();
            String username = edtUsername.getText().toString().trim();
            String password = edtPassword.getText().toString().trim();

            if (email.isEmpty() || username.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                return;
            }

            registerWithAPI(email, username, password);
        });
    }

    private void registerWithAPI(String email, String username, String password) {
        RequestQueue queue = Volley.newRequestQueue(this);

        JSONObject jsonBody = new JSONObject();
        try {
            jsonBody.put("email", email);
            jsonBody.put("username", username);
            jsonBody.put("password", password);
        } catch (JSONException e) {
            e.printStackTrace();
            Toast.makeText(this, "Lỗi tạo JSON!", Toast.LENGTH_SHORT).show();
            return;
        }

        JsonObjectRequest request = new JsonObjectRequest(
                Request.Method.POST,
                REGISTER_URL,
                jsonBody,
                response -> {
                    Toast.makeText(this, "Đăng ký thành công!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(registerActivity.this, loginActivity.class);
                    intent.putExtra("username", username);
                    intent.putExtra("password", password);
                    startActivity(intent);
                    finish();
                },
                error -> {
                    Toast.makeText(this, "Lỗi khi đăng ký: " + error.toString(), Toast.LENGTH_SHORT).show();
                }
        );

        queue.add(request);
    }
}

