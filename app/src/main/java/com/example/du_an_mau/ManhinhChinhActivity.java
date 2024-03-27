package com.example.du_an_mau;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.du_an_mau.DAO.ThuThuDAO;
import com.example.du_an_mau.Model.ThuThuModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.navigation.NavigationView;

public class ManhinhChinhActivity extends AppCompatActivity  {
private DrawerLayout dl1;
    Toolbar tb1;
    FragPhieuMuon frpm;
    FragmentManager mng;

    TextView tv_user;
    View header;
    ThuThuDAO dao;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manhinh_chinh);

         tb1=findViewById(R.id.tb1);
        setSupportActionBar(tb1);

        dl1=findViewById(R.id.dl1);
        ActionBarDrawerToggle abd=new ActionBarDrawerToggle(this,dl1,tb1,R.string.open,R.string.close);
        abd.setDrawerIndicatorEnabled(true);
        dl1.addDrawerListener(abd);
        abd.syncState();

        NavigationView nv1=findViewById(R.id.nv1);
        nv1.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment fr=null;
              int id=item.getItemId();
                if(id==R.id.nav_Phieumuon){
                    fr=new FragPhieuMuon();
                    setTitle("Quản lý phiếu mượn");
                }else if(id==R.id.nav_Loaisach){
                    fr=new FragLoaisach();
                    tb1.setTitle("Quản lý loại sách");
                }else if(id==R.id.nav_Sach){
                    fr=new FragSach();
                    tb1.setTitle("Quản lý sách");
                }
                else if(id==R.id.nav_Thanhvien){
                    fr=new FragThanhvien();
                    tb1.setTitle("Quản lý thành viên");
                }
                else if(id==R.id.nav_Top10){
                    fr=new FragTop10();
                    tb1.setTitle("Top 10 sách");
                }
                else if(id==R.id.nav_Doanhthu){
                    fr=new FragDoanhthu();
                    tb1.setTitle("Doanh thu");
                }
                else if(id==R.id.nav_Doimatkhau){
                    fr=new FragDoimatkhau();
                    tb1.setTitle("Đổi mật khẩu");
                }
                else if(id==R.id.nav_Themnguoidung){
                    fr=new FragThemnguoidung();
                    tb1.setTitle("Thêm người dùng");
                }
                else if(id==R.id.nav_Dangxuat){
                    startActivity(new Intent(getApplicationContext(), DangnhapActivity.class));
                    finish();
                }
                getSupportFragmentManager().beginTransaction().replace(R.id.fl1,fr).commit();
                dl1.close();
                return true;
            }});
        mng=getSupportFragmentManager();
        frpm=new FragPhieuMuon();
        mng.beginTransaction().replace(R.id.fl1,frpm).commit();
        header= nv1.getHeaderView(0);
        tv_user=header.findViewById(R.id.tv_user);
        Intent i=getIntent();
        String user=i.getStringExtra("user");
        dao=new ThuThuDAO(this);
        ThuThuModel thuthu=dao.getID(user);
        String username=thuthu.hoTen_tt;
        tv_user.setText("Welcome "+username+"!");

        if(user.equalsIgnoreCase("admin")){
            nv1.getMenu().findItem(R.id.nav_Themnguoidung).setVisible(true);
        }
        }



}