package vip.dengwj.custom_control.customView.watchface;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import vip.dengwj.custom_control.R;

/**
 * 步骤：
 * 1、继承自 View
 * 2、定义相关属性，获取相关属性
 * 3、测量自己
 * 4、创建相关画笔
 * 5、覆写 onDraw 方法，绘制相关内容
 *
 * 可以做图表📈，折线图，柱状图📊
 */
public class WatchFace extends View {
    private int secondColor;
    private int minColor;
    private int hourColor;
    private int scaleColor;
    private int faceBackground;
    private boolean scaleShow;
    private Paint secondPaint;
    private Paint minPaint;
    private Paint hourPaint;
    private Paint scalePaint;

    public WatchFace(Context context) {
        this(context, null);
    }

    public WatchFace(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public WatchFace(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        // 获取属性
        initAttrs(context, attrs);
        // 初始化画笔
        initPaints();
    }

    /**
     * 创建画笔
     */
    private void initPaints() {
        // 秒针
        secondPaint = createPaint(secondColor, 40f);
        // 分针
        minPaint = createPaint(minColor, 3f);
        // 时针
        hourPaint = createPaint(hourColor, 4f);
        scalePaint = createPaint(scaleColor, 3f);
    }

    private Paint createPaint(int color, float strokeWidth) {
        Paint paint = new Paint();
        paint.setColor(color);
        paint.setStrokeWidth(strokeWidth);
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.STROKE);
        paint.setTextSize(30f);
        // paint.setStrokeCap(Paint.Cap.ROUND);
        return paint;
    }

    private void initAttrs(Context context, AttributeSet attrs) {
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.WatchFace);
        secondColor = a.getColor(R.styleable.WatchFace_secondColor, getResources().getColor(R.color.secondDefaultColor));
        minColor = a.getColor(R.styleable.WatchFace_minColor, getResources().getColor(R.color.minDefaultColor));
        hourColor = a.getColor(R.styleable.WatchFace_hourColor, getResources().getColor(R.color.hourDefaultColor));
        scaleColor = a.getColor(R.styleable.WatchFace_scaleColor, getResources().getColor(R.color.scaleDefaultColor));
        faceBackground = a.getResourceId(R.styleable.WatchFace_faceBackground, -1);
        scaleShow = a.getBoolean(R.styleable.WatchFace_scaleShow, true);
        a.recycle();
    }

    // 测量
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(widthMeasureSpec);
        // 谁小用谁
        int size = Math.min(widthSize, heightSize);
        // 测量自己
        setMeasuredDimension(size, size);
    }

    @Override
    protected void onDraw(@NonNull Canvas canvas) {
        canvas.drawColor(Color.parseColor("#000000"));
        int measuredWidth = getMeasuredWidth();
        int measuredHeight = getMeasuredHeight();
        // 柱状图
        canvas.drawLine(100, measuredHeight, 100, 500, secondPaint);
        canvas.drawLine(200, measuredHeight, 200, 600, secondPaint);
        canvas.drawLine(300, measuredHeight, 300, 800, secondPaint);
        canvas.drawLine(400, measuredHeight, 400, 200, secondPaint);
        canvas.drawText("你好", 70, 490, scalePaint);
        // 折线图
        canvas.drawLine(500, 10, 600, 30, minPaint);
        canvas.drawLine(600, 30, 700, 80, minPaint);
        canvas.drawLine(700, 80, 800, 200, minPaint);
        canvas.drawLine(800, 200, 900, 0, minPaint);
    }
}
