package com.culturer.yoo_home.function.home.home_activity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.armour8.yooplus.yooplus.R;
import com.culturer.yoo_home.bean.ActivityItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/12/9.
 */

public class HomeActivitiesDetailAdapter extends BaseAdapter {

    private List<ActivityItem> items = new ArrayList<>();
    private Context context;

    public HomeActivitiesDetailAdapter(List<ActivityItem> items, Context context) {
        this.items = items;
        this.context = context;
    }

    //填充数据
    private void setData( ActivityItem data,ViewHolder holder){
        if (data !=null && holder!=null){
//            holder.homealbum_item_icon.setImageResource(data.getIcon());
        }
    }

    //更新数据源刷新页面
    public void setDataAndUpdate(List<ActivityItem> items){
        this.items = items;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;

        if (convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.home_activitv_item_detail_item, null);
            viewHolder = new ViewHolder();
            viewHolder.initViewHolder(convertView);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        if ( items!=null ){
            setData(items.get(position),viewHolder);
        }
        return convertView;
    }

    private class ViewHolder{
        public ImageView homealbum_item_icon;

        public void initViewHolder(View convertView){
            homealbum_item_icon = convertView.findViewById(R.id.homealbum_item_icon);

        }

    }
}
