package ro.pub.cs.systems.eim.practicaltest01var05;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import java.util.Date;

public class MyThread extends Thread {

    private Context context;
    private boolean isRunning = true;
    private int scor;

    public MyThread(Context context, int scor) {
        this.context = context;
        this.scor = scor;
    }

    @Override
    public void run() {
        Log.d("[ProcessingThread]", "Thread has started!");

        sleep();
        sendMessage();
    }

    private void sendMessage() {
        String message = "Victory: " + new Date(System.currentTimeMillis()) + ", scor = " + scor;
        Intent intent = new Intent();
        intent.putExtra("message", message);
        intent.setAction("ACTION");

        Log.d("ServiceThread ---- ", message);

        context.sendBroadcast(intent);
    }

    private void sleep() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException interruptedException) {
            interruptedException.printStackTrace();
        }
    }

    public void stopThread() {
        isRunning = false;
    }

}