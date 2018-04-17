package com.culturer.yoo_home.function.world.shop;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.armour8.yooplus.yooplus.R;
import com.culturer.yoo_home.bean.Article;
import com.culturer.yoo_home.bean.Comment;
import com.culturer.yoo_home.bean.Photo;
import com.culturer.yoo_home.bean.User;

import java.util.ArrayList;
import java.util.List;

public class MenuAdapter extends BaseAdapter {

    private static final String TAG = "MenuAdapter";
    
    private List<String> menus  = new ArrayList<>();
    private Context context;
    private LayoutInflater inflater;
    
    public MenuAdapter(List<String> menus, Context context) {
        this.menus = menus;
        this.context = context;
        this.inflater = LayoutInflater.from(context);
    }
    
    @Override
    public int getCount() {
        return menus.size();
    }

    @Override
    public String getItem(int position) {
        return menus.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder;

        if (convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.shop_menu_item, null);
            viewHolder = new ViewHolder();
            viewHolder.initViewHolder(convertView);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        setItem(viewHolder,menus.get(position));
        return convertView;
    }

    //用来更新日程列表
    public void setDataAndrUpdate(List<String> datas){
        if (datas != null && datas.size()>0){
            this.menus = datas;
            notifyDataSetChanged();
        }else {
            this.menus = new ArrayList<>();
            notifyDataSetChanged();
        }
    }

    //用来更新指定日程内容
    public void setItem(ViewHolder holder,String data){
        if (!data.equals("")){
            holder.tv_shop_menu.setText(data);
        }else {
            Log.i(TAG, "setItem: data is null !!!");
        }
    }

    private class ViewHolder{

        public TextView tv_shop_menu;


        public ViewHolder() {}

        public void initViewHolder(View view){
            tv_shop_menu = (TextView) view.findViewById(R.id.tv_shop_menu);
        }

    }

}
