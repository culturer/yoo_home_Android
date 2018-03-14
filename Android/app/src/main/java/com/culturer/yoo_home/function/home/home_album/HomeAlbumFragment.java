package com.culturer.yoo_home.function.home.home_album;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.armour8.yooplus.yooplus.R;
import com.culturer.yoo_home.bean.Album;
import com.culturer.yoo_home.cahce.CacheData;
import com.culturer.yoo_home.config.Config;
import com.culturer.yoo_home.event.Album_Add_Event;
import com.yanzhenjie.album.Action;
import com.yanzhenjie.album.api.widget.Widget;


import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;


import static com.culturer.yoo_home.config.HomeMainConfig.ALBUM_TYPE_HOME;
import static com.culturer.yoo_home.config.HomeMainConfig.ALBUM_TYPE_USER;

public class HomeAlbumFragment extends Fragment implements IHomeAlbumView {

    private static final String TAG = "HomeAlbumFragment";
    private int fragment_type = -1;
    private HomeAlbumPresenter presenter;

    //页面基本布局
    View contentView;
    //列表控件
    ListView homealbum_list;
    //列表控件填充的数据集合
    private List<Album> homeAlbumItems = new ArrayList<>();
    HomeAlbumAdapter adapter;

    public HomeAlbumFragment() {}


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        contentView = inflater.inflate(R.layout.fragment_homealbum_list, container, false);
        setPresenter(createPresenter());
        initData();
        initView();
        EventBus.getDefault().register(this);
        return contentView;
    }

    @Override
    public void onDestroy() {
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }

    //初始化数据
    private void initData(){
        initConvertData();
        initListData();
    }

    //初始化UI组件
    private void initView(){
        initBaseView();
        initList();
    }

    //获取其他页面传递过来的数据
    private void initConvertData(){
        if ( getArguments() != null ){
            Bundle data = getArguments();
            fragment_type = data.getInt(Config.ALBUM_TYPE,-1);
            Log.i(TAG, "initConvertData: "+fragment_type);
        }else {
            Log.i(TAG, "initConvertData: fail --- ConvertData is null !");
        }

    }

    //初始化列表数据
    private void initListData(){
        //加载个人相册数据
        if (fragment_type == ALBUM_TYPE_USER){
            if ( CacheData.userAlbums != null){
                //从缓存读取相册数据
                if (homeAlbumItems == null || homeAlbumItems.size() ==0){
                    homeAlbumItems = CacheData.userAlbums;
                }
            }else {
                //从数据库读取数据
                //好像没有把数据层独立出去的必要，4个操作会需要用上mvp框架吗?
                //数据交互 :
                // 1.删除相册(删除缓存数据，删除数据库数据，发送http删除服务器数据)
                // 2.从数据库读取数据

            }
        }
        //加载家庭相册数据
        else if (fragment_type == ALBUM_TYPE_HOME){
            if (homeAlbumItems == null || homeAlbumItems.size() == 0){
                if ( CacheData.familyAlbums != null ){
                    homeAlbumItems = CacheData.familyAlbums;
                }else {
                    //从数据库读取数据
                }
            }
        }
        else {
            //缓存和数据库中都没有对应数据，则显示提示信息
            Log.i(TAG, "initListData: there is a error in loading list data");
            AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
            AlertDialog dialog = builder.setTitle("信息")
                                        .setMessage("无相册信息，请添加手动")
                                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialogInterface, int i) {
                                                dialogInterface.dismiss();
                                            }
                                        })
                                        .create();
            dialog.show();
        }

    }

    //初始化基本UI组件
    private void initBaseView(){
        homealbum_list = (ListView) findViewById(R.id.homealbum_list);
    }

    //初始化列表
    private void initList(){
        adapter = new HomeAlbumAdapter(homeAlbumItems,getContext());
        homealbum_list.setAdapter(adapter);
        homealbum_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                presenter.showPhotos(homeAlbumItems.get(position).getId());
            }
        });
        homealbum_list.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Log.i(TAG, "onItemLongClick: "+position);
                if (homeAlbumItems.get(position) != null){
                    showDialog( homeAlbumItems.get(position).getId(),homeAlbumItems.get(position).getName());
                }
                return true;
            }
        });
    }

    //删除相册弹框
    private void showDialog(final long albumItemId, String msg){
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("删除相册");
        builder.setMessage(msg);
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Log.i(TAG, "删除相册，确定: ");
                //删除服务器数据
                presenter.delAlbum(albumItemId);
                //删除缓存中的数据，并刷新
                if (fragment_type == ALBUM_TYPE_USER){
                    for (int i= 0 ; i< CacheData.userAlbums.size();i++){
                        if ( CacheData.userAlbums.get(i).getId() == albumItemId ){
                            CacheData.userAlbums.remove(i);
                        }
                    }
                }
                if ( fragment_type == ALBUM_TYPE_HOME ){
                    for (int i= 0 ; i< CacheData.familyAlbums.size();i++){
                        if ( CacheData.familyAlbums.get(i).getId() == albumItemId ){
                            CacheData.familyAlbums.remove(i);
                        }
                    }
                }
                adapter.setDataAndUpdate(homeAlbumItems);
                dialog.dismiss();
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Log.i(TAG, "删除相册，取消: ");
                dialog.dismiss();
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    //重写findViewById,方便使用
    private View findViewById(int id){
        return contentView.findViewById(id);
    }

    @Override
    public void setPresenter(HomeAlbumPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public HomeAlbumPresenter createPresenter() {
        HomeAlbumPresenter homeAlbumPresenter = new HomeAlbumPresenter(
                this,
                new HomeAlbumRespository(
                        new HomeAlbumLocalDatasource(getContext()),
                        new HomeAlbumRemoteDatasource(getContext()),
                        getContext()
                ),
                getContext()
        );
        return homeAlbumPresenter;
    }

    @Override
    public void showPhotos(List<String> photos) {

        if ( photos !=null && photos.size()>0 ){
            //显示相片
            com.yanzhenjie.album.Album.gallery(this)
                    .requestCode(0x01) // 请求码，会在listener中返回。
                    .checkedList((ArrayList<String>) photos) // 要浏览的图片列表：ArrayList<String>。
                    .navigationAlpha(80) // Android5.0+的虚拟导航栏的透明度。
                    .checkable(false) // 是否有浏览时的选择功能。
                    .widget(
                            Widget.newLightBuilder(getContext())
                                    .title("yoo+")
                                    .statusBarColor(Color.WHITE)
                                    .toolBarColor(Color.WHITE)
//                                .mediaItemCheckSelector(Color.BLUE, Color.GREEN)
//                                .bucketItemCheckSelector(Color.RED, Color.YELLOW)
                                    .buttonStyle(
                                            Widget.ButtonStyle.newLightBuilder(getContext())
                                                    .setButtonSelector(Color.WHITE, Color.WHITE)
                                                    .build()
                                    )
                                    .build()
                    )
                    .onResult(new Action<ArrayList<String>>() { // 如果checkable(false)，那么action不用传。
                        @Override
                        public void onAction(int requestCode, @NonNull ArrayList<String> result) {

                        }
                    })
                    .onCancel(new Action<String>() {
                        @Override
                        public void onAction(int requestCode, @NonNull String result) {

                        }
                    })
                    .start(); // 千万不要忘记调用start()方法。
        }

    }

    @Subscribe
    public void updateAlbum(Album_Add_Event event){
        Album album = event.getAlbum();
        int album_type = event.getAlbum_type();
        if (album_type == this.fragment_type){
            if (album_type == ALBUM_TYPE_USER){
                CacheData.userAlbums.add(album);
                this.homeAlbumItems =  CacheData.userAlbums;
            }else if (album_type == ALBUM_TYPE_HOME){
                CacheData.familyAlbums.add(album);
                this.homeAlbumItems =  CacheData.familyAlbums;
            }
            adapter.setDataAndUpdate(homeAlbumItems);
        }
    }

}
