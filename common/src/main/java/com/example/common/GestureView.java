package com.example.common;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Region;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class GestureView extends View {

    private int mHeight;
    private int mWidth;
    int centerX;
    int centerY;
    int currentDraw;

    public GestureView(Context context) {
        this(context, null);
    }

    public GestureView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public GestureView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        mHeight = getMeasuredHeight();
        mWidth = getMeasuredWidth();
        centerX = mWidth / 2;
        centerY = mHeight / 2;
    }

    private int radius = 25;
    private int margin = 175;
    private List<RectF> rectFList = new ArrayList<>();
    Path path = new Path();
    float x;
    float y;
    float lineX;
    float lineY;

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint paint = new Paint();
        paint.setDither(true);
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.WHITE);

        Paint paint1 = new Paint();
        paint1.setDither(true);
        paint1.setAntiAlias(true);
        paint1.setStyle(Paint.Style.STROKE);
        paint1.setColor(Color.RED);
        paint1.setStrokeWidth(15);

        canvas.drawCircle(centerX, centerY, radius, paint);
        canvas.drawCircle(centerX - margin, centerY, radius, paint);
        canvas.drawCircle(centerX + margin, centerY, radius, paint);
        canvas.drawCircle(centerX, centerY - margin, radius, paint);
        canvas.drawCircle(centerX, centerY + margin, radius, paint);
        canvas.drawCircle(centerX - margin, centerY + margin, radius, paint);
        canvas.drawCircle(centerX + margin, centerY - margin, radius, paint);
        canvas.drawCircle(centerX + margin, centerY + margin, radius, paint);
        canvas.drawCircle(centerX - margin, centerY - margin, radius, paint);

        int left = centerX - 30;
        int top = centerY - 30;
        int right = centerY + 30;
        int bottom = centerX + 30;

        RectF centerRectF = new RectF(left, top, right, bottom);
        RectF leftRectF = new RectF(left - margin, top, right - margin, bottom);
        RectF rightRectF = new RectF(left + margin, top, right + margin, bottom);
        RectF topRectF = new RectF(left, top + margin, right, bottom + margin);
        RectF bottomRectF = new RectF(left, top - margin, right, bottom - margin);
        RectF leftTopRectF = new RectF(left - margin, top + margin, right - margin, bottom + margin);
        RectF leftBottomRectF = new RectF(left - margin, top - margin, right - margin, bottom - margin);
        RectF rightTopRectF = new RectF(left + margin, top - margin, right + margin, bottom - margin);
        RectF rightBottomRectF = new RectF(left + margin, top + margin, right + margin, bottom + margin);

        if (currentDraw == 1) {
            rectFList.add(centerRectF);
        } else if (currentDraw == 2) {
            rectFList.add(leftRectF);
        } else if (currentDraw == 3) {
            rectFList.add(rightRectF);
        } else if (currentDraw == 4) {
            rectFList.add(topRectF);
        } else if (currentDraw == 5) {
            rectFList.add(bottomRectF);
        } else if (currentDraw == 6) {
            rectFList.add(leftTopRectF);
        } else if (currentDraw == 7) {
            rectFList.add(leftBottomRectF);
        } else if (currentDraw == 8) {
            rectFList.add(rightTopRectF);
        } else if (currentDraw == 9) {
            rectFList.add(rightBottomRectF);
        }

        canvas.drawPath(path, paint1);
        for (RectF rectF : rectFList) {
            canvas.drawArc(rectF, 0, 360, false, paint1);
        }
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {

        int left = centerX - 25;
        int top = centerY - 25;
        int right = centerY + 25;
        int bottom = centerX + 25;

        x = getX();
        y = getY();

        List<Rect> rectFList = new ArrayList<>();
        Rect rectF = new Rect(left - margin, top - margin, right + margin, bottom + margin);
        Region region = new Region(rectF);
        boolean contains = region.contains((int) x, (int) y);

        Rect centerRectF = new Rect(left, top, right, bottom);
        Rect leftRectF = new Rect(left - margin, top, right - margin, bottom);
        Rect rightRectF = new Rect(left + margin, top, right + margin, bottom);
        Rect topRectF = new Rect(left, top + margin, right, bottom + margin);
        Rect bottomRectF = new Rect(left, top - margin, right, bottom - margin);
        Rect leftTopRectF = new Rect(left - margin, top + margin, right - margin, bottom + margin);
        Rect leftBottomRectF = new Rect(left - margin, top - margin, right - margin, bottom - margin);
        Rect rightTopRectF = new Rect(left + margin, top - margin, right + margin, bottom - margin);
        Rect rightBottomRectF = new Rect(left + margin, top + margin, right + margin, bottom + margin);

//        rectFList.add(rectF);
        rectFList.add(centerRectF);
        rectFList.add(leftRectF);
        rectFList.add(rightRectF);
        rectFList.add(topRectF);
        rectFList.add(bottomRectF);
        rectFList.add(leftTopRectF);
        rectFList.add(leftBottomRectF);
        rectFList.add(rightTopRectF);
        rectFList.add(rightBottomRectF);


//        Region centerRegion = new Region(centerRectF);
//        Region leftRegion = new Region(leftRectF);
//        Region rightRegion = new Region(rightRectF);
//        Region topRegion = new Region(topRectF);
//        Region bottomRegion = new Region(bottomRectF);
//        Region leftTopRegion = new Region(leftTopRectF);
//        Region leftBottomRegion = new Region(leftBottomRectF);
//        Region rightTopRegion = new Region(rightTopRectF);
//        Region rightBottomRegion = new Region(rightBottomRectF);


//        boolean center = centerRegion.contains((int) x, (int) y);
//        boolean leftContainns = leftRegion.contains((int) x, (int) y);
//        boolean rightContainns = rightRegion.contains((int) x, (int) y);
//        boolean topContainns = topRegion.contains((int) x, (int) y);
//        boolean bottomContainns = bottomRegion.contains((int) x, (int) y);
//        boolean leftTop = leftTopRegion.contains((int) x, (int) y);
//        boolean leftBottom = leftBottomRegion.contains((int) x, (int) y);
//        boolean rightTop = rightTopRegion.contains((int) x, (int) y);
//        boolean rightBottom = rightBottomRegion.contains((int) x, (int) y);

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
//                if (contains) {

        for (int i = 0; i < rectFList.size(); i++) {
            Rect rect = rectFList.get(i);
            Region region1 = new Region(rect);
            x = getX();
            y = getY();
            boolean contains1 = region1.contains((int) x, (int) y);
            Log.e("####", ""+contains1);
        }
//
//                }
            case MotionEvent.ACTION_MOVE:

//                if (center) {
//                    currentDraw = 1;
//                    invalidate();
//                } else if (leftBottom) {
//                    currentDraw = 2;
//                    invalidate();
//                } else if (leftContainns) {
//                    currentDraw = 3;
//                    invalidate();
//                } else if (rightContainns) {
//                    currentDraw = 4;
//                    invalidate();
//                } else if (topContainns) {
//                    currentDraw = 5;
//                    invalidate();
//                } else if (bottomContainns) {
//                    currentDraw = 6;
//                    invalidate();
//                } else if (leftTop) {
//                    currentDraw = 7;
//                    invalidate();
//                } else if (rightTop) {
//                    currentDraw = 8;
//                    invalidate();
//                } else if (rightBottom) {
//                    currentDraw = 9;
//                    invalidate();
//                }
                break;
            case MotionEvent.ACTION_UP:
                break;
        }
        return true;
    }
}
