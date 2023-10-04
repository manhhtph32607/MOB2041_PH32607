package com.example.mob2041_ph32607.DataBase;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DbHelper extends SQLiteOpenHelper {
    public static final String Db_name = "PNLIB";


    public DbHelper(Context context){
        super(context,Db_name,null,3);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // bảng thủ thư
        String dbThuThu = "create table ThuThu(maTT text primary key,hoTen text not null,matKhau text not null)";
        db.execSQL(dbThuThu);
        // bảng thành viên
        String dbThanhVien = "create table ThanhVien(maTV integer primary key autoincrement,hoTen text not null, namSinh text not null)";
        db.execSQL(dbThanhVien);
        // bảng loại sách
        String dbLoaiSach = "create table LoaiSach(maLoai integer primary key autoincrement, tenLoai text not null)";
        db.execSQL(dbLoaiSach);
        // bảng sách
        String dbSach = "create table Sach(maSach integer primary key autoincrement, tenSach text not null, giaThue integer not null, maLoai integer references LoaiSach(maLoai))";
        db.execSQL(dbSach);
        // bảng phiếu mượn
        String dbPhieuMuon = "create table PhieuMuon(maPM integer primary key autoincrement, maTV integer references ThanhVien(maTV), maTT text references ThuThu(maTT), maSach integer references Sach(maSach),ngay date not null,traSach integer not null, tienThue integer not null)";
        db.execSQL(dbPhieuMuon);
        // data mẫu
        db.execSQL("INSERT INTO LoaiSach VALUES (1, 'code thiếu nhi'),(2,'siêu nhân'),(3, 'Giáo khoa')");
        db.execSQL("INSERT INTO Sach VALUES (1, 'Hãy đợi đấy', 2500, 1), (2, 'Thằng cuội', 1000, 1), (3, 'Lập trình Android', 2000, 3)");
        db.execSQL("INSERT INTO ThuThu VALUES ('thuthu01','Hoàng Thế Mạnh','123'),('thuthu02','Trần Văn Huy','123')");
        db.execSQL("INSERT INTO ThanhVien VALUES (1,'Hoàng thế Mạnh','2000'),(2,'Trần Mỹ Kim','2000')");
        //trả sách: 1: đã trả - 0: chưa trả
        db.execSQL("INSERT INTO PhieuMuon VALUES (1,1,'thuthu01', 1, '2022/03/19', 1, 2500),(2,1,'thuthu01', 3, '2022/03/19', 0, 2000),(3,2,'thuthu02', 1, '2022/03/19', 1, 2000)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, int i, int i1) {
        if (i != i1) {
            database.execSQL("drop table if exists ThuThu");
            database.execSQL("drop table if exists ThanhVien");
            database.execSQL("drop table if exists LoaiSach");
            database.execSQL("drop table if exists Sach");
            database.execSQL("drop table if exists PhieuMuon");
            onCreate(database);
        }
    }
}
