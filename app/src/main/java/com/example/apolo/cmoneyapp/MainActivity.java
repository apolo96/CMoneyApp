package com.example.apolo.cmoneyapp;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public static String htmlResult = "";
    private String a = "1";
    private Spinner spMoney;
    private ArrayList<Divisa> divisas = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Spinner
        spMoney = (Spinner) findViewById(R.id.spMoney);
        spMoney.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Divisa divisa = (Divisa) parent.getSelectedItem();
                Toast.makeText(getApplicationContext(), "divisa ID: "+ divisa.getId() +" Moneda: " + divisa.getName(), Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        // END Spinner

        // Jsoup
        ParseWeb html = new ParseWeb();
        html.execute();

    }

    public void addDivisas(String value, String texto){
        divisas.add(new Divisa(value,texto));
    }
    public void setDivisasSp(ArrayList divisas){
        ArrayAdapter<Divisa> adapter = new ArrayAdapter<Divisa>(this, android.R.layout.simple_spinner_dropdown_item, divisas);
        spMoney.setAdapter(adapter);
    }
    public class ParseWeb extends AsyncTask<String, Void, String> {
        public Document doc;
        public String url = "https://www.google.com/finance/converter?a=" + a + "&from=USD&to=EUR";
        public String html;
        public Elements selectOpt;
        public boolean verityJsoup = true;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }

        @Override
        protected String doInBackground(String... params) {
            try {
                doc = Jsoup.connect(url).get();
                html = doc.getElementsByClass("bld").text().toString();
                selectOpt = doc.select("select[name=from]").select("option");
            } catch (IOException e) {
                verityJsoup = false;
                Log.e("Error:", e.getMessage().toString());
                Toast.makeText(getApplicationContext(),"Ocurrio un error , porfavor verifique su conexion a internet",Toast.LENGTH_LONG);
            }
            return "OK";
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            if(verityJsoup != true)return;
            for (Element option: selectOpt) {
                addDivisas(option.attr("value"),option.text());
            }
            setDivisasSp(divisas);
            Toast.makeText(getApplication(), "Inner class" + html, Toast.LENGTH_LONG).show();

        }
    }
}
