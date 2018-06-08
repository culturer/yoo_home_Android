package com.culturer.yoo_home.service.MQTT;


import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import com.culturer.yoo_home.cahce.BaseMsg;
import com.culturer.yoo_home.util.ThreadUtil;
import com.google.gson.Gson;

import org.eclipse.paho.android.service.MqttAndroidClient;
import org.eclipse.paho.client.mqttv3.IMqttActionListener;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.IMqttToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import static com.culturer.yoo_home.config.Urls.MQ_URL;


//MQTT协议做推送服务
public class MQTTService extends Service implements IMQTTService {

    private static final String TAG = "MQTTService";
    private MqttAndroidClient client;
    
    private MqttConnectOptions conOpt;
    private MQTTHandler handler;
    
    private Gson gson;

    private String host = MQ_URL;
    private String userName = BaseMsg.getUser().getUsername();
    private String passWord = BaseMsg.getUser().getUsername();
    private String myTopic = "yoo_home/family/"+BaseMsg.getFamily().getId();
//    private String myTopic = "test";
    private String clientId = "yoo_home_" + BaseMsg.getUser().getId();

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i(TAG, "onStartCommand: ");
        init();
        return super.onStartCommand(intent, flags, startId);
    }

    private void init(){
        initEventBus();
        initBaseData();
        initMQ();
        ThreadUtil.startThread(() ->
                initHandler()
        );
    }

    private void initBaseData(){
        gson = new Gson();
        Log.i(TAG, "initBaseData: user---"+BaseMsg.getUser().toString());
        if ( BaseMsg.getUser()!=null ){
            userName = BaseMsg.getUser().getId()+"";
            passWord = BaseMsg.getUser().getFamilyId()+"";
            clientId = BaseMsg.getUser().getId()+"";
            Log.i(TAG, "initBaseData: userName---"+userName+
                    "passWord---"+passWord+
                    "clientId---"+clientId);
        }
    }

    //初始化MQ服务
    private void initMQ() {
        // 服务器地址（协议+地址+端口号）
        client = new MqttAndroidClient(this, host, clientId);
        // 设置MQTT监听并且接受消息
        client.setCallback(mqttCallback);
        conOpt = new MqttConnectOptions();
        // 清除缓存
        conOpt.setCleanSession(false);
        // 设置超时时间，单位：秒
        conOpt.setConnectionTimeout(0);
        // 心跳包发送间隔，单位：秒
        conOpt.setKeepAliveInterval(10);
        // 用户名
        conOpt.setUserName(userName);
        // 密码
        conOpt.setPassword(passWord.toCharArray());
        // last will message
        boolean doConnect = true;
        String message = "{\"terminal_uid\":\"" + clientId + "\"}";
        String topic = myTopic;
        Integer qos = 0;
        Boolean retained = false;
        //存在内存泄露???
        if ((!message.equals("")) || (!topic.equals(""))) {
            // 最后的遗嘱
            try {
                conOpt.setWill(topic, message.getBytes(), qos, retained);
            } catch (Exception e) {
                Log.i(TAG, "Exception Occured", e);
                doConnect = false;
                iMqttActionListener.onFailure(null, e);
            }
        }
        if (doConnect) {
            doClientConnection();
        }
    }

    // 连接MQTT服务器
    private void doClientConnection() {
        if (!client.isConnected() || !isConnectIsNomarl()) {
            try {
                client.connect(conOpt, null, iMqttActionListener);
                Log.i(TAG, "doClientConnection: ");
            } catch (MqttException e) {
                e.printStackTrace();
            }
        }
    }

    // 判断网络是否连接
    private boolean isConnectIsNomarl() {
        ConnectivityManager connectivityManager = (ConnectivityManager) this.getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager.getActiveNetworkInfo() != null){
            NetworkInfo info = connectivityManager.getActiveNetworkInfo();
            if (info != null && info.isAvailable()) {
                String name = info.getTypeName();
                Log.i(TAG, "MQTT当前网络名称：" + name);
                return true;
            } else {
                Log.i(TAG, "MQTT 没有可用网络");
                return false;
            }
        }
        return false;
    }

    //发送信息
    public void publish(String msg){
        ThreadUtil.startThreadInPool(() -> {
            //数据是否保存在服务器
            Log.i(TAG, "publish: "+msg);
            try {
                client.publish(myTopic, msg.getBytes(), 0, false);
            } catch (MqttException e) {
                e.printStackTrace();
            }
        });
    }

    
    
    @Override
    public void onDestroy() {
        try {
            if (client!=null){
                client.disconnect();
            }
        } catch (MqttException e) {
            e.printStackTrace();
        }
        super.onDestroy();
    }
    
    // MQTT是否连接成功
    private IMqttActionListener iMqttActionListener = new IMqttActionListener() {

        @Override
        public void onSuccess(IMqttToken arg0) {
            Log.i(TAG, "连接成功 ");
            try {
                // 订阅myTopic话题
                client.subscribe(myTopic,1);
            } catch (MqttException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onFailure(IMqttToken arg0, Throwable arg1) {
            Log.i(TAG, "onFailure: "+arg1.getMessage());
        }

    };


    // MQTT监听并且接受消息
    private MqttCallback mqttCallback = new MqttCallback() {

        @Override
        public void messageArrived(String topic, MqttMessage message) throws Exception {
            String msg = new String(message.getPayload());
            Log.i(TAG, "messageArrived: "+msg);
            handler.handle(msg);
        }

        @Override
        public void deliveryComplete(IMqttDeliveryToken arg0) {

        }

        @Override
        public void connectionLost(Throwable arg0) {
            // 失去连接，重连
        }

    };

    //初始化handler
    @Override
    public void initHandler() {
        handler = new MQTTHandler();
    }

    //初始化EventBus
    private void initEventBus() {
        EventBus.getDefault().register(this);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Subscribe
    public void sendMessage(MQTTMsg msg){
        if (msg.isSend()){
            msg.isSend = false;
            String strMsg = gson.toJson(msg,MQTTMsg.class);
            publish(strMsg);
        }
    }
}

