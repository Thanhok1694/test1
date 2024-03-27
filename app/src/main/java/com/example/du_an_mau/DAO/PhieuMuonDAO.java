package com.example.du_an_mau.DAO;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.du_an_mau.DBHelper.DBHelper;
import com.example.du_an_mau.Model.LoaiSachModel;
import com.example.du_an_mau.Model.PhieuMuonModel;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class PhieuMuonDAO {
    private DBHelper mydb;
    private SQLiteDatabase db;

    public PhieuMuonDAO(Context context){
        mydb=new DBHelper(context);
        db=mydb.getWritableDatabase();
    }
    public long insert(PhieuMuonModel phieumuon){
        ContentValues values=new ContentValues();
        values.put("maTT",phieumuon.maTT);
        values.put("maTV",phieumuon.maTV);
        values.put("maSach",phieumuon.maSach);
        values.put("tienThue",phieumuon.tienThue);
        values.put("traSach",phieumuon.traSach);
        values.put("ngay",phieumuon.ngay);
        return db.insert("PhieuMuon",null,values);
    }
    public int update(PhieuMuonModel phieumuon){
        ContentValues values=new ContentValues();
        values.put("maTT",phieumuon.maTT);
        values.put("maTV",phieumuon.maTV);
        values.put("maSach",phieumuon.maSach);
        values.put("tienThue",phieumuon.tienThue);
        values.put("traSach",phieumuon.traSach);
        values.put("ngay", phieumuon.ngay);
        String[] dieukien=new String[]{String.valueOf(phieumuon.maPM)};
        return db.update("PhieuMuon",values,"maPM=?",dieukien);
    }
    public int delete(String id){

        return db.delete("PhieuMuon","maPM=?",new String[]{id});
    }
    public ArrayList<PhieuMuonModel> getList(){
        String sql="select * from PhieuMuon";
        return getData(sql);
    }
    @SuppressLint("Range")
    private ArrayList<PhieuMuonModel> getData(String sql, String...selectionArgs){
        ArrayList<PhieuMuonModel> list=new ArrayList<>();
        Cursor c=db.rawQuery(sql,selectionArgs);
        while(c.moveToNext()){
            PhieuMuonModel phieumuon=new PhieuMuonModel();
            phieumuon.maPM=Integer.parseInt(c.getString(c.getColumnIndex("maPM")));
            phieumuon.maTT=c.getString(c.getColumnIndex("maTT"));
            phieumuon.maTV=Integer.parseInt(c.getString(c.getColumnIndex("maTV")));
            phieumuon.maSach=Integer.parseInt(c.getString(c.getColumnIndex("maSach")));
            phieumuon.tienThue=Integer.parseInt(c.getString(c.getColumnIndex("tienThue")));
            phieumuon.traSach=Integer.parseInt(c.getString(c.getColumnIndex("traSach")));
            phieumuon.ngay= c.getString(c.getColumnIndex("ngay"));
            list.add(phieumuon);
        }
        return list;
    }
    public PhieuMuonModel getID(String id){
        String sql="select * from PhieuMuon where maPM=?";
        ArrayList<PhieuMuonModel> list=getData(sql,id);
        return list.get(0);
    }
}
