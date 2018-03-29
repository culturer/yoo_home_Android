package com.culturer.yoo_home.function.home.home_activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
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
import com.culturer.yoo_home.event.Activity_Event;
import com.culturer.yoo_home.util.HttpUtil;
import com.culturer.yoo_home.widget.navigation.impl.HomeNavigation;
import com.kymjs.rxvolley.client.HttpCallback;
import com.kymjs.rxvolley.client.HttpParams;
import com.kymjs.rxvolley.http.VolleyError;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

import static com.culturer.yoo_home.config.ParamConfig.HTTP_OPTIONS;
import static com.culturer.yoo_home.config.Urls.ACTIVITIES_ITEM_URL;
import static com.culturer.yoo_home.config.Urls.ACTIVITIES_URL;

public class HomeActiviesActivity extends AppCompatActivity {

    private static final String TAG = "HomeActiviesActivity";

    View contentView;
    ListView home_activities_list;
    List<Activity> activities_data ;
    HomeActivitiesAdapter adapter;
    ListView homeactivity_detail_list;
    AlertDialog dialog;
    Button home_activities_add;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        contentView = LayoutInflater.from(this).inflate(R.layout.activity_home_activies,null);
        setContentView(contentView);
        init();
    }

    private void init(){
        //初始化数据
        initData();
        //初始化UI组件
        initView();
        //初始化EventBus
        EventBus.getDefault().register(this);
    }

    //接收消息
    @Subscribe
    public void receiveEvent(Activity_Event event){
        if (event.type == Activity_Event.NEW && event.getActivity()!=null){
            Log.i(TAG, "receiveEvent: new Activity --- "+event.getActivity().toString());
            activities_data = CacheData.homeActivities;
            adapter.setDataAndrUpdate(activities_data);
        }
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

    //更新活动详情数据
    private HomeActivitiesDetailAdapter initDetailData(Activity activity){
        List<ActivityItem> activityItems = new ArrayList<>();
        //初始化数据
        for (int i=0;i<CacheData.homeActivityItems.size();i++ ){
            if (CacheData.homeActivityItems.get(i).getActivityId() !=null
                    && activity.getId() != null
                    && CacheData.homeActivityItems.get(i).getActivityId() == activity.getId()){
                ActivityItem item = new ActivityItem();
                item.setId(CacheData.homeActivityItems.get(i).getId());
                item.setFamilyId(CacheData.homeActivityItems.get(i).getFamilyId());
                item.setDesc(CacheData.homeActivityItems.get(i).getDesc());
                item.setTitle(CacheData.homeActivityItems.get(i).getTitle());
                item.setCreateTime(CacheData.homeActivityItems.get(i).getCreateTime());
                item.setActivityId(CacheData.homeActivityItems.get(i).getActivityId());
                activityItems.add(item);
            }
        }
        return new HomeActivitiesDetailAdapter(activityItems, this);
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
                    delActivity(i);
                }
                return true;
            }
        });
    }

    //初始化导航条
    private void initNavigation(View contentView){
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
        homeactivity_detail_list = homeactivities_detai.findViewById(R.id.homeactivity_detail_list);
        homeactivity_detail_list.setDivider(null);
        homeactivity_detail_list.setAdapter(initDetailData(activity));
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

    //删除活动
    //1.删除服务器中的数据
    //2.删除缓存中的数据
    //3.删除页面数据,刷新显示
    private void delActivity(final int position){
        new AlertDialog.Builder(this)
                       .setTitle("删除活动")
                       .setMessage(CacheData.homeActivities.get(position).getDesc())
                       .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i){
                                long activityId = CacheData.homeActivities.get(position).getId();
                                //缓存中删除数据
                                if (position>0){
                                    CacheData.homeActivities.remove(position);
                                }
                                //更新页面显示
                                activities_data = CacheData.homeActivities;
                                if (activities_data!=null){
                                    adapter.setDataAndrUpdate(activities_data);
                                }
                                //删除数据库中的记录
                                removeFromDB(activityId);
                                //同步到服务器
                               removeFromServer((int) activityId);
                            }
                       })
                        .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i){
                                dialog.dismiss();
                            }
                        })
                        .create().show();
    }

    private void removeFromServer(int activityId){

        //删除服务器上的activity
        HttpParams params = new HttpParams();
        params.put(HTTP_OPTIONS,2);
        params.put("activityId", activityId);
        HttpCallback callback = new HttpCallback() {
            @Override
            public void onSuccess(String t) {
                Log.i(TAG, "onSuccess: "+t);
            }
            @Override
            public void onFailure(int errorNo, String strMsg) {
                Log.i(TAG, "onFailure: errNo --- " + errorNo + " || errMsg --- " + strMsg);
                Toast.makeText(HomeActiviesActivity.this,"删除活动失败，请检查网络后再尝试操作",Toast.LENGTH_LONG).show();
            }
        };
        HttpUtil.send(callback,params,ACTIVITIES_URL);

        //删除服务器上相应的activityItem
        for (int i=0;i<CacheData.homeActivityItems.size();i++){
            if (CacheData.homeActivityItems.get(i).getActivityId() == activityId){
                HttpParams params1 = new HttpParams();
                final int finalI = i;
                HttpCallback callback1 = new HttpCallback() {
                    int count = finalI;
                    @Override
                    public void onSuccess(String t) {
                        Log.i(TAG, "onSuccess: "+t);
                        CacheData.homeActivityItems.remove(count);
                    }
                    @Override
                    public void onFailure(VolleyError error) {
                        Log.i(TAG, "onFailure: "+error.getMessage());
                    }
                };
                HttpUtil.send(callback1,params1,ACTIVITIES_ITEM_URL);
            }
        }
    }

    private void removeFromDB(long activityId){

    }

}
