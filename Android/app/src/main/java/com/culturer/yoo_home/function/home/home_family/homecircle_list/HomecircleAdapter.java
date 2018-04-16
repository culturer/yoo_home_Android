package com.culturer.yoo_home.function.home.home_family.homecircle_list;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.armour8.yooplus.yooplus.R;
import com.culturer.yoo_home.bean.Arrangement;
import com.culturer.yoo_home.bean.Article;
import com.culturer.yoo_home.bean.Comment;
import com.culturer.yoo_home.bean.Photo;
import com.culturer.yoo_home.bean.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/12/5.
 */

public class HomecircleAdapter extends BaseAdapter {

    private static final String TAG = "HomecircleAdapter";
    
    private List<Article> articles  = new ArrayList<>();
    private List<Comment> comments = new ArrayList<>();
    private List<Photo> photos = new ArrayList<>();
    private List<User> familyUsers = new ArrayList<>();
    private Context context;
    private LayoutInflater inflater;
    
    public HomecircleAdapter(List<Article> articles, List<Comment> comments, List<Photo> photos, List<User> familyUsers, Context context) {
        this.articles = articles;
        this.comments = comments;
        this.photos = photos;
        this.familyUsers = familyUsers;
        this.context = context;
        this.inflater = LayoutInflater.from(context);
    }
    
    @Override
    public int getCount() {
        return articles.size();
    }

    @Override
    public Object getItem(int position) {
        return articles.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder;

        if (convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.homecircle_item, null);
            viewHolder = new ViewHolder();
            viewHolder.initViewHolder(convertView);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        setItem(viewHolder,articles.get(position));
        return convertView;
    }

    //用来更新日程列表
    public void setDataAndrUpdate(List<Article> datas){
        if (datas != null && datas.size()>0){
            this.articles = datas;
            notifyDataSetChanged();
        }else {
            this.articles = new ArrayList<>();
            notifyDataSetChanged();
        }
    }

    //用来更新指定日程内容
    public void setItem(ViewHolder holder,Article data){
        if (data!=null){
//            holder.home_arrangement_desc.setText(data.getDesc());
//            holder.home_arrangement_time.setText(data.getCreateTime());
        }else {
            Log.i(TAG, "setItem: data is null !!!");
        }
    }

    private class ViewHolder{

        public TextView home_arrangement_desc;
        public TextView home_arrangement_time;

        public ViewHolder() {}

        public void initViewHolder(View view){
            home_arrangement_desc = (TextView) view.findViewById(R.id.home_arrangement_desc);
            home_arrangement_time = (TextView) view.findViewById(R.id.home_arrangement_time);
        }

    }

}
