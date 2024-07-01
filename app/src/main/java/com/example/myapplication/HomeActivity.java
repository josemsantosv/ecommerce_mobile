package com.example.myapplication;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {
//tttttttttttttttt
    private RecyclerView recyclerView;
    private CatalogAdapter adapter;
    private List<Product> productList;
    private DBHelper dbHelper;
    private Button buttonViewCart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        recyclerView = findViewById(R.id.recyclerView);
        buttonViewCart = findViewById(R.id.buttonViewCart);

        // Configuración del RecyclerView con GridLayoutManager para dos columnas
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));

        dbHelper = new DBHelper(this);
        loadProductsFromDatabase();

        adapter = new CatalogAdapter(productList, this);
        recyclerView.setAdapter(adapter);

        buttonViewCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, CartActivity.class);
                ArrayList<Product> selectedProducts = new ArrayList<>(adapter.getSelectedProducts());
                intent.putParcelableArrayListExtra("selectedProducts", selectedProducts);
                startActivity(intent);
            }
        });
    }

    private void loadProductsFromDatabase() {
        productList = new ArrayList<>();
        dbHelper = new DBHelper(this);
        Cursor cursor = dbHelper.getAllProducts();

        if (cursor != null) {
            while (cursor.moveToNext()) {
                int id = cursor.getInt(cursor.getColumnIndexOrThrow(DBHelper.PRODUCT_COLUMN_ID));
                String name = cursor.getString(cursor.getColumnIndexOrThrow(DBHelper.PRODUCT_COLUMN_NAME));
                double price = cursor.getDouble(cursor.getColumnIndexOrThrow(DBHelper.PRODUCT_COLUMN_PRICE));
                String image = cursor.getString(cursor.getColumnIndexOrThrow(DBHelper.PRODUCT_COLUMN_IMAGE));
                productList.add(new Product(id, name, price, image));
            }
            cursor.close();
        }
    }
}