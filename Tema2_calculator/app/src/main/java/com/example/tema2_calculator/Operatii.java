package com.example.tema2_calculator;

import static java.lang.Integer.*;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Button;


public class Operatii extends AppCompatActivity {
    int nr1;
    int nr2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_operatii);
        int number1 = getIntent().getIntExtra("number1", 0);
        int number2 = getIntent().getIntExtra("number2", 0);
        nr1 = number1;
        nr2 = number2;
        TextView viewnr1 = findViewById(R.id.viewnr1);
        TextView viewnr2 = findViewById(R.id.viewnr2);
        viewnr1.setText("Numarul1 = " + number1);
        viewnr2.setText("Numarul 2 = " + number2);
    }

    public void Aduna(View v) {
        int result = nr1 + nr2;
        Intent resultIntent = new Intent();
        resultIntent.putExtra("result", result);
        setResult(RESULT_OK, resultIntent);
        finish();
    }

    public void Scade(View v) {
        int result = nr1 - nr2;
        Intent resultIntent = new Intent();
        resultIntent.putExtra("result", result);
        setResult(RESULT_OK, resultIntent);
        finish();
    }
}