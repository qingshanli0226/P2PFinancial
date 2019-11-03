package com.example.p_two_p;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

public class MyCircleView extends View {


    Paint paint;

    int width;

    public MyCircleView(Context context) {
        super(context);
    }

    public MyCircleView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        paint=new Paint();
        paint.setColor(Color.RED);
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(10);
    }

    public MyCircleView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        width=widthMeasureSpec;

    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int cx=width/2;
        int cy=width/2;

        float radius=width/2 - 10/2;

        canvas.drawCircle(cx,cy,radius,paint);


        RectF rectF=new RectF(10/2,10/2,width-10/2,width-10/2);
        paint.setColor(Color.GREEN);
        canvas.drawArc(rectF,0,70*360/100,false,paint);

        String text=70*100/100+"%";
        paint.setColor(Color.BLACK);
        paint.setTextSize(20);
        paint.setStrokeWidth(0);

        Rect rect=new Rect();
        paint.getTextBounds(text,0,text.length(),rect);
        int x=width/2-rect.width()/2;
        int y=width/2+rect.height()/2;

        canvas.drawText(text,x,y,paint);







    }
}
