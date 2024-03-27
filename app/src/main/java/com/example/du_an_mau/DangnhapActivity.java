package com.example.du_an_mau;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.du_an_mau.DAO.ThuThuDAO;

public class DangnhapActivity extends AppCompatActivity {
EditText ed_UserName;
EditText ed_PassWord;
CheckBox chkRememberPass;
Button btn_Login,btn_Cancel;
ThuThuDAO dao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dangnhap);

        ed_UserName=findViewById(R.id.ed_UserName);
        ed_PassWord=findViewById(R.id.ed_PassWord);
        chkRememberPass=findViewById(R.id.chkRememberPass);
        btn_Login=findViewById(R.id.btn_Login);
        btn_Cancel=findViewById(R.id.btn_Cancel);

        dao=new ThuThuDAO(this);
        SharedPreferences pref=getSharedPreferences("USER_FILE",MODE_PRIVATE);
        ed_UserName.setText(pref.getString("USERNAME",""));
        ed_PassWord.setText(pref.getString("PASSWORD",""));
        chkRememberPass.setChecked(pref.getBoolean("REMEMBER",false));

        btn_Cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ed_UserName.setText("");
                ed_PassWord.setText("");

            }
        });
        btn_Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkLogin();
            }
        });


    }
    public void checkLogin(){

       String strUser=ed_UserName.getText().toString();
        String strPass=ed_PassWord.getText().toString();
        if(strUser.isEmpty() || strPass.isEmpty()){
            Toast.makeText(getApplicationContext(), "Tên đăng nhập và mật khẩu không được bỏ trống", Toast.LENGTH_SHORT).show();
        }else {
            boolean check=dao.checkLogin(strUser,strPass);
            if(check==true){

                Toast.makeText(getApplicationContext(), "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                rememberUser(strUser,strPass,chkRememberPass.isChecked());
                Intent i=new Intent(getApplicationContext(), ManhinhChinhActivity.class);
                i.putExtra("user",strUser);
                startActivity(i);
                finish();
            }else {
                Toast.makeText(getApplicationContext(), "Tên đăng nhập hoặc mật khẩu không đúng", Toast.LENGTH_SHORT).show();
            }
        }
    }
    public void rememberUser(String u,String p,boolean status){
        SharedPreferences pref=getSharedPreferences("USER_FILE",MODE_PRIVATE);
        SharedPreferences.Editor edit=pref.edit();
        if(!status){
            edit.clear();
        }else {
            edit.putString("USERNAME",u);
            edit.putString("PASSWORD",p);
            edit.putBoolean("REMEMBER",status);
        }
        edit.commit();
    }
}