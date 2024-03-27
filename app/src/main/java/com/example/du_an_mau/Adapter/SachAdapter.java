package com.example.du_an_mau.Adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.du_an_mau.DAO.LoaiSachDAO;
import com.example.du_an_mau.DAO.SachDAO;
import com.example.du_an_mau.FragSach;
import com.example.du_an_mau.Model.LoaiSachModel;
import com.example.du_an_mau.Model.SachModel;
import com.example.du_an_mau.R;

import java.util.ArrayList;

public class SachAdapter extends ArrayAdapter<SachModel> {
    private Context context;
    private ArrayList<SachModel> list;
    FragSach frs;
    TextView tv_masach,tv_tensach,tv_giathue,tv_loai;
    ImageButton img_xoasach;
    Button btn_sua_sach;
    EditText ed_sua_tensach;
    EditText ed_sua_giathue;
    int maLoaissach;

    ArrayList<LoaiSachModel> list_ls;



    public SachAdapter(@NonNull Context context, FragSach frs,ArrayList<SachModel> list) {
        super(context, 0,list);
        this.context=context;
        this.list=list;
        this.frs=frs;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v=convertView;
        if(v==null){
            LayoutInflater inf=(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v=inf.inflate(R.layout.item_sach,null);

        }

        final SachModel sa=list.get(position);
        if(sa!=null){
            LoaiSachDAO lsdao=new LoaiSachDAO(context);
            LoaiSachModel ls=lsdao.getID(String.valueOf(sa.maLoai));
            tv_masach=v.findViewById(R.id.tv_masach);
            tv_masach.setText("Mã sách: "+sa.maSach);

            tv_tensach=v.findViewById(R.id.tv_tensach);
            tv_tensach.setText("Tên sách: "+sa.tenSach);

            tv_giathue=v.findViewById(R.id.tv_giathue);
            tv_giathue.setText("Giá thuê: "+sa.giaThue);

            tv_loai=v.findViewById(R.id.tv_tenloaisach);
            tv_loai.setText("Loại sách: "+ls.tenLoai);
            img_xoasach=v.findViewById(R.id.img_xoasach);
            btn_sua_sach=v.findViewById(R.id.btn_sua_sach);
        }
        img_xoasach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                frs.xoa(String.valueOf(sa.maSach));
            }
        });
        btn_sua_sach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder=new AlertDialog.Builder(context);
                LayoutInflater inf=((Activity)context).getLayoutInflater();
                View view=inf.inflate(R.layout.dialog_sua_sach,null);
                builder.setView(view);
                AlertDialog dialog=builder.create();

                EditText ed_sua_masach=view.findViewById(R.id.ed_sua_masach);
                ed_sua_tensach=view.findViewById(R.id.ed_sua_tensach);
                ed_sua_giathue=view.findViewById(R.id.ed_sua_giathue);
                Spinner spn_loaisach1=view.findViewById(R.id.spn_loaisach1);
                Button btn_sua_sach1=view.findViewById(R.id.btn_sua_sach1);
                Button btn_cancel_sua_sach=view.findViewById(R.id.btn_cancel_sua_sach);


                ed_sua_masach.setEnabled(false);
                ed_sua_masach.setText(String.valueOf(sa.maSach));
                ed_sua_tensach.setText(sa.tenSach);
                ed_sua_giathue.setText(String.valueOf(sa.giaThue));

                int position=0;

                LoaiSachDAO lsdao=new LoaiSachDAO(context);
                list_ls=(ArrayList<LoaiSachModel>)lsdao.getList();
                LoaiSachSpinnerAdapter spnadt=new LoaiSachSpinnerAdapter(context,list_ls);
                spn_loaisach1.setAdapter(spnadt);

                for(int i=0;i<list_ls.size();i++){
                    if(sa.maLoai==(list_ls.get(i).maLoai)){
                        position=i;
                    }
                    Log.i("demo","posSach"+position);
                    spn_loaisach1.setSelection(position);
                }


                spn_loaisach1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        maLoaissach=list_ls.get(position).maLoai;
                        Toast.makeText(getContext(), "Chọn "+list_ls.get(position).tenLoai, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });


                btn_cancel_sua_sach.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
                btn_sua_sach1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        SachDAO sadao=new SachDAO(context);
                       SachModel sa=new SachModel();
                       sa.tenSach=ed_sua_tensach.getText().toString();
                       sa.maLoai=maLoaissach;
                       sa.maSach=Integer.parseInt(ed_sua_masach.getText().toString());
                       if(validate()>0){
                           sa.giaThue=Integer.parseInt(ed_sua_giathue.getText().toString());
                           if(sadao.update(sa)>0){
                               list.clear();
                               list.addAll(sadao.getList());
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
        return v;
    }
    public int validate(){
        int check=1;
        if(ed_sua_tensach.getText().length()==0 || ed_sua_giathue.getText().length()==0){
            Toast.makeText(context, "Vui lòng nhập đầy đủ", Toast.LENGTH_SHORT).show();
            check=-1;
        }
        return check;
    }
}

