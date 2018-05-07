package com.leeiidesu.lib;


import org.junit.Test;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        Class<RouterService> routerServiceClass = RouterService.class;
        Method routerToMain = routerServiceClass.getMethod("routerToMain", int.class, boolean.class, String.class);

        Annotation[] annotations = routerToMain.getAnnotations();

        Type[] parameterTypes = routerToMain.getGenericParameterTypes();
        Annotation[][] parameterAnnotations = routerToMain.getParameterAnnotations();
        System.out.println("sss");
    }


    @Test
    public void testCalendar() {
        long date1 = 1420010236000L;
        long date2 = 1420096636000L;

        Calendar calendar1 = Calendar.getInstance();
        calendar1.setTimeInMillis(date1);
        Calendar calendar2 = Calendar.getInstance();
        calendar2.setTimeInMillis(date2);

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");

        System.out.println(dateFormat.format(calendar1.getTime()) + " weekOfYear == " + calendar1.get(Calendar.WEEK_OF_YEAR));
        System.out.println(dateFormat.format(calendar2.getTime()) + "  weekOfYear == " + calendar2.get(Calendar.WEEK_OF_YEAR));


    }

}