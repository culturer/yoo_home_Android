package com.culturer.yoo_home.function.mine;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.armour8.yooplus.yooplus.R;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import com.culturer.yoo_home.base.GlideApp;
import com.vondear.rxtools.RxPhotoTool;

public class Page1Fragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private View contentView;
    private ImageView user_icon;
    private TextView user_name;

    private Intent intent_startUser;

    public Page1Fragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static Page1Fragment newInstance(String param1, String param2) {
        Page1Fragment fragment = new Page1Fragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        contentView = inflater.inflate(R.layout.fragment_page1, container, false);
        init();
        return contentView;
    }

    private void init(){
        initData();
        initView();
    }

    private void initData() {
        //初始化页面跳转Intent
        intent_startUser = new Intent(getContext(),UserActivity.class);
    }

    private void initView(){
        //中间圆形用户头像
        user_name = contentView.findViewById(R.id.user_name);
        //用户名
        user_icon = contentView.findViewById(R.id.user_icon);
        //加载用户头像
        GlideApp.with(this).
                load(RxPhotoTool.cropImageUri).
                diskCacheStrategy(DiskCacheStrategy.RESOURCE).
                apply(RequestOptions.bitmapTransform(new CircleCrop())).
//                        bitmapTransform(new CropCircleTransformation(mContext)).
                thumbnail(0.5f).
                placeholder(R.drawable.logo_black).
                priority(Priority.LOW).
                error(R.drawable.logo_black).
                fallback(R.drawable.logo_black).
                into(user_icon);
        //点击用户头像,切换到用户信息页面
        user_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(intent_startUser);
            }
        });
    }
}
