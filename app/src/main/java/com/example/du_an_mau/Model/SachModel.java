package com.example.du_an_mau.Model;

public class SachModel {
    public int maSach;
    public  String tenSach;
    public int giaThue;

    public int maLoai;

    public SachModel() {
    }

    public SachModel(String tenSach, int giaThue, String tenLoai, int maLoai) {
        this.tenSach = tenSach;
        this.giaThue = giaThue;

        this.maLoai = maLoai;
    }

    public SachModel(int maSach, String tenSach, int giaThue, String tenLoai, int maLoai) {
        this.maSach = maSach;
        this.tenSach = tenSach;
        this.giaThue = giaThue;

        this.maLoai = maLoai;
    }

    public int getMaSach() {
        return maSach;
    }

    public void setMaSach(int maSach) {
        this.maSach = maSach;
    }

    public String getTenSach() {
        return tenSach;
    }

    public void setTenSach(String tenSach) {
        this.tenSach = tenSach;
    }

    public int getGiaThue() {
        return giaThue;
    }

    public void setGiaThue(int giaThue) {
        this.giaThue = giaThue;
    }



    public int getMaLoai() {
        return maLoai;
    }

    public void setMaLoai(int maLoai) {
        this.maLoai = maLoai;
    }
}
