package com.hasika.wateranim;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;


/**
 * Created by HaSiKa on 2016/4/13.
 */
public class MyCav extends View{
    private ArrayList<Yuan> yuanArrayList;
    private long pretime,nowtime;
    private Paint paint;
    private Random random;
    private Thread thread;
    private Handler handler;
    public MyCav(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if(yuanArrayList.isEmpty())
            return;
        Iterator<Yuan> it =yuanArrayList.iterator();
        for (Yuan yuan = it.next();it.hasNext();){
            yuan = it.next();
            yuan.increasR();
            paint.setColor(yuan.getColor());
            canvas.drawCircle(yuan.getX(),yuan.getY(),yuan.getR(),paint);
            if(yuan.getR() > 200)
                it.remove();
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        super.onTouchEvent(event);
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                pretime = System.currentTimeMillis();
                yuanArrayList.add(new Yuan(event.getX(),event.getY()));
                break;
            case MotionEvent.ACTION_MOVE:
                 nowtime = System.currentTimeMillis();
                if(nowtime - pretime > 50){
                    pretime = nowtime;
                    yuanArrayList.add(new Yuan(event.getX(),event.getY()));
                }
                break;
            case MotionEvent.ACTION_POINTER_UP:
                break;
            default:
                break;

        }



        return true;
    }
    private void init(){
        yuanArrayList = new ArrayList<Yuan>();
        random = new Random(System.currentTimeMillis());
        paint = new Paint();
        paint.setAlpha(100);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(15);
        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                invalidate();
            }

        };
        thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while(true){
                    try {
                        Thread.sleep(45);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    handler.sendEmptyMessage(0);
                }
            }
        });
        thread.start();
    }

    private class Yuan{
        private float x,y,r;
        private int color;
        public Yuan(float x, float y) {
            this.x = x;
            this.y = y;
            r = 20;
            color = random.nextInt();

        }

        public float getX() {
            return x;
        }

        public float getY() {
            return y;
        }

        public float getR() {
            return r;
        }
        public  void increasR(){
            r+=3;
        }
        public int getColor(){
            return  color;
        }
    }

}
