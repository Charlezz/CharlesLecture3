package com.tistory.charlezz.wifi;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.List;


public class WifiActivity extends AppCompatActivity implements View.OnClickListener {

    private WifiManager mWifiManager;

    public static final String TAG = "WifiActivity";

    private ListView listView;

    public ArrayAdapter<String> mAdapter;
    private Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mWifiManager = (WifiManager) getSystemService(Context.WIFI_SERVICE);
        Log.e(TAG, "wifi is enabled:" + mWifiManager.isWifiEnabled());

        Log.e(TAG, "current IP:" + mWifiManager.getDhcpInfo().ipAddress);
        Log.e(TAG, "wifistate:" + mWifiManager.getWifiState());
//        Log.e(TAG, "getConnectionInfo:" + mWifiManager.getConnectionInfo());




        WifiInfo info = mWifiManager.getConnectionInfo();

        Log.e(TAG, "SSID:" + info.getSSID());
        Log.e(TAG, "ip:" + info.getIpAddress());
        Log.e(TAG, "신호세기" + info.getRssi());


        listView = (ListView) findViewById(R.id.listView);
        mAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1);

        listView.setAdapter(mAdapter);
        getCurrentWifiList();

        btn = (Button) findViewById(R.id.btn);
        btn.setOnClickListener(this);
    }

    private void getCurrentWifiList() {


        mAdapter.clear();
        //이미 와이파이 스캔이 끝난 데이터를 가져오기
        List<ScanResult> results = mWifiManager.getScanResults();

        for (ScanResult result : results) {
            //어댑터에 하나씩 추가
            mAdapter.add(result.SSID + "        " + result.level);
        }
    }


    @Override
    protected void onResume() {
        super.onResume();
        registerMyReceiver();
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterMyReceiver();
    }


    private void registerMyReceiver() {
        if (iFilter == null) {
            iFilter = new IntentFilter(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION);
            registerReceiver(br, iFilter);
        }
    }

    private void unregisterMyReceiver() {

        if (iFilter != null) {
            unregisterReceiver(br);
            iFilter = null;
        }

    }

    private IntentFilter iFilter;

    //로컬리시버
    private BroadcastReceiver br = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();

            if (action.equals(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION)) {
                Log.e(TAG, "스캔끝남");
                wifiScanningFlag = false;
                getCurrentWifiList();
            }
        }
    };

    private boolean wifiScanningFlag = false;

    @Override
    public void onClick(View v) {
        if (v.equals(findViewById(R.id.btn))) {
            if (!wifiScanningFlag) {
                mAdapter.clear();
                Log.e(TAG, "버튼 클릭후 스캔 가동");
                wifiScanningFlag = true;
                mWifiManager.startScan();

            }
        }
    }
}
