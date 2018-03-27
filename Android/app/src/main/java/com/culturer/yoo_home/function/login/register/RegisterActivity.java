package com.culturer.yoo_home.function.login.register;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.armour8.yooplus.yooplus.R;
import com.culturer.yoo_home.config.CaptchaConfig;
import com.culturer.yoo_home.config.ParamConfig;

import com.culturer.yoo_home.util.HttpUtil;
import com.culturer.yoo_home.util.MD5Util;
import com.culturer.yoo_home.util.StringUtil;
import com.kymjs.rxvolley.RxVolley;
import com.kymjs.rxvolley.client.HttpCallback;
import com.kymjs.rxvolley.client.HttpParams;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Objects;

import static com.culturer.yoo_home.config.ParamConfig.HTTP_OPTIONS;
import static com.culturer.yoo_home.config.ParamConfig.HTTP_STATUS;
import static com.culturer.yoo_home.config.ParamConfig.HTTP_STATUS_SUCCESS;
import static com.culturer.yoo_home.config.Urls.CAPTCHA_URL;
import static com.culturer.yoo_home.config.Urls.REGISTER_URL;

/**
 *  注册
 *  1. 验证两次输入密码是否相同
 *  2. 验证验证码是否正确
 *  3. 向服务器注册
 */
public class RegisterActivity extends AppCompatActivity {

    private static final String TAG = "RegisterActivity";

    EditText register_password;
    EditText register_password1;
    EditText register_phone;
    Button register_ok;
    ImageView register_indicate_img;
    EditText register_indicate;

    private String key = "";
    private String data = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        getCaptcha();
        initBaseView();
    }

    private void initBaseView(){
        register_indicate_img = findViewById(R.id.register_indicate_img);
        register_indicate_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //点击图片修改验证码
                getCaptcha();
            }
        });
        register_indicate = findViewById(R.id.register_indicate);
        register_password = findViewById(R.id.register_password);
        register_password1 = findViewById(R.id.register_password1);
        register_phone = findViewById(R.id.register_phone);
        register_ok = findViewById(R.id.register_ok);
        register_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Objects.equals(register_password.getText().toString(), register_password1.getText().toString())){
                    verfiyCaptcha();
                }else {
                    Log.i(TAG, "register: passeord ---  "+register_password.getText().toString()+",password1 --- "+register_password1.getText().toString());
                    Toast.makeText(RegisterActivity.this,"两次密码输入不相同，请重新输入",Toast.LENGTH_LONG).show();
                }
            }
        });

    }

    private void register(){

        HttpCallback callback = new HttpCallback() {
            @Override
            public void onSuccess(String t) {
                Log.i(TAG, "onSuccess: "+t);
                try {
                    JSONObject jsonObject = new JSONObject(t);
                    int status = jsonObject.getInt(HTTP_STATUS);
                    if ( status == 200 ){
                        Toast.makeText(RegisterActivity.this,"注册成功！",Toast.LENGTH_LONG).show();
                        RegisterActivity.this.finish();
                    }else {
                        String msg = jsonObject.getString("msg");
                        Toast.makeText(RegisterActivity.this,msg,Toast.LENGTH_LONG).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int errorNo, String strMsg) {
                Log.i(TAG, "onFailure: "+errorNo+"msg:"+strMsg);
                Toast.makeText(RegisterActivity.this,"注册失败,请检查网络",Toast.LENGTH_LONG).show();
            }
        };

        HttpParams params = new HttpParams();
        params.put(ParamConfig.PHONE,register_phone.getText().toString());
        params.put(ParamConfig.PASSWORD, MD5Util.encrypt(register_password.getText().toString().trim()));
        HttpUtil.send(callback,params,REGISTER_URL);
    }

    //获取验证码
    private void getCaptcha(){
        HttpCallback callback = new HttpCallback() {
            @Override
            public void onSuccess(String t) {
                Log.i(TAG, "getCaptcha: "+t);
                try {
                    JSONObject jsonObject = new JSONObject(t);
                    if (jsonObject.getInt(HTTP_STATUS) == HTTP_STATUS_SUCCESS){
                        data = jsonObject.getString("data");
                        key = jsonObject.getString("key");
                        register_indicate_img.setImageBitmap(StringUtil.stringtoBitmap(StringUtil.getDataMsg(data)));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int errorNo, String strMsg) {
                Log.i(TAG, "onFailure: "+errorNo+"msg:"+strMsg);
            }
        };

        HttpParams params = new HttpParams();
        params.put(HTTP_OPTIONS, CaptchaConfig.OPTIONS_CREATE);
        params.put(CaptchaConfig.TYPE,CaptchaConfig.TYPE_NUMBER);

        HttpUtil.send(callback,params,CAPTCHA_URL);

    }

    //验证验证码
    private void verfiyCaptcha(){

        HttpCallback callback = new HttpCallback() {
            @Override
            public void onFailure(int errorNo, String strMsg) {
                Log.i(TAG, "verfiyCaptcha: errNo --- "+errorNo+",errMsg --- "+strMsg);
                Toast.makeText(RegisterActivity.this,"网络连接错误，请检查网络后重新尝试",Toast.LENGTH_LONG).show();
            }

            @Override
            public void onSuccess(String t) {
                Log.i(TAG, "verfiyCaptcha: "+t);
                try {
                    JSONObject jsonObject = new JSONObject(t);
                    if (jsonObject.getInt(HTTP_STATUS) == HTTP_STATUS_SUCCESS){
                        //清除缓存数据
                        data = null;
                        key = null;
                        register();
                    }else {
                        Toast.makeText(RegisterActivity.this,"验证码错误",Toast.LENGTH_LONG).show();
                        getCaptcha();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        };

        HttpParams params = new HttpParams();
        params.put(HTTP_OPTIONS,CaptchaConfig.OPTIONS_VERFIY);
        params.put(CaptchaConfig.KEY,key);
        params.put(CaptchaConfig.VALUE,register_indicate.getText().toString());

        HttpUtil.send(callback,params,CAPTCHA_URL);

    }

}

