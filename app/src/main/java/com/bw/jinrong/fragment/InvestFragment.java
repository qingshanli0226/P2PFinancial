package com.bw.jinrong.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bw.jinrong.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class InvestFragment extends Fragment {

    private View view;

    public InvestFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_invest, container, false);
        return view;
    }

}
