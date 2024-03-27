package com.example.du_an_mau.DBHelper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {
    public DBHelper(@Nullable Context context) {
        super(context, "QLTV",null,46);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String tb_thuthu="create table ThuThu (maTT text primary key ,hoTen text not null,matKhau text not null)";
        db.execSQL(tb_thuthu);
        String data_thuthu="insert into ThuThu(maTT,hoTen,matKhau) values ('tt01','Nguyễn Đức Thành','123'),('admin','Nguyễn Khánh Duy','169')";
        db.execSQL(data_thuthu);

        String tb_thanhvien="create table ThanhVien(maTV integer primary key autoincrement,hoTen text not null,namSinh text not null)";
        db.execSQL(tb_thanhvien);
        String data_thanhvien="insert into ThanhVien(maTV,hoTen,namSinh) values (1,'Nguyễn Đức Thành','2004'),(2,'Lý Nhật Minh','2006')";
        db.execSQL(data_thanhvien);

        String tb_phieumuon="create table PhieuMuon(maPM integer primary key autoincrement, maTT text ,maTV integer not null,maSach integer not null,tienThue integer not null,traSach integer not null,ngay Date not null,foreign key (maTT) references ThuThu(maTT),foreign key (maTV) references ThanhVien(maTV),foreign key (maSach) references Sach(maSach))";
        db.execSQL(tb_phieumuon);
        String data_phieumuon="insert into PhieuMuon(maPM,maTT,maTV,maSach,tienThue,traSach,ngay) values (1,'tt01',1,1,3000,1,'16-9-2023')";
        db.execSQL(data_phieumuon);

        String tb_loaisach="create table LoaiSach(maLoai integer primary key autoincrement,tenLoai text not null)";
        db.execSQL(tb_loaisach);
        String data_loaisach="insert into LoaiSach(maLoai,tenLoai) values (1,'CNTT')";
        db.execSQL(data_loaisach);

        String tb_sach="create table Sach(maSach integer primary key autoincrement, tenSach text not null,giaThue integer not null,maLoai integer not null, foreign key (maLoai) references LoaiSach(maLoai))";
        db.execSQL(tb_sach);
        String data_sach="insert into Sach(maSach,tenSach,giaThue,maLoai) values (1,'Java1',3000,1)";
        db.execSQL(data_sach);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
if(newVersion!=oldVersion){
    db.execSQL("drop table if exists ThuThu");
    db.execSQL("drop table if exists LoaiSach");
    db.execSQL("drop table if exists Sach");
    db.execSQL("drop table if exists ThanhVien");
    db.execSQL("drop table if exists PhieuMuon");
    onCreate(db);

}
    }
}
