package com.culturer.yoo_home.function.family;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.culturer.yoo_home.R;
import com.culturer.yoo_home.function.family.family_picture.FamilyPictureActivity;
import com.culturer.yoo_home.function.family.homecircle_list.HomecircleListActivity;
import com.culturer.yoo_home.widget.treeView.model.TreeModel;


public class FamilyFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;
    
    private TreeModel<String> tree;
    
    private View contentView;
    private TextView circle;
    private TextView family_picture;
    private TextView family_father;
    private TextView family_mother;
    private TextView family_other;

    public FamilyFragment() {
        // Required empty public constructor
    }

    public static FamilyFragment newInstance(String param1, String param2) {
        FamilyFragment fragment = new FamilyFragment();
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
        contentView = inflater.inflate(R.layout.fragment_family, container, false);
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
        family_picture = contentView.findViewById(R.id.family_picture);
        family_father = contentView.findViewById(R.id.family_father);
        family_mother = contentView.findViewById(R.id.family_mother);
        family_other = contentView.findViewById(R.id.family_other);
        circle = contentView.findViewById(R.id.circle);
        circle.setOnClickListener(v -> {
            Intent intent = new Intent(getContext(), HomecircleListActivity.class);
            startActivity(intent);
        });
        family_picture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(),FamilyPictureActivity.class);
                startActivity(intent);
            }
        });
        family_father.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
        
            }
        });
        family_mother.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
        
            }
        });
        family_other.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
        
            }
        });
    }
    
}
