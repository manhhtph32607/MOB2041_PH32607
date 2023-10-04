package com.example.mob2041_ph32607;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


import com.example.mob2041_ph32607.DAO.ThuThuDAO;
import com.google.android.material.textfield.TextInputLayout;

public class LoginActivity extends AppCompatActivity {

    TextInputLayout edtUser, edtPass;
    Button btnLogin, btnHuy;
    ThuThuDAO thuThuDAO;
    CheckBox chkLuuTK;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edtUser = findViewById(R.id.edtUsername);
        edtPass = findViewById(R.id.edtPassword);
        btnLogin = findViewById(R.id.btnDangNhap);
        btnHuy = findViewById(R.id.btnHuyDN);
        chkLuuTK = findViewById(R.id.chkLuuTK);

        thuThuDAO = new ThuThuDAO(this);

        // Xử lý khi người dùng bắt đầu nhập tên đăng nhập
        edtUser.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                // Xóa cảnh báo khi người dùng bắt đầu nhập
                edtUser.setError(null);
            }
        });

        // Xử lý khi người dùng bắt đầu nhập mật khẩu
        edtPass.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                // Xóa cảnh báo khi người dùng bắt đầu nhập
                edtPass.setError(null);
            }
        });
        btnHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                edtUser.getEditText().setText("");
                edtPass.getEditText().setText("");
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = edtUser.getEditText().getText().toString().trim();
                String pass = edtPass.getEditText().getText().toString().trim();

                if (validateInput(user, pass)) {
                    if (thuThuDAO.checkLogin(user, pass)) {
                        // Đăng nhập thành công
                        SharedPreferences sharedPreferences = getSharedPreferences("ThongTin", MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString("maTT", user);
                        editor.apply();

                        Toast.makeText(LoginActivity.this, "Đăng Nhập Thành Công", Toast.LENGTH_SHORT).show();
                        rememberUser(user, pass, chkLuuTK.isChecked());
                        startActivity(new Intent(LoginActivity.this, MainActivity.class));
                        finish(); // Kết thúc activity sau khi đăng nhập thành công
                    } else {
                        Toast.makeText(LoginActivity.this, "Tên đăng nhập hoặc mật khẩu sai", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        // lưu tài khoản
        SharedPreferences pref = getSharedPreferences("USER_FILE", MODE_PRIVATE);
        String user = pref.getString("USERNAME", "");
        String pass = pref.getString("PASSWORD", "");
        Boolean rem = pref.getBoolean("REMEMBER", false);

        edtUser.getEditText().setText(user);
        edtPass.getEditText().setText(pass);
        chkLuuTK.setChecked(rem);

    }

    // Phương thức để validate TextInputLayout
    private boolean validateInput(String user, String pass) {
        boolean isValid = true;

        if (user.isEmpty()) {
            edtUser.setError("Nhập tên đăng nhập");
            isValid = false;
        } else {
            edtUser.setError(null);
        }

        if (pass.isEmpty()) {
            edtPass.setError("Nhập mật khẩu");
            isValid = false;
        } else {
            edtPass.setError(null);
        }

        return isValid;
    }

    private void rememberUser(String u, String p, boolean status) {
        SharedPreferences pref = getSharedPreferences("USER_FILE", MODE_PRIVATE);
        SharedPreferences.Editor edit = pref.edit();

        if (!status){
            // xóa tình trạng lưu trc đó
            edit.clear();
        } else {
            edit.putString("USERNAME", u);
            edit.putString("PASSWORD", p);
            edit.putBoolean("REMEMBER", status);
        }
        edit.commit();
    }
}
