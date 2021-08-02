package com.samiei.exceptionhandling;

import android.util.Log;

import com.samiei.central.exceptionHandling.CentralExceptionHandler;
import com.samiei.central.exceptionHandling.Crash;


public class BaseApplication extends CentralExceptionHandler {


    @Override
    public void onCreate() {
        super.onCreate();

    }

    @Override
    protected void exceptionCached(Crash crash) {
//        /**set politics**/
//        CentralExceptionHandler.setPolitics(Politics.EXIT);


        /**available details**/
        String crashClass = crash.getCrashClass();
        String crashMethod = crash.getCrashMethod();
        String crashLine = crash.getCrashLine();
        String crashStacktrace = crash.getCrashStackTrace();
        String crashFile = crash.getCrashFile();
        String errorMessage = crash.getErrorMessage();

        Log.i("crash", "exceptionCached: "+crash);

    }



}
