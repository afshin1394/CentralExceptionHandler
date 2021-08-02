package com.samiei.central.exceptionHandling;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import androidx.multidex.MultiDex;
import androidx.multidex.MultiDexApplication;

import java.io.PrintWriter;
import java.io.StringWriter;

public abstract class CentralExceptionHandler extends Application implements Thread.UncaughtExceptionHandler{

    private static Politics politics;
    protected abstract void exceptionCached(Crash crash);

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

        if (throwable.getStackTrace()[0]!=null) {
             methodName = throwable.getCause().getStackTrace()[0].getMethodName();
             className = throwable.getCause().getStackTrace()[0].getClassName();
             lineNumber = String.valueOf(throwable.getCause().getStackTrace()[0].getLineNumber());
             fileName = throwable.getCause().getStackTrace()[0].getFileName();
             errorMessage = throwable.getMessage();

        }

        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        throwable.printStackTrace(pw);
        String wholeStackTrace = sw.toString();

        Crash crash = new Crash.Builder()
        .setCrashClass(className)
                .setCrashFile(fileName)
                .setCrashMethod(methodName)
                .setCrashLine(lineNumber)
                .setStackTrace(wholeStackTrace)
                .setErrorMessage(errorMessage)
                .build();

            if (AsyncUtils.isUIThread(thread))
            {
                exceptionCached(crash);
                switch (politics)
                {
                    case EXIT:
                        System.exit(0);
                        break;
                    default:
                        break;
                }
            }
            else
            {
                AsyncUtils.runOnUiThread(new AsyncUtils.AsyncEvents() {
                    @Override
                    public void uiThreadIsReady() {
                        exceptionCached(crash);
                        switch (politics)
                        {
                            case EXIT:
                                System.exit(0);
                                break;
                            default:
                                break;
                        }
                    }
                });
            }
    }


   public static void setPolitics(Politics politics)
   {
       CentralExceptionHandler.politics = politics;
   }


    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }
}
