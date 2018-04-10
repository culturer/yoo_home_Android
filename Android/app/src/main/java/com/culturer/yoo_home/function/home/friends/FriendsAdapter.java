package com.culturer.yoo_home.function.home.friends;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.armour8.yooplus.yooplus.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/4/10 0010.
 */

public class FriendsAdapter extends BaseExpandableListAdapter {
	
	List<String> groups = new ArrayList<>();
	List<List<String>> friends = new ArrayList<>();
	
	Context context;
	
	int groupLayout = R.layout.friends_group_item;
	int friendLayout =  R.layout.friends_person_item;
	
	public FriendsAdapter(List<String> groups, List<List<String>> friends, Context context) {
		this.groups = groups;
		this.friends = friends;
		this.context = context;
	}
	
	@Override
	public int getGroupCount() {
		return groups.size();
	}
	
	@Override
	public int getChildrenCount(int i) {
		return friends.get(i).size();
	}
	
	@Override
	public Object getGroup(int i) {
		return groups.get(i);
	}
	
	@Override
	public String getChild(int i, int i1) {
		List<String> group_friends = friends.get(i);
		return group_friends.get(i1);
	}
	
	@Override
	public long getGroupId(int i) {
		return i;
	}
	
	@Override
	public long getChildId(int i, int i1) {
		return 100*i+i1;
	}
	
	@Override
	public boolean hasStableIds() {
		return false;
	}
	
	@Override
	public View getGroupView(int i, boolean b, View view, ViewGroup viewGroup) {
		View view1;
		if (view == null){
			view1 = LayoutInflater.from(context).inflate(groupLayout,null);
		}else {
			view1 = view;
		}
		TextView friend_group = view1.findViewById(R.id.friend_group);
		friend_group.setText(groups.get(i));
		return view1;
	}
	
	@Override
	public View getChildView(int i, int i1, boolean b, View view, ViewGroup viewGroup) {
		View view1;
		if (view == null){
			view1 = LayoutInflater.from(context).inflate(friendLayout,null);
		}else {
			view1 = view;
		}
		TextView friend_person = view1.findViewById(R.id.friend_person);
		friend_person.setText(getChild(i,i1));
		return view1;
	}
	
	@Override
	public boolean isChildSelectable(int i, int i1) {
		return true;
	}
	
}
