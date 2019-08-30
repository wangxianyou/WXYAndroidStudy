package com.wxy.wxyandroidstudy.lowerlevel.thefirstpass.test2_view.custom;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.SweepGradient;
import android.util.AttributeSet;
import android.view.View;

import com.wxy.wxyandroidstudy.R;

import java.util.Calendar;

import androidx.annotation.Nullable;

public class MiClockView extends View {
    private Rect mTextR = new Rect();
    private RectF mOutArcR = new RectF();
    private RectF mTickmarkR = new RectF();
    private RectF mCircleRect = new RectF();
    private Paint mTextP, mCircleP, mLinePaint, mCirclePaint,mSecondPaint,mMinutePaint,mHourPaint;
    private int CircleStrokeWidth = 4;
    private int defautSize = 50;
    private Canvas mCanvas;
    private float tickmark, tickmarkWith;//刻度线长度
    private int mLightColor, mDarkColor;
    private SweepGradient sweepGradient;
    private Matrix mGradientMatrix = new Matrix();
    private Path secondPath = new Path();
    private Path minutePath = new Path();
    private Path hourPath = new Path();

    public MiClockView(Context context) {
        this(context, null);
    }

    public MiClockView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MiClockView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.ClockView);
        mLightColor = ta.getColor(R.styleable.ClockView_clock_lightColor, Color.parseColor("#ffffff"));
        mDarkColor = ta.getColor(R.styleable.ClockView_clock_darkColor, Color.parseColor("#80ffffff"));
        ta.recycle();
        init();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mCanvas = canvas;
        drawOutSideArc();
        getCurrentTime();
        drawTickmark();
        drawSecongPath();
        drawMinutePath();
        drawHourPath();
        invalidate();
    }

    public void init() {
        mTextP = new Paint();
        mTextP.setTextAlign(Paint.Align.CENTER);
        mTextP.setColor(Color.BLUE);
        mTextP.setTextSize(defautSize);
        mCircleP = new Paint(Paint.ANTI_ALIAS_FLAG);
        mCircleP.setStrokeWidth(CircleStrokeWidth);
        mCircleP.setStyle(Paint.Style.STROKE);
        mCircleP.setColor(Color.BLUE);
        //刻度线paint
        mLinePaint = new Paint();
        mLinePaint.setColor(Color.BLACK);
        mLinePaint.setStrokeWidth(tickmarkWith);
        //内环
        mCirclePaint = new Paint();
        mCirclePaint.setColor(Color.GREEN);
        mCirclePaint.setStyle(Paint.Style.STROKE);
        //秒针
        mSecondPaint = new Paint();
        mSecondPaint.setColor(mLightColor);
        //分针
        mMinutePaint = new Paint();
        mMinutePaint.setColor(mLightColor);
        //时针
        mHourPaint = new Paint();
        mHourPaint.setColor(mDarkColor);

    }

    private float secondDegree, minuteDegree, hourDegree;

    public void getCurrentTime() {
        Calendar calendar = Calendar.getInstance();
        float milliscond = calendar.get(Calendar.MILLISECOND);
        float second = calendar.get(Calendar.SECOND) + milliscond / 1000;
        float minute = calendar.get(Calendar.MINUTE) + second / 60;
        float hour = calendar.get(Calendar.HOUR) + minute / 60;
        secondDegree = second / 60 * 360;
        minuteDegree = minute / 60 * 360;
        hourDegree = hour / 12 * 360;
    }

    public void drawOutSideArc() {
        String[] str = {"12", "3", "6", "9"};
        mTextP.getTextBounds(str[0], 0, str[0].length(), mTextR);
        mOutArcR.set(mTextR.width() / 2 + CircleStrokeWidth / 2,
                mTextR.height() / 2 + CircleStrokeWidth / 2,
                getWidth() - mTextR.width() / 2 - CircleStrokeWidth / 2,
                getHeight() - mTextR.height() / 2 - CircleStrokeWidth / 2);
        mCanvas.drawText(str[0], getWidth() / 2, mTextR.height(), mTextP);
        mCanvas.drawText(str[1], getWidth() - mTextR.width() / 2, getHeight() / 2 + mTextR.height() / 2, mTextP);
        mCanvas.drawText(str[2], getWidth() / 2, getHeight(), mTextP);
        mCanvas.drawText(str[3], mTextR.width() / 2, getHeight() / 2 + mTextR.height() / 2, mTextP);
        for (int i = 0; i < 4; i++) {
            mCanvas.drawArc(mOutArcR, 5 + 90 * i, 80, false, mCircleP);
        }

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        tickmark = w / 2 * 0.12f;
        tickmarkWith = tickmark * 0.1f;

        mCirclePaint.setStrokeWidth(tickmark);
        sweepGradient = new SweepGradient(w / 2, h / 2, new int[]{mDarkColor, mLightColor}, new float[]{0.9f, 1f});
    }

    /**
     * 绘制外围的数字和四段圆弧
     */
    public void drawTickmark() {
//        mCanvas.translate(0, mCanvasTranslateY);
        mTickmarkR.set(mTextR.width() + 1.5f * tickmark, mTextR.width() + 1.5f * tickmark, getWidth() - mTextR.width
                        () - 1.5f * tickmark,
                getWidth() - mTextR.width() - 1.5f * tickmark);
        mGradientMatrix.setRotate(secondDegree-90, getWidth() / 2, getHeight() / 2);
        sweepGradient.setLocalMatrix(mGradientMatrix);
        mCirclePaint.setShader(sweepGradient);
        mCanvas.drawArc(mTickmarkR, 0, 360, false, mCirclePaint);
        for (int i = 0; i < 200; i++) {
            mCanvas.drawLine(getWidth() / 2, mTextR.width() + tickmark, getWidth() / 2, mTextR.width() + 2 *
                    tickmark, mLinePaint);
            mCanvas.rotate(1.8f, getWidth() / 2, getHeight() / 2);
        }
    }

    /**
     * 绘制秒针
     */
    public void drawSecongPath(){
        mCanvas.save();
        mCanvas.rotate(secondDegree,getWidth()/2,getHeight()/2);
        secondPath.reset();
        secondPath.moveTo(getWidth()/2,mTextR.width() + 2.1f*tickmark);
        secondPath.lineTo(getWidth()/2+0.5f*tickmark,mTextR.width() + 2.9f*tickmark);
        secondPath.lineTo(getWidth()/2-0.5f*tickmark,mTextR.width() + 2.9f*tickmark);
        secondPath.close();
        mCanvas.drawPath(secondPath,mSecondPaint);
        mCanvas.restore();
    }

    /**
     * 绘制分针
     */
    public void drawMinutePath(){
        mCanvas.save();
        mCanvas.rotate(minuteDegree,getWidth()/2,getHeight()/2);
        minutePath.reset();
        minutePath.moveTo(getWidth()/2-0.2f*tickmark,getHeight()/2-0.35f*tickmark);
        minutePath.lineTo(getWidth()/2-0.08f*tickmark,mTextR.width() + 3.1f * tickmark);
        minutePath.quadTo(getWidth()/2,mTextR.width() + 2.9f * tickmark,
                getWidth()/2+0.08f*tickmark,mTextR.width() + 3.1f * tickmark);
        minutePath.lineTo(getWidth()/2+0.2f*tickmark,getHeight()/2-0.35f*tickmark);
        minutePath.close();
        mMinutePaint.setStyle(Paint.Style.FILL);
        mCanvas.drawPath(minutePath,mMinutePaint);
        mCircleRect.set(getWidth()/2-0.5f*tickmark,getHeight()/2-0.5f*tickmark,
                getWidth()/2+0.5f*tickmark,getHeight()/2+0.5f*tickmark);
        mMinutePaint.setStyle(Paint.Style.STROKE);
        mMinutePaint.setStrokeWidth(0.3f*tickmark);
        mCanvas.drawArc(mCircleRect,0,360,false,mMinutePaint);

        mCanvas.restore();
    }
    /**
     * 绘制时针
     */
    public void drawHourPath(){
        mCanvas.save();
        mCanvas.rotate(hourDegree,getWidth()/2,getHeight()/2);
        hourPath.reset();
        hourPath.moveTo(getWidth()/2-0.3f*tickmark,getHeight()/2-0.35f*tickmark);
        hourPath.lineTo(getWidth()/2-0.1f*tickmark,mTextR.width() + 4f * tickmark);
        hourPath.quadTo(getWidth()/2,mTextR.width() + 3.7f * tickmark,
                getWidth()/2+0.1f*tickmark,mTextR.width() + 4f * tickmark);
        hourPath.lineTo(getWidth()/2+0.3f*tickmark,getHeight()/2-0.35f*tickmark);
        hourPath.close();
        mHourPaint.setStyle(Paint.Style.FILL);
        mCanvas.drawPath(hourPath,mHourPaint);
        mCanvas.restore();
    }
}
