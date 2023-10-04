package com.example.mob2041_ph32607.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;


import com.example.mob2041_ph32607.Model.LoaiSach;
import com.example.mob2041_ph32607.R;

import java.util.ArrayList;

public class LoaiSachSpinnerAdapter extends ArrayAdapter< LoaiSach > {
    private ArrayList<LoaiSach> list;
    private Context context;
    TextView tvMaLoai, tvTenLoai;
    public LoaiSachSpinnerAdapter(@NonNull Context context, ArrayList<LoaiSach> list) {
        super(context, 0, list);
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v = convertView;
        if (v == null) {
            LayoutInflater inflater = (LayoutInflater)
                    context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.item_loaisach_spinner,null);
        }
        final LoaiSach item = list.get(position);
        if (item != null) {
            tvMaLoai = v.findViewById(R.id.tvMaLoaiSachSp);
            tvMaLoai.setText(item.getMaLoai()+". ");
            tvTenLoai = v.findViewById(R.id.tvTenLoaiSachSp);
            tvTenLoai.setText(item.getTenLoai());

        }
        return v;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v = convertView;
        if (v == null) {
            LayoutInflater inflater = (LayoutInflater)
                    context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.item_loaisach_spinner,null);
        }
        final LoaiSach item = list.get(position);
        if (item != null) {
            tvMaLoai = v.findViewById(R.id.tvMaLoaiSachSp);
            tvMaLoai.setText(item.getMaLoai()+". ");
            tvTenLoai = v.findViewById(R.id.tvTenLoaiSachSp);
            tvTenLoai.setText(item.getTenLoai());

        }
        return  v;
    }
}
