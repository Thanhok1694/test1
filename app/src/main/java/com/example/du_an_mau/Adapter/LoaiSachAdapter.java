package com.example.du_an_mau.Adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.du_an_mau.DAO.LoaiSachDAO;
import com.example.du_an_mau.FragLoaisach;
import com.example.du_an_mau.Model.LoaiSachModel;
import com.example.du_an_mau.R;

import java.util.ArrayList;

public class LoaiSachAdapter extends ArrayAdapter<LoaiSachModel> {
    private Context context;
    FragLoaisach frloaisach;
    private ArrayList<LoaiSachModel> list;
    TextView tv_maloai,tv_tenloai;
    ImageButton img_xoaloaisach;
    Button btn_sua_loaisach1;

    public LoaiSachAdapter(@NonNull Context context, FragLoaisach frls, ArrayList<LoaiSachModel> list) {
        super(context, 0,list);
        this.context=context;
        this.list=list;
        this.frloaisach=frls;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
       View v=convertView;
       if(v==null){
           LayoutInflater inf=(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
           v=inf.inflate(R.layout.item_loaisach,null);

       }
       final LoaiSachModel ls=list.get(position);
       if(ls!=null){
           tv_maloai=v.findViewById(R.id.tv_maloai);
           tv_tenloai=v.findViewById(R.id.tv_tenloai);
           tv_maloai.setText("Mã loại: "+ls.maLoai);
           tv_tenloai.setText("Tên loại: "+ls.tenLoai);
           img_xoaloaisach=v.findViewById(R.id.img_xoaloaisach);
           btn_sua_loaisach1=v.findViewById(R.id.btn_sua_loaisach1);

       img_xoaloaisach.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               frloaisach.xoa(String.valueOf(ls.maLoai));
           }
       });
       btn_sua_loaisach1.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               AlertDialog.Builder builder=new AlertDialog.Builder(context);
               LayoutInflater inf=((Activity)context).getLayoutInflater();
               View view=inf.inflate(R.layout.dialog_sua_loaisach,null);
               builder.setView(view);
               AlertDialog dialog=builder.create();

               EditText ed_sua_maloai=view.findViewById(R.id.ed_sua_maloaisach);
               EditText ed_sua_tenloai=view.findViewById(R.id.ed_sua_tenloaisach);
               Button btn_sua_loai=view.findViewById(R.id.btn_luu_sua_loaisach);
               Button btn_cancel_sua_loai=view.findViewById(R.id.btn_cancel_sua_loaisach);

               ed_sua_maloai.setEnabled(false);

               ed_sua_maloai.setText(String.valueOf(ls.maLoai));
               ed_sua_tenloai.setText(ls.tenLoai);

               btn_sua_loai.setOnClickListener(new View.OnClickListener() {
                   @Override
                   public void onClick(View v) {
                       String tenloai=ed_sua_tenloai.getText().toString();

                       LoaiSachDAO lsdao=new LoaiSachDAO(context);
                       LoaiSachModel ls=list.get(position);
                       ls.setTenLoai(tenloai);
                       int id=lsdao.update(ls);
                       if(id>0){
                           list.clear();
                           list.addAll(lsdao.getList());
                           notifyDataSetChanged();
                           Toast.makeText(context, "Sửa thành công", Toast.LENGTH_SHORT).show();
                           dialog.dismiss();
                       }else{
                           Toast.makeText(context, "Sửa thất bại", Toast.LENGTH_SHORT).show();
                       }
                   }
               });
               btn_cancel_sua_loai.setOnClickListener(new View.OnClickListener() {
                   @Override
                   public void onClick(View v) {
                       dialog.dismiss();
                   }
               });
               dialog.show();
           }
       });
       }
       return v;
    }
}
