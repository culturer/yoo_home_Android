package com.culturer.yoo_home.function.login.load;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.armour8.yooplus.yooplus.R;
import com.culturer.yoo_home.function.main.MainActivity;
import com.culturer.yoo_home.service.MQTT.MQTTService;
import com.culturer.yoo_home.util.Typefaces;
import com.culturer.yoo_home.widget.loading_anim.Titanic;
import com.culturer.yoo_home.widget.loading_anim.TitanicTextView;

import static com.culturer.yoo_home.config.Config.LOAD_FILE;

public class LoadActivity extends AppCompatActivity implements ILoadView {

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
        // set fancy typeface
        tv.setTypeface(Typefaces.get(this, "Satisfy-Regular.ttf"));
        logo = findViewById(R.id.logo);
        logo.setImageResource(R.drawable.logo_white);
        topic = findViewById(R.id.topic);
        loadCount = findViewById(R.id.loadCount);

        topic.setTypeface(Typefaces.get(this, "Satisfy-Regular.ttf"));

        titanic = new Titanic();
        // start animation
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

    @Override
    public void loadFail() {
        Intent intent = new Intent();
        Bundle datas = new Bundle();
        intent.putExtra("datas",datas);
        setResult(LOAD_FILE,intent);
        //关闭过渡动画
        titanic.cancel();
    }

    //初始化Service
    private void initService(){
        Intent intent = new Intent(this,MQTTService.class);
        startService(intent);
    }
}
