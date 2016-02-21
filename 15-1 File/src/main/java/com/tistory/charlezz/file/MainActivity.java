package com.tistory.charlezz.file;

import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = "MainActivity";

    public static final String ROOT = Environment.getExternalStorageDirectory().getAbsolutePath();
    public static final String DOWNLOAD_PATH = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getAbsolutePath();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        File file = new File(ROOT);
        String[] list = file.list();
        for (String str : list) {
            Log.e(TAG, str);
        }

        File jungu = new File(ROOT + "/jungu");

        if (!jungu.exists() && !jungu.isDirectory()) {
            jungu.mkdir();
        }

        File clock = new File(DOWNLOAD_PATH + "/clock.png");
        Log.e(TAG, clock.getName());
        Log.e(TAG, clock.getPath());


        try {

            InputStream is;
            OutputStream os;

            FileInputStream fis = new FileInputStream(clock);
            byte[] clockBytes = new byte[(int) clock.length()];
            fis.read(clockBytes);

            for (byte b : clockBytes) {
                System.out.println(b);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
