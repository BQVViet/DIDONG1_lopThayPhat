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

public class registerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_register);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Button btnsignup = findViewById(R.id.btnSignup);

        btnsignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText objEam = findViewById(R.id.txtEmail);
                EditText objUse = findViewById(R.id.txtUsenamee);
                EditText objPass = findViewById(R.id.txtPassword);
                EditText objNBPass = findViewById(R.id.txtNBPassword);

                String txtEmail=objEam.getText().toString();
                String txtUsenamee=objUse.getText().toString();

                String txtPassword=objPass.getText().toString();
                String txtNBPassword=objNBPass.getText().toString();
                if(txtEmail.equals("viet123@email.com") && txtUsenamee.equals("viet")
                        && txtPassword.equals("123456")&& txtNBPassword.equals("123456")){
                    //neu dung thong tin
                    Intent it =new Intent(getApplicationContext(), loginActivity.class);
                    startActivity(it);
                }
                else{
                    // thong bao sai thong tin dang nhap
                    Toast.makeText(getApplicationContext(), "Login Fail", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}