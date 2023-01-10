package com.example.tema3_view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Intent intent = getIntent();
        String str = intent.getStringExtra("key");
        String mesajFinal = str;

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        Bundle bundle = new Bundle();
        bundle.putString("cheie", mesajFinal);
        BlankFragment blankFragment = new BlankFragment();
        blankFragment.setArguments(bundle);
        fragmentTransaction.replace(R.id.fragmentContainerView2, blankFragment).commit();
    }

    public void onClickBack(View view) {
        Intent intent1 = new Intent(MainActivity2.this, MainActivity.class);
        startActivity(intent1);
    }
}