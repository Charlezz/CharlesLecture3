package com.tistory.charlezz.lock;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

/**
 * Created by Charles on 16. 2. 28..
 */
abstract class BaseActivity extends AppCompatActivity {

    public static final String TAG = "BaseActivity";

    private boolean hasLeft = false;

    public static final int REQUEST_PASSWORD = 0;

    @Override
    protected void onUserLeaveHint() {
        super.onUserLeaveHint();
        //사용자가 홈버튼을 누르거나 기타 다른액션에의해서 액티비티 밖으로 나갔을 때 불림
        hasLeft = true;
        Log.e(TAG, "onUserLeaveHint:" + hasLeft);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.e(TAG, "onResume");
        if (hasLeft) {
            startActivityForResult(new Intent(this, LockActivity.class), REQUEST_PASSWORD);
            hasLeft = false;
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.e(TAG, "onPause");
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.e(TAG, "onActivityResult");
        if (requestCode == REQUEST_PASSWORD && resultCode == Activity.RESULT_CANCELED) {
            finish();
        }
    }

    @Override
    public void startActivity(Intent intent) {
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_USER_ACTION);
        super.startActivity(intent);
    }

    @Override
    public void startActivityForResult(Intent intent, int requestCode) {
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_USER_ACTION);
        super.startActivityForResult(intent, requestCode);
    }
}
