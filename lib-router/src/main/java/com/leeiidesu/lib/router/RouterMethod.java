package com.leeiidesu.lib.router;

import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.android.arouter.launcher.ARouter;

import com.leeiidesu.lib.core.util.Check;
import com.leeiidesu.lib.router.anno.Activity;
import com.leeiidesu.lib.router.anno.Flags;
import com.leeiidesu.lib.router.anno.Path;
import com.leeiidesu.lib.router.anno.RequestCode;
import com.leeiidesu.lib.router.anno.With;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * 方法解析缓存
 * Created by liyi on 2018/2/24.
 */

final class RouterMethod {

    private final ParameterHandler.NavigationHandler navigationHandler;
    private final List<ParameterHandler<?, ?>> parameterHandlers;
    private final String path;

    public RouterMethod(Builder builder) {
        this.navigationHandler = builder.navigationHandler;
        this.parameterHandlers = builder.parameterHandlers;
        this.path = builder.path;
    }

    Object routerTo(Object[] args) {
        Postcard build =
                ARouter.getInstance().build(path);

        for (ParameterHandler ph : parameterHandlers) {
            build = (Postcard) ph.apply(build, args);
        }

        return navigationHandler.apply(build, args);
    }

    static class Builder {
        private final Method method;
        private final Annotation[] annotations;
        private final Annotation[][] parameterAnnotations;
        private final Type[] parameterTypes;

        String path;

        ParameterHandler.NavigationHandler navigationHandler = new ParameterHandler.NavigationHandler();

        private List<ParameterHandler<?, ?>> parameterHandlers = new ArrayList<>();
        private Object[] args;


        Builder(Method method, Object... args) {
            this.args = args;
            this.method = method;
            annotations = method.getAnnotations();
            parameterTypes = method.getGenericParameterTypes();
            parameterAnnotations = method.getParameterAnnotations();
        }

        RouterMethod build() {
            parseMethodAnnotations();
            if (Check.isEmpty(path)) {
                throw new RouterServiceException("@Path method annotation is required");
            }

            parseParameters();


            return new RouterMethod(this);
        }

        private void parseParameters() {
            for (int i = 0; i < parameterAnnotations.length; i++) {
                Annotation[] annotations = parameterAnnotations[i];
                if (annotations.length > 1) {
                    throw new RouterServiceException("An annotation must correspond to only one parameter");
                }
                if (annotations.length == 1) {
                    Annotation annotation = annotations[0];

                    if (annotation instanceof Activity) {
                        navigationHandler.setParameterPosition(i);
                    } else if (annotation instanceof RequestCode) {
                        navigationHandler.setRequestCodeParameterPosition(i);
                    } else if (annotation instanceof Flags) {
                        ParameterHandler.FlagsHandler flagsHandler = new ParameterHandler.FlagsHandler();
                        flagsHandler.setParameterPosition(i);
                        parameterHandlers.add(flagsHandler);
                    } else if (annotation instanceof With) {
                        With with = (With) annotation;
                        ParameterHandler.WithHandler withHandler = new ParameterHandler.WithHandler(with.value());
                        withHandler.setParameterPosition(i);
                        parameterHandlers.add(withHandler);
                    }
                }
            }
        }

        private void parseMethodAnnotations() {
            for (Annotation annotation : annotations) {
                if (annotation instanceof Path) {
                    Path path = (Path) annotation;
                    this.path = path.value();
                    if (Check.isEmpty(this.path)) {
                        throw new RouterServiceException("Path must not be null.");
                    }
                    return;
                }
            }
        }


    }
}
