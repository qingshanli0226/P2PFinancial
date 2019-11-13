package com.example.p2pdemo.CustomView;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Handler;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;

public class MyProView extends View {
    Paint paint,text;
    String press;
    float sweep;

    public MyProView(Context context) {
        super(context);
    }

    public MyProView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        paint=new Paint();
        paint.setStyle(Paint.Style.STROKE);
        paint.setAntiAlias(true);
        paint.setColor(Color.BLUE);
        paint.setStrokeWidth(15);

        text=new Paint();
        text.setStyle(Paint.Style.STROKE);
        text.setTextSize(20);
        text.setColor(Color.RED);
        text.setStrokeWidth(1);
        text.setAntiAlias(true);

    }

    public MyProView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);


    }



    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        RectF rectF = new RectF(20, 20, 140, 140);
        canvas.drawArc(rectF,0,sweep,false,paint);
        canvas.drawText(press+"%",60,80,text);

        rote();





    }

    private void rote() {
        float v = 360 * Float.parseFloat("0." + press);
        if(sweep<=v){
            sweep+=10;
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        invalidate();
    }

    public String getProgress(String progress){
        return press=progress;
    }


}
