package com.culturer.yoo_home.function.home.home_smart;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.culturer.yoo_home.R;


public class SmartFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    View contentView ;

    public SmartFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static SmartFragment newInstance(String param1, String param2) {
        SmartFragment fragment = new SmartFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        contentView = inflater.inflate(R.layout.fragment_smart, container, false);
        init();
        return contentView;
    }

    private void init(){
        initData();
        initView();
    }

    private void initData(){

    }

    private void initView(){
        initBaseView();
    }

    private void initBaseView(){

    }

}
