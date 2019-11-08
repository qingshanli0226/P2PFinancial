package com.bw.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.*;
import android.util.AttributeSet;
import android.view.View;

public class RoundProgress extends View {

    //使用自定义属性来初始化如下的变量
    //圆环的颜色
    private int roundColor;
    //圆弧的颜色
    private int roundProgressColor;
    //文本的颜色
    private int textColor;
    //圆环的宽度
    private float roundWidth;
    //文本的字体大小
    private float textSize;
    //圆环的最大值
    private int max;
    //圆环的进度
    private int progress;
    //当前视图的宽度
    private int width;
    //画笔
    private Paint paint;

    public RoundProgress(Context context) {
        super(context);
    }

    public RoundProgress(Context context, AttributeSet attrs) {
        super(context, attrs);

        //初始化画笔
        paint = new Paint();
        //去除毛边
        paint.setAntiAlias(true);

        //获取自定义的属性
        //获取TypeArray的对象(调用两个参数的方法)
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.RoundProgress);

        //取出所有的自定义属性
        roundColor = typedArray.getColor(R.styleable.RoundProgress_roundColor,Color.GRAY);
        roundProgressColor = typedArray.getColor(R.styleable.RoundProgress_roundProgressColor,Color.RED);
        textColor = typedArray.getColor(R.styleable.RoundProgress_textColor,Color.GREEN);
        roundWidth = typedArray.getDimension(R.styleable.RoundProgress_roundWith,10);
        textSize = typedArray.getDimension(R.styleable.RoundProgress_textSize,20);
        max = typedArray.getInteger(R.styleable.RoundProgress_max,100);
        progress = typedArray.getInteger(R.styleable.RoundProgress_progress,30);

        //回收处理
        typedArray.recycle();

    }

    public RoundProgress(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

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

    //测量：获取当前视图宽高

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        width = this.getMeasuredWidth();
    }

    //canvas画布，对应着视图在布局中的范围区间。范围的左上顶点即为坐标原点
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //绘制圆环
        //获取圆心坐标
        int cx = width / 2;
        int cy = width / 2;
        float radius = width / 2 - roundWidth / 2;
        //设置画笔颜色
        paint.setColor(roundColor);
        //设置圆环的样式
        paint.setStyle(Paint.Style.STROKE);
        //设置圆环的宽度
        paint.setStrokeWidth(roundWidth);
        canvas.drawCircle(cx,cy,radius,paint);

        //绘制圆弧
        RectF rectF = new RectF(roundWidth / 2,roundWidth / 2,width - roundWidth / 2,width - roundWidth / 2);
        //设置画笔颜色
        paint.setColor(roundProgressColor);
        canvas.drawArc(rectF,0,progress * 360 / max,false,paint);

        //绘制文本
        String text = progress * 100 / max + "%";
        //设置paint
        paint.setColor(textColor);
        paint.setTextSize(textSize);
        paint.setStrokeWidth(0);
        //创建一个矩形，此时矩形没有具体的宽度和高度
        Rect rect = new Rect();
        //此时的矩形的宽度和高度即为整好包裹文本的矩形的宽高
        paint.getTextBounds(text,0,text.length(),rect);
        //获取左下顶点的坐标
        int x = width / 2 - rect.width() / 2;
        int y = width / 2 + rect.height() / 2;

        canvas.drawText(text,x,y,paint);

    }
}
