package com.example.modulecommon.ui;

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

import com.example.modulecommon.R;


//自定义进度环
public class RoundProgress extends View {
    private int roundColor;//圆环的颜色
    private int roundProgressColor ;//圆弧的颜色
    private int textColor;//文本的颜色

    private float roundWidth ;//圆环的宽度
    private float textSize ;//文本的字体大小

    private int max;//圆环的最大值
    private int progress;//圆环的进度

    private int width;//当前视图的宽度（=高度）

    private Paint paint;//画笔
    public RoundProgress(Context context) {
        this(context,null);
    }

    public RoundProgress(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public RoundProgress(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        //初始化画笔
        paint = new Paint();
        paint.setAntiAlias(true);//抗锯齿

        //获取自定义属性
        //获取typeArray的对象
      @SuppressLint("Recycle") TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.RoundProgress);

        //取出所有属性
        roundColor = typedArray.getColor(R.styleable.RoundProgress_roundColor, Color.GRAY);
        roundProgressColor = typedArray.getColor(R.styleable.RoundProgress_roundProgressColor,Color.RED);
        textColor = typedArray.getColor(R.styleable.RoundProgress_textColor,Color.GREEN);
        roundWidth = typedArray.getDimension(R.styleable.RoundProgress_roundWith,3);
        textSize = typedArray.getDimension(R.styleable.RoundProgress_textSize,20);
        max = typedArray.getInteger(R.styleable.RoundProgress_max,100);
        progress = typedArray.getInteger(R.styleable.RoundProgress_progress,30);
       // 回收处理释放资源
        typedArray.recycle();

}

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public int getProgress() {
        return progress;
    }

    public void setProgress(int progress) {
        this.progress = progress;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        width = this.getMeasuredWidth();
    }

    //canvas:画布，对应着视图在布局中的范围区间。范围的左上顶点即为坐标原点

    @SuppressLint("DrawAllocation")
    @Override
    protected void onDraw(Canvas canvas) {
        //绘制圆环 获取圆心坐标

        int cx = width/2;
        int cy = width/2;
        float radius = width/2 -roundWidth/2;
        paint.setColor(roundColor);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(roundWidth);
        canvas.drawCircle(cx,cy,radius,paint);

        //绘制圆弧
        RectF rectF = new RectF(roundWidth / 2, roundWidth / 2, width - roundWidth / 2, width - roundWidth / 2);
        paint.setColor(roundProgressColor);
        canvas.drawArc(rectF,0,progress * 360 /max,false,paint);

        //绘制文本
        String text = progress * 100 /max + "%";
        //设置paint
        paint.setColor(textColor);
        paint.setTextSize(textSize);
        paint.setStrokeWidth(0);
        Rect rect = new Rect();
        paint.getTextBounds(text,0,text.length(),rect);
        //获取坐下定点的坐标
        int x = width/2 - rect.width() /2;
        int y = width /2 +rect.height()/2;
        canvas.drawText(text,x,y,paint);
    }
}
