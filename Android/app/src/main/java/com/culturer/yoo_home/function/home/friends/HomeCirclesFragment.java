package com.culturer.yoo_home.function.home.friends;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.armour8.yooplus.yooplus.R;
import com.culturer.yoo_home.function.home.friends.homecircle_list.HomecircleListActivity;


public class HomeCirclesFragment extends Fragment {
    
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    
    private String mParam1;
    private String mParam2;

    private View convertView;
    private TextView circle;
    
    public HomeCirclesFragment() {
        // Required empty public constructor
    }

    public static HomeCirclesFragment newInstance(String param1, String param2) {
        HomeCirclesFragment fragment = new HomeCirclesFragment();
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
        convertView = inflater.inflate(R.layout.fragment_homecircles, container, false);
        init();
        return convertView;
    }

    private void init(){
        initData();
        initView();
    }

    private void initData() {

    }

    private void initView() {

        circle = convertView.findViewById(R.id.circle);
        circle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), HomecircleListActivity.class);
                startActivity(intent);
            }
        });
        
    }




}
