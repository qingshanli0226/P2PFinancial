package com.example.administrator.p2pdemo;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class RoundView extends View {
    Paint paint;
    public RoundView(Context context) {
        super(context);
        ssh();
    }

    private void ssh() {
        paint =new Paint();
        paint.setStrokeWidth(3);
        paint.setColor(Color.BLACK);

    }

    public RoundView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        ssh();
    }

    public RoundView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
}
