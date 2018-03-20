package com.culturer.yoo_home.function.login.login;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.armour8.yooplus.yooplus.R;
import com.culturer.yoo_home.function.login.forget.ForgetActivity;
import com.culturer.yoo_home.function.login.load.LoadActivity;
import com.culturer.yoo_home.util.Typefaces;
import com.culturer.yoo_home.function.login.register.RegisterActivity;

import static com.culturer.yoo_home.config.Config.LOAD_FILE;
import static com.culturer.yoo_home.config.Config.LOGIN_ONSUCCESS;

public class LoginActivity extends AppCompatActivity implements ILoginView {

    private static final String TAG = "LoginActivity";

    LoginPresenter presenter;

    private TextView login_largetitle;
    private TextView login_littletitle;
    private EditText login_username;
    private EditText login_password;
    private Button login_login;
    private Button login_register;
    private TextView login_forget;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
        setContentView(R.layout.activity_login);
        setPresenter(createPresenter());
        initBaseView();
    }

    @Override
    public void setPresenter(LoginPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public LoginPresenter createPresenter() {

        LoginPresenter presenter = new LoginPresenter(this,
                new LoginRespository(new LoginLocalDataSource(this),
                        new LoginRemoteDataSource(this),
                        this),
                this);

        return presenter;
    }

    private void initBaseView(){
        login_largetitle = findViewById(R.id.login_large_title);
        login_littletitle = findViewById(R.id.login_littletitle);
        login_username = findViewById(R.id.login_username);
        login_password = findViewById(R.id.login_password);
        login_login = findViewById(R.id.login_login);
        login_register = findViewById(R.id.login_register);
        login_forget = findViewById(R.id.login_forget);

        login_littletitle.setTypeface(Typefaces.get(this, "Satisfy-Regular.ttf"));

        login_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tel = login_username.getText().toString();
                String password = login_password.getText().toString();
                presenter.login(tel,password);
            }
        });
        login_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(intent);
            }
        });
        login_forget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this,ForgetActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void loginSuccess() {
        startActivityForResult(new Intent(LoginActivity.this,LoadActivity.class),LOGIN_ONSUCCESS);
        finish();
    }

    //加载数据失败处理
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == LOGIN_ONSUCCESS && resultCode == LOAD_FILE){
            Toast.makeText(this,"加载数据失败",Toast.LENGTH_LONG).show();
            login_login.setClickable(true);
        }
    }

}
