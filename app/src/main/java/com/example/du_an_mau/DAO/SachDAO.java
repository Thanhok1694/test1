package com.example.du_an_mau.DAO;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.du_an_mau.DBHelper.DBHelper;
import com.example.du_an_mau.Model.SachModel;
import com.example.du_an_mau.Model.ThanhVienModel;

import java.util.ArrayList;

public class SachDAO {

    private DBHelper mydb;
    private SQLiteDatabase db;
    public SachDAO(Context context){
   mydb=new DBHelper(context);
   db=mydb.getWritableDatabase();
    }
    public int insert(SachModel sach){
        ContentValues values=new ContentValues();
        values.put("tenSach",sach.tenSach);
        values.put("giaThue",sach.giaThue);
        values.put("maLoai",sach.maLoai);
        return (int)db.insert("Sach",null,values);
    }
    public int update(SachModel sach){
        ContentValues values=new ContentValues();
        values.put("tenSach",sach.tenSach);
        values.put("giaThue",sach.giaThue);
        values.put("maLoai",sach.maLoai);
        String[] dieukien=new String[]{String.valueOf(sach.maSach)};
        return db.update("Sach",values,"maSach=?",dieukien);
    }
    public int delete(String id){

        return db.delete("Sach","maSach=?",new String[]{id});
    }
    public ArrayList<SachModel> getList(){
        String sql="select * from Sach";
        return getData(sql);
    }
    @SuppressLint("Range")
    private ArrayList<SachModel> getData(String sql, String...selectionArgs){
        ArrayList<SachModel> list=new ArrayList<>();
        Cursor c=db.rawQuery(sql,selectionArgs);
        while(c.moveToNext()){
            SachModel sach=new SachModel();
            sach.maSach=Integer.parseInt(c.getString(c.getColumnIndex("maSach")));
            sach.tenSach=c.getString(c.getColumnIndex("tenSach"));
            sach.giaThue=Integer.parseInt(c.getString(c.getColumnIndex("giaThue")));
            sach.maLoai=Integer.parseInt(c.getString(c.getColumnIndex("maLoai")));
            list.add(sach);
        }
        return list;
    }
    public SachModel getID(String id){
        String sql="select * from Sach where maSach=?";
        ArrayList<SachModel> list=getData(sql,id);
        return list.get(0);
    }
}
