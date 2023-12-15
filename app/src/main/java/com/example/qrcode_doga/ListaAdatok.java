package com.example.qrcode_doga;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ListaAdatok extends AppCompatActivity {

    private ListView listviwe;
    private String api;
    private List<Diak> Diak = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_adatok);
        init();
        RequestTask task = new RequestTask(api,"GET");
        task.execute();
    }

    private void init(){
        Bundle bundle = getIntent().getExtras();
        api = bundle.getString("api");
        listviwe = findViewById(R.id.ListviewAdatok);
    }

    private class RequestTask extends AsyncTask<Void, Void, Response> {
        String requestUrl;
        String requestType;

        public RequestTask(String requestUrl, String requestType) {
            this.requestUrl = requestUrl;
            this.requestType = requestType;
        }

        @Override
        protected Response doInBackground(Void... voids) {
            Response response = null;
            try {
                response = RequestH.get(api);

            }
            catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            return response;
        }

        @Override
        protected void onPostExecute(Response response) {
            super.onPostExecute(response);
            Gson converter = new Gson();
            if (response.getResponseCode() >= 400) {
                Toast.makeText(ListaAdatok.this, "Hiba", Toast.LENGTH_SHORT).show();
                Log.d("onPostExecuteError: ", response.getResponseMessage());
            }
                Diak[] DiakArray = converter.fromJson(response.getResponseMessage(), Diak[].class);
                Diak.clear();
                Diak.addAll(Arrays.asList(DiakArray));
            }
        }
    }
