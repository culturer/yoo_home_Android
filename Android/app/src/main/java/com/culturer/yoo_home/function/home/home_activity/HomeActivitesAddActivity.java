package com.culturer.yoo_home.function.home.home_activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.armour8.yooplus.yooplus.R;
import com.culturer.yoo_home.bean.ActivityItem;
import com.culturer.yoo_home.cahce.BaseMsg;
import com.culturer.yoo_home.cahce.CacheData;
import com.culturer.yoo_home.widget.navigation.impl.HomeNavigation;

public class HomeActivitesAddActivity extends AppCompatActivity {

    private View contentView;
    private Button add_btn;
    private Button add_ok;
    private ListView add_list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        contentView = LayoutInflater.from(this).inflate(R.layout.activity_home_activites_add,null);
        init();
        setContentView(contentView);
    }

    private void init(){
        initData();
        initView();
    }

    private void initData(){

    }

    private void initView(){
        initNavigation(contentView);
        initBaseView();
    }

    //初始化导航条
    private void initNavigation(View contentView) {
        LinearLayout container = contentView.findViewById(R.id.container);
        HomeNavigation.Builder builder = new HomeNavigation.Builder(this, container);
        builder.setCenterHomeTopic("Yoo+")
                .setCenterHomeTitle("添加活动")
                .create()
                .build();
    }

    //初始化基础控件
    private void initBaseView(){
        add_list = contentView.findViewById(R.id.add_list);
        add_btn = contentView.findViewById(R.id.add_btn);
        add_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View dialogView = LayoutInflater.from(HomeActivitesAddActivity.this).inflate(R.layout.activities_item_add,null);
                new AlertDialog.Builder(HomeActivitesAddActivity.this)
                        .setTitle(CacheData.tmp_activity.getDesc())
                        .setView(dialogView)
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                            //在这里增加业务逻辑处理
                            //1.读取输入数据
                            //2.打包成ActivityItem
                            //3.存入缓存
                            //4.刷新显示
                            //5.同步到服务器
                            addItem();
                            }
                        })
                        .create().show();
            }
        });
    }
    private void addItem(){
        //读取数据

        //打包成ActivityItem
        ActivityItem activityItem = new ActivityItem(-1l,CacheData.tmp_activity.getId(), (long) BaseMsg.getFamily().getId());
        //存入缓存
        CacheData.tmp_activity_item.add(activityItem);
        //刷新显示

    }

}
