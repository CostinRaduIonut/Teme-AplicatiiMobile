package com.example.tema8;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Locale;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {
    String jsonURL = "http://www.floatrates.com/daily/ron.json";
    TextView rez, input;
    String suma;
    private RadioGroup radioGroup;
    private RadioButton radioButton;
    private static final String TAG = "MainActivity";

    private TextView texts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClickConvert(View view) {
        radioGroup = (RadioGroup) findViewById(R.id.radio);
        int selectedId = radioGroup.getCheckedRadioButtonId();

        // find the radiobutton by returned id
        radioButton = (RadioButton) findViewById(selectedId);
        input = findViewById(R.id.editTextTextPersonName);
        String operator1 = input.getText().toString();
        if (operator1.equals("")) {
            Toast.makeText(MainActivity.this, "Introdu macar ceva... :(", Toast.LENGTH_LONG).show();
        } else {
            rezultatFinal(view, radioButton.getText().toString().toLowerCase(), operator1);

        }


    }


    public void rezultatFinal(View view, String tip, String operator1) {
        rez = findViewById(R.id.editTextTextPersonName2);
        input = findViewById(R.id.editTextTextPersonName);
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(jsonURL)
                .build();

        Log.i(TAG, "onClickGetData: " + jsonURL);

        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                Log.i(TAG, "onFailure: ");
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                String recData = response.body().string();
                JSONObject obj = null;
                input = findViewById(R.id.editTextTextPersonName);
                try {
                    obj = new JSONObject(recData);
                    JSONObject b = obj.getJSONObject(tip);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                String operator = b.getString("rate").toString();
                                String finalitate = String.valueOf((Double.parseDouble(operator1) * Double.parseDouble(operator)));
                                rez.setText(finalitate);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    });
                } catch (JSONException e) {
                    e.printStackTrace();
                }

//                objToTrim = recData;
            }
        });

    }
}
