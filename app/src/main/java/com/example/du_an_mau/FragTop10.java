package com.example.du_an_mau;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.du_an_mau.Adapter.TopAdapter;
import com.example.du_an_mau.DAO.ThongKeDAO;
import com.example.du_an_mau.Model.Top;

import java.util.ArrayList;


public class FragTop10 extends Fragment {
    ListView lv_top10;
    TopAdapter adt;
    ArrayList<Top> list;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v=inflater.inflate(R.layout.fragment_frag_top10,container,false);
        lv_top10=v.findViewById(R.id.lv_top10);
        ThongKeDAO tkdao=new ThongKeDAO(getActivity());
        list=(ArrayList<Top>) tkdao.getTop();
        adt=new TopAdapter(getActivity(),this,list);
        lv_top10.setAdapter(adt);
        return v;
    }
}