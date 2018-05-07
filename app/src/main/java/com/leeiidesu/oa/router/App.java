package com.leeiidesu.oa.router;

import android.app.Application;

import com.alibaba.android.arouter.launcher.ARouter;

/**
 * Created by liyi on 2018/2/26.
 */

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        ARouter.openDebug();
        ARouter.init(this);
    }
}
