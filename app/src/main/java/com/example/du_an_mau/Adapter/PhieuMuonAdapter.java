package com.example.du_an_mau.Adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.du_an_mau.DAO.PhieuMuonDAO;
import com.example.du_an_mau.DAO.SachDAO;
import com.example.du_an_mau.DAO.ThanhVienDAO;
import com.example.du_an_mau.FragPhieuMuon;
import com.example.du_an_mau.Model.PhieuMuonModel;
import com.example.du_an_mau.Model.SachModel;
import com.example.du_an_mau.Model.ThanhVienModel;
import com.example.du_an_mau.Model.ThuThuModel;
import com.example.du_an_mau.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class PhieuMuonAdapter extends ArrayAdapter<PhieuMuonModel> {
    private Context context;
    private ArrayList<PhieuMuonModel> list;
    FragPhieuMuon frpm;
    TextView tv_mapm,tv_tentv,tv_tensach,tv_tienthue,tv_ngay,tv_trasach;
    ImageButton img_xoaphieumuon;
    SachDAO sachdao;
    ThanhVienDAO tvdao;
    Button btn_sua_phieumuon;
    int maThanhvien,maSach,tienThue;
    EditText ed_sua_ngaythue;

    public PhieuMuonAdapter(@NonNull Context context, FragPhieuMuon frpm,ArrayList<PhieuMuonModel> list) {
        super(context, 0,list);
        this.context=context;
        this.list=list;
        this.frpm=frpm;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v=convertView;
        if(v==null){
            LayoutInflater inf=(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v=inf.inflate(R.layout.item_phieumuon,null);
        }
        final PhieuMuonModel pm=list.get(position);
        if(pm!=null){
            tv_mapm=v.findViewById(R.id.tv_Maphieumuon);
            tv_mapm.setText("Mã phiếu mượn: "+pm.maPM);

            sachdao=new SachDAO(context);
            SachModel sa=sachdao.getID(String.valueOf(pm.maSach));
            tv_tensach=v.findViewById(R.id.tv_Tensach);
            tv_tensach.setText("Tên sách: "+sa.tenSach);

            tvdao=new ThanhVienDAO(context);
            ThanhVienModel tv=tvdao.getID(String.valueOf(pm.maTV));
            tv_tentv=v.findViewById(R.id.tv_Tenthanhvien);
            tv_tentv.setText("Thành viên: "+tv.hoTen);

            tv_tienthue=v.findViewById(R.id.tv_Tienthue);
            tv_tienthue.setText("Tiền thuê: "+pm.tienThue);

            tv_ngay=v.findViewById(R.id.tv_Ngaythue);
            tv_ngay.setText("Ngày thuê: "+pm.ngay);

            tv_trasach=v.findViewById(R.id.tv_Trasach);
            if(pm.traSach==1){
                tv_trasach.setTextColor(Color.BLUE);
                tv_trasach.setText("Đã trả sách");
            }else {
                tv_trasach.setTextColor(Color.RED);
                tv_trasach.setText("Chưa trả sách");
            }
            img_xoaphieumuon=v.findViewById(R.id.img_xoaphieumuon);
            btn_sua_phieumuon=v.findViewById(R.id.btn_sua_phieumuon);


        }
        btn_sua_phieumuon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder=new AlertDialog.Builder(context);
                LayoutInflater inf=((Activity)context).getLayoutInflater();
                View view=inf.inflate(R.layout.dialog_sua_phieumuon,null);
                builder.setView(view);
                AlertDialog dialog=builder.create();

                EditText ed_sua_mapm=view.findViewById(R.id.ed_sua_maphieumuon);
                Spinner spn_sua_thanhvien=view.findViewById(R.id.spinner_sua_thanhvien_phieumuon);
                Spinner spn_sua_sach=view.findViewById(R.id.spinner_sua_sach_phieumuon);
                ed_sua_ngaythue=view.findViewById(R.id.ed_sua_ngaythue);
                TextView tv_sua_tienthue=view.findViewById(R.id.tv_sua_tienthue);
                CheckBox ch_sua_trasach=view.findViewById(R.id.chk_sua_trasach);
                Button btn_sua_phieumuon1=view.findViewById(R.id.btn_sua_phieumuon1);
                Button btn_cancel_phieumuon=view.findViewById(R.id.btn_cancel_sua_phieumuon);

                ed_sua_mapm.setEnabled(false);
                ed_sua_mapm.setText(pm.maPM+"");

                ThanhVienDAO tvdao=new ThanhVienDAO(context);
                ArrayList<ThanhVienModel> list_tv=(ArrayList<ThanhVienModel>) tvdao.getList();
                ThanhVienSpinnerAdapter tvspnadt=new ThanhVienSpinnerAdapter(context,list_tv);
                spn_sua_thanhvien.setAdapter(tvspnadt);
                int position=0;
                for(int i=0;i<list_tv.size();i++){
                    if(pm.maTV==(list_tv.get(i).maTV)){
                        position=i;
                    }
                    spn_sua_thanhvien.setSelection(position);
                }
                spn_sua_thanhvien.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        maThanhvien=list_tv.get(position).maTV;
                        Toast.makeText(context, "Chọn "+list_tv.get(position).hoTen, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });

                int position1=0;
                SachDAO sdao=new SachDAO(context);
                ArrayList<SachModel> list_sach=(ArrayList<SachModel>) sdao.getList();
                SachSpinnerAdapter sadtspn=new SachSpinnerAdapter(context,list_sach);
                spn_sua_sach.setAdapter(sadtspn);
                for(int i=0;i<list_sach.size();i++){
                    if(pm.maSach==(list_sach.get(i).maSach)){
                        position1=i;
                    }
                    spn_sua_sach.setSelection(position1);
                }
                spn_sua_sach.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        maSach=list_sach.get(position).maSach;
                        tienThue=list_sach.get(position).giaThue;
                        Toast.makeText(context, "Chọn "+list_sach.get(position).tenSach, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });
                ed_sua_ngaythue.setText(pm.ngay);
                tv_sua_tienthue.setText("Tiền thuê: "+pm.tienThue+"");
                if(pm.traSach==1){
                    ch_sua_trasach.setChecked(true);
                }else{
                    ch_sua_trasach.setChecked(false);
                }
                btn_cancel_phieumuon.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
                btn_sua_phieumuon1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        PhieuMuonDAO pmdao=new PhieuMuonDAO(context);
                        PhieuMuonModel pmn=new PhieuMuonModel();
                        pmn.maSach=maSach;
                        pmn.maTV=maThanhvien;
                        pmn.tienThue=tienThue;
                        pmn.ngay=ed_sua_ngaythue.getText().toString();
                        if(ch_sua_trasach.isChecked()){
                            pmn.traSach=1;
                        }else{
                            pmn.traSach=0;
                        }
                        pmn.maPM=Integer.parseInt(ed_sua_mapm.getText().toString());
                        if(validate()>0){
                            if(pmdao.update(pmn)>0){
                                list.clear();
                                list.addAll(pmdao.getList());
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

        img_xoaphieumuon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                frpm.xoa(String.valueOf(pm.maPM));
            }
        });
        return v;
    }
    public int validate() {
        int check = 1;
        if (ed_sua_ngaythue.getText().length() == 0) {
            Toast.makeText(context, "Vui lòng nhập ngày thuê", Toast.LENGTH_SHORT).show();
            check=-1;
        }
        return check ;
    }
}
