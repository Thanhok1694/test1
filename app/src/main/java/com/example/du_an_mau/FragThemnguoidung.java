package com.example.du_an_mau;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.du_an_mau.DAO.ThuThuDAO;
import com.example.du_an_mau.Model.ThuThuModel;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragThemnguoidung#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragThemnguoidung extends Fragment {
ThuThuDAO dao;
EditText ed_tendangnhap,ed_hoten,ed_matkhau,ed_rePass;
Button btn_them_nguoidung,btn_cancel_nguoidung;

    public FragThemnguoidung() {
        // Required empty public constructor
    }


    public static FragThemnguoidung newInstance(String param1, String param2) {
        FragThemnguoidung fragment = new FragThemnguoidung();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_frag_themnguoidung, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ed_tendangnhap=view.findViewById(R.id.ed_tendangnhap);
        ed_hoten=view.findViewById(R.id.ed_hoten);
        ed_matkhau=view.findViewById(R.id.ed_matkhau);
        ed_rePass=view.findViewById(R.id.ed_rePass);
        btn_them_nguoidung=view.findViewById(R.id.btn_them_nguoidung);
        btn_cancel_nguoidung=view.findViewById(R.id.btn_cancel_nguoidung);

        dao=new ThuThuDAO(getActivity());

        btn_cancel_nguoidung.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ed_tendangnhap.setText("");
                ed_hoten.setText("");
                ed_matkhau.setText("");
                ed_rePass.setText("");
            }
        });
        btn_them_nguoidung.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ThuThuModel thuthu=new ThuThuModel();
                thuthu.maTT=ed_tendangnhap.getText().toString();
                thuthu.hoTen_tt=ed_hoten.getText().toString();
                thuthu.matKhau=ed_matkhau.getText().toString();
                if(validate()>0){
                    if(dao.insert(thuthu)>0){
                        Toast.makeText(getActivity(), "Lưu thành công", Toast.LENGTH_SHORT).show();
                        ed_tendangnhap.setText("");
                        ed_hoten.setText("");
                        ed_matkhau.setText("");
                        ed_rePass.setText("");
                    }else {
                        Toast.makeText(getActivity(), "Lưu thất bại", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
    public int validate(){
        int check=1;
        if(ed_tendangnhap.getText().length()==0 || ed_hoten.getText().length()==0 || ed_matkhau.getText().length()==0 || ed_rePass.getText().length()==0){
            Toast.makeText(getActivity(), "Vui lòng nhập đầy đủ", Toast.LENGTH_SHORT).show();
            check=-1;
        }else {
            String pass=ed_matkhau.getText().toString();
            String rePass=ed_rePass.getText().toString();
            if(!pass.equals(rePass)){
                Toast.makeText(getActivity(), "Mật khẩu phải trùng khớp", Toast.LENGTH_SHORT).show();
                check=-1;
            }
        }
        return check;
    }

}