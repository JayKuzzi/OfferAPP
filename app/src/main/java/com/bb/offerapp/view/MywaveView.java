package com.bb.offerapp.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import java.util.Random;

/**
 * Created by bb on 2017/5/1.
 */


public class MywaveView extends View {


    //    圆环中心
    private int cx;
    private int cy;
    //    半径
    private int radius;
    //    线条厚度
    private float strokewidth;
    //    画笔

    private Paint paint;


    private Handler handle = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            //刷新状态
            logic();
            invalidate();
            if(paint.getAlpha() !=0){
                handle.sendEmptyMessageDelayed(0,60);
            }
        }
    };

    Random random =new Random();

    public MywaveView(Context context) {
        super(context);
    }

    public MywaveView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        //初始化paint
        paint = new Paint();
        paint.setAntiAlias(true);//抗锯齿
        paint.setAlpha(255); //透明度
        paint.setStyle(Paint.Style.STROKE);//STROKE刻画
        paint.setStrokeWidth(strokewidth); //园厚度
        radius = 0;
        strokewidth = 0;
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawCircle(cx, cy, radius, paint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        super.onTouchEvent(event);

        switch (event.getAction()) {
            //点击获取圆环中心
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_MOVE:
                cx = (int) event.getX();
                cy = (int) event.getY();
                //初始化画笔
                init();
                handle.sendEmptyMessage(0);
                break;

        }

        return true;
    }



    private void logic() {
        //执行逻辑
        radius += 10;
        strokewidth = radius/3;
        paint.setStrokeWidth(strokewidth);
        int nextAlpha= paint.getAlpha()-20;
        if(paint.getAlpha()<=20){
            nextAlpha =0;
        }
        int r= random.nextInt(256);
        int g= random.nextInt(256);
        int b= random.nextInt(256);

        paint.setARGB(nextAlpha,r,g,b);
    }


}
