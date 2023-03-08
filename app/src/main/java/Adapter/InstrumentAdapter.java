package Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.HomeActivity;
import com.example.myapplication.InstrumentDetail;
import com.example.myapplication.R;

import java.util.ArrayList;

import Contexts.AuthContext;
import Contexts.CartContext;
import Dao.InstrumentDao;
import Models.CartItem;
import Models.Instrument;
import Utils.DownloadImageTask;

public class InstrumentAdapter extends RecyclerView.Adapter<InstrumentAdapter.ViewHolder> {

    Context context;
    ArrayList<Instrument> data;
    InstrumentDao dao;

    public InstrumentAdapter(Context context, InstrumentDao dao) {
        this.context = context;
        this.dao = dao;
        data = dao.getAll();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.instrument_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        Instrument instrument = data.get(position);
        viewHolder.instrumentCode = instrument.getCode();
        viewHolder.textView_name.setText(instrument.getCode());
        new DownloadImageTask(viewHolder.imageView)
                .execute(instrument.getImageUrl());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        String instrumentCode;
        TextView textView_name;
        ImageView imageView;
        Button detailButton;

        public ViewHolder(View itemView) {
            super(itemView);
            textView_name = itemView.findViewById(R.id.textView_name);
            imageView = itemView.findViewById(R.id.imageView);
            detailButton = itemView.findViewById(R.id.viewDetailButton);
            if(AuthContext.getRole().equals("customer")) {
                detailButton.setText("Add to Cart");
                detailButton.setOnClickListener(v -> {
                    for(Instrument i: dao.getAll()) {
                        if(i.getCode().equals(instrumentCode)) {
                            CartItem item = new CartItem(instrumentCode, i.getName(), i.getImageUrl(), i.getPrice(), 1);
                            CartContext.addItem(item);
                        }
                    }

                });
            } else {
                detailButton.setText("View detail");
                detailButton.setOnClickListener(v -> {
                    Intent intent = new Intent(context, InstrumentDetail.class);
                    intent.putExtra("instrumentCode", instrumentCode);
                    context.startActivity(intent);
                });
            }
        }
    }
}
