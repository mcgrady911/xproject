package com.mcgrady.xproject.app;

import android.app.Activity;

import java.util.Stack;

/**
 * @author: mcgrady <mogui@weyee.com>
 * @date: 2018/2/5
 * @des: activity栈表
 */

public class ActivityStack {

    private static Stack<Activity> mActivityStack = new Stack<>();
    private static ActivityStack instance = null;

    public static synchronized ActivityStack getInstance() {
        if (instance == null) {
            instance = new ActivityStack();
        }
        return instance;
    }

    /**
     * 弹出指定activity并销毁
     * @param activity
     */
    public void popActivity(Activity activity) {
        if (activity != null) {
            activity.finish();
            mActivityStack.remove(activity);
        }
    }

    /**
     * 弹出当前activity并销毁
     */
    public void popActivity(){
        if(mActivityStack.size() != 0) {
            Activity ac = mActivityStack.pop();
            ac.finish();
            mActivityStack.remove(ac);
        }
    }

    /**
     * 将指定Activity推入栈中
     * @param activity
     */
    public void pushActivity(Activity activity) {
        mActivityStack.add(activity);
    }

    /**
     * 退出栈中所有Activity
     */
    public void clearAllActivity() {
        while (!mActivityStack.isEmpty()) {
            Activity activity = mActivityStack.pop();
            popActivity(activity);
        }
    }
}