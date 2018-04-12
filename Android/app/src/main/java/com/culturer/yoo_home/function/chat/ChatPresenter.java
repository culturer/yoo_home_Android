package com.culturer.yoo_home.function.chat;

import android.content.Context;

import com.culturer.yoo_home.base.mvpbase.BasePresenter;
import com.culturer.yoo_home.cahce.BaseMsg;
import com.culturer.yoo_home.service.MQTT.MQTTMsg;
import com.google.gson.Gson;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by Administrator on 2018/2/24 0024.
 */

public class ChatPresenter extends BasePresenter<IChatView,ChatRespository> {
    public ChatPresenter(IChatView view, ChatRespository respository, Context context) {
        super(view, respository, context);
    }

    @Override
    public void start() {

    }

    ChatMsg sendTextMsg(String strMsg){
        ChatMsg chatMsg = new ChatMsg(ChatMsg.Chat_Msg_Sending,ChatMsg.Chat_Msg_Text, BaseMsg.getUser().getId(), BaseMsg.getUser().getUsername(),BaseMsg.getUser().getIcon(),strMsg,"",null);
        String strChatMsg = new Gson().toJson(chatMsg,ChatMsg.class);
        EventBus.getDefault().post(new MQTTMsg(true,MQTTMsg.CHAT_MSG,strChatMsg));
        return chatMsg;
    }
}
