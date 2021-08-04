package com.samiei.central.exceptionHandling;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;

import androidx.multidex.MultiDex;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

public abstract class ExceptionHandlerApplication extends Application implements Thread.UncaughtExceptionHandler {

    private static Politics politics;
    private List<Activity> activities;
    public static String defaultMethodName = "";
    public static String defaultClassName = "";
    public static String defaultLineNumber = "";
    public static String defaultFileName = "";
    public static String defaultErrorMessage = "";
    public static String defaultErrorStackTrace = "";





    protected abstract void exceptionCached(Crash crash, List<Activity> activities);

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Thread.setDefaultUncaughtExceptionHandler(this);
        registerActivityListener();
        activities = new ArrayList<>();
    }


    @Override
    public void uncaughtException(Thread thread, Throwable throwable) {
        String methodName = "";
        String className = "";
        String lineNumber = "";
        String fileName = "";
        String errorMessage = "";


        if (throwable.getStackTrace()[0] != null) {
            try {
                methodName = throwable.getStackTrace()[0].getMethodName();
            } catch (Exception e) {
                methodName = defaultMethodName;
            }
            try {
                className = throwable.getStackTrace()[0].getClassName();
            } catch (Exception e) {
                methodName = defaultClassName;
            }
            try {
                lineNumber = String.valueOf(throwable.getStackTrace()[0].getLineNumber());
            } catch (Exception e) {
                methodName = defaultLineNumber;
            }
            try {
                fileName = throwable.getStackTrace()[0].getFileName();
            } catch (Exception e) {
                methodName = defaultFileName;
            }
            try {
                errorMessage = throwable.getMessage();
            } catch (Exception e) {
                methodName = defaultErrorMessage;
            }
        }


        String wholeStackTrace = "";
        try {
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            throwable.printStackTrace(pw);
            wholeStackTrace = sw.toString();
        } catch (Exception e) {
            methodName = defaultErrorStackTrace;
        }


        Crash crash = new Crash.Builder()
                .setCrashClass(className)
                .setCrashFile(fileName)
                .setCrashMethod(methodName)
                .setCrashLine(lineNumber)
                .setStackTrace(wholeStackTrace)
                .setErrorMessage(errorMessage)
                .build();


        if (AsyncUtils.isUIThread(thread)) {
            exceptionCached(crash, activities);
        } else {
            AsyncUtils.runOnUiThread(() -> {
                exceptionCached(crash, activities);
            });
        }


    }




    @Override
    public void unregisterActivityLifecycleCallbacks(ActivityLifecycleCallbacks callback) {
        super.unregisterActivityLifecycleCallbacks(callback);
    }



    @Override
    public void onTerminate() {
        super.onTerminate();
    }



    @Override
    public void onLowMemory() {
        super.onLowMemory();
    }



    private void registerActivityListener() {

        registerActivityLifecycleCallbacks(new ActivityLifecycleCallbacks() {
            @Override
            public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
                /**
                 * Monitor to Activity Create Event Add this Activity to list
                 */
                activities.add(activity);

            }

            @Override
            public void onActivityStarted(Activity activity) {

            }

            @Override
            public void onActivityResumed(Activity activity) {

            }

            @Override
            public void onActivityPaused(Activity activity) {

            }

            @Override
            public void onActivityStopped(Activity activity) {

            }

            @Override
            public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

            }

            @Override
            public void onActivityDestroyed(Activity activity) {
                if (null == activities && activities.isEmpty()) {
                    return;
                }
                if (activities.contains(activity)) {
                    /**
                     * Monitor the Activity destruction event Remove the Activity from list
                     */
                    activities.remove(activity);
                }
            }
        });
    }







    public static Politics getPolitics() {
        return politics;
    }



}
