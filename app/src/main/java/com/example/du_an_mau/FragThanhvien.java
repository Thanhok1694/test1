package com.example.du_an_mau;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.du_an_mau.Adapter.ThanhVienAdapter;
import com.example.du_an_mau.DAO.ThanhVienDAO;
import com.example.du_an_mau.Model.ThanhVienModel;

import java.util.ArrayList;


public class FragThanhvien extends Fragment {

    ListView lv;
    ArrayList<ThanhVienModel> list;
    ImageView img_them;

    EditText ed_them_tenthanhvien,ed_them_namsinh,ed_them_mathanhvien,ed_them_luong;

    static ThanhVienDAO dao;
    ThanhVienAdapter adt;
    TextView tv_chongioitinh,tv_setgioitinh;
    ThanhVienModel tv;
    int luong;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v=inflater.inflate(R.layout.fragment_frag_thanhvien,container,false);
        lv=v.findViewById(R.id.lv_thanhvien);
        img_them=v.findViewById(R.id.img_themthanhvien);
        dao=new ThanhVienDAO(getActivity());
        capNhatLV();
        img_them.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder=new AlertDialog.Builder(getContext());
                LayoutInflater inf=getLayoutInflater();
                View view=inf.inflate(R.layout.dialog_themthanhvien,null);
                builder.setView(view);
                AlertDialog dialog=builder.create();

                ed_them_luong=view.findViewById(R.id.ed_them_luong);
                tv_chongioitinh=view.findViewById(R.id.tv_chongioitinh);
                tv_setgioitinh=view.findViewById(R.id.tv_setgioitinh);
                 ed_them_mathanhvien=view.findViewById(R.id.ed_them_mathanhvien);
                 ed_them_tenthanhvien=view.findViewById(R.id.ed_them_tenthanhvien);
                 ed_them_namsinh=view.findViewById(R.id.ed_them_namsinh);
                Button btn_them_thanhvien=view.findViewById(R.id.btn_them_thanhvien);
                Button btn_cancel_thanhvien=view.findViewById(R.id.btn_cancel_thanhvien);

                ed_them_mathanhvien.setEnabled(false);
                tv_chongioitinh.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        AlertDialog.Builder builder=new AlertDialog.Builder(getContext());
                        LayoutInflater inf=getLayoutInflater();
                        View view=inf.inflate(R.layout.dialog_chongioitinh,null);
                        builder.setView(view);


                        RadioButton rdo_nam=view.findViewById(R.id.rdo_nam);
                        RadioButton rdo_nu=view.findViewById(R.id.rdo_nu);
                        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                               tv=new ThanhVienModel();
                                 dao=new ThanhVienDAO(getContext());



                            }
                        });
                        builder.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                        AlertDialog dialog=builder.create();
                        dialog.show();
                    }
                });
                btn_cancel_thanhvien.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
                btn_them_thanhvien.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String hoten=ed_them_tenthanhvien.getText().toString();
                        String namsinh=ed_them_namsinh.getText().toString();



                        if(validate()>0){

                            ThanhVienModel tv=new ThanhVienModel(hoten,namsinh);
                            dao=new ThanhVienDAO(getContext());
                            int idmoi=dao.insert(tv);
                            if(idmoi>0){
                                list.clear();
                                list.addAll(dao.getList());
                                capNhatLV();
                                Toast.makeText(getContext(), "Thêm thành công", Toast.LENGTH_SHORT).show();
                                dialog.dismiss();
                            }else{
                                Toast.makeText(getContext(), "Thâm thất bại", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                });
                dialog.show();
            }
        });
        return v;
    }



    public void xoa(final String id){

        AlertDialog.Builder builder=new AlertDialog.Builder(getContext());
        builder.setTitle("Xóa dữ liệu");
        builder.setMessage("Bạn có muốn xóa không ?");
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

        list=(ArrayList<ThanhVienModel>) dao.getList();
        adt=new ThanhVienAdapter(getActivity(),this,list);
        lv.setAdapter(adt);
    }
    public int validate(){

        int check=1;
        if(ed_them_tenthanhvien.getText().length()==0 || ed_them_namsinh.getText().length()==0 || ed_them_luong.getText().length()==0 ){
            Toast.makeText(getContext(), "Vui lòng nhập đầy đủ", Toast.LENGTH_SHORT).show();
            check=-1;
        }else{
            if(luong<1000000){
                Toast.makeText(getContext(), "Lương phải >1000000", Toast.LENGTH_SHORT).show();
                check=-1;
            }
        }
        return check;
    }
}