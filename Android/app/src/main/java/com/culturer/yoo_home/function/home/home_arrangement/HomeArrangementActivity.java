package com.culturer.yoo_home.function.home.home_arrangement;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.armour8.yooplus.yooplus.R;
import com.culturer.yoo_home.bean.Arrangement;
import com.culturer.yoo_home.cahce.BaseMsg;
import com.culturer.yoo_home.cahce.CacheData;
import com.culturer.yoo_home.config.HomeArrangementConfig;
import com.culturer.yoo_home.event.Arrangement_Event;
import com.culturer.yoo_home.util.TimeUtil;
import com.culturer.yoo_home.widget.navigation.impl.HomeNavigation;
import com.kymjs.rxvolley.RxVolley;
import com.kymjs.rxvolley.client.HttpCallback;
import com.kymjs.rxvolley.client.HttpParams;

import org.greenrobot.eventbus.EventBus;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static com.culturer.yoo_home.config.Urls.ARRANGEMENTS_URL;

public class HomeArrangementActivity extends AppCompatActivity {

    private static final String TAG = "HomeArrangementActivity" ;

    View contenteView;

    ListView home_arrangement_list;
    TextView home_arrangement_Add;

    HomeArrangementAdapter adapter;
    private List<Arrangement> datas = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        contenteView = getLayoutInflater().inflate(R.layout.activity_home_arrangement,null);
        setContentView(contenteView);
        init();
    }

    private void init(){
        initData();
        initView();
    }

    private void initData(){
        initListData();
    }

    private void initView(){
        initBaseView();
        initNavigation(contenteView);
        initBottomBtn();
        initList();
    }

    //----------------------------------------------------------------------------------------------------------------------------------------------------//

    private void initListData(){
        if (CacheData.arrangements!=null &&CacheData.arrangements.size()>0){
            datas = CacheData.arrangements;
        }else {
            //从数据库读取数据
            //如果读取数据为空记得打log

        }
    }

    //----------------------------------------------------------------------------------------------------------------------------------------------------//

    //初始化基本的UI组件 
    private void initBaseView(){
        home_arrangement_list = findViewById(R.id.home_arrangement_list);
        home_arrangement_Add = findViewById(R.id.home_arrangement_Add);
    }

    //列表
    private void initList(){
        adapter = new HomeArrangementAdapter(datas,this);
        home_arrangement_list.setAdapter(adapter);
        home_arrangement_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Arrangement arrangement =  datas.get(position);
                initShowDialog(HomeArrangementConfig.ALERT_SHOWARRANGEMENAT,arrangement.getDesc(),position);
            }
        });
        home_arrangement_list.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Arrangement arrangement =  datas.get(position);
                initShowDialog(HomeArrangementConfig.ALERT_DEL,arrangement.getDesc(),arrangement.getId());
                return true;
            }
        });
    }


    //导航条
    private void initNavigation(View contentView) {
        LinearLayout topNavigation = (LinearLayout) contentView.findViewById(R.id.home_arrangement_topNavigation);
        HomeNavigation.Builder builder = new HomeNavigation.Builder(this, topNavigation);
        builder.setCenterHomeTopic("Yoo+")
                .setCenterHomeTitle("日程安排")
                .create()
                .build();
    }

    //底部添加按钮
    private void initBottomBtn(){
        home_arrangement_Add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initShowDialog(HomeArrangementConfig.ALERT_ADD,null,-1);
            }
        });
    }

    //控制弹框
    private void initShowDialog(int type, final String msg, final long arrangementId){

        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        if (type == HomeArrangementConfig.ALERT_ADD){
            View contentView = this.getLayoutInflater().inflate(R.layout.homearrangement_alert_add,null);
            final EditText homearrangement_alert_name = contentView.findViewById(R.id.homearrangement_alert_name);
            final TextView homearrangement_alert_time = contentView.findViewById(R.id.homearrangement_alert_time);

            builder.setTitle("添加日程");
            builder.setView(contentView);
            builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Log.i(TAG, "onClick: 确定添加日程");
                    String desc = homearrangement_alert_name.getText().toString();
                    String createTime = homearrangement_alert_time.getText().toString();
                    addArrengement(desc,createTime);

                    dialog.dismiss();
                }
            });
            builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                    Log.i(TAG, "onClick: 取消添加日程");

                    dialog.dismiss();
                }
            });

            homearrangement_alert_time.setText(TimeUtil.getCurrentTime());

        }else if (type == HomeArrangementConfig.ALERT_DEL){
            builder.setTitle("删除日程");
            builder.setMessage(msg);
            builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Log.i(TAG, "onClick: 确定删除日程");
                    delArrangement(arrangementId);
                    dialog.dismiss();
                }
            });
            builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                    Log.i(TAG, "onClick: 取消删除日程");

                    dialog.dismiss();
                }
            });
        }else if (type == HomeArrangementConfig.ALERT_SHOWARRANGEMENAT){
            builder.setTitle("日程安排");
            View convertView = LayoutInflater.from(this).inflate(R.layout.homemain_popwindow_arrangement,null);
            TextView arrangement_pop_desc = convertView.findViewById(R.id.arrangement_pop_desc);
            TextView arrangement_pop_time = convertView.findViewById(R.id.arrangement_pop_time);
            Arrangement data =  datas.get((int) arrangementId);
            if (data != null){
                arrangement_pop_desc.setText(data.getDesc());
                arrangement_pop_time.setText(data.getCreateTime());
            }

            builder.setView(convertView);
            builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Log.i(TAG, "onClick: 取消");
                    dialog.dismiss();
                }
            });
            builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Log.i(TAG, "onClick: 确定");
                    dialog.dismiss();
                }
            });
        }

        final AlertDialog dialog = builder.create();
        dialog.show();

    }

    private void addArrengement(String desc,String createTime){
        //1.初始化数据
        final Arrangement arrangement = new Arrangement();
        arrangement.setUserId((long) BaseMsg.getUser().getId());
        arrangement.setDesc(desc);
        arrangement.setCreateTime(createTime);

        //2.更新服务器数据
        HttpParams params = new HttpParams();
        params.put("desc",desc);
        params.put("createTime",createTime);
        params.put("options",1);
        params.put("userId", BaseMsg.getUser().getId());

        HttpCallback callback = new HttpCallback() {
            @Override
            public void onFailure(int errorNo, String strMsg) {
                Log.i(TAG, "addArrengement: errNo --- "+errorNo+"||errMsg --- "+strMsg );
            }

            @Override
            public void onSuccess(String t) {
                arrangement.setId(getArrangementId(t));
                Log.i(TAG, "addArrengement: "+t);
                //刷新本地缓存
                CacheData.arrangements.add(arrangement);
                //更新显示
                adapter.setDataAndrUpdate(datas);
                //更新主页显示
                EventBus.getDefault().post(new Arrangement_Event(Arrangement_Event.Arrangement_NEW,arrangement));
            }
        };

        new RxVolley.Builder()
                .url(ARRANGEMENTS_URL)
                .httpMethod(RxVolley.Method.POST)
                .contentType(RxVolley.ContentType.FORM)
                .params(params)
                .cacheTime(0)
                .shouldCache(false)         //取消页面缓存
                .callback(callback)
                .encoding("UTF-8")
                .doTask();

    }

    private void delArrangement(final long arrangementId){

        //刷新本地缓存
        for (int i=0 ;i<CacheData.arrangements.size();i++){
            if (CacheData.arrangements.get(i).getId() == arrangementId ){
                CacheData.arrangements.remove(i);
                break;
            }
        }

        //3.更新显示
        adapter.setDataAndrUpdate(datas);
        //更新首页标签显示
        EventBus.getDefault().post(new Arrangement_Event(Arrangement_Event.Arrangement_DEL,CacheData.arrangements.get(CacheData.arrangements.size()-1)));

        //4.更新服务器数据
        HttpParams params = new HttpParams();
        params.put("arrangementId",arrangementId+"");
        params.put("options",2);

        HttpCallback callback = new HttpCallback() {

            @Override
            public void onFailure(int errorNo, String strMsg) {
                Log.i(TAG, "onFailure: errNo --- "+errorNo+"||errMsg --- "+strMsg);
            }

            @Override
            public void onSuccess(String t) {
                Log.i(TAG, "delArrangement: "+t);
            }

        };

        //执行请求操作
        new RxVolley.Builder()
                .url(ARRANGEMENTS_URL)
                .httpMethod(RxVolley.Method.POST)
                .contentType(RxVolley.ContentType.FORM)
                .params(params)
                .cacheTime(0)
                .shouldCache(false)         //取消页面缓存
                .callback(callback)
                .encoding("UTF-8")
                .doTask();

    }

    //从Http的response中解析出ArrangementId
    private long getArrangementId(String msg){
        try {
            JSONObject jsonObject = new JSONObject(msg);
            int arrangementId = jsonObject.getInt("arrangementId");
            return arrangementId;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return -1;
    }
}
