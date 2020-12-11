package com.example.sqlitedemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ModositasActivity extends AppCompatActivity {

    EditText etId, etNev, etEmail, etJegy;
    Button btnModosit, btnVissza;
    DBHelper adatbazis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modositas);
        init();
        btnVissza.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent vissza = new Intent(ModositasActivity.this, MainActivity.class);
                startActivity(vissza);
                finish();
            }
        });
        btnModosit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adatModosit();
            }
        });
    }

    private void adatModosit() {
        String id = etId.getText().toString().trim();
        String nev = etNev.getText().toString().trim();
        String email = etEmail.getText().toString().trim();
        String jegy = etJegy.getText().toString().trim();

        if (id.isEmpty()){
            Toast.makeText(this,"ID megadása kötelező",Toast.LENGTH_SHORT).show();
            return;
        }
        if (!adatbazis.idEllenorzes(id)){
            Toast.makeText(this,"Ilyen ID-val nincs rekord",Toast.LENGTH_SHORT).show();
            return;
        }
        if (nev.isEmpty()){
            Toast.makeText(this,"Név megadása kötelező",Toast.LENGTH_SHORT).show();
            return;
        }
        if (email.isEmpty()){
            Toast.makeText(this,"Email megadása kötelező",Toast.LENGTH_SHORT).show();
            return;
        }
        if (jegy.isEmpty()){
            Toast.makeText(this,"Jegy megadása kötelező",Toast.LENGTH_SHORT).show();
            return;
        }
        int jegySzam = Integer.parseInt(jegy);
        if (jegySzam >5 || jegySzam<1) {
            Toast.makeText(this,"A jegy csak 1 és 5 közötti szám lehet",Toast.LENGTH_SHORT).show();
            return;

        }
        if (adatbazis.adatModositas(id,nev,email,jegy)){
            Toast.makeText(this, "Sikeres módosítás", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this, "Sikertelen módosítás", Toast.LENGTH_SHORT).show();
        }
    }

    private void init() {
        etId = findViewById(R.id.et_id_modosit);
        etNev = findViewById(R.id.et_nev_modosit);
        etEmail = findViewById(R.id.et_email_modosit);
        etJegy = findViewById(R.id.et_jegy_modosit);
        btnModosit = findViewById(R.id.btn_modosit);
        btnVissza = findViewById(R.id.btn_modosit_vissza);
        adatbazis = new DBHelper(ModositasActivity.this);

    }
}