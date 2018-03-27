package com.culturer.yoo_home.function.home.family_activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.armour8.yooplus.yooplus.R;
import com.culturer.yoo_home.widget.navigation.impl.HomeNavigation;

public class FamilyActivityActivity extends AppCompatActivity {

    View contentView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        contentView = LayoutInflater.from(this).inflate(R.layout.activity_family_activity,null);
        setContentView(contentView);
        init();
    }

    private void init(){
        initData();
        initView();
    }

    private void initData(){

    }

    private void initView(){
        initNavigation(contentView);
    }

    private void initNavigation(View contentView) {
        LinearLayout topNavigation = (LinearLayout) contentView.findViewById(R.id.family_activities_topNavigation);
        HomeNavigation.Builder builder = new HomeNavigation.Builder(this, topNavigation);
        builder.setCenterHomeTopic("Yoo+")
                .setCenterHomeTitle("家族活动")
                .create()
                .build();
    }
}
