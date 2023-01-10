package com.example.myapplicationserver;

import androidx.appcompat.app.AppCompatActivity;

import android.net.InetAddresses;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.NetworkInterface;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Enumeration;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivityServer";
    private ServerThread singleServerThread;
    private TextView tvsIP, tvsPORT, tvsStatus;
    private String serverIp = "";
    private int serverPORT = 1234;
    TextView detrimis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.i(TAG, "onCreate: ");
        tvsIP = findViewById(R.id.Sip);
        tvsPORT = findViewById(R.id.Sport);
        tvsStatus = findViewById(R.id.status);

        tvsIP.setText("Server IP: " + serverIp);
        tvsPORT.setText("Server PORT: " + String.valueOf(serverPORT));
    }

    public void onClickStartServer(View view) {
        Log.i(TAG, "onClickStartServer: ");
        singleServerThread = new ServerThread();
        singleServerThread.startServer();
    }

    public void onClickStopServer(View view) {
        Log.i(TAG, "onClickStopServer: ");
        singleServerThread.stopServer();
    }

    class ServerThread extends Thread implements Runnable {
        private boolean serverRunning;
        private ServerSocket serverSocket;
        private int count = 0;

        public void startServer() {
            Log.i(TAG, "startServer: ");
            serverRunning = true;
            start();
        }

        @Override
        public void run() {
            Log.i(TAG, "run: class SharedThread");
            try {
                serverSocket = new ServerSocket(serverPORT);
            } catch (IOException e) {
                e.printStackTrace();
            }
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    tvsStatus.setText("Waiting for clients");
                }
            });
            while (serverRunning) {
                Log.i(TAG, "run: inside While ");
                Socket socket = null;
                try {
                    socket = serverSocket.accept();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                count++;

//                runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//                        tvsStatus.setText("Connected to: " + tvsIP + ":" + tvsPORT);
//                    }
//                });

                PrintWriter outputServer = null;
                try {
                    outputServer = new PrintWriter(socket.getOutputStream());
                } catch (IOException e) {
                    e.printStackTrace();
                }
                TextView textView= findViewById(R.id.editTextTextPersonName5);
                outputServer.write(textView.getText().toString());
                outputServer.flush();
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        public void stopServer() {
            Log.i(TAG, "stopServer: clasa Shared Thread");
            serverRunning = false;
            new Thread(new Runnable() {
                @Override
                public void run() {
                    if (serverSocket != null) {
                        try {
                            serverSocket.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                tvsStatus.setText("Connection closed");
                            }
                        });
                    }
                }
            }).start();
        }
    }
}