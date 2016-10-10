package com.tp.demo;

import android.content.Context;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.RelativeLayout;

/**
 * Created by topone37 on 2016/10/10 0010.
 * <p/>
 * Email: 1273305693@qq.com
 * CSDN:  http://blog.csdn.net/topone37
 * <p/>
 * DESC:可以移动的普通View
 */
public class MoveView extends View {

    //屏幕的宽高
    int mScreenWidth;
    int mScreenHeight;

    //view本身的宽高
    int mWidth;
    int mHeight;

    public MoveView(Context context) {
        super(context);
    }

    public MoveView(Context context, AttributeSet attrs) {
        super(context, attrs);
        DisplayMetrics dm = new DisplayMetrics();
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        wm.getDefaultDisplay().getMetrics(dm);
        mScreenWidth = dm.widthPixels;
        mScreenHeight = dm.heightPixels;
        mWidth = getMeasuredWidth();
        mHeight = getMeasuredHeight();
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

    }

    private int lastX;
    private int lastY;

    /**
     * @Desc: 通过重写 onTouch方法来完成 view的移动
     * @Return:
     * @author : topone Create On 2016/10/10 0010  下午 2:56
     */
    @Override
    public boolean onTouchEvent(MotionEvent event) {

        int x = (int) event.getRawX();
        int y = (int) event.getRawY();
        switch (event.getAction()) {

            case MotionEvent.ACTION_DOWN: {

                break;
            }

            case MotionEvent.ACTION_MOVE: {


                /**
                 * LayoutParam的类型和父容器相关
                 */
                RelativeLayout.MarginLayoutParams layoutParams = (RelativeLayout.MarginLayoutParams) getLayoutParams();
                int left = layoutParams.leftMargin + x - lastX;//算出每次新的位置
                int top = layoutParams.topMargin + y - lastY;

                layoutParams.leftMargin = Math.min(mScreenWidth - mWidth, Math.max(0, left));
                layoutParams.topMargin = Math.min(mScreenHeight - mHeight, Math.max(0, top));
                setLayoutParams(layoutParams);
                requestLayout();
                break;
            }

            case MotionEvent.ACTION_UP: {

                break;
            }

        }
        lastX = x;
        lastY = y;
        return true;
    }
}
