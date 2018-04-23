package com.culturer.yoo_home.widget.bottomPopWindow;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.culturer.yoo_home.R;
import com.culturer.yoo_home.util.DisplayUtil;

/**
 * Created by cctv on 2017/9/11.
 */

public class BottomPopupWindow extends PopupWindow implements View.OnClickListener {
    private View view;
    private Context mcontext;
    private OnItemClickListener monItemClickListener;
    private OnItemClickListener2 monItemClickListener2;
    ItemAdapter itemAdapter;
    private String[] feedback_items;

    public BottomPopupWindow(Context context, String[] strings) {
        super(context);
        mcontext = context;
        feedback_items = strings;
//        Resources resources = context.getResources();
//        feedback_items = resources.getStringArray(R.array.spinner_items);
        view = initListView();
        this.setContentView(view);
        this.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        this.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        ColorDrawable dw = new ColorDrawable(0000000000);
        this.setFocusable(true);
        this.setAnimationStyle(R.style.pop_Anim);
        this.setBackgroundDrawable(dw);
        setBackgroundAlpha(0.4f, mcontext);
        view.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                int height = view.findViewById(R.id.tv_item).getTop();
                int y = (int) motionEvent.getY();
                if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                    if (y < height) {
                        dismiss();
                    }
                }
                return true;
            }
        });
        this.setOnDismissListener(new OnDismissListener() {
            @Override
            public void onDismiss() {
                setBackgroundAlpha(1.0f, mcontext);
            }
        });
    }

    /**
     * 初始化一个Listview
     *
     * @return
     */
    private LinearLayout initListView() {
        LinearLayout mLin = new LinearLayout(mcontext);
        LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        mLin.setLayoutParams(layoutParams2);
        int result = DisplayUtil.dip2px(10);
        mLin.setPadding(result,result,result,result);
        ListView mListView = new ListView(mcontext);
        mListView.setDivider(new ColorDrawable(mcontext.getColor(R.color.text_grey)));//mcontext.getResources().
        mListView.setDividerHeight(1);
        ViewGroup.LayoutParams layoutParams = new AbsListView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        mListView.setLayoutParams(layoutParams);
        // 去掉右侧垂直滑动条
        mListView.setVerticalScrollBarEnabled(false);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });

        // 模拟数据
//        itemList

        // 设置适配器展示数据
        itemAdapter = new ItemAdapter();
        mListView.setAdapter(itemAdapter);
        mLin.addView(mListView);
        return mLin;
    }

    /**
     * 设置背景颜色
     *
     * @param bgAlpha
     */
    public static void setBackgroundAlpha(float bgAlpha, Context mContext) {
        WindowManager.LayoutParams lp = ((Activity) mContext).getWindow()
                .getAttributes();
        lp.alpha = bgAlpha;
        ((Activity) mContext).getWindow().setAttributes(lp);
    }


    @Override
    public void onClick(View view) {
        if (monItemClickListener != null) {
            monItemClickListener.setOnItemClick(view);
        }
        if (monItemClickListener2!=null){
        }
    }

    public interface OnItemClickListener {
        void setOnItemClick(View v);
    }
    public interface OnItemClickListener2 {
        void setOnItemClick2(View v, int position);
    }
    public void setOnItemClickListner2(OnItemClickListener2 listner) {
        this.monItemClickListener2 = listner;
    }
    public void setOnItemClickListner(OnItemClickListener listner) {
        this.monItemClickListener = listner;
    }

    //
    class ItemAdapter extends BaseAdapter {

        @Override
        public int getCount() {
//            return itemList.size();
            int length = feedback_items.length;
            return length;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            NumberHolder mHolder = null;
            if (convertView == null) {
                convertView = View.inflate(mcontext, R.layout.view_spinner_item, null);
                mHolder = new NumberHolder(); // 缓存类
                mHolder.tv_item = (TextView) convertView.findViewById(R.id.tv_item);
                mHolder.lay_ll_item = (LinearLayout) convertView.findViewById(R.id.lay_ll_item);
                // 把Holder类设置给convertView对象.
                convertView.setTag(mHolder);
            } else {
                mHolder = (NumberHolder) convertView.getTag();
            }
            mHolder.tv_item.setText(feedback_items[position]);
            mHolder.tv_item.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    System.out.println("点击：" + position);
                    monItemClickListener2.setOnItemClick2(view,position);

                }
            });
            if (position ==0){
                mHolder.tv_item.setBackgroundResource(R.drawable.sel_bg_white2gray_01);
            }else if (position == feedback_items.length-1){
                mHolder.tv_item.setBackgroundResource(R.drawable.sel_bg_white2gray_03);
            }else {
                mHolder.tv_item.setBackgroundResource(R.drawable.sel_bg_white2gray_02);
            }
            return convertView;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }
    }

    class NumberHolder {
        public TextView tv_item;
        public LinearLayout lay_ll_item;
    }
}
