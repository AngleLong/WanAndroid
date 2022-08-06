package com.angle.lib_common.utils;

import android.annotation.SuppressLint;
import android.app.Application;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ApplicationUtils {

    private static final ApplicationUtils APPLICATION_UTILS = new ApplicationUtils();

    private ApplicationUtils() {

    }

    public static ApplicationUtils getInstance() {
        return APPLICATION_UTILS;
    }

    private Application currentApplication;

    /**
     * 获取全局的application
     *
     * @return 返回application
     */
    @SuppressLint("PrivateApi")
    public Application getNewApplication() {
        try {
            if (currentApplication == null) {
                currentApplication = (Application) Class.forName("android.app.ActivityThread").getMethod("currentApplication").invoke(null, (Object[]) null);
            }
            return currentApplication;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
