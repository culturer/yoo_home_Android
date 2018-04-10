package com.culturer.yoo_home.function.home.friends.homecircle_list;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.armour8.yooplus.yooplus.R;
import com.culturer.yoo_home.bean.Arrangement;
import com.culturer.yoo_home.function.home.home_arrangement.HomeArrangementAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/4/9 0009.
 */

public class PictureAdapter extends BaseAdapter {
	
	private static final String TAG = "PictureAdapter" ;
	
	private Context context;
	private List<String> datas;
	
	public PictureAdapter(Context context,List<String> datas) {
		this.context = context;
		this.datas = datas;
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
			convertView = LayoutInflater.from(context).inflate(R.layout.gride_item, null);
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
	public void setDataAndrUpdate(List<String> datas){
		if (datas != null && datas.size()>0){
			this.datas = datas;
			notifyDataSetChanged();
		}else {
			this.datas = new ArrayList<>();
			notifyDataSetChanged();
		}
	}
	
	//用来更新指定日程内容
	public void setItem(ViewHolder holder, String data){
		if (data!=null){
			holder.img.setImageResource(R.mipmap.ic_launcher);
		}else {
			Log.i(TAG, "setItem: data is null !!!");
		}
	}
	
	private class ViewHolder{
		
		ImageView img;
		
		public ViewHolder() {}
		
		void initViewHolder(View view){
			img =  view.findViewById(R.id.gride_item);
		}
		
	}
	
}
