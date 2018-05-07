package com.leeiidesu.lib.router;

import android.app.Activity;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;

import com.alibaba.android.arouter.facade.Postcard;
import com.leeiidesu.lib.core.util.Check;


import java.util.ArrayList;

/**
 * 参数解析缓存
 * Created by liyi on 2018/2/24.
 */
abstract class ParameterHandler<P, R> {
    private int parameterPosition = -1;

    abstract R apply(P node, Object[] args);

    public int getParameterPosition() {
        return parameterPosition;
    }

    public void setParameterPosition(int parameterPosition) {
        this.parameterPosition = parameterPosition;
    }

    static class NavigationHandler extends ParameterHandler<Postcard, Object> {
        private int requestCodeParameterPosition = -1;

        public NavigationHandler() {
        }

        public int getRequestCodeParameterPosition() {
            return requestCodeParameterPosition;
        }

        public void setRequestCodeParameterPosition(int requestCodeParameterPosition) {
            this.requestCodeParameterPosition = requestCodeParameterPosition;
        }

        @Override
        Object apply(Postcard node, Object[] args) {
            if (getParameterPosition() == -1) {
                return node.navigation();
            }
            if (args[getParameterPosition()] instanceof Activity) {
                Activity activity = (Activity) args[getParameterPosition()];
                if (activity != null) {
                    if (requestCodeParameterPosition == -1) {
                        node.navigation(activity, 0);
                    } else {
                        Object arg = args[requestCodeParameterPosition];
                        if (arg.getClass() == int.class || arg.getClass() == Integer.class) {
                            node.navigation(activity, requestCodeParameterPosition == -1 ? 0 : (int) arg);
                        } else {
                            throw new RouterServiceException("parameter error.");
                        }

                    }
                }
            }
            return null;
        }
    }

    static class WithHandler extends ParameterHandler<Postcard, Postcard> {
        private String key;

        WithHandler(@NonNull String key) {
            this.key = key;
        }

        @Override
        Postcard apply(Postcard node, Object[] args) {
            if (getParameterPosition() == -1) {
                return node;
            }
            Object value = args[getParameterPosition()];


            Class<?> aClass = boxIfPrimitive(value.getClass());
            if (aClass == String.class) {
                return node.withString(key, (String) value);
            }
            if (aClass == Boolean.class) {
                return node.withBoolean(key, (Boolean) value);
            }
            if (aClass == Integer.class) {
                return node.withInt(key, (Integer) value);
            }
            if (aClass == Long.class) {
                return node.withLong(key, (Long) value);
            }
            if (aClass == Float.class) {
                return node.withFloat(key, (Float) value);
            }
            if (aClass == Double.class) {
                return node.withDouble(key, (Double) value);
            }
            if (aClass == Byte.class) {
                return node.withByte(key, (Byte) value);
            }
            if (aClass == Character.class) {
                return node.withChar(key, (Character) value);
            }
            if (aClass == Short.class) {
                return node.withShort(key, (Short) value);
            }
            if (aClass == byte[].class || aClass == Byte[].class) {
                return node.withByteArray(key, (byte[]) value);
            }

            if (aClass == Bundle.class) {
                if (Check.isEmpty(key)) {
                    return node.with((Bundle) value);
                } else {
                    return node.withBundle(key, (Bundle) value);
                }
            }

            if (aClass == Parcelable.class) {
                return node.withParcelable(key, (Parcelable) value);
            }

            if (aClass == ArrayList.class) {
                try {
                    return node.withStringArrayList(key, (ArrayList<String>) value);
                } catch (Exception e) {
                    //nothing to do
                }
            }


            return node.withObject(key, value);
        }

        static Class<?> boxIfPrimitive(Class<?> type) {
            if (boolean.class == type) return Boolean.class;
            if (byte.class == type) return Byte.class;
            if (char.class == type) return Character.class;
            if (double.class == type) return Double.class;
            if (float.class == type) return Float.class;
            if (int.class == type) return Integer.class;
            if (long.class == type) return Long.class;
            if (short.class == type) return Short.class;
            return type;
        }
    }

    static class FlagsHandler extends ParameterHandler<Postcard, Postcard> {

        FlagsHandler() {

        }

        @Override
        Postcard apply(Postcard node, Object[] args) {
            if (getParameterPosition() == -1) {
                return node;
            }
            Object value = args[getParameterPosition()];

            if (value.getClass() == Integer.class || int.class == value.getClass()) {
                return node.withFlags((Integer) value);
            }
            throw new RouterServiceException("The value type of flag must be int.");
        }
    }
}
