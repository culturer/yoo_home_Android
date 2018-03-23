package com.culturer.yoo_home.test;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.armour8.yooplus.yooplus.R;
import com.culturer.yoo_home.service.MQTT.MQTTMsg;

import org.greenrobot.eventbus.EventBus;

public class Test4Activity extends AppCompatActivity {

    TextView resv;
    EditText send;
    Button btn_send;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test4);
//        Intent intent = new Intent(this, MQTTService.class);
//        startService(intent);
        resv = (TextView) findViewById(R.id.receive);
        send = (EditText) findViewById(R.id.send);
        btn_send = (Button) findViewById(R.id.bt_send);
        btn_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                send();
            }
        });
    }
    private void send(){
        String tx = send.getText().toString();
        EventBus.getDefault().post(new MQTTMsg(true,MQTTMsg.CHAT_MSG,tx));
        resv.setText(tx);
    }

}
