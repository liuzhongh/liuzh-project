package com.shangkang.msm.activity;

import java.util.LinkedList;
import java.util.List;

import android.app.Activity;
import android.app.Application;
import com.shangkang.msm.manager.XmppConnectionManager;

/**
 *
 * 完整的退出应用.
 *
 * @author Liuzh
 */
public class EimApplication extends Application {
    private List<Activity> activityList = new LinkedList<Activity>();

    // 添加Activity到容器中
    public void addActivity(Activity activity) {
        activityList.add(activity);
    }

    // 遍历所有Activity并finish
    public void exit() {
        XmppConnectionManager.getInstance().disconnect();
        for (Activity activity : activityList) {
            activity.finish();
        }
    }
}