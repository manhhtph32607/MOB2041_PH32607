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

import com.example.mob2041_ph32607.DAO.LoaiSachDAO;
import com.example.mob2041_ph32607.Fragment.Frag_Sach;
import com.example.mob2041_ph32607.Model.LoaiSach;
import com.example.mob2041_ph32607.Model.Sach;
import com.example.mob2041_ph32607.R;

import java.util.List;


public class SachAdapter extends ArrayAdapter< Sach > {

    Context context;
    Frag_Sach fragment;
    List <Sach> list;
    TextView tvMaSach, tvTenSach, tvGiaThue, tvLoai;
    ImageView imgDel;
    public SachAdapter(@NonNull Context context, Frag_Sach fragment, List<Sach> list) {
        super(context, 0, list);
        this.context = context;
        this.fragment = fragment;
        this.list = list;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v = convertView;
        if (v == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.item_lv_sach, null);
        }
        final Sach item = list.get(position);
        if (item != null) {
            LoaiSachDAO loaiSachDAO = new LoaiSachDAO(context);
            LoaiSach loaiSach = loaiSachDAO.getID(String.valueOf(item.getMaLoai()));

            tvMaSach = v.findViewById(R.id.tvMaSach);
            tvMaSach.setText("Mã sách: " +item.getMaSach());
            tvTenSach = v.findViewById(R.id.tvTenSach);
            tvTenSach.setText("Tên sách: "+item.getTenSach());
            tvGiaThue = v.findViewById(R.id.tvGiaThue);
            tvGiaThue.setText("Giá thuê: "+item.getGiaThue());
            tvLoai = v.findViewById(R.id.tvLoai);
            tvLoai.setText(" Loại sách: "+loaiSach.getTenLoai());
            imgDel = v.findViewById(R.id.imgDeleteS);
        }

        imgDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // gọi phương thức xóa
                fragment.xoa(String.valueOf(item.getMaSach()));
            }
        });
        return v;
    }

}
