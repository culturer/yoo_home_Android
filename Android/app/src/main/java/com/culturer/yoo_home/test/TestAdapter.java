package com.culturer.yoo_home.test;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.armour8.yooplus.yooplus.R;

/**
 * Created by Administrator on 2018/3/12 0012.
 */

public class TestAdapter extends BaseAdapter {
    private Context context;
    public TestAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return 10;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = LayoutInflater.from(context).inflate(R.layout.home_activitv_item_detail_item,null);
        return convertView;
    }
}
