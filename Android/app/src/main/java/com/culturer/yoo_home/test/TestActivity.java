package com.culturer.yoo_home.test;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.armour8.yooplus.yooplus.R;

public class TestActivity extends AppCompatActivity{

    private static final String TAG = "TestActivity";
    private View contentView;
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        contentView = getLayoutInflater().inflate(R.layout.activity_test,null);
        setContentView(contentView);
        init();
    }

    private void init(){
// 浏览一般的String路径：
//        Album.gallery(this)
//                .requestCode(0x01) // 请求码，会在listener中返回。
//                .checkedList() // 要浏览的图片列表：ArrayList<String>。
//                .navigationAlpha() // Android5.0+的虚拟导航栏的透明度。
//                .checkable(false) // 是否有浏览时的选择功能。
//                .onResult(new Action<ArrayList<String>>() { // 如果checkable(false)，那么action不用传。
//                    @Override
//                    public void onAction(int requestCode, @NonNull ArrayList<String> result) {
//                    }
//                })
//                .onCancel(new Action<String>() {
//                    @Override
//                    public void onAction(int requestCode, @NonNull String result) {
//                    }
//                })
//                .start(); // 千万不要忘记调用start()方法。
    }

}
