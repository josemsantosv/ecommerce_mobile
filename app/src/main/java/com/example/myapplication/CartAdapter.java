package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder> {

    private List<Product> cartProductList;
    private Context context;
    private OnItemRemovedListener onItemRemovedListener;


    public CartAdapter(List<Product> cartProductList, Context context, OnItemRemovedListener onItemRemovedListener) {
        this.cartProductList = cartProductList;
        this.context = context;
        this.onItemRemovedListener = onItemRemovedListener;
    }

    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_cart, parent, false);
        return new CartViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull CartViewHolder holder, int position) {

        Product product = cartProductList.get(position);
        holder.textViewName.setText(product.getName());
        holder.textViewPrice.setText("$" + String.format("%.2f", product.getPrice()));

        int imageResId = context.getResources().getIdentifier(product.getImage(), "drawable", context.getPackageName());
        if (imageResId != 0) {
            holder.imageView.setImageResource(imageResId);
        } else {
            holder.imageView.setImageResource(R.drawable.product_five); // Imagen por defecto
        }

        holder.buttonRemove.setOnClickListener(v -> {
            cartProductList.remove(position);
            notifyItemRemoved(position);
            notifyItemRangeChanged(position, cartProductList.size());
            if (onItemRemovedListener != null) {
                onItemRemovedListener.onItemRemoved();
            }
        });
    }

    @Override
    public int getItemCount() {
        return cartProductList.size();
    }

    public interface OnItemRemovedListener {
        void onItemRemoved();
    }

    public static class CartViewHolder extends RecyclerView.ViewHolder {

        TextView textViewName, textViewPrice;
        ImageView imageView;
        Button buttonRemove;

        public CartViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewName = itemView.findViewById(R.id.textViewName);
            textViewPrice = itemView.findViewById(R.id.textViewPrice);
            imageView = itemView.findViewById(R.id.imageViewProduct);
            buttonRemove = itemView.findViewById(R.id.buttonRemove);
        }
    }
}
