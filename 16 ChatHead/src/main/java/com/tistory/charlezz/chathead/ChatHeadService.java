package com.tistory.charlezz.chathead;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.IBinder;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;

public class ChatHeadService extends Service {

    public static final String TAG = "ChatHeadService";

    private WindowManager mWindowManager;
    private View v;
    private WindowManager.LayoutParams params;

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        AlarmUtil.getInstance().registerZombieService(false);
        mWindowManager = (WindowManager) getSystemService(Context.WINDOW_SERVICE);

        v = LayoutInflater.from(this).inflate(R.layout.chat_head, null);
        params = new WindowManager.LayoutParams(
                WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.TYPE_PHONE,
                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
                PixelFormat.TRANSPARENT
        );

        v.setOnTouchListener(new View.OnTouchListener() {

            int viewX, viewY;//뷰가 생성되는 기준 좌표
            float windowX, windowY;//윈도우, 디바이스 기준 좌표


            @Override
            public boolean onTouch(View v, MotionEvent event) {

                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN://손가락이 뷰를 눌렀을때
                        viewX = params.x;
                        viewY = params.y;
                        windowX = event.getRawX();
                        windowY = event.getRawY();
                        Log.e(TAG, "rPosX:" + viewX + " rPosY:" + viewY + " aPosX:" + windowX + " aPosY:" + windowY);
                        break;
                    case MotionEvent.ACTION_UP:
                        Log.e(TAG, "ACTION_UP");//뷰가 손가락의 터치로부터 떨어졌을때
                        break;
                    case MotionEvent.ACTION_MOVE:
                        Log.e(TAG, "ACTION_MOVE");
                        params.x = viewX + (int) (event.getRawX() - windowX);
                        params.y = viewY + (int) (event.getRawY() - windowY);
                        mWindowManager.updateViewLayout(v, params);
                        break;
                }

                return false;
            }
        });

        mWindowManager.addView(v, params);

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return Service.START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (v != null) {
            mWindowManager.removeViewImmediate(v);
            v = null;
        }
        AlarmUtil.getInstance().registerZombieService(true);
    }
}
