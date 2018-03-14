package com.culturer.yoo_home.service.MQTT;

import com.culturer.yoo_home.base.handlerbase.BaseHandleMsg;

/**
 * Created by Administrator on 2018/1/17 0017.
 */

public class MQTTMsg extends BaseHandleMsg {

    boolean isSend;

    String msg;

    public MQTTMsg(boolean isSend, String msg) {
        this.isSend = isSend;
        this.msg = msg;
    }

    public boolean isSend() {
        return isSend;
    }

    public void setSend(boolean send) {
        isSend = send;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
