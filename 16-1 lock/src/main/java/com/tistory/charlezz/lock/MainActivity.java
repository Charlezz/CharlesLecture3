package com.tistory.charlezz.lock;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e(TAG, "onCreate");
        Intent i = new Intent(this, LockActivity.class);
        startActivityForResult(i, REQUEST_PASSWORD);

        setContentView(R.layout.activity_main);
        findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, FirstActivty.class));
            }
        });
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        //액티비티객체의 상태를 저장된것을 로드하는부분
        Log.e(TAG, "onRestoreInstanceState");
        String str = savedInstanceState.getString("key1");
        Toast.makeText(MainActivity.this, str, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        //액티비티객체의 상태를 저장하는부분
        Log.e(TAG, "onSaveInstanceState1");
        outState.putString("key1", "value1");
    }

}
