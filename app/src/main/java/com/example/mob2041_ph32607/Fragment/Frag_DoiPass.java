package com.example.mob2041_ph32607.Fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;


import com.example.mob2041_ph32607.DAO.ThuThuDAO;
import com.example.mob2041_ph32607.Model.ThuThu;
import com.example.mob2041_ph32607.R;
import com.google.android.material.textfield.TextInputEditText;

public class Frag_DoiPass extends Fragment {
    TextInputEditText edPassOld, edPass, edRePass;
    Button btnSave, btnCancel;
    ThuThuDAO dao;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_doimk, container, false);

        dao = new ThuThuDAO(getActivity());
        edPassOld = view.findViewById(R.id.edMKCu);
        edPass = view.findViewById(R.id.edMKMoi);
        edRePass = view.findViewById(R.id.edNLMKU);
        btnSave = view.findViewById(R.id.btnLuu);
        btnCancel = view.findViewById(R.id.btnHuy);

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                edPassOld.setText("");
                edPass.setText("");
                edRePass.setText("");
            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences pref = getActivity().getSharedPreferences ("USER_FILE", Context.MODE_PRIVATE);
                String user = pref.getString("USERNAME","");
                if (validate()>0){
                    ThuThu thuThu = dao.getID(user);
                    thuThu.setMatKhau(edPass.getText().toString());
                    if (dao.updatePass (thuThu) > 0) {
                        Toast.makeText(getActivity(),"Thay đổi mật khẩu thành công", Toast.LENGTH_SHORT).show();
                        edPassOld.setText("");
                        edPass.setText("");
                        edRePass.setText("");
                    } else {
                        Toast.makeText(getActivity(),"Thay đổi mật khẩu thất bại", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        return view;

    }

    public int validate() {
        int check = 1;
        if (edPassOld.getText().length() == 0 || edPass.getText().length() == 0 || edRePass.getText().length() == 0) {
            Toast.makeText(getContext(), "Bạn phải nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
            check = -1;
        } else {
// doc user, pass trong SharedPreferences
            SharedPreferences pref = getActivity().getSharedPreferences("USER_FILE", Context.MODE_PRIVATE);
            String pass0ld = pref.getString("PASSWORD", "");
            String pass = edPass.getText().toString();
            String rePass = edRePass.getText().toString();

            if (!pass0ld.equals (edPassOld.getText().toString())){
                Toast.makeText(getContext(),"Mật khẩu cũ sai", Toast.LENGTH_SHORT).show();
                check = -1;
            } else if (!pass.equals(rePass)){
                Toast.makeText(getContext(),"Mật khẩu không trùng khớp", Toast.LENGTH_SHORT).show();
                check = -1;
            }
        }
        return check;
    }
}
