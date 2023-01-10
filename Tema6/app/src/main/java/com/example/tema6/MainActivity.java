package com.example.tema6;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
        }

        public void onStart(View view) {
            TextView perioada = findViewById(R.id.perioada);
            TextView volum = findViewById(R.id.volum);
            TextView durata = findViewById(R.id.durata);
            String P = perioada.getText().toString();
            String V = volum.getText().toString();
            String D = durata.getText().toString();

            Intent serviceIntent = new Intent(MainActivity.this, MyService.class);
            serviceIntent.putExtra("perioada", P);
            serviceIntent.putExtra("volum", V);
            serviceIntent.putExtra("durata",D);
            this.startService(serviceIntent);


        }

        public void onStop(View view) {
            Intent intent_2 = new Intent(getApplicationContext(), MyService.class);
            stopService(intent_2);
        }
}