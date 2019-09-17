package com.example.router.viewrouter.example.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.example.router.viewrouter.example.model.Bezier;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @date: 2019-09-12
 * @autror: guojian
 * @description: 贝塞尔曲线图
 */
public class BezierView extends View {

    private List<Bezier> mData = new ArrayList<>();

    private Context mContext;

    private float mWidth, mHeight;

    private int count = 11;

    private Paint mCirclePaint, mCubicPaint, mtextPaint, mXPaint;

    private float radio = 4;

    private float margin = 12;

    public BezierView(Context context) {
        super(context);
        init(context);
    }

    public BezierView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public BezierView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        mContext = context;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (mData.isEmpty()) {
            return;
        }
        //文本画笔
        mtextPaint = new Paint();
        mtextPaint.setAntiAlias(true);
        mtextPaint.setTextSize(24);
        mtextPaint.setTextAlign(Paint.Align.CENTER);
        //水平虚线画笔
        mXPaint = new Paint();
        mXPaint.setAntiAlias(true);
        mXPaint.setStyle(Paint.Style.STROKE);
        mXPaint.setPathEffect ( new DashPathEffect( new float [ ] { 3, 2 }, 0 ) ) ;
        //圆点画笔
        mCirclePaint = new Paint();
        mCirclePaint.setAntiAlias(true);
        mCirclePaint.setStyle(Paint.Style.STROKE);
        //曲线画笔
        mCubicPaint = new Paint();
        mCubicPaint.setAntiAlias(true);
        mCubicPaint.setStyle(Paint.Style.STROKE);
        mCubicPaint.setStrokeWidth(3);
        //坐标轴空出文本宽度
        float textWidth = mtextPaint.getTextSize() + margin;

        mWidth = getWidth() - 3 * textWidth;
        mHeight = getHeight() - 2 * textWidth;

        int maxData = Collections.max(partIntegerList(mData));
        int minData = Collections.min(partIntegerList(mData));

        float base = mHeight / (maxData - minData);
        //画水平线
        for (int i = 0; i < (maxData - minData); i++) {
            canvas.drawLine(2 *textWidth, i * base + textWidth, mWidth + 2 *textWidth + margin, i * base + textWidth, mXPaint);
            //y轴文字
            canvas.drawText(((long) (mHeight - i * base - minData)) + "", textWidth, i * base + textWidth + textWidth/4, mtextPaint);
        }

        for (int i = 0; i < mData.size(); i++) {
            Bezier detail = mData.get(i);
            //点圆心
            float centerX = mWidth / count * (detail.getMonth() - 1) + radio + 2 *textWidth;
            float centerY = mHeight - base * (detail.getArticalCount() - minData) + textWidth;

            //绘制x轴数据
            canvas.drawText(detail.getMonth() + "月", centerX, mHeight + 2 * textWidth - margin, mtextPaint);
            //绘制点
            canvas.drawCircle(centerX, centerY, radio, mCirclePaint);
            //绘制数据曲线
            if (i > 0) {
                Bezier lastDetail = mData.get(i - 1);
                float lastCenterX = mWidth / count * (lastDetail.getMonth() - 1) + radio + 2 *textWidth;
                float lastCenterY = mHeight - base * (lastDetail.getArticalCount() - minData) + textWidth;

                Path path = new Path();
                path.moveTo(lastCenterX, lastCenterY);
                path.cubicTo(lastCenterX + mWidth / count / 2, lastCenterY,lastCenterX + mWidth / count / 2, centerY,
                        centerX, centerY);
                canvas.drawPath(path, mCubicPaint);
            }
        }

    }

    private List<Integer> partIntegerList(List<Bezier> list) {
        List<Integer> newlist = new ArrayList<>();
        for (Bezier detail : list) {
            newlist.add(detail.getArticalCount());
        }
        return newlist;
    }

    public void setData(List<Bezier> data) {
        mData = data;
        invalidate();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(getDefaultSize(getSuggestedMinimumWidth(), widthMeasureSpec),
                getDefaultSize(getSuggestedMinimumHeight(), heightMeasureSpec));
    }

    public static int getDefaultSize(int size, int measureSpec) {
        int result = size;
        int specMode = MeasureSpec.getMode(measureSpec);
        int specSize = MeasureSpec.getSize(measureSpec);

        switch (specMode) {
            case MeasureSpec.UNSPECIFIED:
                result = size;
                break;
            case MeasureSpec.AT_MOST:
            case MeasureSpec.EXACTLY:
                result = specSize;
                break;
        }
        return result;
    }

}
