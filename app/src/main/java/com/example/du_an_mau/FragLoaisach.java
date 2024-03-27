package com.example.du_an_mau;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.du_an_mau.Adapter.LoaiSachAdapter;
import com.example.du_an_mau.DAO.LoaiSachDAO;
import com.example.du_an_mau.Model.LoaiSachModel;

import java.util.ArrayList;


public class FragLoaisach extends Fragment {

    ListView lv;
    ArrayList<LoaiSachModel> list;
    ImageView img_themloaisach;

    EditText ed_maloai,ed_tenloai;
    Button btn_luu_loaisach,btn_cancel_loaisach;
    static LoaiSachDAO dao;
    LoaiSachAdapter adt;




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v=inflater.inflate(R.layout.fragment_frag_loaisach,container,false);
        lv=v.findViewById(R.id.lv_loaisach);
        img_themloaisach=v.findViewById(R.id.img_themloaisach);
        dao=new LoaiSachDAO(getActivity());
        capNhatLV();
        img_themloaisach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder=new AlertDialog.Builder(getContext());
                LayoutInflater inf=getLayoutInflater();

                View view=inf.inflate(R.layout.dialog_themloaisach,null);
                builder.setView(view);
                AlertDialog dialog=builder.create();

               ed_maloai=view.findViewById(R.id.ed_maloaisach);
               ed_tenloai=view.findViewById(R.id.ed_tenloaisach);
               btn_luu_loaisach=view.findViewById(R.id.btn_luu_loaisach);
               btn_cancel_loaisach=view.findViewById(R.id.btn_cancel_loaisach);

               LoaiSachDAO lsdao=new LoaiSachDAO(getContext());
               ArrayList<LoaiSachModel> list=lsdao.getList();

               ed_maloai.setEnabled(false);
               btn_luu_loaisach.setOnClickListener(new View.OnClickListener() {
                   @Override
                   public void onClick(View v) {

                       String tenloai=ed_tenloai.getText().toString();
                       LoaiSachModel ls=new LoaiSachModel(tenloai);
                       if(validate()>0){
                           LoaiSachDAO lsdao=new LoaiSachDAO(getContext());

                           int idmoi=lsdao.insert(ls);
                           if(idmoi>0){
                               list.clear();
                               list.addAll(lsdao.getList());
                               capNhatLV();
                               Toast.makeText(getContext(), "Thêm thành công", Toast.LENGTH_SHORT).show();
                               dialog.dismiss();
                           }
                       }else{
                           Toast.makeText(getContext(), "Thêm thất bại", Toast.LENGTH_SHORT).show();
                       }

                   }
               });
                btn_cancel_loaisach.setOnClickListener(new View.OnClickListener() {
           @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
               dialog.show();

            }
        });

        return v;
    }
//    public void openDialog(final Context context,final int type){
//
//        dialog=new Dialog(context);
//        dialog.setContentView(R.layout.dialog_themloaisach);
//        ed_maloai=dialog.findViewById(R.id.ed_maloaisach);
//        ed_tenloai=dialog.findViewById(R.id.ed_tenloaisach);
//        btn_luu_loaisach=dialog.findViewById(R.id.btn_luu_loaisach);
//        btn_cancel_loaisach=dialog.findViewById(R.id.btn_cancel_loaisach);
//
//        ed_maloai.setEnabled(false);
//
//        btn_cancel_loaisach.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                dialog.dismiss();
//            }
//        });
//        btn_luu_loaisach.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                ls=new LoaiSachModel();
//                ls.tenLoai=ed_tenloai.getText().toString();
//                if(validate()>0){
//                    if(type==0){
//                        if(dao.insert(ls)>0){
//                            Toast.makeText(context, "Thêm thành công", Toast.LENGTH_SHORT).show();
//                        }else {
//                            Toast.makeText(context, "Thêm thất bại", Toast.LENGTH_SHORT).show();
//                        }
//                    }else {
//                        ls.maLoai=Integer.parseInt(ed_maloai.getText().toString());
//                        if(dao.update(ls)>0){
//                            Toast.makeText(context, "Sửa thành công", Toast.LENGTH_SHORT).show();
//                        }else {
//                            Toast.makeText(context, "Sửa thất bại", Toast.LENGTH_SHORT).show();
//                        }
//                    }
//                    capNhatLV();
//                    dialog.dismiss();
//                }
//
//            }
//        });
//        dialog.show();
//    }
    public void xoa(final String id){

        AlertDialog.Builder builder=new AlertDialog.Builder(getContext());
        builder.setTitle("Xóa loại sách!");
        builder.setMessage("Bạn có muốn xóa loại sách này không?");
        builder.setCancelable(true);
        builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dao.delete(id);
                capNhatLV();
                dialog.cancel();
            }
        });
        builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        AlertDialog dialog=builder.create();
        builder.show();
    }
    void capNhatLV(){

        list=(ArrayList<LoaiSachModel>) dao.getList();
        adt=new LoaiSachAdapter(getActivity(),this,list);
        lv.setAdapter(adt);
    }
    public int validate(){

        int check=1;
        if(ed_tenloai.getText().length()==0){
            Toast.makeText(getContext(), "Vui lòng nhập đầy đủ", Toast.LENGTH_SHORT).show();
            check=-1;
        }
        return check;
    }
}