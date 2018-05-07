package com.leeiidesu.lib;

import com.leeiidesu.lib.router.anno.Path;
import com.leeiidesu.lib.router.anno.With;

/**
 * Created by liyi on 2018/2/26.
 */

public interface RouterService {
    @Path("/oa/host")
    void routerToMain(int value, @With("key2") boolean value2, @With("key3") String value3);
}
