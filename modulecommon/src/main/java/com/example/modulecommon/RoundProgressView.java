package com.example.modulecommon;

import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.SweepGradient;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;

public class RoundProgressView extends View {
    private ValueAnimator valueAnimator;
    private int mCenterX;
    private int mCenterY;
    private int mInRadio;
    private float mRingWidth;
    private int mInColor;
    private int mRingNormalColor;
    private Paint paint;
    private int color[] = new int[3];

    private RectF mRectF;
    private int mSlectRing = 0;
    private  int mMaxValue;
    public RoundProgressView(Context context) {
        this(context, null);
    }

    public RoundProgressView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public RoundProgressView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.RoundProgressView);
        //内圆的半径
        mInRadio = a.getInteger(R.styleable.RoundProgressView_circle_radio,200);
        //圆环的宽度
        mRingWidth = a.getFloat(R.styleable.RoundProgressView_ring_width,40);
        //内圆的颜色
        mInColor = a.getColor(R.styleable.RoundProgressView_in_circle_color, Color.WHITE);
        //圆环默认颜色
        mRingNormalColor = a.getColor(R.styleable.RoundProgressView_ring_normal_color,Color.BLUE);
        //圆环彩色区域
        mSlectRing = a.getInt(R.styleable.RoundProgressView_ring_color_rgb,0);

        mMaxValue = a.getInt(R.styleable.RoundProgressView_maxValue,100);

        a.recycle();
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setAntiAlias(true);
        //需要重写onDraw就调用此
        this.setWillNotDraw(false);

        //渐变色
        color[0] = Color.parseColor("#FFD300");
        color[1] = Color.parseColor("#FF0084");
        color[2] = Color.parseColor("#16FF00");
    }
//位置
    @SuppressLint("DrawAllocation")
    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        int viewWidth = getMeasuredWidth();
        int viewHeight =getMeasuredHeight();
        mCenterX = viewWidth/2;
        mCenterY = viewHeight/2;
        //画矩形
        mRectF = new RectF((mCenterX - mInRadio - mRingWidth/2),(mCenterY - mInRadio - mRingWidth/2),(mCenterX + mInRadio + mRingWidth/2),(mCenterY + mInRadio+mRingWidth/2));
    }
    //开始画圆

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //内圆
        paint.setColor(mInColor);
        canvas.drawCircle(mCenterX,mCenterY,mInRadio,paint);
        //默认圆环
        drawNormalRing(canvas);
        drawColorRing(canvas);
    }

    private void drawNormalRing(Canvas canvas) {
        Paint normalPaint = new Paint(paint);
        normalPaint.setStyle(Paint.Style.STROKE);
        normalPaint.setStrokeWidth(mRingWidth);
        normalPaint.setColor(mRingNormalColor);
        canvas.drawArc(mRectF,360,360,false,normalPaint);
    }

    private void drawColorRing(Canvas canvas) {
        Paint ringColorPaint = new Paint(paint);
        ringColorPaint.setStyle(Paint.Style.STROKE);
        ringColorPaint.setStrokeWidth(mRingWidth);
        ringColorPaint.setShader(new SweepGradient(mCenterX, mCenterY, color, null));
        //逆时针旋转90度
        canvas.rotate(-90, mCenterX, mCenterY);
        canvas.drawArc(mRectF, 360, mSlectRing, false, ringColorPaint);
        ringColorPaint.setShader(null);
    }
    public void setValue(int value,TextView textView) {
        if (value > mMaxValue) {
            value = mMaxValue;
        }
        int start = 0;
        int end = value;
        startAnimator(start, end, 4000,textView);
    }

    private void startAnimator(int start, int end, long animTime, final TextView textView) {
        valueAnimator = ValueAnimator.ofInt(start, end);
        valueAnimator.setDuration(animTime);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int i = Integer.valueOf(String.valueOf(animation.getAnimatedValue()));
                textView.setText(i + "");
                //每个单位长度占多少度
                mSlectRing=(int) (360 * (i / 100f));
                invalidate();
            }
        });
        valueAnimator.start();
    }

}
