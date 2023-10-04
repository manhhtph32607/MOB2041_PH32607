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

import com.example.mob2041_ph32607.Fragment.Frag_QLThanhVien;
import com.example.mob2041_ph32607.Model.ThanhVien;
import com.example.mob2041_ph32607.R;

import java.util.ArrayList;

public class ThanhVienAdapter extends ArrayAdapter< ThanhVien > {

    private ArrayList<ThanhVien> list;
    private Context context;
    Frag_QLThanhVien fragment;
    TextView tvMaTv, tvTenTv, tvNamSinh;
    ImageView imgDel;

    public ThanhVienAdapter(@NonNull Context context, Frag_QLThanhVien fragment, ArrayList<ThanhVien> list) {
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
            v = inflater.inflate(R.layout.item_lv_thanhvien, null);
        }
        final ThanhVien item = list.get(position);
        if (item != null) {
            tvMaTv = v.findViewById(R.id.tvMaTv);
            tvMaTv.setText("Mã thành viên: " + item.getMaTV());
            tvTenTv = v.findViewById(R.id.tvTenTv);
            tvTenTv.setText("Tên thành viên: " + item.getHoTen());
            tvNamSinh = v.findViewById(R.id.tvNamSinh);
            tvNamSinh.setText("Năm sinh: " + item.getNamSinh());
            imgDel = v.findViewById(R.id.btn_remove);
        }
        imgDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // gọi phương thức xóa
                fragment.xoa(String.valueOf(item.getMaTV()));
            }
        });
        return v;
    }


}
