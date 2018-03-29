package com.culturer.yoo_home.function.login.login;

import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.armour8.yooplus.yooplus.R;
import com.culturer.yoo_home.function.login.forget.ForgetActivity;
import com.culturer.yoo_home.function.login.load.LoadActivity;
import com.culturer.yoo_home.test.TestActivity;
import com.culturer.yoo_home.util.MD5Util;
import com.culturer.yoo_home.util.Typefaces;
import com.culturer.yoo_home.function.login.register.RegisterActivity;
import com.vondear.rxtools.RxAnimationTool;
import com.vondear.rxtools.RxKeyboardTool;
import com.vondear.rxtools.activity.AndroidBug5497Workaround;

import static com.culturer.yoo_home.config.Config.LOAD_FILE;
import static com.culturer.yoo_home.config.Config.LOGIN_ONSUCCESS;

public class LoginActivity extends AppCompatActivity implements ILoginView {

    private static final String TAG = "LoginActivity";

    LoginPresenter presenter;

    View contentView;

    ImageView mLogo;
    EditText mEtMobile;
    ImageView mIvCleanPhone;
    EditText mEtPassword;
    ImageView mCleanPassword;
    ImageView mIvShowPwd;
    Button mBtnLogin;
    TextView mRegist;
    TextView mForgetPassword;
    LinearLayout mContent;
    ScrollView mScrollView;
    LinearLayout mService;
    RelativeLayout mRoot;

    private int screenHeight = 0;//屏幕高度
    private int keyHeight = 0; //软件盘弹起后所占高度
    private float scale = 0.6f; //logo缩放比例
    private int height = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
        contentView = LayoutInflater.from(this).inflate(R.layout.activity_login,null);
        setContentView(contentView);
        setPresenter(createPresenter());
        if (isFullScreen(this)) {
            AndroidBug5497Workaround.assistActivity(this);
        }
        init();
    }

    private void init(){
        screenHeight = this.getResources().getDisplayMetrics().heightPixels; //获取屏幕高度
        keyHeight = screenHeight / 3;//弹起高度为屏幕高度的1/3
        initData();
        initView();
        initEvent();
    }

    private void initData(){

    }

    private void initView(){
        initBaseView();
    }

    private void initBaseView(){
        mLogo = contentView.findViewById(R.id.logo);
        mEtMobile = contentView.findViewById(R.id.et_mobile);
        mIvCleanPhone = contentView.findViewById(R.id.iv_clean_phone);
        mEtPassword = contentView.findViewById(R.id.et_password);
        mCleanPassword = contentView.findViewById(R.id.clean_password);
        mIvShowPwd = contentView.findViewById(R.id.iv_show_pwd);
        mBtnLogin = contentView.findViewById(R.id.btn_login);
        mRegist = contentView.findViewById(R.id.regist);
        mForgetPassword = contentView.findViewById(R.id.forget_password);
        mContent = contentView.findViewById(R.id.content);
        mScrollView = contentView.findViewById(R.id.scrollView);
        mService = contentView.findViewById(R.id.service);
        mRoot = contentView.findViewById(R.id.root);

        mIvCleanPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mEtMobile.setText("");
            }
        });
        mCleanPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mEtPassword.setText("");
            }
        });
        mIvShowPwd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mEtPassword.getInputType() != InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD) {
                    mEtPassword.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                    mIvShowPwd.setImageResource(R.drawable.pass_visuable);
                } else {
                    mEtPassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    mIvShowPwd.setImageResource(R.drawable.pass_gone);
                }
                String pwd = mEtPassword.getText().toString();
                if (!TextUtils.isEmpty(pwd))
                    mEtPassword.setSelection(pwd.length());
            }
        });
        mRegist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(intent);
            }
        });
        mForgetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this,ForgetActivity.class);
                startActivity(intent);
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.HONEYCOMB)
    private void initEvent() {
        mEtMobile.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!TextUtils.isEmpty(s) && mIvCleanPhone.getVisibility() == View.GONE) {
                    mIvCleanPhone.setVisibility(View.VISIBLE);
                } else if (TextUtils.isEmpty(s)) {
                    mIvCleanPhone.setVisibility(View.GONE);
                }
            }
        });
        mEtPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!TextUtils.isEmpty(s) && mCleanPassword.getVisibility() == View.GONE) {
                    mCleanPassword.setVisibility(View.VISIBLE);
                } else if (TextUtils.isEmpty(s)) {
                    mCleanPassword.setVisibility(View.GONE);
                }
                if (s.toString().isEmpty())
                    return;
                if (!s.toString().matches("[A-Za-z0-9]+")) {
                    String temp = s.toString();
                    Toast.makeText(LoginActivity.this, "请输入数字或字母", Toast.LENGTH_SHORT).show();
                    s.delete(temp.length() - 1, temp.length());
                    mEtPassword.setSelection(s.length());
                }
            }
        });
        /**
         * 禁止键盘弹起的时候可以滚动
         */
        mScrollView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return true;
            }
        });
        mScrollView.addOnLayoutChangeListener(new ViewGroup.OnLayoutChangeListener() {
            @Override
            public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {
              /* old是改变前的左上右下坐标点值，没有old的是改变后的左上右下坐标点值
              现在认为只要控件将Activity向上推的高度超过了1/3屏幕高，就认为软键盘弹起*/
                if (oldBottom != 0 && bottom != 0 && (oldBottom - bottom > keyHeight)) {
                    Log.e("wenzhihao", "up------>" + (oldBottom - bottom));
                    int dist = mContent.getBottom() - bottom;
                    if (dist > 0) {
                        ObjectAnimator mAnimatorTranslateY = ObjectAnimator.ofFloat(mContent, "translationY", 0.0f, -dist);
                        mAnimatorTranslateY.setDuration(300);
                        mAnimatorTranslateY.setInterpolator(new LinearInterpolator());
                        mAnimatorTranslateY.start();
                        RxAnimationTool.zoomIn(mLogo, 0.6f, dist);
                    }
                    mService.setVisibility(View.INVISIBLE);

                } else if (oldBottom != 0 && bottom != 0 && (bottom - oldBottom > keyHeight)) {
                    Log.e("wenzhihao", "down------>" + (bottom - oldBottom));
                    if ((mContent.getBottom() - oldBottom) > 0) {
                        ObjectAnimator mAnimatorTranslateY = ObjectAnimator.ofFloat(mContent, "translationY", mContent.getTranslationY(), 0);
                        mAnimatorTranslateY.setDuration(300);
                        mAnimatorTranslateY.setInterpolator(new LinearInterpolator());
                        mAnimatorTranslateY.start();
                        //键盘收回后，logo恢复原来大小，位置同样回到初始位置
                        RxAnimationTool.zoomOut(mLogo, 0.6f);
                    }
                    mService.setVisibility(View.VISIBLE);
                }
            }
        });

        mBtnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RxKeyboardTool.hideSoftInput(LoginActivity.this);
                String tel = mEtMobile.getText().toString();
                String password = MD5Util.encrypt(mEtPassword.getText().toString().trim());
                presenter.login(tel,password);
            }
        });
    }

    public boolean isFullScreen(Activity activity) {
        return (activity.getWindow().getAttributes().flags &
                WindowManager.LayoutParams.FLAG_FULLSCREEN) == WindowManager.LayoutParams.FLAG_FULLSCREEN;
    }

    @Override
    public void setPresenter(LoginPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public LoginPresenter createPresenter() {

        LoginPresenter presenter = new LoginPresenter(
                this,
                new LoginRespository(
                        new LoginLocalDataSource(this),
                        new LoginRemoteDataSource(this),
                        this),
                this);
        return presenter;
    }

    @Override
    public void loginSuccess() {
        startActivityForResult(new Intent(LoginActivity.this,LoadActivity.class),LOGIN_ONSUCCESS);
        finish();
    }

    @Override
    public void loginFail() {
        mBtnLogin.setEnabled(true);
        mEtMobile.setEnabled(true);
        mIvCleanPhone.setEnabled(true);
        mEtPassword.setEnabled(true);
        mCleanPassword.setEnabled(true);
        mIvShowPwd.setEnabled(true);
        mRegist.setEnabled(true);
        mForgetPassword.setEnabled(true);
    }

    //加载数据失败处理
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == LOGIN_ONSUCCESS && resultCode == LOAD_FILE){
            Toast.makeText(this,"加载数据失败 "+data.getStringExtra("msg"),Toast.LENGTH_LONG).show();
            mBtnLogin.setClickable(true);
        }
    }

    @Override
    public void logining() {
        mBtnLogin.setEnabled(false);
        mEtMobile.setEnabled(false);
        mIvCleanPhone.setEnabled(false);
        mEtPassword.setEnabled(false);
        mCleanPassword.setEnabled(false);
        mIvShowPwd.setEnabled(false);
        mRegist.setEnabled(false);
        mForgetPassword.setEnabled(false);
    }
}
