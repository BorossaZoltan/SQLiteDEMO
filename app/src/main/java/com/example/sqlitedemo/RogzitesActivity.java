package com.example.sqlitedemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RogzitesActivity extends AppCompatActivity {

    EditText etNev, etEmail, etJegy;
    Button btnRogzit, btnVissza;
    DBHelper adatbazis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rogzites);

        init();
        btnRogzit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adatokRogzitese();
            }
        });
        btnVissza.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent vissza = new Intent(RogzitesActivity.this, MainActivity.class);
                startActivity(vissza);
                finish();
            }
        });

    }

    private void adatokRogzitese() {
        String nev = etNev.getText().toString().trim();
        String email = etEmail.getText().toString().trim();
        String jegy = etJegy.getText().toString().trim();
        if (nev.isEmpty()) {
            Toast.makeText(this, "Név megadása kötelező", Toast.LENGTH_SHORT).show();
            return;
        }
        if (email.isEmpty()) {
            Toast.makeText(this, "Email megadása kötelező", Toast.LENGTH_SHORT).show();
            return;
        }
        if (jegy.isEmpty()) {
            Toast.makeText(this, "Jegy megadása kötelező", Toast.LENGTH_SHORT).show();
            return;
        }
        int jegySzam = Integer.parseInt(jegy);
        if (jegySzam < 1 || jegySzam > 5) {
            Toast.makeText(this, "Jegy 1 és 5 között kell lennie!", Toast.LENGTH_SHORT).show();
            return;
        }
        if(adatbazis.adatRogzites(nev, email, jegy)){
            Toast.makeText(this, "Sikeres rögzítés", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this, "Sikertelen rögzítés", Toast.LENGTH_SHORT).show();
        }
    }

    private void init() {
        etNev = findViewById(R.id.et_nev_rogzit);
        etEmail = findViewById(R.id.et_email_rogzit);
        etJegy = findViewById(R.id.et_jegy_rogzit);
        btnRogzit = findViewById(R.id.btn_rogzit);
        btnVissza = findViewById(R.id.btn_rogzit_vissza);
        adatbazis = new DBHelper(RogzitesActivity.this);
    }


}