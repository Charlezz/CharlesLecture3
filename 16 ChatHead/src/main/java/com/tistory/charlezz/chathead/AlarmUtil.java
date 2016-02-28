package com.tistory.charlezz.chathead;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.SystemClock;

/**
 * Created by Charles on 16. 2. 28..
 */
public class AlarmUtil {
    private static AlarmUtil ourInstance = new AlarmUtil();


    private AlarmManager am;

    public static AlarmUtil getInstance() {
        return ourInstance;
    }

    private AlarmUtil() {
        am = (AlarmManager) MyApplication.getContext().getSystemService(Context.ALARM_SERVICE);
    }

    public void registerZombieService(boolean enabled) {

        PendingIntent pi = PendingIntent.getService(
                MyApplication.getContext(),
                0,
                new Intent(MyApplication.getContext(),
                        ChatHeadService.class),
                PendingIntent.FLAG_UPDATE_CURRENT);

        if (enabled) {
            //서비스가 꺼지면 다시 시작
            am.setRepeating(AlarmManager.RTC_WAKEUP, SystemClock.currentThreadTimeMillis() + 5 * 1000, 10 * 1000, pi);
        } else {
            //서비스가 켜졌으면 더이상 시작 하지 않음
            am.cancel(pi);
        }
    }
}
