package com.example.du_an_mau.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.du_an_mau.Model.SachModel;
import com.example.du_an_mau.R;

import java.util.ArrayList;

public class SachSpinnerAdapter extends ArrayAdapter<SachModel> {
    private Context context;
    private ArrayList<SachModel> list;
    TextView tv_masach,tv_tensach;

    public SachSpinnerAdapter(@NonNull Context context, ArrayList<SachModel> list) {
        super(context, 0,list);
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v=convertView;
        if(v==null){
            LayoutInflater inf=(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v=inf.inflate(R.layout.item_spinner_sach,null);
        }
        final SachModel sa=list.get(position);
        if(sa!=null){
            tv_masach=v.findViewById(R.id.tv_spinner_masach);
            tv_masach.setText(sa.maSach+"");
            tv_tensach=v.findViewById(R.id.tv_spinner_tensach);
            tv_tensach.setText(sa.tenSach);
        }
        return v;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v=convertView;
        if(v==null){
            LayoutInflater inf=(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v=inf.inflate(R.layout.item_spinner_sach,null);

        }
        final SachModel sa=list.get(position);
        if(sa!=null){
            tv_masach=v.findViewById(R.id.tv_spinner_masach);
            tv_tensach=v.findViewById(R.id.tv_spinner_tensach);
            tv_masach.setText(sa.maSach+".  ");
            tv_tensach.setText(sa.tenSach);
        }
        return v;
    }
}
