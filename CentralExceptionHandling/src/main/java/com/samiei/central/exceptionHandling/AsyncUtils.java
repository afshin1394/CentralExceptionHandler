package com.samiei.central.exceptionHandling;

import android.os.Handler;
import android.os.Looper;

public class AsyncUtils {
    /**getCurrent Thread**/
    public static boolean isUIThread(){
        return Looper.getMainLooper().getThread() == Thread.currentThread();
    }

    /**current Thread as parameter**/
    public static boolean isUIThread(Thread thread){
        return thread == Thread.currentThread();
    }

    /**run on uiThread from any thread**/
    public static void runOnUiThread(AsyncEvents asyncEvents) {
        Handler mainHandler = new Handler(Looper.getMainLooper());
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                asyncEvents.uiThreadIsReady();
            }
        };
        mainHandler.post(runnable);
    }

    public interface AsyncEvents {
        void uiThreadIsReady();
    }
}
