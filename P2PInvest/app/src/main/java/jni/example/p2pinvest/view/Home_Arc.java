package jni.example.p2pinvest.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

public class Home_Arc extends View {

    private RectF rectF = new RectF();
    public Home_Arc(Context context) {
        super(context);
        rectF.left = 400;
        rectF.right = 600;
        rectF.bottom = 600;
        rectF.top = 400;
    }

    public Home_Arc(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        rectF.left = 400;
        rectF.right = 600;
        rectF.bottom = 600;
        rectF.top = 400;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint paint = new Paint();
        paint.setColor(Color.parseColor("#7CD6F0"));
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(20);
        paint.setAntiAlias(true);
        canvas.drawArc(rectF,0,324,false,paint);
    }
}
