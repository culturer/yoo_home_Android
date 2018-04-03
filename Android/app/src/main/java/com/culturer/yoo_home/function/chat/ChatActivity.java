package com.culturer.yoo_home.function.chat;

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
import com.culturer.yoo_home.service.handler.chat_handler.ChatMsg;
import com.culturer.yoo_home.widget.navigation.impl.HomeNavigation;
import com.vondear.rxtools.view.dialog.RxDialogChooseImage;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

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
        chatAdapter = new ChatAdapter(chatMsgs,this);
    }

                                                                    //收发聊天数据//
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    //接收消息
    @Subscribe
    public void receiveMsg(ChatMsg msg){
        if (indicate(msg)){
            chatMsgs.add(msg);
            chatAdapter.setDataAndupdate(chatMsgs);
        }
    }

    //发送消息
    private void sendMsg(String strMsg){
        ChatMsg chatMsg = presenter.sendTextMsg(strMsg);
        chatMsgs.add(chatMsg);
        chatAdapter.setDataAndupdate(chatMsgs);
    }

    //权限验证
    private boolean indicate(ChatMsg chatMsg){
        boolean flag = false;
        //验证消息是不是自己发的
        if (chatMsg.getUserId()!=BaseMsg.getUser().getId()) flag = true;
        Log.i(TAG, "indicate: the msg is sent by myself --- "+chatMsg.toString());
        //验证消息是不是发给自己的
        List<Integer> users = chatMsg.getUsers();
        if (users!=null){
            for (int i=0;i<users.size();i++){
                if (users.get(i) == BaseMsg.getUser().getId()){
                    flag = true;
                    break;
                }
            }
        }
        return flag;
    }

                                                                    //初始UI组件//
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    //初始化基础UI组件
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
        chat_camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                initDialogChooseImage();
            }
        });

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
        chat_audio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //1.修改UI界面，显示开始录音
                //2.录音完将数据打包发送到HttpService
                //3.HttpService将音频文件上传成功后返回信息
                //4.收到返回信息后向mqttservice发送消息
                //5.mqttservice将消息publish出去

            }
        });

        //发送聊天信息
        chat_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            //1.打包数据
            // 2.将打包的数据发送到MQTT
            //3.MQTT将打包的数据发送出去
            String strMsg  = chat_edit.getText().toString();
            if (!strMsg.equals("")){
                sendMsg(strMsg);
                toLast();
                clearText();
            }
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
