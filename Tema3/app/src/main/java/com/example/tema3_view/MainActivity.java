package com.example.tema3_view;

import static com.example.tema3_view.R.drawable.modem;
import static com.example.tema3_view.R.drawable.prelungitor;
import static com.example.tema3_view.R.drawable.rack;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

String informatia;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FrameLayout frameLayout = findViewById(R.id.frameLayout);
        setFragment(new Modem());
        informatia = "Gol";
    }

    public void onClickInfo(View view) {
        Intent intent = new Intent(this, MainActivity2.class);
        Toast toast = Toast.makeText(getApplicationContext(), informatia, Toast.LENGTH_SHORT);
        intent.putExtra("key",informatia);
        toast.show();
        startActivity(intent);
    }


    int clickcountN = 1;
    int clickcountP = 1;

    public void onClickNext(View view) {

        clickcountN = clickcountN + 1;
        if (clickcountN == 1) {
            setFragment(new Prelungitor());
            informatia = "prelungitor";
        } else if (clickcountN == 2) {
            setFragment(new Modem());
            informatia = "modem";
        } else if (clickcountN == 3) {
            setFragment(new Rack());
            informatia = "rack";
        } else {
            clickcountN = 1;
            setFragment(new Prelungitor());
            informatia = "prelungitor";
        }
    }

    public void onClickPrev(View view) {
        clickcountP = clickcountP + 1;
        if (clickcountP == 1) {
            setFragment(new Rack());
            informatia = "rack";
        } else if (clickcountP == 2) {
            setFragment(new Modem());
            informatia = "modem";
        } else if (clickcountP == 3) {
            setFragment(new Prelungitor());
            informatia = "prelungitor";
        } else {
            clickcountP = 1;
            setFragment(new Rack());
            informatia = "rack";
        }
    }

    private void setFragment(Fragment fragmentParam)
    {
        FragmentManager fragManager = getSupportFragmentManager();
        FragmentTransaction fragTransaction = fragManager.beginTransaction();
        fragTransaction.replace(R.id.frameLayout,fragmentParam);
        fragTransaction.commit();
    }


}