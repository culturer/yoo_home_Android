package com.culturer.yoo_home.widget.circleMenu;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.PointF;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;
import android.widget.TextView;

import com.armour8.yooplus.yooplus.R;

public class SkyWheel extends ViewGroup {
    
    PointF center; // 圆心
    float radius; // 半径
    double cellDegree; // 子控件间的夹角
    double diffDegree;
    private GestureDetector mDetector;
    private ValueAnimator mAnimator;

    public SkyWheel(Context context) {
        this(context,null);
    }

    public SkyWheel(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        mDetector = new GestureDetector(getContext(),new GestureDetector
                .SimpleOnGestureListener(){

            @Override
            public boolean onDown(MotionEvent e) {
                if (mAnimator != null && mAnimator.isRunning()){
                    mAnimator.cancel();
                }
                return false;
            }

            /**
             * 手指点击一次的时候调用一次
             * @param e
             * @return
             */
            @Override
            public boolean onSingleTapUp(MotionEvent e) {
                // 点击时，判断当前点是否被某个子控件包含，如果包含直接调用子控件的点击事件即可
                int index = getClickPosition(e);
                if (index > -1){
                    getChildAt(index).performClick();// 强制让当前的控件的点击事件执行
                }
                return false;
            }

            /**
             * 
             * @param e1 按下事件 action—_down
             * @param e2 move事件，最近一次的action_move
             * @param distanceX = 上一次move事件的x值 - 当前move事件的x值
             * @param distanceY = 上一次move事件的y值 - 当前move事件的y值
             * @return
             */
            @Override
            public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
                // 计算出手指滑动过程中的角度变化夹角
                // 计算出起始点的角度
                double startDegree = getDegree(e2.getX()+distanceX, e2.getY()+distanceX);
                // 计算出结束点的角度
                double endDegree = getDegree(e2.getX(),e2.getY());
                // 计算出夹角
                double diff = endDegree - startDegree;
                setDegree(diffDegree + diff);
                
                return false;
            }

            /**
             * 
             * @param e1 手指按下的事件
             * @param e2 手指移动的事件
             * @param velocityX 手指离开屏幕时，在x轴的速率（像素/s）
             * @param velocityY 手指离开屏幕时，在y轴的速率（像素/s）
             * @return
             */
            @Override
            public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
                // 计算当前点的角度
                double startDegree = getDegree(e2.getX(),e2.getY());
                // 计算1ms后的角度
                double endDegreeAfter1ms = getDegree(e2.getX() + velocityX/1000,e2.getY() + velocityY/1000);
                // 计算出1ms的角速度
                double velocityAfter1ms = endDegreeAfter1ms - startDegree;
                // 计算出1s的角速度
                double velocityAfter1s = velocityAfter1ms*1000;
                // 通过角速度设置，要移动到的角度
                startFlingAnimation(velocityAfter1s);
                return false;
            }
        });
    }

    private int getClickPosition(MotionEvent e) {
        // 遍历每个子控件是否包含当前点
        for (int i = 0; i < getChildCount(); i++) {
            View child = getChildAt(i);
            RectF rect = new RectF(child.getLeft(),child.getTop(),child.getRight(),child.getBottom());
            if (rect.contains(e.getX(),e.getY())){
                return i;
            }
        }
        return -1;
    }

    private void startFlingAnimation(double velocityAfter1s) {
        // 根据当前的角速度，计算出执行时间，与对应时间之后的角度变化
        int duration = (int) Math.abs(velocityAfter1s*1000);
        if (duration > 1000){
            duration = 1000;
        }
        // 计算出角度变化
        double diff = velocityAfter1s*duration/1000;
        // 设置角度变化
        mAnimator = ValueAnimator.ofFloat((float) diffDegree,(float) (diffDegree+diff));
        mAnimator.setDuration(1000);
        mAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                // 获取当前正在变化的角度，设置给转盘移动
                float animatedValue = (float) animation.getAnimatedValue();
                setDegree(animatedValue);
            }
        });
        // 设置先快后慢的插值器/估值器
        mAnimator.setInterpolator(new DecelerateInterpolator());
        mAnimator.start();
    }

    /**
     * 使用反正切函数计算出角度
     * @param x
     * @param y
     * @return
     */
    public double getDegree(float x, float y){
        return Math.atan2(y-center.y,x-center.x);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        calculateValue();
        
        for (int i = 0; i < getChildCount(); i++) {
            View childView = getChildAt(i);
            // 根据三角函数的规律，排版每个子控件的位置
            childView.layout(
                    (int)(center.x + Math.sin(i*cellDegree + diffDegree)*radius - childView.getWidth()/2),
                    (int)(center.y - Math.cos(i*cellDegree + diffDegree)*radius - childView.getHeight()/2),
                    (int)(center.x + Math.sin(i*cellDegree + diffDegree)*radius + childView.getWidth()/2),
                    (int)(center.y - Math.cos(i*cellDegree + diffDegree)*radius + childView.getHeight()/2)
            );
        }
    }

    /**
     * 计算圆心、半径、夹角
     */
    public void calculateValue(){
        
        // 圆心
        center = new PointF();
        center.x = getWidth()/2;
        center.y = getHeight()/2;

        int maxwidth = 0;
        int maxheight = 0;

        for (int i=0; i<getChildCount();i++){
            maxwidth = Math.max(maxwidth,getChildAt(i).getWidth());
            maxheight = Math.max(maxheight,getChildAt(i).getHeight());
        }
        
        // 半径
        float r1 = center.x - maxwidth;
        float r2 = center.y - maxheight;
        radius = Math.min(r1,r2);
        
        // 夹角
        cellDegree = Math.PI*2/getChildCount();
    }

    /**
     * 动态切换控件的位置
     * @param degree
     */
    public void setDegree(double degree){
        diffDegree += degree;
        // 强制控件重新排版，onlayout()方法重新执行
        requestLayout();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        mDetector.onTouchEvent(event);
        return true;
    }

    public void setDatas(final int[] resIds, String[] texts,View.OnClickListener[] listeners) {
        for (int i = 0; i < resIds.length; i++) {
            View view = View.inflate(getContext(), R.layout.circle_item, null);
            ImageView iv = (ImageView) view.findViewById(R.id.iv);
            TextView tv = (TextView) view.findViewById(R.id.tv);
            iv.setImageResource(resIds[i]);
            tv.setText(texts[i]);
            iv.setOnClickListener(listeners[i]);
            addView(view);
        }
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return true;
    }
}