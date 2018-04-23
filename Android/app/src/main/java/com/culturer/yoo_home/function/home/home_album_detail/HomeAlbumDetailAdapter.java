package com.culturer.yoo_home.function.home.home_album_detail;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.culturer.yoo_home.R;
import com.culturer.yoo_home.bean.Photo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/12/9.
 */

public class HomeAlbumDetailAdapter extends BaseAdapter {

    private List<Photo> items = new ArrayList<>();
    private Context context;

    public HomeAlbumDetailAdapter(List<Photo> items, Context context) {
        this.items = items;
        this.context = context;
    }

    //填充数据
    private void setData( Photo data,ViewHolder holder){
        if (data !=null && holder!=null){
            //图标可以用glide来加载
//            holder.homealbum_item_icon.setImageResource(data.getIcon());
            holder.homealbum_item_time.setText(data.getCreateTime());
        }
    }

    //更新数据源刷新页面
    public void setDataAndUpdate(List<Photo> items){
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
            convertView = LayoutInflater.from(context).inflate(R.layout.fragment_album_detail_item, null);
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
        public TextView homealbum_item_title;
        public TextView homealbum_item_time;

        public void initViewHolder(View convertView){
            homealbum_item_icon = (ImageView) convertView.findViewById(R.id.homealbum_item_icon);
            homealbum_item_title = (TextView) convertView.findViewById(R.id.homealbum_item_title);
            homealbum_item_time = (TextView) convertView.findViewById(R.id.homealbum_item_time);
        }

    }
}
