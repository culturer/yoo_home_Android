package com.culturer.yoo_home.function.home.home_activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.armour8.yooplus.yooplus.R;
import com.culturer.yoo_home.bean.Activity;
import com.culturer.yoo_home.bean.ActivityItem;
import com.culturer.yoo_home.cahce.BaseMsg;
import com.culturer.yoo_home.cahce.CacheData;
import com.culturer.yoo_home.event.Activity_Event;
import com.culturer.yoo_home.util.HttpUtil;
import com.culturer.yoo_home.util.TimeUtil;
import com.culturer.yoo_home.widget.navigation.impl.HomeNavigation;
import com.kymjs.rxvolley.client.HttpCallback;
import com.kymjs.rxvolley.client.HttpParams;

import org.greenrobot.eventbus.EventBus;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static com.culturer.yoo_home.config.ParamConfig.HTTP_OPTIONS;
import static com.culturer.yoo_home.config.ParamConfig.HTTP_STATUS;
import static com.culturer.yoo_home.config.ParamConfig.HTTP_STATUS_SUCCESS;
import static com.culturer.yoo_home.config.Urls.ACTIVITIES_ITEM_URL;
import static com.culturer.yoo_home.config.Urls.ACTIVITIES_URL;


public class HomeActivitesAddActivity extends AppCompatActivity {

    private static final String TAG = "HomeActivitesAddActivity" ;

    private View contentView;
    private Button add_btn;
    private Button ok_btn;
    private ListView add_list;
    private EditText add_title;

    private HomeActivityItemAddAdapter addAdapter;
    private List<ActivityItem> items;

    private int num;
    
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
        //初始化临时数据
        CacheData.tmp_activity = new Activity();
        CacheData.tmp_activity_item.clear();
        num = 0;
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
        add_title = contentView.findViewById(R.id.add_title);
    }

    private void initListView(){
        add_list.setAdapter(addAdapter);
        add_list.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                ActivityItem item = items.get(position);
                //删除指定项
                new AlertDialog.Builder(HomeActivitesAddActivity.this)
                        .setTitle("确定删除 --- "+item.getTitle()+" ? ")
                        .setMessage(item.getDesc())
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                //做出删除操作
                                delItem(position);
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
                homeacty_alert_time.setText(item.getCreateTime());
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
        ActivityItem activityItem = new ActivityItem(-1l, (long) BaseMsg.getFamily().getId(),CacheData.tmp_activity.getId(),item_title,item_time,item_content,num++);
        Log.i(TAG, "addItem: "+activityItem.toString());
        //存入临时缓存
        CacheData.tmp_activity_item.add(activityItem);
        //刷新显示
        items.add(activityItem);
        addAdapter.setDataAndrUpdate(items);
    }

    //修改活动项
    private void changeItem(int position , String title , String content , String time){
        CacheData.tmp_activity_item.get(position).setDesc(content);
        CacheData.tmp_activity_item.get(position).setTitle(title);
        CacheData.tmp_activity_item.get(position).setCreateTime(time);
        items =  CacheData.tmp_activity_item;
        addAdapter.setDataAndrUpdate(items);
    }

    //删除活动项
    private void delItem(int position){
        items.remove(position);
        CacheData.tmp_activity_item.remove(position);
        addAdapter.setDataAndrUpdate(items);
    }

    //确定提交操作
    private void addOption(){
        //将数据备份进缓存
        CacheData.tmp_activity.setCreateTime(TimeUtil.getCurrentTime());
        CacheData.tmp_activity.setActivityType(true);
        CacheData.tmp_activity.setAddressId(-1L);
        CacheData.tmp_activity.setDesc(add_title.getText().toString());
        CacheData.tmp_activity.setFamilyId((long) BaseMsg.getFamily().getId());
        //从缓存中读出临时数据并打包
        Activity activity = new Activity();
        activity.setFamilyId( CacheData.tmp_activity.getFamilyId());
        activity.setActivityType( CacheData.tmp_activity.isActivityType());
        activity.setAddressId( CacheData.tmp_activity.getAddressId());
        activity.setDesc( CacheData.tmp_activity.getDesc());
        activity.setCreateTime( CacheData.tmp_activity.getCreateTime());
        //刷新显示
        items.clear();
        addAdapter.setDataAndrUpdate(items);
        //发送活动数据
        sendActivity(activity);
    }

    //将CacheData.tmp_activity 以及items中的数据同步到服务器
    private void sendActivity(final Activity activity){

        if ( activity.getDesc() == null ){
            Toast.makeText(this,"活动标题为空，请输入活动标题！",Toast.LENGTH_LONG).show();
        }
        else if (activity.getFamilyId() == null || activity.getFamilyId() == 0){
            Toast.makeText(this,"家庭信息为空，请完善家庭信息后再发起活动！",Toast.LENGTH_LONG).show();
            //在此跳转到完善家庭信息的页面
            
        }
        else {
            HttpParams params = new HttpParams();
            params.put(HTTP_OPTIONS,1);
            params.put("activityType","true");
            params.put("familyId",BaseMsg.getFamily().getId());
            params.put("desc",activity.getDesc());
            params.put("addressId",-1);
    
            HttpCallback callback = new HttpCallback() {
                @Override
                public void onSuccess(String t) {
                    Log.i(TAG, "sendActivity: "+t);
                    try {
                        JSONObject jsonObject = new JSONObject(t);
                        if (jsonObject.getInt(HTTP_STATUS) == HTTP_STATUS_SUCCESS){
                            int activityId = jsonObject.getInt("activityId");
                            CacheData.tmp_activity.setId((long) activityId);
                            activity.setId((long) activityId);
                            CacheData.homeActivities.add(activity);
                            //发送广播
                            EventBus.getDefault().post(new Activity_Event(Activity_Event.NEW,CacheData.tmp_activity));
                            //发送具体的活动项
                            sendItem(activity);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                @Override
                public void onFailure(int errorNo, String strMsg) {
                    Log.i(TAG, "onFailure: errNo --- "+errorNo+" || errMsg --- "+strMsg);
                }
            };
            HttpUtil.send(callback,params,ACTIVITIES_URL);
            //存数据库
            save2DB(CacheData.tmp_activity);
            HomeActivitesAddActivity.this.finish();
        }
    }
    
    //发送活动项
    private void sendItem(Activity activity){
        
        //--------------------------------打印一下内存中的数据，调试用-----------------------------------------//
        for (int i=0; i<CacheData.tmp_activity_item.size();i++){
            Log.i(TAG, "CacheData.tmp_activity_item: ["+i+"] --- "+CacheData.tmp_activity_item.get(i).toString());
        }
        //用完记得删掉
        //--------------------------------打印一下内存中的数据，调试用-----------------------------------------//
        
        for (int i=0 ;i<CacheData.tmp_activity_item.size();i++){
            CacheData.tmp_activity_item.get(i).setActivityId(activity.getId());
            HttpParams params = new HttpParams();
            params.put(HTTP_OPTIONS,1);
            params.put("familyId",BaseMsg.getFamily().getId());
            params.put("activityId", String.valueOf(CacheData.tmp_activity_item.get(i).getActivityId()));
            params.put("title",CacheData.tmp_activity_item.get(i).getTitle());
            params.put("desc",CacheData.tmp_activity_item.get(i).getDesc());
            params.put("num",CacheData.tmp_activity_item.get(i).getNum());
            params.put("createTime",CacheData.tmp_activity_item.get(i).getCreateTime());
            final int finalI = i;
            HttpCallback callback = new HttpCallback() {
                int count = finalI;
                @Override
                public void onSuccess(String t) {
                    
                    //--------------------------------打印一下内存中的数据，调试用-----------------------------------------//
    
                    Log.i(TAG, "callbackItem: count --- "+count+"，callback --- "+t);
                    
                    //用完记得删掉
                    //--------------------------------打印一下内存中的数据，调试用-----------------------------------------//
                    
                    try {
                        JSONObject jsonObject = new JSONObject(t);
                        if (jsonObject.getInt(HTTP_STATUS )== HTTP_STATUS_SUCCESS){
                            int activityItemId = jsonObject.getInt("activityItemId");
                            CacheData.tmp_activity_item.get(count).setId((long) activityItemId);
                            CacheData.homeActivityItems.add( CacheData.tmp_activity_item.get(count));
                            if (count >= CacheData.tmp_activity_item.size()-1){
                                Log.i(TAG, "activity send finished ! now start clear cache data ! ");
                                CacheData.tmp_activity_item.clear();
                            }
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                @Override
                public void onFailure(int errorNo, String strMsg) {
                    Log.i(TAG, "onFailure: errNo --- "+errorNo+" || errMsg --- "+strMsg);
                }
            };
            HttpUtil.send(callback,params,ACTIVITIES_ITEM_URL);
        }
    }

    private void save2DB(ActivityItem item){
        Log.i(TAG, "save2DB(ActivityItem): id --- "+item.getId()+",desc --- "+item.getDesc());
    }

    private void save2DB(Activity activity){
        Log.i(TAG, "savw2DB(Activity): id --- "+activity.getId()+",desc --- "+activity.getDesc());
    }

}
