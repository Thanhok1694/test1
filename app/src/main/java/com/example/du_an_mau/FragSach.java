package com.example.du_an_mau;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.du_an_mau.Adapter.LoaiSachSpinnerAdapter;
import com.example.du_an_mau.Adapter.SachAdapter;
import com.example.du_an_mau.DAO.LoaiSachDAO;
import com.example.du_an_mau.DAO.SachDAO;
import com.example.du_an_mau.Model.LoaiSachModel;
import com.example.du_an_mau.Model.SachModel;

import java.util.ArrayList;


public class FragSach extends Fragment {
    ListView lv_sach;
    LinearLayoutManager llm;
    ArrayList<SachModel> list;
    ImageView img_themsach;

    EditText ed_them_masach,ed_them_tensach,ed_them_giathue;


    static SachDAO dao;
    SachAdapter adt;
    SachModel sa;
    LoaiSachSpinnerAdapter spnadt;
    ArrayList<LoaiSachModel> list_ls;
    LoaiSachDAO lsdao;
    LoaiSachModel ls;
    int maLoaissach,position;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       View v=inflater.inflate(R.layout.fragment_frag_sach,container,false);

       return v;

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        lv_sach=view.findViewById(R.id.lv_sach);
        img_themsach=view.findViewById(R.id.img_themsach);
        dao=new SachDAO(getActivity());
        capNhatLV();
        img_themsach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder=new AlertDialog.Builder(getContext());
                LayoutInflater inf=getLayoutInflater();
                View dialogview=inf.inflate(R.layout.dialog_themsach,null);
                builder.setView(dialogview);
                AlertDialog dialog=builder.create();

                 ed_them_masach=dialogview.findViewById(R.id.ed_them_masach);
                 ed_them_tensach=dialogview.findViewById(R.id.ed_them_tensach);
                 ed_them_giathue=dialogview.findViewById(R.id.ed_them_giathue);
                Spinner spn_loaisach=dialogview.findViewById(R.id.spn_loaisach);
                Button btn_them_sach=dialogview.findViewById(R.id.btn_them_sach);
                Button btn_cancel_them_sach=dialogview.findViewById(R.id.btn_cancel_them_sach);

                // Spinner loại sách
                list_ls=new ArrayList<>();
                lsdao=new LoaiSachDAO(getContext());
                list_ls=(ArrayList<LoaiSachModel>)lsdao.getList();
                spnadt=new LoaiSachSpinnerAdapter(getContext(),list_ls);
                spn_loaisach.setAdapter(spnadt);

                spn_loaisach.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        maLoaissach=list_ls.get(position).maLoai;
                        Toast.makeText(getContext(), "Chọn "+list_ls.get(position).tenLoai, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });

                SachDAO sdao=new SachDAO(getContext());
                ArrayList<SachModel> list=sdao.getList();
                ed_them_masach.setEnabled(false);
                btn_them_sach.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        sa=new SachModel();
                        sa.tenSach=ed_them_tensach.getText().toString();

                        sa.maLoai=maLoaissach;
                        if(validate()>0){
                            sa.giaThue=Integer.parseInt(ed_them_giathue.getText().toString());
                            int idmoi=sdao.insert(sa);
                            if(idmoi>0){
                                Toast.makeText(getContext(), "Thêm thành công", Toast.LENGTH_SHORT).show();
                                capNhatLV();
                                dialog.dismiss();
                            }else{
                                Toast.makeText(getContext(), "Thêm thất bại", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                });
                btn_cancel_them_sach.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
                dialog.show();
            }

        });



    }

//    protected void openDialog(final Context context, final int type){
//
//        dialog=new Dialog(context);
//        dialog.setContentView(R.layout.dialog_themsach);
//        ed_masach=dialog.findViewById(R.id.ed_masach);
//        ed_tensach=dialog.findViewById(R.id.ed_tensach);
//        ed_giathue=dialog.findViewById(R.id.ed_giathue);
//        spnloaisach=dialog.findViewById(R.id.spn_loaisach);
//        btn_themsach=dialog.findViewById(R.id.btn_them_sach);
//        btn_cancelsach=dialog.findViewById(R.id.btn_cancel_sach);

//        list_ls=new ArrayList<LoaiSachModel>();
//        lsdao=new LoaiSachDAO(context);
//        list_ls=(ArrayList<LoaiSachModel>) lsdao.getList();
//        spnadt=new LoaiSachSpinnerAdapter(context,list_ls);
//        spnloaisach.setAdapter(spnadt);
//
//        spnloaisach.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                maLoaissach=list_ls.get(position).maLoai;
//                Toast.makeText(context, "Chọn" +list_ls.get(position).tenLoai, Toast.LENGTH_SHORT).show();
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> parent) {
//
//            }
//        });
//        ed_masach.setEnabled(false);
//        if(type==1){
//            ed_masach.setText(String.valueOf(sa.maSach));
//            ed_tensach.setText(sa.tenSach);
//            ed_giathue.setText(String.valueOf(sa.giaThue));
//            for(int i=0; i<list_ls.size();i++){
//                if(sa.maLoai == (list_ls.get(i).maLoai)){
//                    position=i;
//                }
//                Log.i("demo","posSach"+position);
//                spnloaisach.setSelection(position);
//            }}
//            btn_cancelsach.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    dialog.dismiss();
//                }
//            });
//            btn_themsach.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    sa=new SachModel();
//                    sa.tenSach=ed_tensach.getText().toString();
//                    sa.giaThue=Integer.parseInt(ed_giathue.getText().toString());
//                    sa.maLoai=maLoaissach;
//                    if(validate()>0){
//                        if(type==0){
//                            if(dao.insert(sa)>0){
//                                Toast.makeText(context, "Thêm thành công", Toast.LENGTH_SHORT).show();
//                            }else {
//                                Toast.makeText(context, "Thêm thất bại", Toast.LENGTH_SHORT).show();
//                            }
//                        }else{
//                            sa.maSach=Integer.parseInt(ed_masach.getText().toString());
//                            if(dao.update(sa)>0){
//                                Toast.makeText(context, "Sửa thành công", Toast.LENGTH_SHORT).show();
//                            }else {
//                                Toast.makeText(context, "Sửa thất bại", Toast.LENGTH_SHORT).show();
//                            }
//                        }
//                        capNhatLV();
//                        dialog.dismiss();
//                    }
//                }
//            });
//
//
//        dialog.show();
//    }
    public void xoa(final String id){

        AlertDialog.Builder builder=new AlertDialog.Builder(getContext());
        builder.setTitle("Xóa Sách!");
        builder.setMessage("Bạn có muốn xóa sách này không?");
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
        dialog.show();
    }
    void capNhatLV(){
   list=(ArrayList<SachModel>) dao.getList();
   llm=new LinearLayoutManager(getActivity());
   adt=new SachAdapter(getActivity(),this,list);
   lv_sach.setAdapter(adt);
    }
    public int validate(){
        int check=1;
        if(ed_them_tensach.getText().toString().isEmpty() || ed_them_giathue.getText().toString().isEmpty()){
            Toast.makeText(getContext(), "Vui lòng nhập đầy đủ", Toast.LENGTH_SHORT).show();
            check=-1;
        }
        return check;
    }
}