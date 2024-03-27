package com.example.du_an_mau.DAO;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.du_an_mau.DBHelper.DBHelper;
import com.example.du_an_mau.Model.LoaiSachModel;
import com.example.du_an_mau.Model.SachModel;

import java.util.ArrayList;

public class LoaiSachDAO {
    private DBHelper mydb;
    private SQLiteDatabase db;
    public LoaiSachDAO(Context context){
        mydb=new DBHelper(context);
        db=mydb.getWritableDatabase();
    }
    public int insert(LoaiSachModel loaisach){
        ContentValues values=new ContentValues();
        values.put("tenLoai",loaisach.tenLoai);
        return (int)db.insert("LoaiSach",null,values);
    }
    public int update(LoaiSachModel loaisach){
        ContentValues values=new ContentValues();
        values.put("tenLoai",loaisach.tenLoai);
        String[] dieukien=new String[]{String.valueOf(loaisach.maLoai)};
        return (int)db.update("LoaiSach",values,"maLoai=?",dieukien);
    }
    public int delete(String id){

        return db.delete("LoaiSach","maLoai=?",new String[]{id});
    }
    public ArrayList<LoaiSachModel> getList(){
        String sql="select * from LoaiSach";
        return getData(sql);
    }
    @SuppressLint("Range")
    private ArrayList<LoaiSachModel> getData(String sql, String...selectionArgs){
        ArrayList<LoaiSachModel> list=new ArrayList<>();
        Cursor c=db.rawQuery(sql,selectionArgs);
        while(c.moveToNext()){
            LoaiSachModel loaisach=new LoaiSachModel();
            loaisach.maLoai=Integer.parseInt(c.getString(c.getColumnIndex("maLoai")));
            loaisach.tenLoai=c.getString(c.getColumnIndex("tenLoai"));
            list.add(loaisach);
        }
        return list;
    }
    public LoaiSachModel getID(String id){
        String sql="select * from LoaiSach where maLoai=?";
        ArrayList<LoaiSachModel> list=getData(sql,id);
        return list.get(0);
    }
}
