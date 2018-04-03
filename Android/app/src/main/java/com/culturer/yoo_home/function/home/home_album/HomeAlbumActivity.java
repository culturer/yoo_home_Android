package com.culturer.yoo_home.function.home.home_album;

import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.armour8.yooplus.yooplus.R;
import com.google.gson.Gson;
import com.culturer.yoo_home.bean.Photo;
import com.culturer.yoo_home.cahce.BaseMsg;
import com.culturer.yoo_home.cahce.CacheData;
import com.culturer.yoo_home.event.Album_Event;
import com.culturer.yoo_home.function.home.HomePagerAdapter;
import com.culturer.yoo_home.util.StringUtil;
import com.culturer.yoo_home.util.ThreadUtil;
import com.culturer.yoo_home.util.TimeUtil;
import com.culturer.yoo_home.widget.navigation.impl.HomeNavigation;
import com.kymjs.rxvolley.RxVolley;

import com.kymjs.rxvolley.client.HttpCallback;
import com.kymjs.rxvolley.client.HttpParams;
import com.yanzhenjie.album.Action;
import com.yanzhenjie.album.Album;
import com.yanzhenjie.album.AlbumFile;

import org.greenrobot.eventbus.EventBus;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static com.culturer.yoo_home.config.Config.ALBUM_FAMILY_ID;
import static com.culturer.yoo_home.config.Config.ALBUM_ITEM_NAME;
import static com.culturer.yoo_home.config.Config.ALBUM_OPYIONS;
import static com.culturer.yoo_home.config.Config.ALBUM_OPYIONS_ADD;
import static com.culturer.yoo_home.config.Config.ALBUM_TYPE;
import static com.culturer.yoo_home.config.Config.ALBUM_TYPE_FAMILY;
import static com.culturer.yoo_home.config.Config.ALBUM_TYPE_PERSON;
import static com.culturer.yoo_home.config.Config.ALBUM_USER_ID;
import static com.culturer.yoo_home.config.HomeMainConfig.ALBUM_TYPE_HOME;
import static com.culturer.yoo_home.config.HomeMainConfig.ALBUM_TYPE_USER;
import static com.culturer.yoo_home.config.Urls.ALBUMS_URL;
import static com.culturer.yoo_home.config.Urls.FILES_URL;

public class HomeAlbumActivity extends AppCompatActivity {

    private static final String TAG = "HomeAlbumActivity";

    private View contentView;
    List<Fragment> fragmentList = new ArrayList<>();
    List<String> pagerList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        contentView = getLayoutInflater().inflate(R.layout.activity_home_album,null);
        setContentView(contentView);

        initData();
        initView();

    }

    private void initData(){
        initFragmentData();
        initTabData();
    }

    private void initView(){
        initNavigation(contentView);
        initPager();
        initBottomButton();
    }

    //初始化fragment数据
    private void initFragmentData(){

        Fragment user_album_fragment = new HomeAlbumFragment();
        Bundle user_album_data = new Bundle();
        user_album_data.putInt(ALBUM_TYPE,ALBUM_TYPE_USER);
        user_album_fragment.setArguments(user_album_data);
        fragmentList.add(user_album_fragment);

        Fragment home_album_fragment = new HomeAlbumFragment();
        Bundle home_album_data = new Bundle();
        home_album_data.putInt(ALBUM_TYPE,ALBUM_TYPE_HOME);
        home_album_fragment.setArguments(home_album_data);
        fragmentList.add(home_album_fragment);

    }

    //初始化标签数据
    private void initTabData(){
        pagerList.add("私人");
        pagerList.add("家庭");
    }

    //初始化导航条
    private void initNavigation(View contentView) {
        LinearLayout topNavigation = contentView.findViewById(R.id.container);
        HomeNavigation.Builder builder = new HomeNavigation.Builder(this, topNavigation);
        builder.setCenterHomeTopic("Yoo+")
                .setCenterHomeTitle("相册")
                .create().
                build();
    }

    //初始化ViewPager 滑动页面
    private void initPager(){

        TabLayout home_tab = findViewById(R.id.homealbum_tab);
        ViewPager home_pager = findViewById(R.id.homealbum_pager);

        //MODE_SCROLLABLE可滑动的展示
        //MODE_FIXED固定展示
        home_tab.setTabMode(TabLayout.MODE_FIXED);

        if ( pagerList != null ){
            for ( int i=0 ; i < pagerList.size() ; i++ ){
                home_tab.addTab(home_tab.newTab().setText(pagerList.get(i)));
            }
        }

        HomePagerAdapter pagerAdapter = new HomePagerAdapter(getSupportFragmentManager(), fragmentList, pagerList);
        home_pager.setAdapter(pagerAdapter);
        home_tab.setupWithViewPager(home_pager);

    }

    //底部添加相册按钮
    private void initBottomButton(){
        RelativeLayout bottomButton = contentView.findViewById(R.id.bottomButton);
        View bottomButtonView = getLayoutInflater().inflate(R.layout.homealbum_buttombtn,null);
        bottomButton.addView(bottomButtonView);
        (bottomButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAlertDialog();
            }
        });
    }

    //添加相册弹框
    private void showAlertDialog(){

        View contentView = this.getLayoutInflater().inflate(R.layout.homealbum_alert_add,null);
        final EditText homealbum_alert_albumname = (EditText) contentView.findViewById(R.id.homealbum_alert_albumname);
        final TextView homealbum_alert_time = (TextView) contentView.findViewById(R.id.homealbum_alert_time);
        final RadioGroup radios  = (RadioGroup) contentView.findViewById(R.id.homealbum_alert_type);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("添加相册");
        builder.setView(contentView);
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                int type = 0;
                if (radios.getCheckedRadioButtonId() == R.id.homealbum_alert_type_persional){
                    type = ALBUM_TYPE_USER;
                }else {
                    type = ALBUM_TYPE_HOME;
                }
                addAlbumItem(type,homealbum_alert_albumname.getText().toString());
                Log.i(TAG, "onClick: 确定添加相册");
                dialog.dismiss();
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Log.i(TAG, "onClick: 取消添加相册");
                dialog.dismiss();
            }
        });

        homealbum_alert_time.setText(TimeUtil.getCurrentTime());

        final AlertDialog dialog = builder.create();
        dialog.show();

    }

    private void addAlbumItem(final int albumType , String name ){
        //处理数据
        final com.culturer.yoo_home.bean.Album album = new com.culturer.yoo_home.bean.Album();
        album.setName(name);
        album.setCreateTime(StringUtil.getDateEN());

        //处理接口回调
        HttpCallback callback = new HttpCallback() {
            @Override
            public void onSuccess(String t) {
                Log.i(TAG, "addAlbumItem: "+t);
                long albumId = getAlbumId(t);
                album.setId(albumId);
                addPhotos(albumId);
                Log.i(TAG, "addAlbumItem: "+album.toString());
                if (albumType == ALBUM_TYPE_USER){
                    Album_Event event = new Album_Event(ALBUM_TYPE_USER,album);
                    EventBus.getDefault().post(event);
                }else if (albumType == ALBUM_TYPE_HOME){
                    Album_Event event = new Album_Event(ALBUM_TYPE_HOME,album);
                    EventBus.getDefault().post(event);
                }
            }
            @Override
            public void onFailure(int errorNo, String strMsg) {
                Log.i(TAG, "onFailure: errNo --- "+errorNo+" || errMsg"+strMsg);
            }
        };

        //初始化参数
        HttpParams params = new HttpParams();
        params.putHeaders("Authorization", BaseMsg.getToken());
        params.put(ALBUM_OPYIONS,ALBUM_OPYIONS_ADD);
        if ( albumType == ALBUM_TYPE_USER ){
            params.put(ALBUM_TYPE,ALBUM_TYPE_PERSON);
            params.put(ALBUM_USER_ID,BaseMsg.getUser().getId());

            album.setUserId((long) BaseMsg.getUser().getId());
        }else if (albumType == ALBUM_TYPE_HOME){
            params.put(ALBUM_TYPE,ALBUM_TYPE_FAMILY);
            params.put(ALBUM_FAMILY_ID,BaseMsg.getUser().getFamilyId()+"");

            album.setFamilyId((long) BaseMsg.getFamily().getId());
        }


        params.put(ALBUM_ITEM_NAME,name);
        params.put(ALBUM_OPYIONS,ALBUM_OPYIONS_ADD);

        new RxVolley.Builder()
                .url(ALBUMS_URL)
                .httpMethod(RxVolley.Method.POST)
                .contentType(RxVolley.ContentType.FORM)
                .params(params)
                .cacheTime(0)
                .shouldCache(false)         //取消页面缓存
                .callback(callback)
                .encoding("UTF-8")
                .doTask();
    }

    private void addPhotos(final long albumId){
        Album.image(this) // 选择图片。
                .multipleChoice()
                .requestCode(300)
                .camera(true)
                .columnCount(3)
                .selectCount(15)
                .checkedList(new ArrayList<AlbumFile>())
                .filterSize(null)
                .filterMimeType(null)
                .afterFilterVisibility(false) // 显示被过滤掉的文件，但它们是不可用的。
                .onResult(new Action<ArrayList<AlbumFile>>() {
                    @Override
                    public void onAction(int requestCode, @NonNull final ArrayList<AlbumFile> result) {
                        ThreadUtil.startThread(new ThreadUtil.Task() {
                            @Override
                            public void doTask() {
                                for (int i= 0 ;i<result.size();i++){

                                    HttpParams params = new HttpParams();
                                    AlbumFile photo = result.get(i);
                                    Log.i(TAG, "photo --- name : "+photo.getName());
                                    Log.i(TAG, "photo --- type : "+photo.getMimeType());
                                    Log.i(TAG, "photo --- url : "+photo.getPath());
                                    params.put("attachment",new File(photo.getPath()));
                                    //放入文件操作方法
                                    params.put("options",0);
                                    params.put("userId",BaseMsg.getUser().getId());
                                    params.put("familyId",BaseMsg.getFamily().getId());
                                    params.put("albumId", (int) albumId);

                                    Log.i(TAG, "params: "+params.toString());

                                    HttpCallback callback = new HttpCallback() {

                                        @Override
                                        public void onSuccess(String t) {
                                            Log.i(TAG, "onSuccess: "+t);
                                            try {
                                                JSONObject jsonObject = new JSONObject(t);
                                                String strPhoto = jsonObject.getString("photo");
                                                Gson gson = new Gson();
                                                Photo photol = gson.fromJson(strPhoto,Photo.class);
                                                if (photol!=null){
                                                    //去重
                                                    boolean flag = false;
                                                    for (int i=0 ;i<CacheData.photos.size();i++){
                                                        if (CacheData.photos.get(i).getId() == photol.getId()){
                                                            //检测到相片已经添加到相册
                                                            Log.i(TAG, "检测到重复照片"+photol.getFileName());
                                                            flag = true;
                                                            break;
                                                        }
                                                    }
                                                    if (!flag){
                                                        CacheData.photos.add(photol);
                                                        Log.i(TAG, "add photo: "+photol.toString());
                                                    }
                                                }
                                            } catch (JSONException e) {
                                                e.printStackTrace();
                                            }
                                        }

                                        @Override
                                        public void onFailure(int errorNo, String strMsg) {
                                            Log.i(TAG, "errorNo: "+errorNo+" || strMsg:"+strMsg);
                                        }

                                    };

                                    new RxVolley.Builder()
                                            .url(FILES_URL)
//                                            .url(FILES_URL)
                                            .httpMethod(RxVolley.Method.POST)
                                            .contentType(RxVolley.ContentType.FORM)
                                            .params(params)
                                            .cacheTime(0)
                                            .shouldCache(false)
                                            .callback(callback)
                                            .encoding("UTF-8")
                                            .doTask();
                                }

                            }
                        });
                    }

                })
                .onCancel(new Action<String>() {
                    @Override
                    public void onAction(int requestCode, @NonNull String result) {
                    }
                })
                .start();
    }

    //从http传来的参数中解析出AlbumId
    private Long getAlbumId(String msg){
        try {
            JSONObject jsonObject = new JSONObject(msg);
            long albumItemId = jsonObject.getLong("albumItemId");
            return albumItemId;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        //ID解析失败，返回默认的ID
        return -1l;
    }

}
