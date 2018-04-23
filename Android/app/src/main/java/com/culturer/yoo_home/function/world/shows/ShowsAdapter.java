package com.culturer.yoo_home.function.world.shows;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.culturer.yoo_home.R;


/**
 * Created by Administrator on 2018/3/7 0007.
 */

public class ShowsAdapter extends BaseAdapter {

    private Context context;

    public ShowsAdapter(Context context) {
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
        ViewHolder viewHolder;

        if (convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.shows_item, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        setItem(viewHolder);
        return convertView;
    }

    private void setItem(ViewHolder viewHolder){

    }

    private class ViewHolder{

        private View convertView;

        public ViewHolder(View convertView) {
            this.convertView = convertView;
        }

    }
}
