package com.example.buiquocviet_2123110186;

import android.content.Intent;
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




        Button btnbutton = findViewById(R.id.btnLogin);

        btnbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText objUse = findViewById(R.id.txtUsename);
                EditText objPass = findViewById(R.id.txtPassword);

                String txtUse=objUse.getText().toString();
                String txtPassword=objPass.getText().toString();
                if(txtUse.equals("viet") && txtPassword.equals("123")){
                    //neu dung thong tin
                    Intent it =new Intent(getApplicationContext(), registerActivity.class);
                    startActivity(it);
                    Toast.makeText(getApplicationContext(), "Đăng nhập thành công.", Toast.LENGTH_SHORT).show();
                }
                else{
                    // thong bao sai thong tin dang nhap
                    Toast.makeText(getApplicationContext(), "Đăng nhập thất bại.", Toast.LENGTH_SHORT).show();
                }
            }
        });
        Button btnregister = findViewById(R.id.btnregister);
        btnregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), registerActivity.class);
                startActivity(intent);
            }
        });

    }

}