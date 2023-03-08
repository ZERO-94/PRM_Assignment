package Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;

import Contexts.AuthContext;
import Contexts.CartContext;
import Models.CartItem;
import Utils.DownloadImageTask;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.ViewHolder> {

    Context context;

    public CartAdapter(Context context) {
        System.out.println(CartContext.size());
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.cart_item, parent, false);
        return new CartAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        CartItem item = CartContext.get(position);
        holder.nameTextView.setText(item.getName());
        holder.codeTextView.setText(item.getCode());
        holder.removeButton.setOnClickListener(v -> {
            CartContext.removeItem(item.getCode());
            this.notifyDataSetChanged();
        });
        new DownloadImageTask(holder.imageView)
                .execute(item.getImageUrl());
    }

    @Override
    public int getItemCount() {
        return CartContext.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView codeTextView, nameTextView;
        ImageView imageView;
        Button removeButton;

        public ViewHolder(View view) {
            super(view);
            codeTextView = view.findViewById(R.id.cartCodeTextView);
            nameTextView = view.findViewById(R.id.cartNameTextView);
            imageView = view.findViewById(R.id.cartImageView);
            removeButton = view.findViewById(R.id.removeButton);
        }
    }
}
