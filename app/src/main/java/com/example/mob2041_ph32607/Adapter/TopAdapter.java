package com.example.mob2041_ph32607.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;


import com.example.mob2041_ph32607.Fragment.Frag_Top10;
import com.example.mob2041_ph32607.Model.Top;
import com.example.mob2041_ph32607.R;

import java.util.ArrayList;

public class TopAdapter extends ArrayAdapter< Top > {
    private ArrayList<Top> list;
    private Context context;
    Frag_Top10 fragment;
    TextView tvSach, tvSoLuong;
    public TopAdapter(@NonNull Context context, Frag_Top10 fragment, ArrayList<Top> list) {
        super(context, 0, list);
        this.list = list;
        this.context = context;
        this.fragment = fragment;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v = convertView;
        if (v == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.item_lv_top, null);
        }
        final Top item = list.get(position);
        if (item != null) {
            tvSach = v.findViewById(R.id.tvSach);
            tvSach.setText("Sách: " + item.getTenSach());
            tvSoLuong = v.findViewById(R.id.tvSoLuong);
            tvSoLuong.setText("Số lượng: " + item.getSoLuong());
        }
        return v;
    }
}
