package com.culturer.yoo_home.service.MQTT;

import com.culturer.yoo_home.base.handlerbase.BaseHandleMsg;

/**
 * Created by Administrator on 2018/1/17 0017.
 */

public class MQTTMsg extends BaseHandleMsg {

    public static final int CHAT_MSG = 0;


    //标志位，代表该消息是否是自己向服务器发出
    boolean isSend;
    //信息的种类
    int type;
    //发送的信息
    String msg;

    public MQTTMsg() {
    }

    public MQTTMsg(boolean isSend, int type, String msg) {
        this.isSend = isSend;
        this.type = type;
        this.msg = msg;
    }

    public boolean isSend() {
        return isSend;
    }

    public void setSend(boolean send) {
        isSend = send;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
