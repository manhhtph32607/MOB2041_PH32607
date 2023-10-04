package com.example.mob2041_ph32607.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;


import com.example.mob2041_ph32607.DAO.SachDAO;
import com.example.mob2041_ph32607.DAO.ThanhVienDAO;
import com.example.mob2041_ph32607.Fragment.Frag_QLPhieuMuon;
import com.example.mob2041_ph32607.Model.PhieuMuon;
import com.example.mob2041_ph32607.Model.Sach;
import com.example.mob2041_ph32607.Model.ThanhVien;
import com.example.mob2041_ph32607.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class PhieuMuonAdapter extends ArrayAdapter< PhieuMuon > {
    private Context context;
    Frag_QLPhieuMuon fragment;
    private ArrayList<PhieuMuon> list;
    TextView tvMaPM, tvTenTV, tvTenSach, tvTienThue, tvNgay, tvTraSach;
    ImageView imgDel;
    SachDAO sachDAO;
    ThanhVienDAO thanhVienDAO;
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    public PhieuMuonAdapter(@NonNull Context context, Frag_QLPhieuMuon fragment, ArrayList<PhieuMuon> list) {
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
            v = inflater.inflate(R.layout.item_lv_phieumuon, null);
        }
        final PhieuMuon item = list.get(position);
        if (item != null) {
            tvMaPM = v.findViewById(R.id.tvMaPhieu);
            tvMaPM.setText("Mã phiếu: "+item.getMaPM());
            sachDAO = new SachDAO(context);
            Sach sach = sachDAO.getID (String.valueOf(item.getMaSach()));
            tvTenSach = v.findViewById(R.id.tvTenSach);
            tvTenSach.setText("Tên sách: "+sach.getTenSach());
            thanhVienDAO = new ThanhVienDAO(context);
            ThanhVien thanhVien = thanhVienDAO.getID (String.valueOf(item.getMaTV()));
            tvTenTV = v.findViewById(R.id.tvThanhVien);
            tvTenTV.setText("Thành viên: "+thanhVien.getHoTen());
            tvTienThue = v.findViewById(R.id.tvTienThue);
            tvTienThue.setText("Tiền thuê: "+item.getTienThue());
            tvNgay = v.findViewById(R.id.tvNgayThue);
            tvNgay.setText("Ngày thuê: "+sdf.format(item.getNgay()));
            tvTraSach = v.findViewById(R.id.tvTrangThai);
            if (item.getTraSach() == 1){
                tvTraSach.setTextColor(Color.BLUE);
                tvTraSach.setText("Đã trả sách");
            }else {
                tvTraSach.setTextColor(Color.RED);
                tvTraSach.setText("Chưa trả sách");
            }
            imgDel = v.findViewById(R.id.btn_remove);
        }
        imgDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // gọi phương thức xóa
                fragment.xoa(String.valueOf(item.getMaPM()));
            }
        });
        return v;
    }

}
