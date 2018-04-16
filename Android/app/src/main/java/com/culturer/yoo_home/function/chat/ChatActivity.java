package com.culturer.yoo_home.function.chat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;

import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.armour8.yooplus.yooplus.R;


import com.culturer.yoo_home.cahce.CacheData;

import com.culturer.yoo_home.service.MQTT.MQTTMsg;

import com.culturer.yoo_home.util.AudioRecoder;
import com.culturer.yoo_home.util.DirUtil;

import com.culturer.yoo_home.util.TimeUtil;
import com.culturer.yoo_home.widget.navigation.impl.HomeNavigation;
import com.google.gson.Gson;
import com.samanlan.lib_permisshelper.PermissionsListener;
import com.samanlan.lib_permisshelper.PermissionsUtils;
import com.yanzhenjie.album.Action;
import com.yanzhenjie.album.Album;
import com.yanzhenjie.album.AlbumFile;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import io.github.rockerhieu.emojicon.EmojiconEditText;
import io.github.rockerhieu.emojicon.EmojiconGridFragment;
import io.github.rockerhieu.emojicon.EmojiconsFragment;
import io.github.rockerhieu.emojicon.emoji.Emojicon;

import static com.culturer.yoo_home.config.HomeMainConfig.CHAT_DATA;
import static com.culturer.yoo_home.config.HomeMainConfig.CHAT_RECEIVER;
import static com.culturer.yoo_home.config.HomeMainConfig.CHAT_TYPE;


public class ChatActivity extends AppCompatActivity implements IChatView,
        EmojiconGridFragment.OnEmojiconClickedListener,
        EmojiconsFragment.OnEmojiconBackspaceClickedListener   {

    private static final String TAG = "ChatActivity";
    
    ChatPresenter presenter;
    
    AudioRecoder audioRecoder;
    
    boolean chat_type;
    String username;
    int userId;
    String userIcon;

    private View contentViwe;
    private RecyclerView chat_list;
    private EmojiconEditText chat_edit;
    
    private FrameLayout emojicons;
    private  EmojiconsFragment emojiconsFragment;

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
        initGrant();
        initData();
        initView();
        setEmojiconFragment();
    }
    
    //初始化EventBus
    private void initEventBus(){
        EventBus.getDefault().register(this);
    }

    //授权
    private void initGrant(){
       
        PermissionsUtils   mPermissionsUtils = new PermissionsUtils();
        mPermissionsUtils
                .setPermissionsListener(new PermissionsListener() {
                    @Override
                    public void onDenied(String[] deniedPermissions) {
                        for (String deniedPermission : deniedPermissions) {
                            Log.i(TAG, "onDenied: "+deniedPermission + " 权限被拒绝");
                        }
                    }
                
                    @Override
                    public void onGranted() {
                        Log.i(TAG, "onGranted: 所有权限都被同意");
                    }
                })
                .withActivity(this)
                .getPermissions(ChatActivity.this
                        , 100
                        , Manifest.permission.RECORD_AUDIO
                        , Manifest.permission.READ_EXTERNAL_STORAGE
                        , Manifest.permission.READ_CALENDAR
                        , Manifest.permission.ACCESS_FINE_LOCATION);
    }
    
    //初始化数据
    private void initData(){
        initConvertData();
        initListData();
        initEmoji();
    }

    //初始化UI组件
    private void initView(){
        initBaseView();
        initNavigation(contentViwe);
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

    private void initEmoji(){
        emojiconsFragment =EmojiconsFragment.newInstance(true);
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
        
        chat_list = findViewById(R.id.chat_list);
        chat_edit = findViewById(R.id.chat_edit);
        ImageButton chat_camera = findViewById(R.id.chat_camera);
        ImageButton chat_emoji = findViewById(R.id.chat_emoji);
        ImageButton chat_tel = findViewById(R.id.chat_tel);
        ImageButton chat_audio = findViewById(R.id.chat_audio);
        TextView chat_send = findViewById(R.id.chat_send);
        emojicons = findViewById(R.id.emojicons);
    
        chat_edit.setOnClickListener(view -> emojicons.setVisibility(View.GONE));
        
        //打开相机拍照
        chat_camera.setOnClickListener(view -> {
            initDialogChooseImage();
            emojicons.setVisibility(View.GONE);
        });

        //表情包
        chat_emoji.setOnClickListener(view -> emojicons.setVisibility(View.VISIBLE));

        //语音/视频聊天，预留接口
        chat_tel.setOnClickListener(view -> {
            emojicons.setVisibility(View.GONE);
        });

        //发送语音，预留接口，暂时屏蔽
        chat_audio.setOnTouchListener((v, event) -> {
            switch (event.getAction()){
                case MotionEvent.ACTION_DOWN:
                    //初始化录音设置
                    recordAudio();
                    //开始录音
                    audioRecoder.startRecord();
                    emojicons.setVisibility(View.GONE);
                    break;
                case MotionEvent.ACTION_UP:
                    //结束录音（保存录音文件）
                    audioRecoder.stopRecord();
                    break;
            }
            return false;
        });

        //发送聊天信息
        chat_send.setOnClickListener(view -> {
            //1.打包数据
            // 2.将打包的数据发送到MQTT
            //3.MQTT将打包的数据发送出去
            String strMsg  = chat_edit.getText().toString();
            if (!strMsg.equals("")){
                emojicons.setVisibility(View.GONE);
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
    private void initNavigation(View contentView) {
        LinearLayout topNavigation = contentView.findViewById(R.id.container);
        HomeNavigation.Builder builder = new HomeNavigation.Builder(this, topNavigation);
        builder.setCenterHomeTopic("Yoo+")
                .setCenterHomeTitle("心若向阳，无畏悲伤")
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
        Album.image(this) // 选择图片。
                .multipleChoice()
                .requestCode(300)
                .camera(true)
                .columnCount(4)
                .selectCount(1)
                .checkedList(new ArrayList<AlbumFile>())
                .filterSize(null)
                .filterMimeType(null)
                .afterFilterVisibility(false) // 显示被过滤掉的文件，但它们是不可用的。
                .onResult(new Action<ArrayList<AlbumFile>>() {
                    @Override
                    public void onAction(int requestCode, @NonNull final ArrayList<AlbumFile> result) {
                    
                    }
                
                })
                .onCancel((requestCode, result) -> {
                })
                .start();
    }
    
    //初始化录音工具
    //由于AudioUtil可能每次调用结束就会清理缓存，所以在录音时进行初始化
    private void recordAudio(){
        audioRecoder = new AudioRecoder(DirUtil.AUDIO_PATH);
        //录音回调
        audioRecoder.setOnAudioStatusUpdateListener(new AudioRecoder.OnAudioStatusUpdateListener() {
            @Override
            public void onUpdate(double db, long time) {
//               根据分贝值来设置录音时话筒图标的上下波动
//               mImageView.getDrawable().setLevel((int) (3000 + 6000 * db / 100));
//               mTextView.setText(TimeUtils.long2String(time));
                Log.i(TAG, "onUpdate: Audio --- "+((int) (3000 + 6000 * db / 100)));
                Log.i(TAG, "Audio --- onUpdate: time["+ TimeUtil.getDataToString(time)+"]"+" , db["+db+"]");
            }
            
            @Override
            public void onStop(String filePath) {

               Toast.makeText(ChatActivity.this, "录音保存在：" + filePath, Toast.LENGTH_SHORT).show();
//               mTextView.setText(TimeUtils.long2String(0));
                Log.i(TAG, "Audio --- onStop: filePath["+filePath+"]");
            }
        });
        
    }
    
    private void setEmojiconFragment() {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.emojicons, emojiconsFragment)
                .commit();
    }
    
    @Override
    public void onEmojiconClicked(Emojicon emojicon) {
        EmojiconsFragment.input(chat_edit, emojicon);
    }
    
    @Override
    public void onEmojiconBackspaceClicked(View v) {
        EmojiconsFragment.backspace(chat_edit);
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
