package com.example.apolo.cmoneyapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Spinner spMoney = (Spinner) findViewById(R.id.spMoney);
        final ArrayAdapter<CharSequence> adapterSp = ArrayAdapter.createFromResource(this,R.array.money,android.R.layout.simple_spinner_item);
        adapterSp.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spMoney.setAdapter(adapterSp);
        spMoney.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position > 0)
                    Toast.makeText(getApplicationContext(),"Select:"+parent.getItemAtPosition(position),Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
}
