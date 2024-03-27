package com.example.du_an_mau.Adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.du_an_mau.DAO.ThanhVienDAO;
import com.example.du_an_mau.FragThanhvien;
import com.example.du_an_mau.Model.ThanhVienModel;
import com.example.du_an_mau.R;

import java.util.ArrayList;
import java.util.List;

public class ThanhVienAdapter extends ArrayAdapter<ThanhVienModel> {
    private Context context;
    FragThanhvien frtv;
    private ArrayList<ThanhVienModel> list;
    TextView tv_mathanhvien,tv_tenthanhvien,tv_namssinh;
    ImageButton img_xoathanhvien;
    Button btn_sua_thanhvien;
    EditText ed_sua_tenthanhvien,ed_sua_namsinh;
    public ThanhVienAdapter(@NonNull Context context, FragThanhvien frtv, ArrayList<ThanhVienModel> list) {
        super(context, 0, list);
        this.context=context;
        this.list=list;
        this.frtv=frtv;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v=convertView;
        if(v==null){
            LayoutInflater inf=(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v=inf.inflate(R.layout.item_thanhvien,null);
        }
        final ThanhVienModel item=list.get(position);
        if(item!=null){
            tv_mathanhvien=v.findViewById(R.id.tv_mathanhvien);
            tv_tenthanhvien=v.findViewById(R.id.tv_tenthanhvien);
            tv_namssinh=v.findViewById(R.id.tv_namsinh);

            img_xoathanhvien=v.findViewById(R.id.img_xoathanhvien);
            btn_sua_thanhvien=v.findViewById(R.id.btn_sua_thanhvien);


            tv_mathanhvien.setText("Mã thành viên: "+item.maTV);
            tv_tenthanhvien.setText("Tên thành viên: "+item.hoTen);
            tv_namssinh.setText("Năm sinh: "+item.namSinh);


            img_xoathanhvien.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    frtv.xoa(String.valueOf(item.maTV));
                }
            });
            btn_sua_thanhvien.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AlertDialog.Builder builder=new AlertDialog.Builder(context);
                    LayoutInflater inf=((Activity)context).getLayoutInflater();
                    View view=inf.inflate(R.layout.dialog_sua_thanhvien,null);
                    builder.setView(view);
                    AlertDialog dialog=builder.create();

                    EditText ed_sua_mathanhvien=view.findViewById(R.id.ed_sua_mathanhvien);
                    ed_sua_tenthanhvien=view.findViewById(R.id.ed_sua_tenthanhvien);
                    ed_sua_namsinh=view.findViewById(R.id.ed_sua_namsinh);
                    Button btn_sua_thanhvien1=view.findViewById(R.id.btn_sua_thanhvien1);
                    Button btn_cancel_sua_thanhvien=view.findViewById(R.id.btn_cancel_sua_thanhvien);

                    ThanhVienModel tv=list.get(position);
                    ed_sua_mathanhvien.setEnabled(false);
                    ed_sua_mathanhvien.setText(String.valueOf(tv.maTV));
                    ed_sua_tenthanhvien.setText(tv.hoTen);
                    ed_sua_namsinh.setText(tv.namSinh);

                    btn_cancel_sua_thanhvien.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            dialog.dismiss();
                        }
                    });
                    btn_sua_thanhvien1.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            String ten=ed_sua_tenthanhvien.getText().toString();
                            String namsinh=ed_sua_namsinh.getText().toString();

                            ThanhVienDAO tvdao=new ThanhVienDAO(context);
                            ThanhVienModel tv=list.get(position);
                            tv.setHoTen(ten);
                            tv.setNamSinh(namsinh);

                            if(validate()>0){
                                int id=tvdao.update(tv);
                                if(id>0){
                                    list.clear();
                                    list.addAll(tvdao.getList());
                                    notifyDataSetChanged();
                                    Toast.makeText(context, "Sửa thành công", Toast.LENGTH_SHORT).show();
                                    dialog.dismiss();
                                }else{
                                    Toast.makeText(context, "Sửa thất bại", Toast.LENGTH_SHORT).show();
                                }
                            }
                        }
                    });
                    dialog.show();
                }
            });

        }
        return v;
    }
    public int validate(){
        int check=1;
        if(ed_sua_tenthanhvien.getText().length()==0 || ed_sua_namsinh.getText().length()==0){
            Toast.makeText(context, "Vui lòng nhập đầy đủ", Toast.LENGTH_SHORT).show();
            check=-1;
        }
        return check;
    }
}
