package com.dbo.test;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Administrator on 2016/5/6.
 */
public class MyView extends View {

    private int textColor;
    private float textSize;

    private int unProgressColor;
    private int progressColor;

    private Paint circlePaint ;
    private Paint arcPaint ;
    private Paint textPaint ;

    private float arcWidth;

    private RectF oval =new RectF(0,0,0,0);

    private int currentProgress = 0;
    private int maxProgress = 100;

    private String currentDrawText;






    public MyView(Context context) {
        this(context, null);
    }

    public MyView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.MyView);
        textColor = a.getColor(R.styleable.MyView_textColor, Color.parseColor("#3498DB"));
        float size = a.getDimension(R.styleable.MyView_textSize, 14f);
        textSize = sp2px(context, size);

        unProgressColor = a.getColor(R.styleable.MyView_unProgressColor, Color.parseColor("#113498DB"));
        progressColor = a.getColor(R.styleable.MyView_progressColor, Color.parseColor("#3498DB"));

        currentProgress = a.getInt(R.styleable.MyView_currentProgress, 0);
        maxProgress = a.getInt(R.styleable.MyView_maxProgress, 100);

        arcWidth = dip2px(context,a.getDimension(R.styleable.MyView_progressWidth,4));

        circlePaint = new Paint();
        circlePaint.setAntiAlias(true);// 是否抗锯齿
//        Paint.Style.FILL    :填充内部   Paint.Style.STROKE  ：仅描边   Paint.Style.FILL_AND_STROKE  ：填充内部和描边
        circlePaint.setStyle(Paint.Style.STROKE);//设置填充样式,
        circlePaint.setStrokeWidth(arcWidth);//设置画笔宽度

        arcPaint = new Paint();
        arcPaint.setAntiAlias(true);
        arcPaint.setStyle(Paint.Style.STROKE);
        arcPaint.setStrokeWidth(arcWidth);
        arcPaint.setColor(progressColor);


        textPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        textPaint.setColor(textColor);
        textPaint.setTextSize(textSize);

        a.recycle();
    }




    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(measureWidth(widthMeasureSpec), measureHeight(heightMeasureSpec));
    }


    private int measureWidth(int widthMeasureSpec) {
        int result ;
        int mode = MeasureSpec.getMode(widthMeasureSpec);
        int size = MeasureSpec.getSize(widthMeasureSpec);

        int padding = getPaddingTop()+getPaddingBottom();
        if (mode == MeasureSpec.EXACTLY) {
            result = size;
        } else {
            result = getSuggestedMinimumWidth();
            result += padding;

            if (mode == MeasureSpec.AT_MOST) { //wrap_content
                result = Math.max(result, size); //
            }


        }

        return result;
    }
    private int measureHeight(int heightMeasureSpec) {
        return 0;
    }


    @Override
    protected int getSuggestedMinimumWidth() {

        return (int) dip2px(getContext(), 100);
    }

    @Override
    protected int getSuggestedMinimumHeight() {
        return (int) dip2px(getContext(), 100);
    }

    @Override
    protected void onDraw(Canvas canvas) {






    }


    public int getCurrentProgress() {
        return currentProgress;
    }

    public void setCurrentProgress(int currentProgress) {
        this.currentProgress = currentProgress;
        invalidate();

    }


    // dp2px
    public static float dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (dpValue * scale + 0.5f);
    }

    // sp2px
    public static float sp2px(Context context, float spValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (spValue * fontScale + 0.5f);
    }


}
