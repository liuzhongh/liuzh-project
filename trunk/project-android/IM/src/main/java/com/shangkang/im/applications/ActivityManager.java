package com.shangkang.im.applications;

import android.app.Activity;
import android.app.Application;

/**
 * Created by liuzh on 13-10-26.
 */
public class ActivityManager extends Application {

    private Activity activity;

    public Activity getActivity()
    {
        return activity;
    }

    public void addActivity(Activity activity)
    {
        this.activity = activity;
    }

    public void exit()
    {
        if(activity != null && !activity.isFinishing())
            activity.finish();
    }

}
