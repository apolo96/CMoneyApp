package com.example.apolo.cmoneyapp;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.jsoup.Jsoup;
import org.jsoup.select.Elements;
import org.jsoup.nodes.Document;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    public String htmlResult = "";
    private String a = "1";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Spinner spMoney = (Spinner) findViewById(R.id.spMoney);
        final ArrayAdapter<CharSequence> adapterSp = ArrayAdapter.createFromResource(this, R.array.money, android.R.layout.simple_spinner_item);
        adapterSp.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spMoney.setAdapter(adapterSp);
        spMoney.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position > 0)
                    Toast.makeText(getApplicationContext(), "Select:" + parent.getItemAtPosition(position), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        ParseWeb html = new ParseWeb();
        html.execute();

        Toast.makeText(getApplication(), htmlResult, Toast.LENGTH_LONG).show();
    }

    public class ParseWeb extends AsyncTask<String, Void, String> {
        public Document doc;
        public String url = "https://www.google.com/finance/converter?a=" + a + "&from=USD&to=EUR";
        public String html;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }

        @Override
        protected String doInBackground(String... params) {
//            Log.e("url",url);
            try {
                doc = Jsoup.connect(url).get();
//                Elements spans = doc.getElementsByClass("bld");
                html = doc.getElementsByClass("bld").toString();
                Log.d("span", doc.getElementsByClass("bld").toString());
            } catch (IOException e) {
                Log.e("Error:", e.getMessage().toString());
            }
            return "OK";
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            htmlResult = html;
        }
    }
}
