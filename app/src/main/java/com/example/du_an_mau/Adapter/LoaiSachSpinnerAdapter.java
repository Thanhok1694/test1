package com.example.du_an_mau.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.du_an_mau.Model.LoaiSachModel;
import com.example.du_an_mau.R;

import java.util.ArrayList;

public class LoaiSachSpinnerAdapter extends ArrayAdapter<LoaiSachModel> {
    private Context context;
    private ArrayList<LoaiSachModel> list;
    TextView tv_maloai,tv_tenloai;

    public LoaiSachSpinnerAdapter(@NonNull Context context, ArrayList<LoaiSachModel> list) {
        super(context, 0,list);
        this.context=context;
        this.list=list;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
View v=convertView;
if(v==null){
    LayoutInflater inf=(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    v=inf.inflate(R.layout.item_spinner_loaisach,null);
}
final LoaiSachModel ls=list.get(position);
if(ls!=null){
    tv_maloai=v.findViewById(R.id.tv_spinner_maloaisach);
    tv_tenloai=v.findViewById(R.id.tv_spinner_tenloaisach);
    tv_maloai.setText(ls.maLoai+"");
    tv_tenloai.setText(ls.tenLoai);
}
return v;
    }
    public View getDropDownView(int position,@Nullable View convertView,@NonNull ViewGroup parent){

        View v=convertView;
        if(v==null){
            LayoutInflater inf=(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v=inf.inflate(R.layout.item_spinner_loaisach,null);

        }
        final LoaiSachModel ls=list.get(position);
        if(ls!=null){
            tv_maloai=v.findViewById(R.id.tv_spinner_maloaisach);
            tv_tenloai=v.findViewById(R.id.tv_spinner_tenloaisach);
            tv_maloai.setText(ls.maLoai+"");
            tv_tenloai.setText(ls.tenLoai);
        }
        return v;
    }
}
