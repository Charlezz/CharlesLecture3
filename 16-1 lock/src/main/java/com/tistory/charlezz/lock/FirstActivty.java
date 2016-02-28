package com.tistory.charlezz.lock;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class FirstActivty extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_activty);
        findViewById(R.id.btn2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(FirstActivty.this, SecondActivity.class));
            }
        });
    }
}
