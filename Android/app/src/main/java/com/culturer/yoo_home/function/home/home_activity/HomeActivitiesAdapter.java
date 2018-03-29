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
        for(int i= 0;i<datas.size();i++){
            Log.i(TAG, "datas: --- "+i+" "+datas.get(i).getDesc());
        }
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


    public void setDataAndrUpdate(List<Activity> datas){
        if (datas != null && datas.size()>0){
            this.datas = datas;
            notifyDataSetChanged();
        }
    }

    private void setItem(ViewHolder holder,Activity data){
        if (data!=null){
            holder.home_arrangement_desc.setText(data.getDesc());
            holder.home_arrangement_time.setText(data.getCreateTime());
        }else {
            Log.i(TAG, "setItem: data is null !!!");
        }
    }

    private class ViewHolder{
        TextView home_arrangement_desc;
        TextView home_arrangement_time;
        ViewHolder() {}
        void initViewHolder(View view){
            home_arrangement_desc = view.findViewById(R.id.home_activity_desc);
            home_arrangement_time = view.findViewById(R.id.home_activity_time);
        }
    }

}
