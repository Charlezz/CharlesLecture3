package com.tistory.charlezz.wifi;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

public class BluetoothActivity extends AppCompatActivity implements View.OnClickListener {


    public static final String TAG = "BluetoothActivity";
    private Button scan;

    private BluetoothAdapter mBTAdapter;
    private ArrayAdapter<String> mAdapter;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        scan = (Button) findViewById(R.id.scan);
        scan.setOnClickListener(this);

        //블루투스 어댑터 얻기
        mBTAdapter = BluetoothAdapter.getDefaultAdapter();


        listView = (ListView) findViewById(R.id.listView);
        mAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1);
        listView.setAdapter(mAdapter);


        if (!mBTAdapter.isEnabled()) {

//            mBTAdapter.enable();//강제활성화
            startActivity(new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE));
        }
    }

    @Override
    public void onClick(View v) {
        if (v.equals(scan)) {
            Log.e(TAG, "scan");
            //스캔 시작
            if (!mBTAdapter.isDiscovering()) {
                //약 5~12초정도의 스캔시간 필요, 최대12초
                mBTAdapter.startDiscovery();
            }

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
            iFilter = new IntentFilter();
            //스캔 시작
            iFilter.addAction(BluetoothAdapter.ACTION_DISCOVERY_STARTED);
            //스캔 끝남
            iFilter.addAction(BluetoothAdapter.ACTION_DISCOVERY_FINISHED);
            //블루투스 연결 변경상태 가져오기
            iFilter.addAction(BluetoothAdapter.ACTION_CONNECTION_STATE_CHANGED);
            //이름 변경 되었을때 가져오기
            iFilter.addAction(BluetoothAdapter.ACTION_LOCAL_NAME_CHANGED);
            //블루투스 디스커버러블 요청시 가져오기
            iFilter.addAction(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
            //블루투스 활성화 요청 가져오기
            iFilter.addAction(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            //스캔모드 변경 상황 가져오기
            iFilter.addAction(BluetoothAdapter.ACTION_SCAN_MODE_CHANGED);
            //블루투스 상태 변화 가져오기
            iFilter.addAction(BluetoothAdapter.ACTION_STATE_CHANGED);
            //블루투스 스캔시 찾은 디바이스 가져오기
            iFilter.addAction(BluetoothDevice.ACTION_FOUND);

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

    private BroadcastReceiver br = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (action.equals(BluetoothDevice.ACTION_FOUND)) {
                BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                StringBuffer sb = new StringBuffer();
                sb.append(device.getName());
                sb.append("\n");
                //블루투스 mac어드레스
                sb.append(device.getAddress());
                sb.append("\n");
                sb.append(device.getBondState());
                sb.append("\n");
                mAdapter.add(sb.toString());
            } else if (action.equals(BluetoothAdapter.ACTION_DISCOVERY_STARTED)) {
                //스캔 시작
                mAdapter.clear();
            } else if (action.equals(BluetoothAdapter.ACTION_DISCOVERY_FINISHED)) {
                //스캔 끝
                Toast.makeText(context, "스캔끝~~", Toast.LENGTH_SHORT).show();
            } else if (action.equals(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE)){
                Toast.makeText(BluetoothActivity.this,"블루투스 활성화가 요청됨!", Toast.LENGTH_SHORT).show();
            }
        }
    };
}
