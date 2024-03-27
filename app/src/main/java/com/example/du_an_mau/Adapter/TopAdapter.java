package com.example.du_an_mau.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.du_an_mau.FragTop10;
import com.example.du_an_mau.Model.Top;
import com.example.du_an_mau.R;

import java.util.ArrayList;

public class TopAdapter extends ArrayAdapter<Top> {
    private Context context;
     private ArrayList<Top> list;
    FragTop10 frt;
    TextView tv_tensach,tv_soluong;


    public TopAdapter(@NonNull Context context, FragTop10 frt, ArrayList<Top> list) {
        super(context, 0,list);
        this.context=context;
        this.frt=frt;
        this.list=list;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
      View v=convertView;

      if(v==null){
          LayoutInflater inf=(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
          v=inf.inflate(R.layout.item_top10,null);
      }
      final Top top=list.get(position);
      if(top!=null){
          tv_tensach=v.findViewById(R.id.tv_tensach);
          tv_soluong=v.findViewById(R.id.tv_soluong);
          tv_tensach.setText("Tên sách: "+top.tensach);
          tv_soluong.setText("Số lượng: "+top.soluong);
      }
      return v;
    }
}
