package com.culturer.yoo_home.function.home.home_main;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.armour8.yooplus.yooplus.R;
import com.culturer.yoo_home.cahce.BaseMsg;
import com.culturer.yoo_home.bean.Activity;
import com.culturer.yoo_home.bean.ActivityItem;
import com.culturer.yoo_home.bean.Arrangement;
import com.culturer.yoo_home.bean.Family;
import com.culturer.yoo_home.cahce.CacheData;
import com.culturer.yoo_home.config.HomeMainConfig;
import com.culturer.yoo_home.event.Activity_Event;
import com.culturer.yoo_home.event.Arrangement_Event;
import com.culturer.yoo_home.function.chat.ChatActivity;
import com.culturer.yoo_home.function.home.family_activity.FamilyActivityActivity;
import com.culturer.yoo_home.function.home.home_activity.HomeActiviesActivity;
import com.culturer.yoo_home.function.home.home_activity.HomeActivitiesDetailAdapter;
import com.culturer.yoo_home.function.home.home_album.HomeAlbumActivity;
import com.culturer.yoo_home.function.home.home_arrangement.HomeArrangementActivity;
import com.culturer.yoo_home.util.StringUtil;

import com.culturer.yoo_home.widget.circleMenu.CircleMenu;


import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;


public class HomeMainFragment extends Fragment implements IHomeMainView {

    private static final String TAG = "HomeMainFragment";

    public HomeMainFragment() {
        // Required empty public constructor
    }

    private Context context;
    private LayoutInflater inflater;
    private HomeMainPresenter presenter;
    //下拉刷新

    //中间转盘组件
    private CircleMenu homemain_circleview;
    //中间按钮
    private ImageView homemain_center_btn;

    //底部的标签
    private ImageView homamain_album_icon;
    private ImageView homamain_arrange_icon;
    private ImageView homamain_activity_icon;
    private ImageView homamain_familyactivity_icon;
    //四个角的标签
    private TextView homemain_notify;
    private TextView homemain_activity;
    private TextView homemain_familyactivity;
    private TextView homemain_arrangement;

    private AlertDialog dialog;

    //用来加载页面的布局
    private View contentView;

    //初始化中间转盘的资源
    //图片资源数组
    private int[] homemain_imgs_src = new int[]{
            R.mipmap.ic_launcher_round,
            R.mipmap.ic_launcher_round,
            R.mipmap.ic_launcher_round,
            R.mipmap.ic_launcher_round,
    };

    /**
     * 中间圆形布局文字
     */
    private List<String> mStrs = new ArrayList<>();

    private List<View.OnClickListener> listeners = new ArrayList<>();

    //初始化四个角标签数据
    Activity homeActivity = new Activity();
    List<ActivityItem> activityItems = new ArrayList<>();
    Arrangement arrangement = new Arrangement();
    Family family = BaseMsg.getFamily();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (context == null){
            context = getContext();
        }
        if (inflater == null){
            inflater = LayoutInflater.from(context);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (contentView == null){
            contentView = inflater.inflate(R.layout.fragment_home_main, container, false);
            presenter = createPresenter();
            setPresenter(presenter);
            init();
        }
        ViewGroup parent = (ViewGroup) contentView.getParent();
        if ( parent!=null ){
            parent.removeView(contentView);
        }
        return contentView;
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.start();
    }

    private void init(){
        initData();
        initView();
        EventBus.getDefault().register(this);

    }

    //接收Arrangement变更广播
    @Subscribe
    public void receiveMsg(Arrangement_Event event){
        if ( event.type == Arrangement_Event.Arrangement_NEW || event.type == Arrangement_Event.Arrangement_DEL){
            arrangement = event.arrangement;
            String arg = "";
            if (arrangement!=null && arrangement.getDesc()!=null){
                arg = arrangement.getDesc();
            }else {
                homemain_arrangement.setVisibility(View.GONE);
            }
            homemain_arrangement.setText(arg);
        }
    }


    @Subscribe
    public void reveiveMsg(Activity_Event event){
        if (event.type == Activity_Event.HomeActivity_NEW){
            homeActivity = event.getActivity();
            String desc = "";
            if (homeActivity != null && homeActivity.getDesc() !=null){
                desc =homeActivity.getDesc();
            }else {
                homemain_activity.setVisibility(View.GONE);
            }
            homemain_activity.setText(desc);
        }
    }

    //初始化数据
    private void initData(){
        //初始化四个角的标签的数据
        initTabData();
        //初始化中间转盘的数据
        initCircleData();
    }

    //初始化UI组件
    private void initView(){
        //初始化基本UI组件
        initBaseView();
        //初始化中间转盘
        initCircleView();
    }

                                                                            //初始化数据//
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    //初始化四个角的标签的数据
    private void initTabData(){

        //初始化home_activity标签
        if (CacheData.homeActivities !=null && CacheData.homeActivities.size()>0 ){
            homeActivity = CacheData.homeActivities.get(CacheData.homeActivities.size()-1);
        }

        //初始化home_activity弹窗内部内容
        if (CacheData.homeActivityItems!=null && CacheData.homeActivityItems.size()>0 ){
            for (int i=0 ;i<CacheData.homeActivityItems.size();i++){
                ActivityItem activityItem = CacheData.homeActivityItems.get(i);
                if (activityItem!=null && activityItem.getHomeActivityId() == homeActivity.getId()){
                    activityItems.add(activityItem);
                }
            }
        }

        //初始化日程安排标签
        if ( CacheData.arrangements != null && CacheData.arrangements.size()>0 ){
            arrangement = CacheData.arrangements.get(CacheData.arrangements.size()-1);
        }
    }

    //初始化中间转盘的数据
    private void initCircleData(){
        for (int i=0 ;i<homemain_imgs_src.length;i++){
            final int finalI = i;
            listeners.add(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(getContext(), ChatActivity.class);
                    Bundle datas = new Bundle();
                    intent.putExtra("datas",datas);
                    startActivity(intent);
                }
            });
            mStrs.add("song"+i);
        }

    }

                                                                          //初始化UI组件//
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //初始化布局
    private void initBaseView(){

        //初始化转盘
        homemain_circleview = contentView.findViewById(R.id.homemain_circleview);
        //初始化中间按钮
        homemain_center_btn = contentView.findViewById(R.id.homemain_center_btn);
        homemain_center_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), ChatActivity.class);
                Bundle datas = new Bundle();
                intent.putExtra("datas",datas);
                startActivity(intent);
            }
        });
        //初始化四角的标签
        homemain_notify = contentView.findViewById(R.id.homemain_notify);
        homemain_activity = contentView.findViewById(R.id.homemain_activity);
        homemain_familyactivity = contentView.findViewById(R.id.homemain_familyactivity);
        homemain_arrangement = contentView.findViewById(R.id.homemain_arrangement);

        //设置四个角标签的数据以及点击事件
        setLabels();

        //初始化底部控件
        homamain_album_icon = contentView.findViewById(R.id.homamain_album_icon);
        homamain_arrange_icon = contentView.findViewById(R.id.homamain_arrange_icon);
        homamain_activity_icon = contentView.findViewById(R.id.homamain_activity_icon);
        homamain_familyactivity_icon = contentView.findViewById(R.id.homamain_familyactivity_icon);

        //设置底部按钮点击事件，发送页面跳转
        setButtomBtn();

    }

    //初始化中间转盘
    private void initCircleView(){
        homemain_circleview.setDatas(homemain_imgs_src,mStrs,listeners);
    }

    //四个角标签的弹窗显示
    private void showDialog(int popType){

        final AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        //日程安排弹框
        if (popType == HomeMainConfig.HOMEMAIN_POP_ARRANGEMENT){
            builder.setTitle("日程安排");
            View arrangementView = inflater.inflate(R.layout.homemain_popwindow_arrangement,null);
            TextView arrangement_pop_desc = arrangementView.findViewById(R.id.arrangement_pop_desc);
            TextView arrangement_pop_time = arrangementView.findViewById(R.id.arrangement_pop_time);
            arrangement_pop_desc.setText(arrangement.getDesc());
            arrangement_pop_time.setText(arrangement.getCreateTime());
            builder.setView(arrangementView);
            builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });
        }

        //家庭公告弹框
        else if (popType == HomeMainConfig.HOMEMAIN_POP_NOTIFY){
            builder.setTitle("家庭公告");
            View familyView = inflater.inflate(R.layout.homemain_popwindow_notify,null);
            final EditText homemain_notify_title = (EditText) familyView.findViewById(R.id.homemain_notify_title);
            homemain_notify_title.setText(family.getFamilyNotifyTitle());
            builder.setView(familyView);
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
                    //在此修改公告
                    //1.从布局中获取数据
                    //2.同步到服务器
                    //3.更新主界面标签显示
                    String notify =  homemain_notify_title.getText().toString();
                    Log.i(TAG, "onClick: notify---"+notify);
                    presenter.updateFamily(notify, StringUtil.getDateEN());
                    dialog.dismiss();
                }
            });
        }

        //家庭活动弹框
        else if (popType == HomeMainConfig.HOMEMAIN_POP_ACTIVITY){
            builder.setTitle("家庭活动");
            View homeActivityView = inflater.inflate(R.layout.homeactivities_detail,null);
            TextView homemain_activity_poptitle = homeActivityView.findViewById(R.id.homemain_activity_poptitle);
            ListView homeactivity_detail_list = homeActivityView.findViewById(R.id.homeactivity_detail_list);
            homemain_activity_poptitle.setText(homeActivity.getDesc());
            //此处设置activityItem
            for (int i=0;i<10;i++){
                activityItems.add(new ActivityItem());
            }
            HomeActivitiesDetailAdapter home_adapter = new HomeActivitiesDetailAdapter(activityItems,getContext());
            homeactivity_detail_list.setAdapter(home_adapter);
            homeactivity_detail_list.setDivider(null);

            builder.setView(homeActivityView);
            builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Log.i(TAG, "onClick: 确定");
                    dialog.dismiss();
                }
            });

        }

        //家族活动弹框
        else if (popType == HomeMainConfig.HOMEMAIN_POP_FAMILYACTIVITY){
            builder.setTitle("家族活动");
            builder.setView(R.layout.homemain_popwindow_familyactivity);
            builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Log.i(TAG, "onClick: 确定");
                    dialog.dismiss();
                }
            });
        }

        dialog = builder.create();
        dialog.show();
    }

    //设置四个角标签数据
    private void setLabels(){
        //四个角标签的点击事件
        String ntfy = "";
        if (family!=null && family.getMsg()!=null){
            ntfy = family.getFamilyNotifyTitle();
        }else {
            homemain_notify.setVisibility(View.GONE);
        }
        homemain_notify.setText(ntfy);
        homemain_notify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG, "onClick: homemain_notify");
                showDialog(HomeMainConfig.HOMEMAIN_POP_NOTIFY);
            }
        });

        String desc = "";
        if (homeActivity != null && homeActivity.getDesc() !=null){
            desc =homeActivity.getDesc();
        }else {
            homemain_activity.setVisibility(View.GONE);
        }
        homemain_activity.setText(desc);
        homemain_activity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG, "onClick: homemain_activity");
                showDialog(HomeMainConfig.HOMEMAIN_POP_ACTIVITY);
            }
        });

        homemain_familyactivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG, "onClick: homemain_familyactivity");
                showDialog(HomeMainConfig.HOMEMAIN_POP_FAMILYACTIVITY);
            }
        });

        String arg = "";
        if (arrangement!=null && arrangement.getDesc()!=null){
            arg = arrangement.getDesc();
        }else {
            homemain_arrangement.setVisibility(View.GONE);
        }
        homemain_arrangement.setText(arg);
        homemain_arrangement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG, "onClick: homemain_arrangement");
                showDialog(HomeMainConfig.HOMEMAIN_POP_ARRANGEMENT);
            }
        });
    }

    //设置底部标签点击事件
    private void setButtomBtn(){
        homamain_album_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), HomeAlbumActivity.class);
                startActivity(intent);
            }
        });
        homamain_arrange_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), HomeArrangementActivity.class);
                startActivity(intent);
            }
        });
        homamain_activity_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), HomeActiviesActivity.class);
                startActivity(intent);
            }
        });
        homamain_familyactivity_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), FamilyActivityActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void setPresenter(HomeMainPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public HomeMainPresenter createPresenter() {
        HomeMainPresenter presenter = new HomeMainPresenter(this,
                new HomeMainRespository(
                    new HomeMainLocalDataSource(context),
                    new HomeMainRemoteDataSource(context),
                    context),
                context);
        return presenter;
    }

    @Override
    public void loadFail() {

    }

    @Override
    public void loadSuccess(Family family) {
        this.family = family;
        //更新四个角标签显示
        setLabels();
    }

}
