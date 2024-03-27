package com.example.du_an_mau.DAO;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.du_an_mau.DBHelper.DBHelper;
import com.example.du_an_mau.Model.LoaiSachModel;
import com.example.du_an_mau.Model.ThanhVienModel;
import com.example.du_an_mau.Model.ThuThuModel;

import java.util.ArrayList;

public class ThuThuDAO {
    private DBHelper mydb;
    private SQLiteDatabase db;
    public ThuThuDAO(Context context){
        mydb=new DBHelper(context);
        db=mydb.getWritableDatabase();
    }

    public boolean checkLogin(String strUser,String strPass){
        Cursor c = db.rawQuery("Select * from ThuThu where  maTT = ? AND matKhau = ?", new String[]{strUser,strPass});
        Log.d("zzzzzzzzzzz", "checkLogin: "+c.getCount());
        return c.getCount() > 0;
    }
    public ThuThuModel getID(String id){

        String sql="select * from ThuThu where maTT=?";
        ArrayList<ThuThuModel> list=getData(sql,id);
        return list.get(0);
    }
    @SuppressLint("Range")
    private ArrayList<ThuThuModel> getData(String sql, String...selectionArgs){
        ArrayList<ThuThuModel> list=new ArrayList<>();
        Cursor c=db.rawQuery(sql,selectionArgs);
        while(c.moveToNext()){
            ThuThuModel tt=new ThuThuModel();
            tt.maTT=c.getString(c.getColumnIndex("maTT"));
            tt.hoTen_tt=c.getString(c.getColumnIndex("hoTen"));
            tt.matKhau=c.getString(c.getColumnIndex("matKhau"));
            list.add(tt);
        }
        return list;
    }


    public int updatePass(ThuThuModel thuthu){
        ContentValues values=new ContentValues();
        values.put("maTT",thuthu.maTT);
        values.put("hoTen",thuthu.hoTen_tt);
        values.put("matKhau",thuthu.matKhau);
        return db.update("ThuThu",values,"maTT=?",new String[]{thuthu.maTT});
    }
    public int insert(ThuThuModel thuthu){
        ContentValues values=new ContentValues();
        values.put("maTT",thuthu.maTT);
        values.put("hoTen",thuthu.hoTen_tt);
        values.put("matKhau",thuthu.matKhau);
        return (int)db.insert("ThuThu",null,values);
    }


}
