package com.example.buiviet_2123110186;

import android.app.AlertDialog;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class paymentActivity extends AppCompatActivity {

    TextView totalAmountTextView, orderInfoTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        // Edge to edge
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Ánh xạ view
        totalAmountTextView = findViewById(R.id.totalAmount);
        orderInfoTextView = findViewById(R.id.orderInfo);

        // Nhận dữ liệu từ Intent (List<Product>)
        ArrayList<Product> cartList = (ArrayList<Product>) getIntent().getSerializableExtra("cartList");

        if (cartList != null && !cartList.isEmpty()) {
            int total = 0;
            StringBuilder orderInfo = new StringBuilder();

            for (Product item : cartList) {
                // Chuyển đổi giá từ String -> int
                String priceStr = item.getPrice().replace("₫", "").replace(".", "").trim();
                int price = 0;
                try {
                    price = Integer.parseInt(priceStr);
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }

                total += price;

                orderInfo.append("- ")
                        .append(item.getName())
                        .append(" (").append(String.format("%,d₫", price).replace(",", ".")).append(")\n");
            }

            String formattedTotal = String.format("%,d₫", total).replace(",", ".");
            totalAmountTextView.setText("Tổng tiền: " + formattedTotal);
            orderInfoTextView.setText("Sản phẩm:\n" + orderInfo.toString());

            // 👉 Gọi phương thức chọn thanh toán
            showPaymentMethods();

        } else {
            totalAmountTextView.setText("Không có sản phẩm");
            orderInfoTextView.setText("");
        }
    }

    // Hiển thị các phương thức thanh toán
    private void showPaymentMethods() {
        String[] methods = {"Ví Momo", "ZaloPay", "Thẻ ATM", "Thanh toán khi nhận hàng (COD)", "Quét mã QR"};

        new AlertDialog.Builder(this)
                .setTitle("Chọn phương thức thanh toán")
                .setItems(methods, (dialog, which) -> {
                    switch (which) {
                        case 0:
                        case 1:
                        case 2:
                        case 3:
                            String selectedMethod = methods[which];
                            Toast.makeText(this, "Đã chọn: " + selectedMethod, Toast.LENGTH_LONG).show();
                            finish(); // Kết thúc activity
                            break;
                        case 4:
                            showQRCodeDialog(); // Hiển thị mã QR
                            break;
                    }
                })
                .setNegativeButton("Huỷ", null)
                .show();
    }

    // Hiển thị mã QR (dạng ảnh)
    private void showQRCodeDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Mã QR thanh toán");

        ImageView qrImage = new ImageView(this);
        qrImage.setImageResource(R.drawable.q_r); // 🖼️ Đặt ảnh mã QR vào res/drawable/
        qrImage.setPadding(50, 50, 50, 50);

        builder.setView(qrImage);
        builder.setPositiveButton("Đã thanh toán", (dialog, which) -> {
            Toast.makeText(this, "Thanh toán thành công!", Toast.LENGTH_LONG).show();
            finish(); // Hoàn tất
        });
        builder.setNegativeButton("Huỷ", null);
        builder.show();
    }
}
