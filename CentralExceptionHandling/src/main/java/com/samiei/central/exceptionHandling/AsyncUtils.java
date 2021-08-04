package com.samiei.central.exceptionHandling;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;

public class AsyncUtils {

    public static  Handler mHandler;

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

    /**sendThreadData from any thread**/
    public static void sendThreadData(Thread thread,Object object)
    {
        Message message = new Message();
        if(thread.isAlive()) {
            message.obj = object;
            if (mHandler!=null){
                mHandler.sendMessage(message);
            }
        }
    }

    /**receiveDataInNewThread**/
    public static void receiveDataInNewThread(IThread iThread){

         new Thread(new Runnable() {
            @Override
            public void run() {
                Looper.prepare();
                mHandler = new Handler(new Handler.Callback() {
                    @Override
                    public boolean handleMessage(Message message) {
                        mHandler.obtainMessage();
                        Log.i("message", "handleMessage: "+message.obj);
                        iThread.receivedData(message.obj);
                        return false;
                    }
                });
                Looper.loop();
            }
        }).start();
    }

    /**ThreadActions**/
    public interface IThread{
        void receivedData(Object object);
    }
}




