package com.example.mob2041_ph32607.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.fragment.app.Fragment;


import com.example.mob2041_ph32607.Adapter.TopAdapter;
import com.example.mob2041_ph32607.DAO.PhieuMuonDAO;
import com.example.mob2041_ph32607.Model.Top;
import com.example.mob2041_ph32607.R;

import java.util.ArrayList;

public class Frag_Top10 extends Fragment {
    ListView lvTop;
    ArrayList< Top > list;
    TopAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_top10, container, false);
        lvTop = v.findViewById(R.id.lvTop);
        PhieuMuonDAO phieuMuonDAO = new PhieuMuonDAO(getActivity());
        list = (ArrayList<Top>) phieuMuonDAO.getTop();
        adapter = new TopAdapter(getActivity(), this, list);
        lvTop.setAdapter(adapter);
        
        return v;
    }
}