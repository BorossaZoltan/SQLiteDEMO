package com.example.sqlitedemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class TorlesActivity extends AppCompatActivity {
EditText etId;
Button btnTorol, btnVissza;
DBHelper adatbazis;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_torles);
        init();
        btnVissza.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent vissza = new Intent( TorlesActivity.this, MainActivity.class);
                startActivity(vissza);
                finish();
            }
        });
        btnTorol.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adatTorles();
            }
        });

    }

    private void adatTorles() {
        String id = etId.getText().toString().trim();
        if (id.isEmpty()) {
            Toast.makeText(this,"ID megadása kötelező",Toast.LENGTH_SHORT).show();
            return;
        }
        if (!adatbazis.idEllenorzes(id)) {
            Toast.makeText(this,"Ilyen id-val nincs rekord",Toast.LENGTH_SHORT).show();
            return;
        }
        if (adatbazis.adatTorles(id)){
            Toast.makeText(this,"Sikeres törlés",Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(this,"Sikertelen törlés",Toast.LENGTH_SHORT).show();
        }

    }

    private void init() {
        etId = findViewById(R.id.et_id_torol);
        btnTorol = findViewById(R.id.btn_torol);
        btnVissza = findViewById(R.id.btn_torles_vissza);
        adatbazis = new DBHelper(TorlesActivity.this);

    }


}