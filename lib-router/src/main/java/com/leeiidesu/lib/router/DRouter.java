package com.leeiidesu.lib.router;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 路由管理
 * Created by liyi on 2018/2/24.
 */

public final class DRouter {
    private final Map<Method, RouterMethod> routerMethodCache = new ConcurrentHashMap<>();

    @SuppressWarnings("unchecked")
    public <T> T create(Class<T> clazz) {
        Utils.validateServiceInterface(clazz);

        return (T) Proxy.newProxyInstance(
                clazz.getClassLoader(),
                new Class<?>[]{clazz},
                new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        Class<?> declaringClass = method.getDeclaringClass();
                        if (declaringClass == Object.class) {
                            return method.invoke(this, args);
                        }


                        RouterMethod routerMethod = loadRouterMethod(method, args);
                        return routerMethod.routerTo(args);
                    }
                }
        );
    }

    private RouterMethod loadRouterMethod(Method method, Object[] args) {
        RouterMethod routerMethod = routerMethodCache.get(method);
        if (routerMethod != null) {
            return routerMethod;
        }

        synchronized (routerMethodCache) {
            routerMethod = routerMethodCache.get(method);
            if (routerMethod == null) {
                routerMethod = new RouterMethod.Builder(method, args).build();
                routerMethodCache.put(method, routerMethod);
            }
        }
        return routerMethod;
    }
}
