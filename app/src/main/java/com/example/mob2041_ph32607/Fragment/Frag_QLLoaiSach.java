package com.example.mob2041_ph32607.Fragment;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;


import com.example.mob2041_ph32607.Adapter.LoaiSachAdapter;
import com.example.mob2041_ph32607.DAO.LoaiSachDAO;
import com.example.mob2041_ph32607.Model.LoaiSach;
import com.example.mob2041_ph32607.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class Frag_QLLoaiSach extends Fragment {
    ListView lvLoaiSach;
    ArrayList< LoaiSach > list;
    FloatingActionButton fab;
    Dialog dialog;
    EditText edMaLoai, edTenLoai;
    Button btnSave, btnCancel;
    static LoaiSachDAO dao;
    LoaiSachAdapter adapter;
    LoaiSach item;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_qlloaisach, container, false);
        lvLoaiSach = v.findViewById(R.id.lvLoaiSach);
        fab = v.findViewById(R.id.fltAdd);
        dao = new LoaiSachDAO(getActivity());
        capNhatLoaiSach();

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDialog(getActivity(), 0);
                //them
            }
        });

        lvLoaiSach.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                item = list.get(i);
                openDialog(getActivity(), 1);
                //update
                return false;
            }
        });
        return v;
    }

    void capNhatLoaiSach() {
        list = (ArrayList<LoaiSach>) dao.getAll();
        adapter = new LoaiSachAdapter(getActivity(), this, list);
        lvLoaiSach.setAdapter(adapter);
    }

    public void xoa(final String Id) {
        // Su dung Alert
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Delete");
        builder.setIcon(R.drawable.thongbao);
        builder.setMessage("Bạn có muốn xóa không? ?");
        builder.setCancelable(true);
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dao.delete(Id);
                capNhatLoaiSach();
                dialog.cancel();
            }
        });

        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                dialog.cancel();
            }
        });

        AlertDialog alert = builder.create();
        builder.show();
    }

    protected void openDialog(final Context context, final int type) {
        //custom dialog
        dialog = new Dialog(context);
        dialog.setContentView(R.layout.dialog_loaisach);
        edMaLoai = dialog.findViewById(R.id.edtMaLoaiSach_itemAddLoaiSach);
        edTenLoai = dialog.findViewById(R.id.edtTenLoaiSach_itemAddLoaiSach);

        btnCancel = dialog.findViewById(R.id.btnHuy);
        btnSave = dialog.findViewById(R.id.btnLuu);
        //kiem tra type insert 0 hay Update 1
        edMaLoai.setEnabled(false);
        if (type != 0) {
            edMaLoai.setText(String.valueOf(item.getMaLoai()));
            edTenLoai.setText(item.getTenLoai());
        }

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                item = new LoaiSach();
                item.setTenLoai(edTenLoai.getText().toString());
                if (validate() > 0) {
                    if (type == 0) {
                        //type = 0 (insert)
                        if (dao.insert(item) > 0) {
                            Toast.makeText(context, "Thêm thành công", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(context, "Thêm thất bại", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        //type =1 (update)
                        item.setMaLoai(Integer.parseInt(edMaLoai.getText().toString()));
                        if (dao.update(item) > 0) {
                            Toast.makeText(context, "Sứa thành công", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(context, "Sứa thất bại", Toast.LENGTH_SHORT).show();
                        }
                    }
                    capNhatLoaiSach();
                    dialog.dismiss();
                }
            }
        });
        dialog.show();
    }

    public int validate() {
        int check = 1;
        if (edTenLoai.getText().length() == 0) {
            Toast.makeText(getContext(), "Bạn phải nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
            check = -1;
        }
        return check;
    }
}