package com.example.tema2_calculator;

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
    public void onClick (View view){
        TextView editTextNumber1 = findViewById(R.id.nr1);
        TextView editTextNumber2 = findViewById(R.id.nr2);
        int number1 = Integer.parseInt(editTextNumber1.getText().toString());
        int number2 = Integer.parseInt(editTextNumber2.getText().toString());
        Intent intent = new Intent(MainActivity.this, Operatii.class);
        intent.putExtra("number1", number1);
        intent.putExtra("number2", number2);
        startActivityForResult(intent, 1);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        TextView rez = findViewById(R.id.rez);
        int rezultat = data.getIntExtra("result", 0);
        rez.setText("" + rezultat);
    }
}
