package com.culturer.yoo_home.function.mine;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.armour8.yooplus.yooplus.R;
import com.culturer.yoo_home.widget.directionViewPager.DirectionalViewPager;


import java.util.ArrayList;
import java.util.List;


public class MineFragment extends Fragment {

    private static final String TAG = "MineFragment" ;

    private View contentView;
    private PagerAdapter pagerAdapter;
    private List<Fragment> pages;

    public MineFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        contentView = inflater.inflate(R.layout.fragment_mine, container, false);
        init();
        return contentView;
    }

    private void init() {
        initData();
        initView();
    }

    private void initData() {
        pages = new ArrayList<>();
        pages.add(Page1Fragment.newInstance("",""));
        pages.add(Page2Fragment.newInstance("",""));
        pagerAdapter = new PagerAdapter(getChildFragmentManager(),pages);
    }

    private void initView(){
        DirectionalViewPager pager = contentView.findViewById(R.id.pager);
        pager.setOrientation(DirectionalViewPager.VERTICAL);
        pager.setAdapter(pagerAdapter);
    }

}

