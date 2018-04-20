package com.culturer.yoo_home.function.main;

import android.app.ActivityManager;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.armour8.yooplus.yooplus.R;
import com.culturer.yoo_home.function.home.HomeFragment;
import com.culturer.yoo_home.function.mine.MineFragment;
import com.culturer.yoo_home.function.setting.FamilyManagerActivity;
import com.culturer.yoo_home.function.setting.HomeManagerActivity;
import com.culturer.yoo_home.function.setting.PrivacySettingActivity;
import com.culturer.yoo_home.function.setting.SafeSettingActivity;
import com.culturer.yoo_home.function.world.WorldFragment;
import com.culturer.yoo_home.widget.navigation.impl.HomeNavigation;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener  {

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
        setContentView(R.layout.activity_main);
        init();
    }

    private void init(){
        initData();
        initView();
    }
    
    private void initData(){
        initFragments();
        adapter = new MainPagerAdapter(getSupportFragmentManager(),fragments);
    }
    private void initView(){
        contentView = findViewById(R.id.container);
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
        initDrawer();
    }

    private void initNavigation(View contentView) {
        LinearLayout topNavigation = contentView.findViewById(R.id.container);
        HomeNavigation.Builder builder = new HomeNavigation.Builder(this, topNavigation);
        builder.setCenterHomeTopic("Yoo+")
                .setCenterHomeTitle("心若向阳，无畏悲伤")
                .create().
                build();
    }
    
    private void initDrawer(){
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, null, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
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
    
    
    
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        Intent intent = null;
        if (id == R.id.home_manager) {
           intent = new Intent(this, HomeManagerActivity.class);
        } else if (id == R.id.family_manager) {
            intent = new Intent(this, FamilyManagerActivity.class);
        } else if (id == R.id.privacy_setting) {
            intent = new Intent(this, PrivacySettingActivity.class);
        } else if (id == R.id.safe_setting) {
            intent = new Intent(this, SafeSettingActivity.class);
        }
        if (intent!=null) startActivity(intent);
        
        
        if (id == R.id.add_friend) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("添加朋友")
//                    .setView()
            .create().show();
        } else if (id == R.id.add_group) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("群组")
//                    .setView()
                    .create().show();
        }
        
    
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
        
    }
    
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
    
    //记录用户首次点击返回键的时间
    private long firstTime=0;
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode==KeyEvent.KEYCODE_BACK && event.getAction()==KeyEvent.ACTION_DOWN){
            if (System.currentTimeMillis()-firstTime>2000){
                Toast.makeText(MainActivity.this,"双击返回键退出应用！",Toast.LENGTH_SHORT).show();
                firstTime=System.currentTimeMillis();
            }else{
                return super.onKeyDown(keyCode, event);
//                finish();
//                System.exit(0);
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
    
}
