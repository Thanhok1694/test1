package com.example.du_an_mau.DAO;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.du_an_mau.DBHelper.DBHelper;
import com.example.du_an_mau.Model.SachModel;
import com.example.du_an_mau.Model.Top;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class ThongKeDAO {
    private SQLiteDatabase db;
    private Context context;
    SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
    public ThongKeDAO(Context context){
        this.context=context;
        DBHelper dbHelper=new DBHelper(context);
        db=dbHelper.getWritableDatabase();
    }
    @SuppressLint("Range")
    public ArrayList<Top> getTop(){
        String sqlTop="select maSach,count(maSach) as soLuong from PhieuMuon group by maSach order by soLuong desc limit 10";
        ArrayList<Top> list=new ArrayList<Top>();
        SachDAO sachdao=new SachDAO(context);
        Cursor c=db.rawQuery(sqlTop,null);
        while(c.moveToNext()){
            Top top=new Top();
            @SuppressLint("Range") SachModel sach=sachdao.getID(c.getString(c.getColumnIndex("maSach")));
            top.tensach=sach.tenSach;
            top.soluong=Integer.parseInt(c.getString(c.getColumnIndex("soLuong")));
            list.add(top);
        }
        return list;
    }
    @SuppressLint("Range")
    public int getDoanhthu(String tuNgay, String denNgay){
        String sqlDoanhthu="select sum(tienThue) as doanhThu from PhieuMuon where ngay between ? and ?";
        ArrayList<Integer> list=new ArrayList<>();
        Cursor c=db.rawQuery(sqlDoanhthu,new String[]{tuNgay,denNgay});
        while(c.moveToNext()){
            try{
                list.add(Integer.parseInt(c.getString(c.getColumnIndex("doanhThu"))));

            }catch (Exception e){
                list.add(0);
            }

        }
        return list.get(0);
    }
}
