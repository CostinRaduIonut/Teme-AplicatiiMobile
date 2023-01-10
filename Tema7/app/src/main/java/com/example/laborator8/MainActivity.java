package com.example.laborator8;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class MainActivity extends AppCompatActivity {
    private TextView tvcReceivedData;
    private EditText etcIp, etcPORT;
    private Button btnClientConnect;
    private String ServerName;
    private int ServerPort;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       tvcReceivedData = findViewById(R.id.data);
       etcIp = findViewById(R.id.ip);
       etcPORT = findViewById(R.id.port);
       btnClientConnect = findViewById(R.id.button);
    }

    public void onClickBtnClientConnect(View view){
       ServerName = etcIp.getText().toString();
       ServerPort = Integer.valueOf(etcPORT.getText().toString());

        new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    Socket socket_obj = new Socket(ServerName,ServerPort);
                    BufferedReader input = Utilities.getReader(socket_obj);
                    String txtFromServer = input.readLine();

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            tvcReceivedData.setText(txtFromServer);
                        }
                    });
                } catch (UnknownHostException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

}