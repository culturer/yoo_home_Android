package com.culturer.yoo_home.test;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.armour8.yooplus.yooplus.R;

public class TestActivity extends AppCompatActivity {

    private static final String TAG = "TestActivity";

    private View contentView;
    ListView homeactivity_detail_list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        contentView = getLayoutInflater().inflate(R.layout.activity_test,null);
        homeactivity_detail_list = contentView.findViewById(R.id.homeactivity_detail_list);
        homeactivity_detail_list.setDivider(null);
        TestAdapter adapter = new TestAdapter(this);
        homeactivity_detail_list.setAdapter(adapter);
        setContentView(contentView);

    }


}
