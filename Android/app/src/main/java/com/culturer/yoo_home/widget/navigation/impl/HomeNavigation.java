package com.culturer.yoo_home.widget.navigation.impl;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.armour8.yooplus.yooplus.R;
import com.culturer.yoo_home.widget.navigation.base.AbsNavigation;
import com.culturer.yoo_home.widget.navigation.base.DefaultNavigation;
import com.culturer.yoo_home.widget.navigation.base.INavigation;

/**
 * Created by Administrator on 2017/11/17.
 */

public class HomeNavigation extends DefaultNavigation<HomeNavigation.HomeNavigationParams> {

    public HomeNavigation(HomeNavigationParams params) {
        super(params);
    }


    @Override
    public int bindLeftLayoutId() {
        return R.layout.navigation_home_left;
    }

    @Override
    public int bindCenterLayoutId() {
        return R.layout.navigation_home_center;
    }

    @Override
    public int bindRightLayoutId() {
        return R.layout.navigation_home_right;
    }

    @Override
    public void initLeftLayout(View view) {

    }

    @Override
    public void initCenterLayout(View view) {

        TextView HomeTopic = view.findViewById(R.id.HomeTopic);
        TextView HomeTitle = (TextView) findViewById(R.id.HomeTitle);
        HomeTopic.setText(getParams().homeTopic);
        HomeTitle.setText(getParams().homeTitle);

    }

    @Override
    public void initRightLayout(View view) {
        ImageView Right_Img = view.findViewById(R.id.Right_Img);
        if (getParams().rightImg!=0){
            Right_Img.setImageResource(getParams().rightImg);
            view.setOnClickListener(getParams().rightListener);
            Right_Img.setVisibility(View.VISIBLE);
        }
        
    }

    public static class HomeNavigationParams extends AbsNavigation.NavigationParams {

        public String homeTopic;
        public String homeTitle;
        public int rightImg;
        public View.OnClickListener rightListener;

        public HomeNavigationParams(Context context, ViewGroup parent){
            super(context,parent);
        }

    }

    public static class Builder extends AbsNavigation.Builder {

        private HomeNavigationParams p;

        public Builder(Context context,ViewGroup parent){
            super(context,parent);
            this.p = new HomeNavigationParams(context,parent);
        }


        public Builder setCenterHomeTopic(String homeTopic){
            this.p.homeTopic = homeTopic;
            return this;
        }

        public Builder setCenterHomeTitle(String homeTitle){
            this.p.homeTitle = homeTitle;
            return this;
        }

        public Builder setRightImg(int rightImg){
            this.p.rightImg = rightImg;
            return this;
        }
    
        public Builder setRightListener( View.OnClickListener listener){
            this.p.rightListener = listener;
            return this;
        }
        
        @Override
        public INavigation create() {
            return new HomeNavigation(this.p);
        }
    }
}
