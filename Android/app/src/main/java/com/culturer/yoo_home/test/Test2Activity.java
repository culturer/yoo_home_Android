package com.culturer.yoo_home.test;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.armour8.yooplus.yooplus.R;
import com.yanzhenjie.album.Action;
import com.yanzhenjie.album.Album;
import com.yanzhenjie.album.AlbumFile;

import java.util.ArrayList;
import java.util.List;

public class Test2Activity extends AppCompatActivity {

    List<AlbumFile> chckList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test2);
        chckList = new ArrayList<>();
        Album.image(this) // 选择图片。
                .multipleChoice()
                .requestCode(0)
                .camera(true)
                .columnCount(3)
                .selectCount(9)
                .checkedList((ArrayList<AlbumFile>) chckList)
                .filterSize(null)
                .filterMimeType(null)
                .afterFilterVisibility(false) // 显示被过滤掉的文件，但它们是不可用的。
                .onResult(new Action<ArrayList<AlbumFile>>() {
                    @Override
                    public void onAction(int requestCode, @NonNull ArrayList<AlbumFile> result) {
                        chckList=result;
                    }
                })
                .onCancel(new Action<String>() {
                    @Override
                    public void onAction(int requestCode, @NonNull String result) {
                    }
                })
                .start();
    }
}
