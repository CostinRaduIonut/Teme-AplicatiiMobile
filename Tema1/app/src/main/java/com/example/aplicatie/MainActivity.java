package com.example.aplicatie;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.view.View;
import android.widget.TextView;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void onClickbutton(View view) {
        TextView nume = findViewById(R.id.nume);
        TextView prenume = findViewById(R.id.prenume);
        TextView email = findViewById(R.id.email);

        TextView textView1 = findViewById(R.id.NumeIn);
        TextView textView2 = findViewById(R.id.PrenumeIn);
        TextView textView3 = findViewById(R.id.EmailIn);

        textView1.setText(nume.getText().toString());
        textView2.setText(prenume.getText().toString());
        textView3.setText(email.getText().toString());

        Toast.makeText(getApplicationContext(), "Date salvate", Toast.LENGTH_LONG).show();
    }

    public void onClickShowToast(View view) {
        CharSequence text = "toast 2";
        Toast toast = Toast.makeText(getApplicationContext(), text, Toast.LENGTH_LONG);
        toast.show();
    }
}