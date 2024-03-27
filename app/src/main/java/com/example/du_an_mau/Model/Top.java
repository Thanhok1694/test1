package com.example.du_an_mau.Model;

public class Top {
    public String tensach;
    public int soluong;

    public Top() {
    }

    public Top(String tensach, int soluong) {
        this.tensach = tensach;
        this.soluong = soluong;
    }

    public String getTensach() {
        return tensach;
    }

    public void setTensach(String tensach) {
        this.tensach = tensach;
    }

    public int getSoluong() {
        return soluong;
    }

    public void setSoluong(int soluong) {
        this.soluong = soluong;
    }
}
