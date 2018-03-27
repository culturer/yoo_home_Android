package com.culturer.yoo_home.test;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

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

    }

}
