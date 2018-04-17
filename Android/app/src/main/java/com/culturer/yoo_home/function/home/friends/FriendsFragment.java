package com.culturer.yoo_home.function.home.friends;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.TextView;

import com.armour8.yooplus.yooplus.R;

import java.util.ArrayList;
import java.util.List;


public class FriendsFragment extends Fragment {
    
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    
    private String mParam1;
    private String mParam2;

    private View convertView;
    private TextView circle;
    private ExpandableListView friends;
    
    private FriendsAdapter friendsAdapter;
    private List<String> gruop = new ArrayList<>();
    private List<List<String>> persons = new ArrayList<>();
    
    public FriendsFragment() {
        // Required empty public constructor
    }

    public static FriendsFragment newInstance(String param1, String param2) {
        FriendsFragment fragment = new FriendsFragment();
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
        initData();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        convertView = inflater.inflate(R.layout.fragment_friends, container, false);
        init();
        return convertView;
    }

    private void init(){
        initView();
    }

    private void initData() {
        initListData();
    }

    private void initView() {
        initBaseView();
        initListView();
    }
    
    private void initListData(){
    
        gruop.add("朋友");
        gruop.add("同学");
        gruop.add("发小");
        gruop.add("同事");
        gruop.add("网友");
        gruop.add("陌生人");
        
        for (int i= 0;i<gruop.size();i++){
            List<String> items = new ArrayList<>();
            for (int j = 0 ; j<100 ;j++){
                items.add("朋友"+j);
            }
            persons.add(items);
        }
        
        friendsAdapter = new FriendsAdapter(gruop,persons,getContext());
        
    }
    
    private void initBaseView(){
        friends = convertView.findViewById(R.id.friends);
    }
    
    private void initListView(){
        friends.setAdapter(friendsAdapter);
        friends.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView expandableListView, View view, int i, int i1, long l) {
                //设置点击事件
                return false;
            }
        });
        //互斥展开
        friends.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            @Override
            public void onGroupExpand(int groupPosition) {
                for (int i = 0; i < friendsAdapter.getGroupCount(); i++) {
                    // ensure only one expanded Group exists at every time
                    if (groupPosition != i && friends.isGroupExpanded(groupPosition)) {
                        friends.collapseGroup(i);
                    }
                }
            }
        });
    }

}
