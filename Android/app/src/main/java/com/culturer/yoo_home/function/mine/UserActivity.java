package com.culturer.yoo_home.function.mine;



import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.armour8.yooplus.yooplus.R;

public class UserActivity extends AppCompatActivity {

    TextView mTvBg;
    ImageView mIvAvatar;
    LinearLayout mLlAnchorLeft;
    RelativeLayout mRlAvatar;
    TextView mTvName;
    TextView mTvConstellation;
    TextView mTvBirthday;
    TextView mTvAddress;
    TextView mTvLables;
    TextView mTextView2;
    TextView mEditText2;
    Button mBtnExit;
    LinearLayout mActivityUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        init();
    }

    private void init(){
        initData();
        initView();
    }

    private void initData(){

    }

    private void initView(){
        initBaseView();
        initDialogChooseImage();
    }

    private void initBaseView() {
        mTvBg = findViewById(R.id.tv_bg);
        mIvAvatar = findViewById(R.id.iv_avatar);
        mLlAnchorLeft = findViewById(R.id.ll_anchor_left);
        mRlAvatar = findViewById(R.id.rl_avatar);
        mTvName = findViewById(R.id.tv_name);
        mTvConstellation = findViewById(R.id.tv_constellation);
        mTvBirthday = findViewById(R.id.tv_birthday);
        mTvAddress = findViewById(R.id.tv_address);
        mTvLables = findViewById(R.id.tv_lables);
        mTextView2 = findViewById(R.id.textView2);
        mEditText2 = findViewById(R.id.editText2);
        mBtnExit = findViewById(R.id.btn_exit);
        mActivityUser = findViewById(R.id.activity_user);

        mIvAvatar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                initDialogChooseImage();
            }
        });
        mIvAvatar.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
//                RxImageTool.showBigImageView(mContext, resultUri);
//                RxDialogScaleView rxDialogScaleView = new RxDialogScaleView(UserActivity.this);
//                rxDialogScaleView.setImageUri(resultUri);
//                rxDialogScaleView.show();
                return false;
            }
        });
    }

    private void initDialogChooseImage() {

    }

}
