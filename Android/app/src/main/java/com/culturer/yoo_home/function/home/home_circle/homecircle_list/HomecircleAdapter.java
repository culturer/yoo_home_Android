package com.culturer.yoo_home.function.home.home_circle.homecircle_list;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.armour8.yooplus.yooplus.R;

import java.util.LinkedList;
import java.util.List;

public class HomecircleAdapter extends   RecyclerView.Adapter<RecyclerView.ViewHolder>  {

    List<String> items  = new LinkedList<>();
    Context context;
    LayoutInflater inflater;

    HomecircleAdapter(List<String> items, Context context) {
        this.items = items;
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    //viewType---0,自己发送的信息；1他人发送的信息
    @Override
    public int getItemViewType(int position) {
        String chatMsg = items.get(position);
        return 0;
//        if (chatMsg!=null && chatMsg.getUserId()!=0 && BaseMsg.getUser()!=null){
//            if (chatMsg.getUserId() == BaseMsg.getUser().getId()){
//                return 0;
//            }else {
//                return 1;
//            }
//        }else {
//            return 0;
//        }
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView;
        if (viewType == 0){
            itemView = inflater.inflate(R.layout.homecircle_item,parent,false);
        }else {
            itemView = inflater.inflate(R.layout.homecircle_item,parent,false);
        }
        if (itemView!=null){
            ViewHolder1 viewHolder1 = new ViewHolder1(itemView);
            return viewHolder1;
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Log.i("adapter", "onBindViewHolder: "+items.get(position).toString());
//        if (holder instanceof ViewHolder1 ){
//            ((ViewHolder1) holder).chat_item_desc.setText(items.get(position).getMsg());
//        }
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    //更新数据源
    public void setDataAndupdate(List<String> datas){
        this.items = datas;
        notifyDataSetChanged();
    }

    class ViewHolder1 extends RecyclerView.ViewHolder{

        View itemView ;
        ImageView chat_item_icon;
        TextView chat_item_desc;

        ViewHolder1(View itemView) {
            super(itemView);
            this.itemView = itemView;
            init();
        }

        private void init(){
            chat_item_icon = itemView.findViewById(R.id.chat_item_icon);
            chat_item_desc = itemView.findViewById(R.id.chat_item_desc);
        }

    }

}
