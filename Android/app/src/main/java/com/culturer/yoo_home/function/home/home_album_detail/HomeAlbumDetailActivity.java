package com.culturer.yoo_home.function.home.home_album_detail;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.culturer.yoo_home.R;
import com.culturer.yoo_home.bean.Photo;
import com.culturer.yoo_home.cahce.CacheData;
import com.culturer.yoo_home.widget.navigation.impl.HomeNavigation;

import java.util.ArrayList;
import java.util.List;

public class HomeAlbumDetailActivity extends AppCompatActivity {

    private View contentView;
    ListView home_album_detail_list;

    private Long albumId = -1l;
    List<Photo> photos = new ArrayList<>();
    HomeAlbumDetailAdapter homeAlbumDetailAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        contentView = LayoutInflater.from(this).inflate(R.layout.activity_home_album_detail,null);
        setContentView(contentView);
        initData();
        initView();
    }

    private void initData(){
        initConvertData();
        initListData();
    }

    private void initView(){
        initNavigation(contentView);
        initListView();
    }

    //初始化Intent传过来的数据
    private void initConvertData(){
        Intent intent = getIntent();
        if (intent != null){
            Bundle datas = intent.getBundleExtra("datas");
            if (datas != null) {
                albumId = datas.getLong("albumId", -1l);
            }
        }
    }

    //初始化相片列表数据
    private void initListData(){

        //从缓存中读取数据
        if (CacheData.photos!=null){
            for (int i=0 ;i<CacheData.photos.size();i++){
                if (CacheData.photos.get(i)!=null){
                    if (albumId == CacheData.photos.get(i).getAlbumId()){
                        photos.add(CacheData.photos.get(i));
                    }
                }
            }
        }else{
            //从数据库中读取数据,预留接口

        }

        homeAlbumDetailAdapter = new HomeAlbumDetailAdapter(photos,this);

    }

    private void initNavigation(View contentView) {
        LinearLayout topNavigation = contentView.findViewById(R.id.home_album_detail_topNavigation);
        HomeNavigation.Builder builder = new HomeNavigation.Builder(this, topNavigation);
        builder.setCenterHomeTopic("Yoo+")
                .setCenterHomeTitle("心若向阳无畏悲伤")
                .create()
                .build();
    }

    private void initListView(){
        home_album_detail_list = findViewById(R.id.home_album_detail_list);
    }
}
