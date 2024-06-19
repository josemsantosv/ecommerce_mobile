package com.example.myapplication;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class CartActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private TextView textViewTotal;
    private CartAdapter adapter;
    private List<Product> cartProductList;
    private double total;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        // Inicializar vistas
        recyclerView = findViewById(R.id.recyclerView);
        textViewTotal = findViewById(R.id.textViewTotal);

        // Configurar el RecyclerView
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Obtener la lista de productos seleccionados
        cartProductList = getIntent().getParcelableArrayListExtra("selectedProducts");

        // Configurar el adaptador del RecyclerView
        adapter = new CartAdapter(cartProductList, this);
        recyclerView.setAdapter(adapter);

        // Calcular el total
        calculateTotal();
    }

    private void calculateTotal() {
        total = 0;
        for (Product product : cartProductList) {
            total += product.getPrice();
        }
        textViewTotal.setText("$" + String.format("%.2f", total));
    }
}
