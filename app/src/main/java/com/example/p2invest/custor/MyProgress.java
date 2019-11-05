package com.example.p2invest.custor;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class MyProgress extends View {
    public MyProgress(Context context) {
        super(context);
    }

    private int num=0;
    public MyProgress(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MyProgress(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);



        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(Color.rgb(90,110,158));
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(20);
        canvas.drawCircle(getWidth()/2,getHeight()/2,getHeight()/3,paint);

        paint.setColor(Color.RED);
        paint.setTextSize(60);
        paint.setStrokeWidth(0);
        canvas.drawText(num+"%",getWidth()/2-30,getHeight()/2+20,paint);
        Log.i("wzy", "onDraw: "+num);
    }
    public  void refresh(){
        num++;
        postInvalidate();
    }
}
