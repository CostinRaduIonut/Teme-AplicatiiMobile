package com.example.tema5;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    int nrDeAles, nrMeu, nrLui = 0;
    private static final Handler mainHandler = new Handler();
    private volatile boolean stopThread = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onStart(View view) {
        Random random = new Random();
        TextView nrMax = findViewById(R.id.maxNr);
        if (nrMax.getText().length() <= 0) {
            int ceva = random.nextInt(11);
            nrMax.setText("" + ceva);
        }

        stopThread = false;
        Fire fire = new Fire();
        new Thread(fire).start();
    }


    public void onVerifica(View view) {
        comparanrMeu(nrDeAles);
    }

    public void comparanrMeu(int nrDeAles) {
        TextView nrIntrodus = findViewById(R.id.nrAles);
        if (nrIntrodus.getText().length() <= 0) {
            nrIntrodus.setText("0");
        }
        nrMeu = Integer.parseInt(nrIntrodus.getText().toString());

        TextView castigator = findViewById(R.id.castigator);
        if (nrMeu == nrDeAles) {
            castigator.setText("User");
            stopThread = true;
        } else {
            castigator.setText("Nimeni inca");
        }
    }

    public void comparaNrLui(int nrLui, int nrDeAles) {
        TextView castigator = findViewById(R.id.castigator);
        if (nrLui == nrDeAles) {
            castigator.setText("Fir");
            stopThread = true;
        } else {
            castigator.setText("Nimeni inca");
        }
    }

    public int randomPls() {
        TextView nrMax = findViewById(R.id.maxNr);
        Random random = new Random();
        int ran, max;
        max = Integer.parseInt(nrMax.getText().toString());
        ran = random.nextInt(max);

        return ran;
    }

    class Fire implements Runnable {

        Fire() {
        }

        @Override
        public void run() {
            nrDeAles = randomPls();
            System.out.println("nr de ghicit " + nrDeAles);
            while (!stopThread) {
                mainHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        nrLui = randomPls();
                        TextView textView = findViewById(R.id.nr);
                        textView.setText("" + nrLui);
                        System.out.println("nr lui " + nrLui);
                        comparaNrLui(nrLui, nrDeAles);
                    }
                });
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}


