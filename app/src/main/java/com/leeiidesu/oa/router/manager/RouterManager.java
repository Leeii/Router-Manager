package com.leeiidesu.oa.router.manager;

import com.leeiidesu.lib.router.DRouter;

/**
 * Created by iilee on 2018/5/7.
 */

public class RouterManager {
    private RouterService routerService;

    private RouterManager() {
        routerService = new DRouter().create(RouterService.class);
    }


    public static RouterService getService() {
        return Holder.INSTANCE.routerService;
    }

    private static class Holder {
        static RouterManager INSTANCE = new RouterManager();
    }
}
