package com.yibuimg.hasika.yibuimg;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.widget.ImageView;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by HaSiKa on 2016/1/26.
 */
public class YBIMG extends ImageView{
    private Handler handler;
    public YBIMG(Context context) {
        super(context);
        init();
    }

    public YBIMG(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }


    private void init(){
        handler = new Handler(){

            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                Bitmap bitmap = (Bitmap) msg.obj;
                YBIMG.this.setImageBitmap(bitmap);
                msg = null;
            }
        };
    }

    public void setIMGfromURL(final String s_url){
        new Thread(){
            @Override
            public void run() {
                super.run();
                try {
                    URL url = new URL(s_url);

                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.setConnectTimeout(5000);
                    conn.setRequestMethod("GET");

                    if(conn.getResponseCode() == 200){
                        Bitmap bitmap = BitmapFactory.decodeStream(conn.getInputStream());
                        Message msg = Message.obtain();
                        if(msg == null){
                            msg = new Message();
                        }
                        msg.obj = bitmap;
                        handler.sendMessage(msg);

                    }
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

}
