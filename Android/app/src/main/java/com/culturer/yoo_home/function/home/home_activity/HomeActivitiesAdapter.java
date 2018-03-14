package com.culturer.yoo_home.function.home.home_activity;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.armour8.yooplus.yooplus.R;
import com.culturer.yoo_home.bean.Activity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/12/5.
 */

public class HomeActivitiesAdapter extends BaseAdapter {

    private static final String TAG = "HomeActivitiesAdapter";

    private List<Activity> datas = new ArrayList<>();
    private Context context;

    public HomeActivitiesAdapter(List<Activity> datas, Context context) {
        this.datas = datas;
        this.context = context;
    }

    @Override
    public int getCount() {
        return datas.size();
    }

    @Override
    public Object getItem(int position) {
        return datas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder;

        if (convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.homeactivities_item, null);
            viewHolder = new ViewHolder();
            viewHolder.initViewHolder(convertView);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        setItem(viewHolder,datas.get(position));
        return convertView;
    }

    //用来更新日程列表
    public void setDataAndrUpdate(List<Activity> datas){
        if (datas != null && datas.size()>0){
            this.datas = datas;
            notifyDataSetChanged();
        }
    }

    //用来更新指定日程内容
    public void setItem(ViewHolder holder,Activity data){
        if (data!=null){
//            holder.home_arrangement_usericon.setImageResource(data.usericon);
//            holder.home_arrangement_username.setText(data.username);
            holder.home_arrangement_desc.setText(data.getDesc());
            holder.home_arrangement_time.setText(data.getCreateTime());
        }else {
            Log.i(TAG, "setItem: data is null !!!");
        }
    }

    private class ViewHolder{

        public ImageView home_arrangement_usericon;
        public TextView home_arrangement_username;
        public TextView home_arrangement_desc;
        public TextView home_arrangement_time;


        public ViewHolder() {}

        public void initViewHolder(View view){
            home_arrangement_desc = (TextView) view.findViewById(R.id.home_arrangement_desc);
            home_arrangement_time = (TextView) view.findViewById(R.id.home_arrangement_time);
        }

    }

}
