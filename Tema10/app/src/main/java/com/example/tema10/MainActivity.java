package com.example.tema10;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.provider.Settings;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    public SensorManager sensorManager;
    public Sensor lightSensor;
    public SensorEventListener lightEventListener;
    public float maxValue;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.textView);
        textView.setMovementMethod(new ScrollingMovementMethod());

        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        lightSensor = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);


        if (lightSensor == null) {
            Toast.makeText(getApplicationContext(), "nu il ai", Toast.LENGTH_SHORT).show();
            finish();
        } else {
            sensorManager.registerListener(this, lightSensor, SensorManager.SENSOR_DELAY_NORMAL);
        }

        maxValue = lightSensor.getMaximumRange();


    }


    @Override
    public void onSensorChanged(SensorEvent event) {
        Settings.System.putInt(getContentResolver(), Settings.System.SCREEN_BRIGHTNESS_MODE, Settings.System.SCREEN_BRIGHTNESS_MODE_MANUAL);
        float value = event.values[0];
        textView = findViewById(R.id.textView);
        String valoare = String.valueOf(value);
        textView.setText(valoare);
        int newValue = (int) (40000 - value);
        Settings.System.putInt(getContentResolver(), Settings.System.SCREEN_BRIGHTNESS, newValue);
        WindowManager.LayoutParams layoutpars = getWindow().getAttributes();
        layoutpars.screenBrightness = newValue / (float)255;
        getWindow().setAttributes(layoutpars);


    }


    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    @Override
    protected void onResume() {
        super.onResume();
        sensorManager.registerListener(lightEventListener, lightSensor, SensorManager.SENSOR_DELAY_FASTEST);
    }

    @Override
    protected void onPause() {
        sensorManager.unregisterListener(lightEventListener);
        super.onPause();
    }
}