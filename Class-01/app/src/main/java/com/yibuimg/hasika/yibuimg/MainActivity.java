package com.yibuimg.hasika.yibuimg;

import android.content.Context;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        YBIMG ybimg = (YBIMG) findViewById(R.id.ybimg);
        ybimg.setIMGfromURL("http://coldpic.sfacg.com/Pic/OnlineComic4/QYZ/157/001_9999.png");
        Handler handler = new Handler();

        WindowManager wm = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
        
    }
}
