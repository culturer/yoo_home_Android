package com.culturer.yoo_home.function.chat;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.armour8.yooplus.yooplus.R;
import com.culturer.yoo_home.cahce.BaseMsg;
import com.culturer.yoo_home.bean.ChatMsg;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Administrator on 2018/1/2 0002.
 */

public class ChatAdapter extends   RecyclerView.Adapter<RecyclerView.ViewHolder>  {

    List<ChatMsg> items  = new LinkedList<>();
    Context context;
    LayoutInflater inflater;

    public ChatAdapter(List<ChatMsg> items, Context context) {
        this.items = items;
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    //viewType---0,自己发送的信息；1他人发送的信息
    @Override
    public int getItemViewType(int position) {
        ChatMsg chatMsg = items.get(position);
        if (chatMsg!=null && chatMsg.getUserId()!=0 && BaseMsg.getUser()!=null){
            if (chatMsg.getUserId() == BaseMsg.getUser().getId()){
                return 0;
            }else {
                return 1;
            }
        }else {
            return 0;
        }
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView;
        if (viewType == 0){
            itemView = inflater.inflate(R.layout.chat_item2,parent,false);
        }else {
            itemView = inflater.inflate(R.layout.chat_item1,parent,false);
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
            ((ViewHolder1) holder).chat_item_desc.setText(items.get(position).getMsg());

//            ((ViewHolder1) holder).chat_item_icon.setImageResource(items.get(position).getUserIcon());
//        }
    }

    @Override
    public int getItemCount() {
        return items.size();
    }


    //增加单条数据
    public void addData(ChatMsg chatMsg){
        items.add(chatMsg);
        notifyItemInserted(items.size());
    }
    //更新数据源
    public void setDataAndupdate(List<ChatMsg> datas){
        this.items = datas;
        notifyDataSetChanged();
    }

    class ViewHolder1 extends RecyclerView.ViewHolder{

        public View itemView ;
        public ImageView chat_item_icon;
        public TextView chat_item_desc;

        public ViewHolder1(View itemView) {
            super(itemView);
            this.itemView = itemView;
            init();
        }

        private void init(){
            chat_item_icon = (ImageView) itemView.findViewById(R.id.chat_item_icon);
            chat_item_desc = (TextView) itemView.findViewById(R.id.chat_item_desc);
        }

    }

}
