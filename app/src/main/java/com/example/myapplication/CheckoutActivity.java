package com.example.myapplication;

import android.os.Bundle;
import android.widget.TextView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

import androidx.appcompat.app.AppCompatActivity;

public class CheckoutActivity extends AppCompatActivity {

    private TextView textViewSubtotal;
    private TextView textViewTotal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);




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
