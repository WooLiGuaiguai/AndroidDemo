package com.example.demo1;


import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

public class CircleView extends View {
    private int mColor = Color.BLUE;
    private Paint paint=new Paint(Paint.ANTI_ALIAS_FLAG);
    public CircleView(Context context) {
        super(context);
        init();
    }
    public CircleView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CircleView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        //加载自定义属性集合CircleView
        TypedArray a=context.obtainStyledAttributes(attrs,R.styleable.CircleView);
        //解析CircleView属性集合中的属性
        mColor=a.getColor(R.styleable.CircleView_circle_color,Color.RED);
        //实现资源
        a.recycle();
        init();
    }
    private void init(){
        paint.setColor(mColor);
    }
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthSpecMode=MeasureSpec.getMode(widthMeasureSpec);
        int widthSpecSize=MeasureSpec.getSize(widthMeasureSpec);
        int heightSpecMode=MeasureSpec.getMode(heightMeasureSpec);
        int heighSpecSize=MeasureSpec.getSize(heightMeasureSpec);
        if(widthSpecMode==MeasureSpec.AT_MOST
                && heightSpecMode==MeasureSpec.AT_MOST){
            setMeasuredDimension(200,200);
        }else if(widthSpecMode==MeasureSpec.AT_MOST ){
            setMeasuredDimension(200,heighSpecSize);
        }else if(heightSpecMode==MeasureSpec.AT_MOST){
            setMeasuredDimension(widthSpecSize,200);
        }
    }
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        final int paddingLeft = getPaddingLeft();
        final int paddingRight = getPaddingRight();
        final int paddingTop = getPaddingTop();
        final int paddingBottom = getPaddingBottom();
        int width=getWidth()- paddingLeft - paddingRight;
        int height=getHeight()- paddingTop - paddingBottom;
        int radius=Math.min(width,height)/2;
        canvas.drawCircle(paddingLeft + width/2, paddingTop+height/2,radius,paint);
    }
}
