package com.example.du_an_mau.Model;

public class LoaiSachModel {
    public int maLoai;
    public String tenLoai;

    public LoaiSachModel() {
    }

    public LoaiSachModel(String tenLoai) {
        this.tenLoai = tenLoai;
    }

    public LoaiSachModel(int maLoai, String tenLoai) {
        this.maLoai = maLoai;
        this.tenLoai = tenLoai;
    }

    public int getMaLoai() {
        return maLoai;
    }

    public void setMaLoai(int maLoai) {
        this.maLoai = maLoai;
    }

    public String getTenLoai() {
        return tenLoai;
    }

    public void setTenLoai(String tenLoai) {
        this.tenLoai = tenLoai;
    }
}
