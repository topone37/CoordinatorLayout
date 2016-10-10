package com.tp.demo;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowManager;

/**
 * Created by topone37 on 2016/10/10 0010.
 * <p/>
 * Email: 1273305693@qq.com
 * CSDN:  http://blog.csdn.net/topone37
 * <p/>
 * DESC:自定义我们动作行为
 * (需要理解一个概念 ,动作的协调是有主动和被动的,被动方会根据自己依赖的主动view的各项改变做出相应的动作)
 */
public class CustomBehavior extends CoordinatorLayout.Behavior {
    private int mScreenWith;

    /**
     * @DESC: 这个带参数的构造函数必须重写(必须重写!!!)
     * CoordinatorLayout中使用反射通过这个携带参数的构造函数去获取这个Behavior  *
     * @author : topone Create On 2016/10/10   下午 2:16
     */
    public CustomBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);

        /*获取屏幕宽度*/
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics dm = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(dm);
        mScreenWith = dm.widthPixels;

    }

    /**
     * @return :主要是对主动方做一个筛选,例如主动方是 TextView (return dependency instanceof TextView)
     * @DESC: 第一个参数：CoordinatorLayout 第二个参数：被动方  第三个参数 主动方
     * @author : topone Create On 2016/10/10 0010  下午 2:18
     */
    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, View child, View dependency) {
        //如果dependency是MoveView的实例，说明它就是我们所需要的Dependency
        // return dependency instanceof MoveView;
        /*如果类型不能唯一区分,也可以通过ID来进行区分*/
        return dependency.getId() == R.id.view_1;
    }

    /**
     * @Desc: 主动方(被依赖方发生变化时, 会回调该函数, 在这里可以完成我们自定义的动作, 主要指的是对第二个参数的一些调整)
     * @Return:
     * @author : topone Create On 2016/10/10 0010  下午 2:25
     */
    @Override
    public boolean onDependentViewChanged(CoordinatorLayout parent, View child, View dependency) {
        CoordinatorLayout.LayoutParams params = (CoordinatorLayout.LayoutParams) child.getLayoutParams();

        params.leftMargin = mScreenWith - dependency.getLeft() - child.getWidth();
        params.topMargin = dependency.getTop();

        child.setLayoutParams(params);
        return true;
    }


}
