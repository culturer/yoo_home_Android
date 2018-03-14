package com.culturer.yoo_home.widget.navigation.base;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;


public abstract class AbsNavigation<P extends AbsNavigation.NavigationParams> implements INavigation {

    //当前实现类作用:根据builder参数集,动态创建对应的UI

    public static final int DEFAULT_LAYOUT_ID = 0;

    private P params;
    private View contentView;

    public AbsNavigation(P params) {
        this.params = params;
    }

    public P getParams() {
        return params;
    }

    public View getContentView() {
        return contentView;
    }

    public View findViewById(int id){
        return contentView.findViewById(id);
    }

    public String getString(int id){
        return params.context.getResources().getString(id);
    }


    @Override
    public int bindLayoutId() {
        return DEFAULT_LAYOUT_ID;
    }

    @Override
    public void build() {
        if (contentView == null){
            contentView = bindParent(bindLayoutId(),getParams().parent);
        }
    }

    public View bindParent(int layoutId, ViewGroup parent) {
        if (layoutId == DEFAULT_LAYOUT_ID) {
            return null;
        }
        View layout = getParams().inflater.inflate(layoutId, parent,false);
        return bindParent(layout, parent);
    }

    public View bindParent(View childView, ViewGroup parent) {
        //子容器只允许有一个爸爸
        //保证我们的当前创建的视图有且只有一个父容器
        ViewParent viewParent = childView.getParent();
        if (viewParent != null) {
            ViewGroup viewGroup = (ViewGroup) viewParent;
            viewGroup.removeView(childView);
        }
        parent.addView(childView, 0);
        return childView;
    }

    /**
     * 所有的Toolbar都必须有的参数(Builder设计模式---参数集(专门的参数累))
     */
    public static class NavigationParams {
        public Context context;
        public ViewGroup parent;
        public LayoutInflater inflater;

        public NavigationParams(Context context, ViewGroup parent) {
            this.context = context;
            this.parent = parent;
            this.inflater = LayoutInflater.from(context);
        }
    }

    /** 
     * 根据参数集构建我们想要的功能
     */
    public abstract static class Builder {
        public Builder(Context context, ViewGroup parent) {

        }

        /**
         * 创建Toolbar
         *
         * @return
         */
        public abstract INavigation create();
    }

}
