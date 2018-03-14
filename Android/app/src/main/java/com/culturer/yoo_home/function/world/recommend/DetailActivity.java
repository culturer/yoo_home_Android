package com.culturer.yoo_home.function.world.recommend;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.LinearLayout;

import com.armour8.yooplus.yooplus.R;
import com.culturer.yoo_home.widget.navigation.impl.HomeNavigation;

public class DetailActivity extends AppCompatActivity {

    private static final String TAG = "DetailActivity";

    private View contentView;
    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        contentView = getLayoutInflater().inflate(R.layout.activity_detail,null);
        setContentView(contentView);
        init();
    }

    private void init() {
        initData();
        initView();
    }

    private void initData() {

    }
    private void initView() {
        initNavigation(contentView);
        initBaseView();

    }

    private void initNavigation(View contentView) {
        if (contentView!=null){
            LinearLayout topNavigation = contentView.findViewById(R.id.container);
            HomeNavigation.Builder builder = new HomeNavigation.Builder(this, topNavigation);
            builder.setCenterHomeTopic("Yoo+")
                    .setCenterHomeTitle("心若向阳无畏悲伤")
                    .create().
                    build();
        }else {
            Log.i(TAG, "initNavigation: content is null !!!");
        }
    }

    private void initBaseView() {
        webView = findViewById(R.id.webView);
    }

}
