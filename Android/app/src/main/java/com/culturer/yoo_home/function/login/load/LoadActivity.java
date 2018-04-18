package com.culturer.yoo_home.function.login.load;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.armour8.yooplus.yooplus.R;
import com.culturer.yoo_home.function.login.login.LoginActivity;
import com.culturer.yoo_home.function.main.MainActivity;
import com.culturer.yoo_home.service.MQTT.MQTTService;
import com.culturer.yoo_home.util.Typefaces;
import com.culturer.yoo_home.widget.loading_anim.Titanic;
import com.culturer.yoo_home.widget.loading_anim.TitanicTextView;

public class LoadActivity extends AppCompatActivity implements ILoadView {

    private static final String TAG = "LoadActivity";
    
    LoadPresenter presenter;

    Titanic titanic;
    ImageView logo;
    TextView topic;
    TextView loadCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load);
        setPresenter(createPresenter());
        initBaseView();
        load();
    }

    //加载数据
    private void load(){
        presenter.loadMsg();
    }

    private void initBaseView(){
        //进度条
        TitanicTextView tv = findViewById(R.id.my_text_view);
        // 修改字体
        tv.setTypeface(Typefaces.get(this, "Satisfy-Regular.ttf"));
        logo = findViewById(R.id.logo);
        logo.setImageResource(R.drawable.logo_white);
        topic = findViewById(R.id.topic);
        loadCount = findViewById(R.id.loadCount);
        topic.setTypeface(Typefaces.get(this, "Satisfy-Regular.ttf"));
        titanic = new Titanic();
        // 启动加载动画
        titanic.start(tv);
    }

    @Override
    public void setPresenter(LoadPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public LoadPresenter createPresenter() {
        LoadPresenter presenter = new LoadPresenter(this,
                new LoadRespository(
                        new LoadLocalDataSource(this),
                        new LoadRemoteDataSource(this),
                        this),
                this);

        return presenter;
    }

    @Override
    public void loadSuccess() {
        //启动后台服务
        initService();
        //页面跳转
        Intent intent = new Intent(LoadActivity.this, MainActivity.class);
        startActivity(intent);
        this.finish();
    }

    //初始化Service
    private void initService(){
        //启动MQTT服务
        Intent intentMQTT = new Intent(this,MQTTService.class);
        startService(intentMQTT);
        //启动IM服务
//        Intent intentIM = new Intent(this, IMService.class);
//        startService(intentIM);
    }

    @Override
    public void loadFail(String msg ) {
        Intent intent = new Intent(this, LoginActivity.class);
        Log.i(TAG, "loadFail: ");
        intent.putExtra("msg","加载数据异常,请检查网络后重试。");
        startActivity(intent);
        //关闭过渡动画
        titanic.cancel();
        this.finish();
    }

    @Override
    public void setprogress(float num) {
        loadCount.setText((num*100)+"%");
    }
}
