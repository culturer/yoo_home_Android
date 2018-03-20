package com.culturer.yoo_home.function.home.home_activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.armour8.yooplus.yooplus.R;
import com.culturer.yoo_home.bean.Activity;
import com.culturer.yoo_home.bean.ActivityItem;
import com.culturer.yoo_home.cahce.CacheData;
import com.culturer.yoo_home.widget.navigation.impl.HomeNavigation;

import java.util.ArrayList;
import java.util.List;

public class HomeActiviesActivity extends AppCompatActivity {

    View contentView;
    ListView home_activities_list;
    List<Activity> activities_data ;
    HomeActivitiesAdapter adapter;
    ListView homeactivity_detail_list;
    HomeActivitiesDetailAdapter detailAdapter;
    AlertDialog dialog;
    Button home_activities_add;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        contentView = LayoutInflater.from(this).inflate(R.layout.activity_home_activies,null);
        setContentView(contentView);
        //初始化数据
        initData();
        //初始化UI组件
        initView();
    }

    //初始化数据
    private void initData(){
        initListData();
    }

    //初始化列表数据
    private void initListData(){
        activities_data = CacheData.homeActivities;
        adapter = new HomeActivitiesAdapter(activities_data,this);
    }
    //初始化视图
    private void initView(){
        initNavigation(contentView);
        initBaseView();
        initList();
    }

    //初始化基本UI组件
    private void initBaseView(){
        home_activities_list = findViewById(R.id.home_activities_list);
        home_activities_add = contentView.findViewById(R.id.home_activities_add);
        home_activities_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(HomeActiviesActivity.this,"click",Toast.LENGTH_LONG).show();
                Intent intent = new Intent(HomeActiviesActivity.this,HomeActivitesAddActivity.class);
                startActivity(intent);
            }
        });
    }

    //初始化列表
    private void initList(){
        home_activities_list.setAdapter(adapter);
        //添加点击事件,点击显示活动详情
        home_activities_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if (activities_data!=null &&activities_data.get(i)!=null){
                    showActivity(activities_data.get(i));
                }
            }
        });
        //添加长按事件,长按删除活动
        home_activities_list.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                if (activities_data!=null &&activities_data.get(i)!=null){
                    delActivity(activities_data.get(i));
                }
                return true;
            }
        });
    }

    //初始化导航条
    private void initNavigation(View contentView) {
        LinearLayout topNavigation = contentView.findViewById(R.id.home_activities_topNavigation);
        HomeNavigation.Builder builder = new HomeNavigation.Builder(this, topNavigation);
        builder.setCenterHomeTopic("Yoo+")
                .setCenterHomeTitle("家庭活动")
                .create()
                .build();
    }

    //显示活动详情
    //1.从缓存读取ActivityItem显示
    //2.如果缓存中数据为空,则刷新缓存再获取
    private void showActivity(Activity activity){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View homeactivities_detai = LayoutInflater.from(this).inflate(R.layout.homeactivities_detail,null);
        initDetailData();
        initDetailView(homeactivities_detai);
        builder.setTitle(activity.getDesc())
                .setView(homeactivities_detai);
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        dialog = builder.create();
        dialog.show();
    }

    //初始化活动详情数据
    private void initDetailData(){
        List<ActivityItem> activityItems = new ArrayList<>();
        for (int i=0;i<10;i++){
            activityItems.add(new ActivityItem());
        }
        detailAdapter = new HomeActivitiesDetailAdapter(activityItems,this);
    }

    //初始化活动详情视图
    private void initDetailView(View convertView){
        homeactivity_detail_list = convertView.findViewById(R.id.homeactivity_detail_list);
        homeactivity_detail_list.setDivider(null);
        homeactivity_detail_list.setAdapter(detailAdapter);
    }

    //删除活动
    //1.删除服务器中的数据
    //2.删除缓存中的数据
    //3.删除页面数据,刷新显示。
    private void delActivity(Activity activity){
        new AlertDialog.Builder(this)
                       .setTitle("删除活动")
                       .setMessage(activity.getDesc())
                       .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                       })
                        .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        })
                        .create().show();
    }
}
