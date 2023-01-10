package com.example.tema4;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    EditText editText;
    String numeNou;
    ArrayList<String> listaStud= new ArrayList<String>();
    ArrayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.listView);
        editText = findViewById(R.id.deAdaugat);

        listaStud.add("Ili");
        listaStud.add("Rares");


        adapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1, listaStud);
        listView.setAdapter(adapter);

    }
    public void onClick(View view) {
        numeNou = editText.getText().toString();
        listaStud.add(numeNou);
        adapter.notifyDataSetChanged();

        editText.setText("");
    }
    public void onClickDel(View view) {
        listaStud.remove(listaStud.size() - 1);
        adapter.notifyDataSetChanged();
    }
}