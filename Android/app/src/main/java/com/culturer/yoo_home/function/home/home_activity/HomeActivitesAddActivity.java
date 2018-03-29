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
        ActivityItem activityItem = new ActivityItem(-1l, (long) BaseMsg.getFamily().getId(),CacheData.tmp_activity.getId(),item_title,item_time,item_content);
        Log.i(TAG, "addItem: "+activityItem.toString());
        //存入临时缓存
        CacheData.tmp_activity_item.add(activityItem);
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
        item.setCreateTime(time);
        addAdapter.setDataAndrUpdate(items);
        //刷新临时缓存
        ActivityItem tmp_item = CacheData.tmp_activity_item.get(position);
        tmp_item.setDesc(content);
        tmp_item.setTitle(title);
        tmp_item.setCreateTime(time);
    }

    //删除活动项
    private void delItem(int position){
        items.remove(position);
        CacheData.tmp_activity_item.remove(position);
        addAdapter.setDataAndrUpdate(items);
    }

    //确定提交操作
    private void addOption(){

        CacheData.tmp_activity.setCreateTime(TimeUtil.getCurrentTime());
        CacheData.tmp_activity.setActivityType(true);
        CacheData.tmp_activity.setAddressId(-1l);
        CacheData.tmp_activity.setDesc(add_title.getText().toString());
        CacheData.tmp_activity.setFamilyId((long) BaseMsg.getFamily().getId());

        Activity activity = new Activity();
        activity.setFamilyId( CacheData.tmp_activity.getFamilyId());
        activity.setActivityType( CacheData.tmp_activity.isActivityType());
        activity.setAddressId( CacheData.tmp_activity.getAddressId());
        activity.setDesc( CacheData.tmp_activity.getDesc());
        activity.setCreateTime( CacheData.tmp_activity.getCreateTime());

        sendActivity(activity);
        //存缓存
        CacheData.homeActivities.add(CacheData.tmp_activity);
        //存数据库
        savw2DB(CacheData.tmp_activity);

        //将数据存到缓存以及本地数据库
        for (int i= 0; i<CacheData.tmp_activity_item.size();i++){
            ActivityItem item = new ActivityItem();
            item.setId(((long) i+3000));
            item.setActivityId(CacheData.tmp_activity_item.get(i).getActivityId());
            item.setCreateTime(CacheData.tmp_activity_item.get(i).getCreateTime());
            item.setTitle(CacheData.tmp_activity_item.get(i).getTitle());
            item.setDesc(CacheData.tmp_activity_item.get(i).getDesc());
            item.setFamilyId(CacheData.tmp_activity_item.get(i).getFamilyId());

            CacheData.tmp_activity_item.remove(i);
            CacheData.homeActivityItems.add(item);
            save2DB(item);
        }

        //刷新显示
        items.clear();
        addAdapter.setDataAndrUpdate(items);
        //发送广播
        EventBus.getDefault().post(new Activity_Event(Activity_Event.HomeActivity_NEW,CacheData.tmp_activity));
        HomeActivitesAddActivity.this.finish();
    }

    //将CacheData.tmp_activity 以及items中的数据同步到服务器
    private void sendActivity(final Activity activity){

        HttpParams params = new HttpParams();
//        params.putHeaders();
        params.put(HTTP_OPTIONS,1);
        params.put("activityType","true");
        params.put("familyId",BaseMsg.getFamily().getId());
        params.put("desc","desc");
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
    }

    private void sendItem(Activity activity){

        for (int i=0 ;i<CacheData.tmp_activity_item.size();i++){
            CacheData.tmp_activity_item.get(i).setActivityId(activity.getId());
            HttpParams params = new HttpParams();
//       params.putHeaders();
            params.put(HTTP_OPTIONS,1);
            params.put("familyId",BaseMsg.getFamily().getId());
            params.put("activityId", String.valueOf(CacheData.tmp_activity_item.get(i).getActivityId()));
            params.put("title",CacheData.tmp_activity_item.get(i).getTitle());
            params.put("desc",CacheData.tmp_activity_item.get(i).getDesc());

            final int finalI = i;
            HttpCallback callback = new HttpCallback() {
                int count = finalI;
                @Override
                public void onSuccess(String t) {
                    Log.i(TAG, "sendItem: "+t);
                    Log.i(TAG, "sendItem: "+count);
                    try {
                        JSONObject jsonObject = new JSONObject(t);
                        if (jsonObject.getInt(HTTP_STATUS )== HTTP_STATUS_SUCCESS){
                            int activityItemId = jsonObject.getInt("activityItemId");
//                            这里存在数组越界
                            CacheData.tmp_activity_item.get(count).setId((long) activityItemId);
                            CacheData.homeActivityItems.add( CacheData.tmp_activity_item.get(count));
                            CacheData.tmp_activity_item.remove(count);
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

    }

    private void savw2DB(Activity activity){

    }

}
