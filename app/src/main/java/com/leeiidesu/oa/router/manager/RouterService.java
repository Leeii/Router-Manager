package com.leeiidesu.oa.router.manager;

import com.leeiidesu.lib.router.anno.Path;
import com.leeiidesu.lib.router.anno.With;

/**
 * 路由管理服务
 * Created by liyi on 2018/2/26.
 */

public interface RouterService {
    interface Host {
        String PATH_MAIN2 = "/oa/main3";
    }

    @Path(Host.PATH_MAIN2)
    void routerToMain3(@With("name")String name);
}
