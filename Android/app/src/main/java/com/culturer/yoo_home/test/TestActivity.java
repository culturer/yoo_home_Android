package com.culturer.yoo_home.test;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.culturer.yoo_home.R;
import com.vondear.rxtools.view.RxQRCode;

public class TestActivity extends AppCompatActivity{

    private static final String TAG = "TestActivity";
    private View contentView;
    ImageView iv_test;
    EditText et_test;
    Button bt_test;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        contentView = getLayoutInflater().inflate(R.layout.activity_test,null);
        setContentView(contentView);
        init();
    }

    private void init(){
        iv_test = contentView.findViewById(R.id.iv_test);
        et_test = contentView.findViewById(R.id.et_test);
        bt_test = contentView.findViewById(R.id.bt_test);
        bt_test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RxQRCode.builder(et_test.getText().toString()).
                        backColor(getResources().getColor(com.vondear.rxtools.R.color.white)).
                        codeColor(getResources().getColor(com.vondear.rxtools.R.color.black)).
                        codeSide(600).
                        into(iv_test);
            }
        });
    }

}
