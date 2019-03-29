package ro.pub.cs.systems.eim.practicaltest01var05;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class PracticalTest01Var05Service extends Service {


    private MyThread thread = null;

    public PracticalTest01Var05Service() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        String scor = intent.getStringExtra("finalScor");
        Log.d("============", "onStartCommand: " + scor);

        if (Integer.parseInt(scor) > 110) {
            thread = new MyThread(this, Integer.parseInt(scor));
            thread.start();
        }

        return Service.START_REDELIVER_INTENT;
    }

    @Override
    public void onDestroy() {
        thread.stopThread();
    }
}
