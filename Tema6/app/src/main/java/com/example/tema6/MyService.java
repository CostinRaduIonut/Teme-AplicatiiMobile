package com.example.tema6;

import android.app.Service;
import android.content.Intent;
import android.media.AudioManager;
import android.media.ToneGenerator;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

public class MyService extends Service {
    private static final String TAG = "MyService";
    private volatile boolean stopThread = false;

    public MyService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        String Per = intent.getStringExtra("perioada");
        String Vol = intent.getStringExtra("volum");
        String Dur = intent.getStringExtra("durata");
        int P = Integer.parseInt(Per);
        int V = Integer.parseInt(Vol);
        int D = Integer.parseInt(Dur);

        stopThread = false;
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                while (!stopThread) {
                    final ToneGenerator tg = new ToneGenerator(AudioManager.STREAM_MUSIC, V);
                    tg.startTone(ToneGenerator.TONE_CDMA_PIP, D);
//pentru a preveni unele erori carea apar la ToneGenerator
                    tg.release();
                    try {
                        Thread.sleep(P);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };

        Thread service_task = new Thread(runnable);
        service_task.start();
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        Toast.makeText(getApplicationContext(), "Sunet oprit", Toast.LENGTH_LONG).show();
        Log.i(TAG, "onDestroy");
        super.onDestroy();
        stopThread = true;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i(TAG, "onCreate");
        Toast.makeText(getApplicationContext(), "onCreate", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onTaskRemoved(Intent rootIntent) {
        Log.i(TAG, "onRemove");
        stopSelf();

        super.onTaskRemoved(rootIntent);
    }
}