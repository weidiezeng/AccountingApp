package com.example.administrator.accountingapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.robinhood.ticker.TickerUtils;
import com.robinhood.ticker.TickerView;

public class MainActivity extends AppCompatActivity {
    private TickerView tickerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        getSupportActionBar().setElevation(0);
    }
}
