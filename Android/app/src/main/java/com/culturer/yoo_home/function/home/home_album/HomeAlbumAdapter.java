package com.culturer.yoo_home.function.home.home_album;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.armour8.yooplus.yooplus.R;
import com.culturer.yoo_home.bean.Album;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/12/9.
 */

public class HomeAlbumAdapter extends BaseAdapter {

    private List<Album> homeAlbumItems = new ArrayList<>();
    private Context context;

    public HomeAlbumAdapter(List<Album> homeAlbumItems, Context context) {
        this.homeAlbumItems = homeAlbumItems;
        this.context = context;
    }

    //填充数据
    private void setData(Album data,ViewHolder holder){
        if (data !=null && holder!=null){
            //图标可以用glide来加载
//            holder.homealbum_item_icon.setImageResource(data.getIcon());
            holder.homealbum_item_title.setText(data.getName());
            holder.homealbum_item_time.setText(data.getCreateTime());
        }
    }

    //更新数据源刷新页面
    public void setDataAndUpdate(List<Album> homeAlbumItems){
        this.homeAlbumItems = homeAlbumItems;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return homeAlbumItems.size();
    }

    @Override
    public Object getItem(int position) {
        return homeAlbumItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;

        if (convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.fragment_homealbum_item, null);
            viewHolder = new ViewHolder();
            viewHolder.initViewHolder(convertView);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        if ( homeAlbumItems!=null ){
            setData(homeAlbumItems.get(position),viewHolder);
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
