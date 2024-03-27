package com.example.du_an_mau.Model;

public class ThanhVienModel {
    public int maTV;
    public String hoTen;
    public String namSinh;


    public ThanhVienModel() {
    }

    public ThanhVienModel(String hoTen, String namSinh) {
        this.hoTen = hoTen;
        this.namSinh = namSinh;

    }

    public ThanhVienModel(int maTV, String hoTen, String namSinh) {
        this.maTV = maTV;
        this.hoTen = hoTen;
        this.namSinh = namSinh;

    }

    public int getMaTV() {
        return maTV;
    }

    public void setMaTV(int maTV) {
        this.maTV = maTV;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public String getNamSinh() {
        return namSinh;
    }

    public void setNamSinh(String namSinh) {
        this.namSinh = namSinh;
    }

  
}

