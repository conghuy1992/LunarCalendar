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
public class ConvertDate extends AppCompatActivity implements View.OnClickListener {
    EditText date, month, year;
    TextView lunardate;
    Button convert;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.conver_date);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        init();
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

    public void init() {
        date = (EditText) findViewById(R.id.date);
        month = (EditText) findViewById(R.id.month);
        year = (EditText) findViewById(R.id.year);
        lunardate = (TextView) findViewById(R.id.lunardate);
        convert = (Button) findViewById(R.id.btnconver);
        convert.setOnClickListener(this);
    }

    public void doStart() {
        int dd = Integer.parseInt(date.getText().toString());
        int mm = Integer.parseInt(month.getText().toString());
        int yy = Integer.parseInt(year.getText().toString());
        convertSolar2Lunar(dd, mm, yy, 7);
    }

    void convertSolar2Lunar(int dd, int mm, int yy, int timeZone) {
        int k, dayNumber, lunarMonth, lunarYear, lunarDay, lunarLeap, a11, b11, monthStart, diff, leapMonthDiff;
        dayNumber = (int) Const.getJulius(dd, mm, yy);
        k = (int) ((dayNumber - 2415021.076998695) / 29.530588853);
        monthStart = (int) Const.getNewMoonDay(k + 1, timeZone);
        if (monthStart > dayNumber) {
            monthStart = (int) Const.getNewMoonDay(k, timeZone);
        }
        a11 = (int) Const.getLunarMonthll(yy, timeZone);
        b11 = a11;

        if (a11 >= monthStart) {
            lunarYear = yy;
            a11 = (int) Const.getLunarMonthll(yy - 1, timeZone);
        } else {
            lunarYear = yy + 1;
            b11 = (int) Const.getLunarMonthll(yy + 1, timeZone);
        }
        lunarDay = dayNumber - monthStart + 1;
        int Ngay = lunarDay;
        diff = (monthStart - a11) / 29;
        lunarLeap = 0;
        lunarMonth = diff + 11;
        if (b11 - a11 > 365) {
            leapMonthDiff = (int) Const.getLeapMonthOffset(a11, timeZone);
            if (diff >= leapMonthDiff) {
                lunarMonth = diff + 10;
                if (diff == leapMonthDiff) {
                    lunarLeap = 1;
                }
            }
        }
        if (lunarMonth > 12) {
            lunarMonth = lunarMonth - 12;
        }
        if (lunarMonth >= 11 && diff < 4) {
            lunarYear -= 1;
        }
        int month = (lunarMonth) + 1;
        if (month > 12) month = 1;
        int Nam = (lunarYear);
        lunardate.setText("" + Ngay + "/" + month + "/" + Nam + " (Âm Lịch)");
    }

    @Override
    public void onClick(View v) {
        if (v == convert)
            doStart();
    }
}
