package com.example.sqlitedemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button btnOlvas, btnRogzit, btnTorles, btnModositas;
    TextView textAdatok;
    DBHelper adatbazis;
    //TODO: adatbázis

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
        listeners();
    }

    private void adatLekérdezés() {
        Cursor adatok = adatbazis.adatLekerdezes();
        if (adatok == null) {
            Toast.makeText(this,"Sikertelen lekérdezés", Toast.LENGTH_SHORT).show();
            return;
        }
        if (adatok.getCount()==0){
            Toast.makeText(this,"Még nincs adat felvéve", Toast.LENGTH_SHORT).show();
            return;
        }
        StringBuilder builder = new StringBuilder();
        while (adatok.moveToNext()){
            builder.append("ID: ").append(adatok.getInt(0)).append("\n");
            builder.append("Név: ").append(adatok.getString(1)).append("\n");
            builder.append("Email: ").append(adatok.getString(2)).append("\n");
            builder.append("Jegy: ").append(adatok.getInt(3)).append("\n\n");
        }
        textAdatok.setText(builder.toString());
    }

    private void listeners() {
        btnOlvas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adatLekérdezés();
            }
        });
        btnRogzit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent rogzit = new Intent(MainActivity.this, RogzitesActivity.class);
                startActivity(rogzit);
                finish();
            }
        });
        btnTorles.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent torles = new Intent(MainActivity.this, TorlesActivity.class);
                startActivity(torles);
                finish();
            }
        });
        btnModositas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent modosit = new Intent(MainActivity.this, ModositasActivity.class);
                startActivity(modosit);
                finish();
            }
        });
    }

    private void init() {
        btnOlvas = findViewById(R.id.btn_olvas);
        btnRogzit = findViewById(R.id.btn_rogzitesre);
        btnTorles = findViewById(R.id.btn_torlesre);
        btnModositas = findViewById(R.id.btn_modositasra);
        textAdatok = findViewById(R.id.text_adatok);
        textAdatok.setMovementMethod(new ScrollingMovementMethod());
        adatbazis = new DBHelper(MainActivity.this);

    }
}