package com.tistory.charlezz.chathead;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class ZombieReceiver extends BroadcastReceiver {
    public ZombieReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {

        String action = intent.getAction();
        if (action.equals(Intent.ACTION_BOOT_COMPLETED)) {
            AlarmUtil.getInstance().registerZombieService(true);
        }

    }
}
