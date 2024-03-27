package com.example.du_an_mau;

import static androidx.constraintlayout.motion.widget.TransitionBuilder.validate;

import android.content.Context;
import android.content.SharedPreferences;
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
 * Use the {@link FragDoimatkhau#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragDoimatkhau extends Fragment {
ThuThuDAO dao;
EditText ed_oldpass,ed_newpass,ed_reNewpass;
Button btn_luu, btn_cancel;


    public FragDoimatkhau() {
        // Required empty public constructor
    }


    public static FragDoimatkhau newInstance(String param1, String param2) {
        FragDoimatkhau fragment = new FragDoimatkhau();

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
        return inflater.inflate(R.layout.fragment_frag_doimatkhau, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ed_oldpass=view.findViewById(R.id.ed_oldpass);
        ed_newpass=view.findViewById(R.id.ed_newpass);
        ed_reNewpass=view.findViewById(R.id.ed_reNewpass);
         btn_luu=view.findViewById(R.id.btn_luu);
        btn_cancel=view.findViewById(R.id.btn_cancel);
        dao=new ThuThuDAO(getActivity());

        btn_cancel.setOnClickListener((v) -> {
            ed_oldpass.setText("");
            ed_newpass.setText("");
            ed_reNewpass.setText("");
        });

        btn_luu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences pref=getActivity().getSharedPreferences("USER_FILE", Context.MODE_PRIVATE);
                String user=pref.getString("USERNAME","");
                if(validate()>0){
                    ThuThuModel thuthu=dao.getID(user);
                    thuthu.matKhau=ed_newpass.getText().toString();
                    dao.updatePass(thuthu);
                    if(dao.updatePass(thuthu)>0){
                        Toast.makeText(getActivity(), "Đổi mật khẩu thành công", Toast.LENGTH_SHORT).show();
                        ed_oldpass.setText("");
                        ed_newpass.setText("");
                        ed_reNewpass.setText("");
                    }else {
                        Toast.makeText(getActivity(), "Đổi mật khẩu thất bại", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });


    }
    public int validate(){
        int check=1;
        if(ed_oldpass.getText().length()==0 || ed_newpass.getText().length()==0 || ed_reNewpass.getText().length()==0){
            Toast.makeText(getContext(), "Bạn phải nhập đầy đủ", Toast.LENGTH_SHORT).show();
            check=-1;
        }else {
            SharedPreferences pref=getActivity().getSharedPreferences("USER_FILE",Context.MODE_PRIVATE);
            String oldpass=pref.getString("PASSWORD","");
            String newpass=ed_newpass.getText().toString();
            String renewpass=ed_reNewpass.getText().toString();
            if(!oldpass.equals(ed_oldpass.getText().toString())){
                Toast.makeText(getContext(), "Mật khẩu cũ sai", Toast.LENGTH_SHORT).show();
                check=-1;
            }
            if(!newpass.equals(renewpass)){
                Toast.makeText(getContext(), "Mật khẩu không trùng khớp", Toast.LENGTH_SHORT).show();
                check=-1;
            }
        }
        return check;
    }
}