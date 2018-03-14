package com.culturer.yoo_home.service.MQTT;

import com.culturer.yoo_home.base.handlerbase.BaseHandler;
import com.culturer.yoo_home.base.handlerbase.BaseHandleMsg;

/**
 * Created by Administrator on 2018/1/17 0017.
 */

public class MQTTHandler extends BaseHandler<IMQTTService,BaseHandleMsg> {
    IMQTTService imqttService;

    public MQTTHandler(IMQTTService imqttService) {
        this.imqttService = imqttService;
    }

    @Override
    public void initHandler() {

    }

    @Override
    public void handle(BaseHandleMsg msg) {
    }

}
