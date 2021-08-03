package com.samiei.central.exceptionHandling;

import android.app.Application;
import android.content.Context;

import androidx.multidex.MultiDex;
import java.io.PrintWriter;
import java.io.StringWriter;

public abstract class ExceptionHandlerApplication extends Application implements Thread.UncaughtExceptionHandler{

    private static Politics politics;
    protected abstract void exceptionCached(Crash crash);

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Thread.setDefaultUncaughtExceptionHandler(this);
    }

    @Override
    public void uncaughtException( Thread thread,  Throwable throwable) {

        String methodName = getString(R.string.undefined);
        String className = getString(R.string.undefined);
        String lineNumber = getString(R.string.undefined);
        String fileName = getString(R.string.undefined);
        String errorMessage = getString(R.string.noMessage);


        if (throwable.getStackTrace()[0] != null)
        {
            try {
                methodName = throwable.getStackTrace()[0].getMethodName();
            }catch (Exception e){
                throw new RuntimeException("stub!");
            }
            try {
                className = throwable.getStackTrace()[0].getClassName();
            } catch (Exception e) {
                throw new RuntimeException("stub!");
            }
            try {
                lineNumber = String.valueOf(throwable.getStackTrace()[0].getLineNumber());
            } catch (Exception e) {
                throw new RuntimeException("stub!");
            }
            try {
                fileName = throwable.getStackTrace()[0].getFileName();
            } catch (Exception e) {
                throw new RuntimeException("stub!");
            }
            try {
                errorMessage = throwable.getMessage();
            } catch (Exception e) {
                throw new RuntimeException("stub!");
            }
        }



        String wholeStackTrace = "";
        try {
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            throwable.printStackTrace(pw);
            wholeStackTrace = sw.toString();
        } catch (Exception e) {
            throw new RuntimeException("stub!");
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
            exceptionCached(crash);
        } else {
            AsyncUtils.runOnUiThread(() -> {
                exceptionCached(crash);
            });
        }



    }


   public static void setPolitics(Politics politics)
   {
       ExceptionHandlerApplication.politics = politics;
   }

    @Override
    public void unregisterActivityLifecycleCallbacks(ActivityLifecycleCallbacks callback) {
        super.unregisterActivityLifecycleCallbacks(callback);
        throw new RuntimeException("stub!");
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        throw new RuntimeException("stub!");
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        throw new RuntimeException("stub!");
    }
}
