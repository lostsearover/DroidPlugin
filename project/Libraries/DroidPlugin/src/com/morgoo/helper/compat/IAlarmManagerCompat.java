package com.morgoo.helper.compat;

import android.os.IBinder;

import com.morgoo.droidplugin.reflect.MethodUtils;

import java.lang.reflect.InvocationTargetException;

public class IAlarmManagerCompat {

    private static Class sClass;

    public static Class Class() throws ClassNotFoundException {
        if (sClass == null) {
            sClass = Class.forName("android.app.IAlarmManager");
        }
        return sClass;
    }

    public static Object asInterface( IBinder binder) throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        Class clazz = Class.forName("android.app.IAlarmManager$Stub");
        return MethodUtils.invokeStaticMethod(clazz, "asInterface", binder);
    }
}
