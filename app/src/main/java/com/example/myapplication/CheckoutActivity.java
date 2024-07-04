package com.example.myapplication;

import android.os.Bundle;
import android.widget.TextView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;
import androidx.appcompat.app.AppCompatActivity;

public class CheckoutActivity extends AppCompatActivity {

    private TextView textViewSubtotal;
    private TextView textViewTotal;
    private RecyclerView recyclerViewCheckout;
    private CartAdapter cartAdapter;
    private List<Product> cartItems;
    private Button backButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);

        backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //
                Intent intent = new Intent(CheckoutActivity.this, CartActivity.class);
                startActivity(intent);
                finish();
            }
        });

        Button confirmButton = findViewById(R.id.confirmPurchaseButton);
        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navegar a la pantalla de agradecimiento
                Intent intent = new Intent(CheckoutActivity.this, ThankYouActivity.class);
                startActivity(intent);
                finish();


            }
        });

        // Inicializar vistas
        textViewSubtotal = findViewById(R.id.tsubtotal);
        textViewTotal = findViewById(R.id.ttotal);

        // Obtener el total pasado desde CartActivity
        double total = getIntent().getDoubleExtra("total", 0.0);

        // Mostrar el total y subtotal
        textViewSubtotal.setText("Subtotal: $" + String.format("%.2f", total-(0.18*total)));
        textViewTotal.setText("Total: $" + String.format("%.2f", total));
    }
}
