package com.example.du_an_mau;

import static java.time.MonthDay.now;

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
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.du_an_mau.Adapter.PhieuMuonAdapter;
import com.example.du_an_mau.Adapter.SachSpinnerAdapter;
import com.example.du_an_mau.Adapter.ThanhVienSpinnerAdapter;
import com.example.du_an_mau.DAO.PhieuMuonDAO;
import com.example.du_an_mau.DAO.SachDAO;
import com.example.du_an_mau.DAO.ThanhVienDAO;
import com.example.du_an_mau.DAO.ThuThuDAO;
import com.example.du_an_mau.Model.PhieuMuonModel;
import com.example.du_an_mau.Model.SachModel;
import com.example.du_an_mau.Model.ThanhVienModel;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


public class FragPhieuMuon extends Fragment {

ListView lv_phieumuon;
ArrayList<PhieuMuonModel> list;
ImageView img_themphieumuon;


Spinner sptv,spsach;
TextView tv_ngaythue,tv_tienthue;
CheckBox chk_trasach;
Button btn_them_phieumuon,btn_cancel_phieumuon;
static PhieuMuonDAO pmdao;
PhieuMuonAdapter adt;
PhieuMuonModel pm;
ThanhVienSpinnerAdapter tvspnadt;
ArrayList<ThanhVienModel> list_tv;
ThanhVienDAO tvdao;
ThanhVienModel tv;
int maThanhVien;
SachSpinnerAdapter sachspnadt;
ArrayList<SachModel> list_sach;
SachDAO sachdao;
SachModel sach;
int maSach,tienThue;
int positionTV,positionSach;

SimpleDateFormat sdf=new SimpleDateFormat("dd-MM-yyyy");

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       View v=inflater.inflate(R.layout.fragment_frag_phieu_muon,container,false);
       return v;

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        View v=view;

        lv_phieumuon=v.findViewById(R.id.lv_phieumuon);
        img_themphieumuon=v.findViewById(R.id.img_themphieumuon);
        pmdao=new PhieuMuonDAO(getActivity());
        capNhatLV();
        img_themphieumuon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder=new AlertDialog.Builder(getContext());
                LayoutInflater inf=getLayoutInflater();
                View view=inf.inflate(R.layout.dialog_themphieumuon,null);
                builder.setView(view);
                AlertDialog dialog=builder.create();

                EditText ed_them_maphieumuon=view.findViewById(R.id.ed_them_maphieumuon);
                Spinner spn_thanhvien=view.findViewById(R.id.spinner_thanhvien_phieumuon);
                Spinner spn_sach=view.findViewById(R.id.spinner_sach_phieumuon);

                tv_tienthue=view.findViewById(R.id.tv_tienthue);
                tv_ngaythue=view.findViewById(R.id.tv_ngaythue);
                CheckBox chk_trasach=view.findViewById(R.id.chk_trasach);
                Button btn_them_phieumuon=view.findViewById(R.id.btn_them_phieumuon);
                Button btn_cancel_phieumuon=view.findViewById(R.id.btn_cancel_phieumuon);

                ed_them_maphieumuon.setEnabled(false);




                tvdao=new ThanhVienDAO(getActivity());
                list_tv=new ArrayList<>();
                list_tv=(ArrayList<ThanhVienModel>) tvdao.getList();
                tvspnadt=new ThanhVienSpinnerAdapter(getActivity(),list_tv);
                spn_thanhvien.setAdapter(tvspnadt);
                spn_thanhvien.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        maThanhVien=list_tv.get(position).maTV;
                        Toast.makeText(getContext(), "Chọn "+list_tv.get(position).hoTen, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });

                sachdao=new SachDAO(getActivity());
                list_sach=new ArrayList<SachModel>();
                list_sach=(ArrayList<SachModel>) sachdao.getList();
                sachspnadt=new SachSpinnerAdapter(getActivity(),list_sach);
                spn_sach.setAdapter(sachspnadt);
                spn_sach.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        maSach=list_sach.get(position).maSach;
                        tienThue=list_sach.get(position).giaThue;
                        tv_tienthue.setText("Tiền thuê: "+String.valueOf(tienThue));
                        Toast.makeText(getContext(), "Chọn "+list_sach.get(position).tenSach, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });

                btn_cancel_phieumuon.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
                java.util.Date date = new java.util.Date();
                String ngay = sdf.format(date);
                tv_ngaythue.setText("Ngày thuê: "+ngay);

                btn_them_phieumuon.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        PhieuMuonModel pmnew=new PhieuMuonModel();

                        pmnew.maTV=maThanhVien;
                        pmnew.maSach=maSach;
                        Date date=new Date();
                        String ngayt=sdf.format(date);
                        pmnew.ngay=ngayt;

                        pmnew.tienThue=tienThue;
                        if(chk_trasach.isChecked()){
                            pmnew.traSach=1;
                        }else{
                            pmnew.traSach=0;
                        }

                        if(pmdao.insert(pmnew)>0){
                            list.clear();
                            list.addAll(pmdao.getList());

                            Toast.makeText(getContext(), "Thêm thành công", Toast.LENGTH_SHORT).show();
                            capNhatLV();
                            dialog.dismiss();
                        }else{
                            Toast.makeText(getContext(), "Thêm thất bại", Toast.LENGTH_SHORT).show();
                        }
                    }

                });
                dialog.show();
            }
        });


    }

    public void xoa(final String id){
        AlertDialog.Builder builder=new AlertDialog.Builder(getContext());
        builder.setTitle("Xóa phiếu mượn!");
        builder.setMessage("Bạn có muốn xóa phiếu mượn này?");
        builder.setCancelable(true);
        builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                pmdao.delete(id);
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
        list=(ArrayList<PhieuMuonModel>) pmdao.getList();
        adt=new PhieuMuonAdapter(getActivity(),this,list);
        lv_phieumuon.setAdapter(adt);
    }

}