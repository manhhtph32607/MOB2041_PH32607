package com.example.mob2041_ph32607.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;


import com.example.mob2041_ph32607.Fragment.Frag_QLLoaiSach;
import com.example.mob2041_ph32607.Model.LoaiSach;
import com.example.mob2041_ph32607.R;

import java.util.ArrayList;


public class LoaiSachAdapter extends ArrayAdapter< LoaiSach > {
    private ArrayList<LoaiSach> list;
    private Context context;
    Frag_QLLoaiSach fragment;
    TextView tvMaLoai, tvTenLoai;
    ImageView imgDel;

    public LoaiSachAdapter(@NonNull Context context, Frag_QLLoaiSach fragment, ArrayList<LoaiSach> list) {
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
            v = inflater.inflate(R.layout.item_lv_loaisach, null);
        }
        final LoaiSach item = list.get(position);
        if (item != null) {
            tvMaLoai = v.findViewById(R.id.tvMaLoaiSach);
            tvMaLoai.setText("Mã loại: " + item.getMaLoai());
            tvTenLoai = v.findViewById(R.id.tvTenLoaiSach);
            tvTenLoai.setText("Tên loại: " + item.getTenLoai());
            imgDel = v.findViewById(R.id.btn_remove);
        }
        imgDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // gọi phương thức xóa
                fragment.xoa(String.valueOf(item.getMaLoai()));
            }
        });
        return v;
    }
}
