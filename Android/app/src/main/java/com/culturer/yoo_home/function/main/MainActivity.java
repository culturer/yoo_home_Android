package com.culturer.yoo_home.function.main;

import android.app.ActivityManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;

import com.armour8.yooplus.yooplus.R;
import com.culturer.yoo_home.function.home.HomeFragment;
import com.culturer.yoo_home.function.mine.MineFragment;
import com.culturer.yoo_home.function.world.WorldFragment;
import com.culturer.yoo_home.widget.navigation.impl.HomeNavigation;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private View contentView;

    private List<Fragment> fragments;
    BottomNavigationView navigation;
    private ViewPager fragment_container;
    private  MainPagerAdapter adapter;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    fragment_container.setCurrentItem(0);
                    return true;
                case R.id.navigation_dashboard:
                    fragment_container.setCurrentItem(1);
                    return true;
                case R.id.navigation_notifications:
                    fragment_container.setCurrentItem(2);
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        contentView = getLayoutInflater().inflate(R.layout.activity_main,null);
        setContentView(contentView);
        initData();
        initView();
    }

    private void initData(){
        initFragments();
        adapter = new MainPagerAdapter(getSupportFragmentManager(),fragments);
    }
    private void initView(){
        fragment_container = findViewById(R.id.fragment_container);
        navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        fragment_container.setCurrentItem(0);
        fragment_container.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (fragments!=null){
                    switchTab(position%fragments.size());
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        fragment_container.setAdapter(adapter);
        switchTab(2);
        initNavigation(contentView);
    }

    private void initNavigation(View contentView) {
        LinearLayout topNavigation = contentView.findViewById(R.id.container);
        HomeNavigation.Builder builder = new HomeNavigation.Builder(this, topNavigation);
        builder.setCenterHomeTopic("Yoo+")
                .setCenterHomeTitle("心若向阳无畏悲伤")
                .create().
                build();
    }

    private void initFragments(){
        HomeFragment homeFragment = new HomeFragment();
        WorldFragment worldFragment = new WorldFragment();
        MineFragment mineFragment = new MineFragment();
        fragments = new ArrayList<>();
        fragments.add(homeFragment);
        fragments.add(worldFragment);
        fragments.add(mineFragment);
    }

    private void switchTab(int position){
        if (position == 0){
            navigation.setSelectedItemId(R.id.navigation_home);
        }
        else if (position == 1){
            navigation.setSelectedItemId(R.id.navigation_dashboard);
        }
        else if (position == 2){
            navigation.setSelectedItemId(R.id.navigation_notifications);
        }
    }

}
