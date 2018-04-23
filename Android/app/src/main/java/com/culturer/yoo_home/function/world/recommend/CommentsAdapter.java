package com.culturer.yoo_home.function.world.recommend;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.culturer.yoo_home.R;

import java.util.List;

/**
 * Created by Administrator on 2018/3/5 0005.
 */

public class CommentsAdapter extends BaseAdapter {
    private List<String> items ;
    private Context context;
    public CommentsAdapter(Context context, List<String> photos) {
        this.context = context;
        this.items = photos;
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
        LayoutInflater inflator = LayoutInflater.from(context);
        View contentView = inflator.inflate(R.layout.gride_item,null);
        ImageView img = contentView.findViewById(R.id.gride_item);
        Glide.with(context)
                .load(items.get(position))//例 url "http://www.iconpng.com/png/media_monster_christmas/mail.png"
                .thumbnail(0.2f)
                .into(img);
        return contentView;
    }
}
