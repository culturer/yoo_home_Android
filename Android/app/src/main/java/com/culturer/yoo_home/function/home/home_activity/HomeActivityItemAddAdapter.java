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
import com.culturer.yoo_home.bean.ActivityItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/12/5.
 */

public class HomeActivityItemAddAdapter extends BaseAdapter {

    private static final String TAG = "HomeActivitiesAdapter";

    private List<ActivityItem> datas = new ArrayList<>();
    private Context context;

    public HomeActivityItemAddAdapter(List<ActivityItem> datas, Context context) {
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
            convertView = LayoutInflater.from(context).inflate(R.layout.homeactivityitem_item, null);
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
    public void setDataAndrUpdate(List<ActivityItem> datas){
        if (datas != null && datas.size()>0){
            this.datas = datas;
            notifyDataSetChanged();
        }
    }

    //用来更新指定日程内容
    public void setItem(ViewHolder holder,ActivityItem data){
        if (data!=null){
            holder.home_activityitem_title.setText(data.getTitle());
            holder.home_activityitem_desc.setText(data.getDesc());
            holder.home_activityitem_time.setText(data.getTime());
        }else {
            Log.i(TAG, "setItem: data is null !!!");
        }
    }

    private class ViewHolder{

        TextView home_activityitem_title;
        TextView home_activityitem_desc;
        TextView home_activityitem_time;

        ViewHolder() {}

        void initViewHolder(View view){
            home_activityitem_title = view.findViewById(R.id.home_activityitem_title);
            home_activityitem_desc = view.findViewById(R.id.home_activityitem_desc);
            home_activityitem_time = view.findViewById(R.id.home_activityitem_time);
        }

    }

}
