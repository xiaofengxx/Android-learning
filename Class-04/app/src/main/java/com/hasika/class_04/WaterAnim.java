package com.hasika.class_04;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.util.AttributeSet;
import android.util.EventLog;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

/**
 * Created by HaSiKa on 2016/5/14.
 */
public class WaterAnim extends SurfaceView implements SurfaceHolder.Callback ,Runnable{


    private SurfaceHolder msurfaceHolder;

    private Canvas mcanvas;

    private Thread mthread;

    private ArrayList<Yuan> list;

    private boolean isAlive;

    private Random random;

    private Paint paint;

    private int FPS = 10;
    private int sleeptiem = 1000/FPS;

    private int animSpeed = 5;
    private int w,h;

    public WaterAnim(Context context) {
        super(context);
    }

    public WaterAnim(Context context, AttributeSet attrs) {
        super(context, attrs);
        list = new ArrayList<Yuan>();
        msurfaceHolder = getHolder();
        msurfaceHolder.addCallback(this);
        //setZOrderOnTop(true);
        //msurfaceHolder.setFormat(PixelFormat.TRANSLUCENT);  //据说是透明

        setFocusable(true);
        setFocusableInTouchMode(true);
        paint = new Paint();
        paint.setAlpha(100);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(5);
        random = new Random(System.currentTimeMillis());

    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        long pretime = 0,nowtime;
        long ss,ee;
        ss = System.currentTimeMillis();
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                pretime = System.currentTimeMillis();
                list.add(new Yuan(event.getX(),event.getY()));
                break;
            case MotionEvent.ACTION_MOVE:
                nowtime = System.currentTimeMillis();
                if(nowtime - pretime > 50){
                    pretime = nowtime;
                    list.add(new Yuan(event.getX(),event.getY()));
                }
                break;
            case MotionEvent.ACTION_POINTER_UP:
                break;
            default:
                break;

        }
        ee = System.currentTimeMillis();
        return  true;
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        isAlive = true;
        mthread = new Thread(this);
        mthread.start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        isAlive = false;
    }

    @Override
    public void run() {
        while(isAlive){
            long start = System.currentTimeMillis();
            updata();
            draw();
            long end = System.currentTimeMillis();
            if(end - start < sleeptiem){
                try {
                    Thread.sleep(sleeptiem);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }else{
                System.out.println("绘制的时间"+(end-start));
            }
        }
    }

    public void updata(){
        for(int i = 0 ;i < list.size(); i ++){
            Yuan yuan = list.get(i);
            yuan.increasR();
            if(yuan.r > 210) {
                list.remove(i);
                i--;
            }
        }
    }


    private void draw(){
        try {

            mcanvas = msurfaceHolder.lockCanvas();
            if(mcanvas!= null){

                for(int i = 0 ;i < list.size(); i ++){
                    Yuan yuan = list.get(i);
                    paint.setColor(yuan.color);
                    mcanvas.drawCircle(yuan.x,yuan.y,yuan.r,paint);
                }
            }
        }finally {
            if(mcanvas != null)
                msurfaceHolder.unlockCanvasAndPost(mcanvas);
        }
    }

    public void setFPS(int FPS){
        this.FPS = FPS;
        sleeptiem = 1000/FPS;
    }

    class Yuan{
        float x,y,r;
        int  color;
        public Yuan(float x, float y){
            this.x = x;
            this.y = y;
            color = random.nextInt();
            r = 30;
        }
        public  void increasR(){
            r+= animSpeed;
        }
    }
}
