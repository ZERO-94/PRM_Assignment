package Adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;

import java.util.ArrayList;

import Dao.InstrumentDao;
import Models.Instrument;

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
        viewHolder.textView_name.setText(instrument.getName());
        viewHolder.imageView.setImageResource(R.drawable.guitar);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView_name;
        ImageView imageView;

        public ViewHolder(View itemView) {
            super(itemView);

            textView_name = itemView.findViewById(R.id.textView_name);
            imageView = itemView.findViewById(R.id.imageView);
        }
    }
}
