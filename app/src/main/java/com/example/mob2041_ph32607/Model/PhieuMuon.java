package com.example.mob2041_ph32607.Model;

import java.util.Date;

public class PhieuMuon {
    private int maPM;
    private String maTT;
    private int maTV;
    private int maSach;
    private int tienThue;
    private Date Ngay;
    private int traSach;

    public PhieuMuon() {
    }

    public PhieuMuon(int maPM, int maTV, String maTT, int maSach, Date ngay, int traSach, int tienThue) {
        this.maPM = maPM;
        this.maTV = maTV;
        this.maTT = maTT;
        this.maSach = maSach;
        Ngay = ngay;
        this.tienThue = tienThue;
        this.traSach = traSach;
    }

    public int getMaPM() {
        return maPM;
    }

    public void setMaPM(int maPM) {
        this.maPM = maPM;
    }

    public String getMaTT() {
        return maTT;
    }

    public void setMaTT(String maTT) {
        this.maTT = maTT;
    }

    public int getMaTV() {
        return maTV;
    }

    public void setMaTV(int maTV) {
        this.maTV = maTV;
    }

    public int getMaSach() {
        return maSach;
    }

    public void setMaSach(int maSach) {
        this.maSach = maSach;
    }

    public int getTienThue() {
        return tienThue;
    }

    public void setTienThue(int tienThue) {
        this.tienThue = tienThue;
    }

    public Date getNgay() {
        return Ngay;
    }

    public void setNgay(Date ngay) {
        Ngay = ngay;
    }

    public int getTraSach() {
        return traSach;
    }

    public void setTraSach(int traSach) {
        this.traSach = traSach;
    }
}
