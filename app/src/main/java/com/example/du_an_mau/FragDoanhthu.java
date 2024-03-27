package com.example.du_an_mau;

import android.app.DatePickerDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import com.example.du_an_mau.DAO.ThongKeDAO;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;


public class FragDoanhthu extends Fragment {
Button btn_tungay,btn_denngay,btn_xemdoanhthu;
EditText ed_tungay,ed_denngay;
TextView tv_doanhthu;
SimpleDateFormat sdf=new SimpleDateFormat("dd-MM-yyyy");
int mYear,mMonth,mDay;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v=inflater.inflate(R.layout.fragment_frag_doanhthu,container,false);
        ed_tungay=v.findViewById(R.id.ed_tungay);
        ed_denngay=v.findViewById(R.id.ed_denngay);
        btn_tungay=v.findViewById(R.id.btn_tungay);
        btn_denngay=v.findViewById(R.id.btn_denngay);
        tv_doanhthu=v.findViewById(R.id.tv_doanhthu);
        btn_xemdoanhthu=v.findViewById(R.id.btn_xemdoanhthu);

        btn_tungay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar c=Calendar.getInstance();
                mYear=c.get(Calendar.YEAR);
                mMonth=c.get(Calendar.MONTH);
                mDay=c.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog d=new DatePickerDialog(getActivity(),0,mDateTuNgay,mYear,mMonth,mDay);
                d.show();

            }
        });
        btn_denngay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar c=Calendar.getInstance();
                mYear=c.get(Calendar.YEAR);
                mMonth=c.get(Calendar.MONTH);
                mDay=c.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog d=new DatePickerDialog(getActivity(),0,mDateDenNgay,mYear,mMonth,mDay);
                d.show();
            }
        });
        btn_xemdoanhthu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tuNgay=ed_tungay.getText().toString();
                String denNgay=ed_denngay.getText().toString();
                ThongKeDAO tkdao=new ThongKeDAO(getActivity());
                tv_doanhthu.setText("Doanh thu: "+tkdao.getDoanhthu(tuNgay,denNgay)+" VNĐ");
            }
        });
        return v;
    }
    DatePickerDialog.OnDateSetListener mDateTuNgay=new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            mYear=year;
            mMonth=month;
            mDay=dayOfMonth;
            GregorianCalendar c=new GregorianCalendar(mYear,mMonth,mDay);
            ed_tungay.setText(sdf.format(c.getTime()));

        }
    };
    DatePickerDialog.OnDateSetListener mDateDenNgay=new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            mYear=year;
            mMonth=month;
            mDay=dayOfMonth;
            GregorianCalendar c=new GregorianCalendar(mYear,mMonth,mDay);
            ed_denngay.setText(sdf.format(c.getTime()));

        }
    };
}