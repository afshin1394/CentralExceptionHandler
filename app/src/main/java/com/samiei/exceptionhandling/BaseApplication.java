package com.samiei.exceptionhandling;

import android.app.Activity;
import android.util.Log;

import com.samiei.central.exceptionHandling.ExceptionHandlerApplication;
import com.samiei.central.exceptionHandling.Crash;

import java.util.List;


public class BaseApplication extends ExceptionHandlerApplication {

   public static final String TAG = BaseApplication.class.getSimpleName();
    @Override
    public void onCreate() {
        Log.i(TAG, "onCreate: ");

        super.onCreate();

    }

    @Override
    protected void exceptionCached(Crash crash, List<Activity> activities) {
        Log.i(TAG, "exceptionCached-crash---> " + crash);
    }

    @Override
    protected void onResume(Activity activity) {
        Log.i(TAG, "onResume: activity--->"+activity);

    }

    @Override
    protected void onPause(Activity activity) {
        Log.i(TAG, "onPause: activity--->"+activity);
    }

    @Override
    protected void onStop(Activity activity) {
        Log.i(TAG, "onStop: activity--->"+activity);

    }

    @Override
    protected void onSaveInstanceState(Activity activity) {
        Log.i(TAG, "onSaveInstanceState: activity--->"+activity);
    }

    @Override
    protected void onTerminate(Activity activity) {
        Log.i(TAG, "onTerminate: activity--->"+activity);
    }

    @Override
    protected void onStart(Activity activity) {
        Log.i(TAG, "onStart: activity--->"+activity);

    }


}
