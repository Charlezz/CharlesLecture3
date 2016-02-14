package com.tistory.charlezz.wifi;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class SelectActivity extends AppCompatActivity implements View.OnClickListener {


    private Button wifi, bt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select);
        bt = (Button) findViewById(R.id.bt);
        wifi = (Button) findViewById(R.id.wifi);

        bt.setOnClickListener(this);
        wifi.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.equals(bt)) {
            startActivity(new Intent(SelectActivity.this, BluetoothActivity.class));
        } else if (v.equals(wifi)) {
            startActivity(new Intent(SelectActivity.this, WifiActivity.class));
        }
    }
}
