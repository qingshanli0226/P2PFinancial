package jni.example.p2pinvest.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import jni.example.p2pinvest.R;

public class Home_Arc extends View {

    //TODO 设置圆环颜色
    private int roundColor;
    //TODO 设置圆弧的颜色
    private int roundProgressColor;
    //TODO 文本颜色
    private int textColor;

    //TODO 圆环的宽度
    private int roundWidth;
    //TODO 字体大小
    private int textSize;
    //TODO 圆环最大值
    private int max;
    //TODO 圆环进度
    private int progress;
    //TODO 当前视图宽度
    private int width;
    //TODO 画笔
    private Paint paint;
    private RectF rectF = new RectF();

    public Home_Arc(Context context) {
        super(context);
    }

    public Home_Arc(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public Home_Arc(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        //TODO 初始化画笔
        paint = new Paint();
        //TODO 抗锯齿
        paint.setAntiAlias(true);

        //TODO 获取自定义属性
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.RoundProgress);

        //TODO 获取所有的自定义属性
        roundColor = typedArray.getColor(R.styleable.RoundProgress_roundColor, Color.GRAY);//圆环颜色
        roundProgressColor = typedArray.getColor(R.styleable.RoundProgress_roundColor,Color.RED);//圆弧颜色
        textColor = typedArray.getColor(R.styleable.RoundProgress_textColor,Color.GREEN);//字体颜色
        roundWidth = typedArray.getDimension(R.styleable.RoundProgress_roundWidth,100);
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
