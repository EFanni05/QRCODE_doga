package com.example.qrcode_doga;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

public class ListaAdatok extends AppCompatActivity {

    private ListView listviwe;
    private String api;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_adatok);
        init();
    }

    private void init(){
        Bundle bundle = getIntent().getExtras();
        api = bundle.getString("api");
        listviwe = findViewById(R.id.ListviewAdatok);
    }
}