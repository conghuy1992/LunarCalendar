package com.example.huy.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by huy on 1/16/16.
 */
public class ConverCalendar extends AppCompatActivity implements View.OnClickListener {
    EditText  txtyy;
    Button btnconvert;
    TextView txtlunarday;
    String Can = "", Chi = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.convertcalendar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        init();
    }

    public void init() {
        txtyy = (EditText) findViewById(R.id.txtyear);
        btnconvert = (Button) findViewById(R.id.btnconver);
        btnconvert.setOnClickListener(this);
        txtlunarday = (TextView) findViewById(R.id.txtlunardate);
    }

    public void doStart() {
        int _chi = Integer.parseInt(txtyy.getText().toString()) % 12;
        int _can = Integer.parseInt(txtyy.getText().toString()) % 10;
        Can = Const.ConverCan(_can);
        Chi = Const.ConvertChi(_chi);
        txtlunarday.setText(Can + " " + Chi);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onClick(View v) {
        if (v == btnconvert)
            doStart();
    }
}
