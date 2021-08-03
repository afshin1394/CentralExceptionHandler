package com.samiei.exceptionhandling;

import android.app.Activity;
import android.util.Log;

import com.samiei.central.exceptionHandling.ExceptionHandlerApplication;
import com.samiei.central.exceptionHandling.Crash;

import java.util.List;


public class BaseApplication extends ExceptionHandlerApplication {


    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    protected void exceptionCached(Crash crash, List<Activity> activities) {
//        /**set politics**/
//        CentralExceptionHandler.setPolitics(Politics.EXIT);


        /**available details**/
        String crashClass = crash.getCrashClass();
        String crashMethod = crash.getCrashMethod();
        String crashLine = crash.getCrashLine();
        String crashStacktrace = crash.getCrashStackTrace();
        String crashFile = crash.getCrashFile();
        String errorMessage = crash.getErrorMessage();


        for (Activity activity : activities) {
            Log.i("exceptionCached", "exceptionCached: "+activity.getLocalClassName());
            activity.finish();
        }

    }



}
