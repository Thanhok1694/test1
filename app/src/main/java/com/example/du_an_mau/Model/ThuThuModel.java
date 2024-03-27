package com.example.du_an_mau.Model;

public class ThuThuModel {

    public String maTT;
    public String hoTen_tt;
    public String matKhau;

    public ThuThuModel() {
    }

    public ThuThuModel(String maTT, String hoTen, String matKhau) {
        this.maTT = maTT;
        this.hoTen_tt = hoTen;
        this.matKhau = matKhau;
    }

    public String getMaTT() {
        return maTT;
    }

    public void setMaTT(String maTT) {
        this.maTT = maTT;
    }

    public String getHoTen() {
        return hoTen_tt;
    }

    public void setHoTen(String hoTen) {
        this.hoTen_tt = hoTen;
    }

    public String getMatKhau() {
        return matKhau;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }
}
