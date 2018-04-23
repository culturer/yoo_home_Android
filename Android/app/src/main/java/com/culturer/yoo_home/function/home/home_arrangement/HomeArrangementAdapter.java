package com.culturer.yoo_home.function.home.home_arrangement;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.culturer.yoo_home.R;
import com.culturer.yoo_home.bean.Arrangement;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/12/5.
 */

public class HomeArrangementAdapter extends BaseAdapter {

    private static final String TAG = "MenuAdapter";

    private List<Arrangement> datas = new ArrayList<>();
    private Context context;

    public HomeArrangementAdapter(List<Arrangement> datas, Context context) {
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
            convertView = LayoutInflater.from(context).inflate(R.layout.homearrangement_item, null);
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
    public void setDataAndrUpdate(List<Arrangement> datas){
        if (datas != null && datas.size()>0){
            this.datas = datas;
            notifyDataSetChanged();
        }else {
            this.datas = new ArrayList<>();
            notifyDataSetChanged();
        }
    }

    //用来更新指定日程内容
    public void setItem(ViewHolder holder,Arrangement data){
        if (data!=null){
            holder.home_arrangement_desc.setText(data.getDesc());
            holder.home_arrangement_time.setText(data.getCreateTime());
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
