package jni.example.p2pinvest.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
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

    public void setProgress(int progress) {
        this.progress = progress;
    }


    public Home_Arc(Context context) {
        super(context);
    }

    public Home_Arc(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
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
        roundWidth = (int) typedArray.getDimension(R.styleable.RoundProgress_roundWidth,20);
        textSize = (int) typedArray.getDimension(R.styleable.RoundProgress_textSize,20);
        max = typedArray.getInteger(R.styleable.RoundProgress_max,100);
        progress = typedArray.getInteger(R.styleable.RoundProgress_progress,30);

        //TODO 回收
        typedArray.recycle();
    }

    public Home_Arc(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

    }

    //TODO 获取当前视图宽高
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        width = this.getMeasuredWidth();
    }

    @SuppressLint("DrawAllocation")
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //TODO 绘制圆环
        //TODO 圆心
        int arcX = width/2;
        int arcY = width/2;

        float radius = width / 2 - roundWidth / 2;
        paint.setColor(roundColor);//设置画笔颜色
//        paint.setColor(Color.GRAY);
        paint.setStyle(Paint.Style.STROKE);//设置圆环的样式
        paint.setStrokeWidth(roundWidth);//设置圆环的宽度
        canvas.drawCircle(arcX, arcY, radius, paint);

        RectF rectF = new RectF(roundWidth / 2, roundWidth / 2, width - roundWidth / 2, width - roundWidth / 2);
        paint.setColor(roundProgressColor);
        canvas.drawArc(rectF,0,progress *360 /max,false,paint);

        String text_progress = progress * 100 /max +"%";

        paint.setColor(textColor);
        paint.setTextSize(textSize);
        paint.setStrokeWidth(0);
        //TODO 这个矩形是用来包裹文本
        Rect rect = new Rect();
        paint.getTextBounds(text_progress,0,text_progress.length(),rect);

        // TODO 获取左下角的坐标
        int x = width/2 -rect.width()/2;
        int y = width/2 -rect.height()/2;

        canvas.drawText(text_progress,x,y,paint);
    }
}
