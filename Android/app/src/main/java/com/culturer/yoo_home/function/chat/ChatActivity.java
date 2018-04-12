package com.culturer.yoo_home.function.chat;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.armour8.yooplus.yooplus.R;
import com.culturer.yoo_home.cahce.BaseMsg;
import com.culturer.yoo_home.cahce.CacheData;
import com.culturer.yoo_home.service.MQTT.MQTTMsg;
import com.culturer.yoo_home.util.AudioUtil;
import com.culturer.yoo_home.util.TimeUtil;
import com.culturer.yoo_home.widget.navigation.impl.HomeNavigation;
import com.google.gson.Gson;
import com.vondear.rxtools.view.dialog.RxDialogChooseImage;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static com.culturer.yoo_home.config.HomeMainConfig.CHAT_DATA;
import static com.culturer.yoo_home.config.HomeMainConfig.CHAT_RECEIVER;
import static com.culturer.yoo_home.config.HomeMainConfig.CHAT_TYPE;
import static com.vondear.rxtools.view.dialog.RxDialogChooseImage.LayoutType.TITLE;

public class ChatActivity extends AppCompatActivity implements IChatView {

    private static final String TAG = "ChatActivity";
    
    ChatPresenter presenter;
    
    AudioUtil audioUtil;
    
    boolean chat_type;
    String username;
    int userId;
    String userIcon;

    private View contentViwe;
    private RecyclerView chat_list;
    private EditText chat_edit;
    private ImageButton chat_camera;
    private ImageButton chat_emoji;
    private ImageButton chat_tel;
    private TextView chat_send;
    private ImageButton chat_audio;
    private LinearLayout chat_bottom_view;

    private List<ChatMsg> chatMsgs = new LinkedList<>();
    private ChatAdapter chatAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        contentViwe = LayoutInflater.from(this).inflate(R.layout.activity_chat,null);
        setContentView(contentViwe);
        setPresenter(createPresenter());
        init();
    }

    private void init(){
        initEventBus();
        initData();
        initView();
    }
    
    //初始化EventBus
    private void initEventBus(){
        EventBus.getDefault().register(this);
    }

    //初始化数据
    private void initData(){
        initConvertData();
        initListData();
    }

    //初始化UI组件
    private void initView(){
        initBaseView();
        initNavigation(contentViwe,"Yoo+","心若向阳，无畏悲伤");
        initList();
    }

                                                                    //初始化数据//
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    //获取从intent传输过来的数据
    private void initConvertData(){
        Intent convertIntent = getIntent();
        Bundle data = convertIntent.getBundleExtra(CHAT_DATA);
        if (data!=null && data.getString(CHAT_RECEIVER)!=null){
            chat_type = data.getBoolean(CHAT_TYPE);
        }
    }

    //初始化列表数据
    private void initListData(){
        chatMsgs = new ArrayList<>();
        for (int i=0 ;i<CacheData.chatMsgs.size();i++){
            ChatMsg chatMsg = CacheData.chatMsgs.get(i);
            if (chatMsg.getStatus()!=ChatMsg.Chat_Msg_Read){
                ChatMsg statusMsg = new ChatMsg(chatMsg.getId(),ChatMsg.Chat_Msg_Read);
                Gson gson = new Gson();
                String strMsg = gson.toJson(statusMsg);
                MQTTMsg mqttMsg = new MQTTMsg(true,MQTTMsg.CHAT_MSG,strMsg);
                EventBus.getDefault().post(mqttMsg);
            }
            chatMsgs.add(chatMsg);
        }
        chatAdapter = new ChatAdapter(chatMsgs,this);
    }

                                                                    //收发聊天数据//
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //接收消息
    @Subscribe
    public void receiveMsg(ChatMsg msg){
        Log.i(TAG, "handle: "+msg.toString());
        if (msg.getStatus() == ChatMsg.Chat_Msg_Sending){
            Log.i(TAG, "receiveMsg: 更新新消息显示");
            //更新显示
            msg.setStatus(ChatMsg.Chat_Msg_Read);
            chatMsgs.add(msg);
            chatAdapter.setDataAndupdate(chatMsgs);
            chat_list.scrollToPosition(chatAdapter.getItemCount()-1);
            //发送已读信息
            Log.i(TAG, "receiveMsg: 发送已读信息");
            ChatMsg statusMsg = new ChatMsg(msg.getId(),ChatMsg.Chat_Msg_Read);
            Gson gson = new Gson();
            String strMsg = gson.toJson(statusMsg);
            MQTTMsg mqttMsg = new MQTTMsg(true,MQTTMsg.CHAT_MSG,strMsg);
            EventBus.getDefault().post(mqttMsg);
        }
        if (msg.getStatus() == ChatMsg.Chat_Msg_Success){
            Log.i(TAG, "receiveMsg: change Status"+msg.toString());
            for (int i=0 ;i<chatMsgs.size();i++){
                if (chatMsgs.get(i).getId().equals( msg.getId())){
                    Log.i(TAG, "change Status: chat["+i+"] --- "+chatMsgs.get(i).getId()+" , chatMsg --- "+msg.getId());
                    Log.i(TAG, "receiveMsg: 更新消息已读状态");
                    chatMsgs.get(i).setStatus(ChatMsg.Chat_Msg_Success);
                    chatAdapter.setDataAndupdate(chatMsgs);
                    break;
                }
            }
        }
        if (msg.getStatus() == ChatMsg.Chat_Msg_Read){
            Log.i(TAG, "receiveMsg: change Status"+msg.toString());
            for (int i=0 ;i<chatMsgs.size();i++){
                if (chatMsgs.get(i).getId().equals( msg.getId())){
                    Log.i(TAG, "change Status: chat["+i+"] --- "+chatMsgs.get(i).getId()+" , chatMsg --- "+msg.getId());
                    chatMsgs.get(i).setStatus(ChatMsg.Chat_Msg_Read);
                    chatAdapter.setDataAndupdate(chatMsgs);
                    break;
                }
            }
        }
        
    }

    //发送消息
    private void sendMsg(String strMsg){
        ChatMsg chatMsg = presenter.sendTextMsg(strMsg);
        CacheData.chatMsgs.add(chatMsg);
        chatMsgs.add(chatMsg);
        chatAdapter.setDataAndupdate(chatMsgs);
    }
    

                                                                    //初始UI组件//
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    //初始化基础UI组件
    @SuppressLint("ClickableViewAccessibility")
    private void initBaseView(){

        chat_bottom_view  = findViewById(R.id.chat_bottom_view);
        chat_list = findViewById(R.id.chat_list);
        chat_edit = findViewById(R.id.chat_edit);
        chat_camera = findViewById(R.id.chat_camera);
        chat_emoji = findViewById(R.id.chat_emoji);
        chat_tel = findViewById(R.id.chat_tel);
        chat_audio = findViewById(R.id.chat_audio);
        chat_send = findViewById(R.id.chat_send);

        //打开相机拍照
        chat_camera.setOnClickListener(view -> initDialogChooseImage());

        //表情包
        chat_emoji.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        //语音/视频聊天，预留接口
        chat_tel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            
            }
        });

        //发送语音，预留接口，暂时屏蔽
//        chat_audio.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//
//                switch (event.getAction()){
//
//                    case MotionEvent.ACTION_DOWN:
//                        //初始化录音设置
//                        initAudio();
//                        //开始录音
//                        audioUtil.startRecord();
//                        break;
//                    case MotionEvent.ACTION_UP:
//                        //结束录音（保存录音文件）
//                        audioUtil.stopRecord();
//                        break;
//                }
//                return true;
//            }
//        });

        //发送聊天信息
        chat_send.setOnClickListener(view -> {
            //1.打包数据
            // 2.将打包的数据发送到MQTT
            //3.MQTT将打包的数据发送出去
            String strMsg  = chat_edit.getText().toString();
            if (!strMsg.equals("")){
                sendMsg(strMsg);
                toLast();
                clearText();
            }
        });

    }

    //初始化列表组件
    private void initList(){
        chat_list.setAdapter(chatAdapter);
        chat_list.setLayoutManager(new LinearLayoutManager(this));
    }

    //初始化导航条
    private void initNavigation(View contentView,String topic,String title) {
        LinearLayout topNavigation = contentView.findViewById(R.id.container);
        HomeNavigation.Builder builder = new HomeNavigation.Builder(this, topNavigation);
        builder.setCenterHomeTopic(topic)
                .setCenterHomeTitle(title)
                .create().
                build();
    }

    @Override
    protected void onDestroy() {
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }

    private void toLast(){
        chat_list.scrollToPosition(chatAdapter.getItemCount()-1);
    }

    private void clearText(){
        chat_edit.setText("");
        chat_edit.setHint("出来聊天啦");
    }
    
    private void initDialogChooseImage() {
        RxDialogChooseImage dialogChooseImage = new RxDialogChooseImage(this, TITLE);
        dialogChooseImage.show();
    }
    
    //初始化录音工具
    //由于AudioUtil可能每次调用结束就会清理缓存，所以在录音时进行初始化
    private void initAudio(){
        audioUtil = new AudioUtil();
        //录音回调
        audioUtil.setOnAudioStatusUpdateListener(new AudioUtil.OnAudioStatusUpdateListener() {
            @Override
            public void onUpdate(double db, long time) {
//               根据分贝值来设置录音时话筒图标的上下波动
//               mImageView.getDrawable().setLevel((int) (3000 + 6000 * db / 100));
//               mTextView.setText(TimeUtils.long2String(time));
                Log.i(TAG, "Audio --- onUpdate: time["+ TimeUtil.getDataToString(time)+"]"+" , db["+db+"]");
            }
            
            @Override
            public void onStop(String filePath) {

//               Toast.makeText(MainActivity.this, "录音保存在：" + filePath, Toast.LENGTH_SHORT).show();
//               mTextView.setText(TimeUtils.long2String(0));
                Log.i(TAG, "Audio --- onStop: filePath["+filePath+"]");
            }
        });
        
    }
    
    @Override
    public void setPresenter(ChatPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public ChatPresenter createPresenter() {
        ChatPresenter presenter = new ChatPresenter(this,
                new ChatRespository(
                        new ChatLocalDataSource(this),
                        new ChatRemoteDataSource(this),
                        this),
                this);
        return presenter;
    }

}
