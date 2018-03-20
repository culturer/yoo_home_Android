package com.culturer.yoo_home.function.home.home_activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.armour8.yooplus.yooplus.R;
import com.culturer.yoo_home.bean.ActivityItem;
import com.culturer.yoo_home.cahce.BaseMsg;
import com.culturer.yoo_home.cahce.CacheData;
import com.culturer.yoo_home.widget.navigation.impl.HomeNavigation;

import java.util.ArrayList;
import java.util.List;

public class HomeActivitesAddActivity extends AppCompatActivity {

    private static final String TAG = "HomeActivitesAddActivity" ;

    private View contentView;
    private Button add_btn;
    private Button ok_btn;
    private ListView add_list;

    private HomeActivityItemAddAdapter addAdapter;
    private List<ActivityItem> items ;

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
        items = new ArrayList<>();
        addAdapter = new HomeActivityItemAddAdapter(items,this);
    }

    private void initView(){
        initNavigation(contentView);
        //初始化基础控件
        initBaseView();
        //初始化列表
        initListView();
        //初始化添加按钮
        initAddItem();
        //初始化提交按钮
        initAddOk();
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
        ok_btn = contentView.findViewById(R.id.ok_btn);
    }

    private void initListView(){
        add_list.setAdapter(addAdapter);
        add_list.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                ActivityItem item = items.get(position);
                //删除指定项
                new AlertDialog.Builder(HomeActivitesAddActivity.this)
                        .setTitle("确定删除 --- "+item.getTitle()+" ? ")
                        .setMessage(item.getDesc())
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                //做出删除操作
                            }
                        })
                        .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        })
                        .create().show();
                return true;
            }
        });
        add_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                ActivityItem item = items.get(position);
                final View dialogView = LayoutInflater.from(HomeActivitesAddActivity.this).inflate(R.layout.activities_item_add,null);
                EditText homeacty_alert_title = dialogView.findViewById(R.id.homeacty_alert_title);
                EditText homeacty_alert_content = dialogView.findViewById(R.id.homeacty_alert_content);
                EditText homeacty_alert_time = dialogView.findViewById(R.id.homeacty_alert_time);
                homeacty_alert_title.setText(item.getTitle());
                homeacty_alert_content.setText(item.getDesc());
                homeacty_alert_time.setText(item.getTime());
                new AlertDialog.Builder(HomeActivitesAddActivity.this)
                        .setTitle("修改活动项")
                        .setView(dialogView)
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                EditText homeacty_alert_title = dialogView.findViewById(R.id.homeacty_alert_title);
                                EditText homeacty_alert_content = dialogView.findViewById(R.id.homeacty_alert_content);
                                EditText homeacty_alert_time = dialogView.findViewById(R.id.homeacty_alert_time);
                                final String item_title = homeacty_alert_title.getText().toString();
                                final String item_content = homeacty_alert_content.getText().toString();
                                final String item_time = homeacty_alert_time.getText().toString();

                                //进行修改操作
                                changeItem(position,item_title,item_content,item_time);

                                dialog.dismiss();

                            }
                        })
                        .create().show();
            }
        });
    }

    private void initAddItem(){
        add_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final View dialogView = LayoutInflater.from(HomeActivitesAddActivity.this).inflate(R.layout.activities_item_add,null);
                new AlertDialog.Builder(HomeActivitesAddActivity.this)
                        .setTitle("新增活动项")
                        .setView(dialogView)
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                EditText homeacty_alert_title = dialogView.findViewById(R.id.homeacty_alert_title);
                                EditText homeacty_alert_content = dialogView.findViewById(R.id.homeacty_alert_content);
                                EditText homeacty_alert_time = dialogView.findViewById(R.id.homeacty_alert_time);
                                final String item_title = homeacty_alert_title.getText().toString();
                                final String item_content = homeacty_alert_content.getText().toString();
                                final String item_time = homeacty_alert_time.getText().toString();
                                addItem(item_title,item_content,item_time);
                                dialog.dismiss();
                            }
                        })
                        .create().show();
            }
        });
    }

    private void initAddOk(){
        ok_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //提交Activity数据
                addOption();
            }
        });
    }

    //添加活动项
    private void addItem(String item_title,String item_content,String item_time){
        //读取数据
        //打包成ActivityItem
        ActivityItem activityItem = new ActivityItem(-1l, (long) BaseMsg.getFamily().getId(),CacheData.tmp_activity.getId(),-1l,item_title,item_time,item_content);
        Log.i(TAG, "addItem: "+activityItem.toString());
        //存入缓存
//        CacheData.tmp_activity_item.add(activityItem);
        //存入缓存应该放在与服务器同步数据之后
        //刷新显示
        items.add(activityItem);
        addAdapter.setDataAndrUpdate(items);
    }

    //修改活动项
    private void changeItem(int position , String title , String content , String time){
        //刷新列表显示
        ActivityItem item = items.get(position);
        item.setDesc(content);
        item.setTitle(title);
        item.setTime(time);
        items.set(position,item);
        addAdapter.setDataAndrUpdate(items);
    }

    //确定提交操作
    private void addOption(){
        //将CacheData.tmp_activity 以及items中的数据同步到服务器
        //将数据存到缓存以及本地数据库
        //刷新显示
    }

}
