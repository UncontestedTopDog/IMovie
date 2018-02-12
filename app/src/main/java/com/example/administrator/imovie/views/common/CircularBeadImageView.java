package com.example.administrator.imovie.views.common;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PaintFlagsDrawFilter;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;

import com.example.administrator.imovie.R;

/**
 * @author yuqirong
 */

public class CircularBeadImageView extends ImageView {

    private Path mPath;
    private RectF mRectF;
    private float rid ;
    private float[] rids = new float[8];
    private PaintFlagsDrawFilter paintFlagsDrawFilter;

    public CircularBeadImageView(Context context) {
        this(context, null);
    }

    public CircularBeadImageView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CircularBeadImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray attributes = context.obtainStyledAttributes(attrs, R.styleable.CircularBeadImageView, defStyleAttr, 0);
        rid = attributes.getFloat(R.styleable.CircularBeadImageView_rid, 10);
        rids[0] = rid;
        rids[1] = rid;
        rids[2] = rid;
        rids[3] = rid;
        rids[4] = rid;
        rids[5] = rid;
        rids[6] = rid;
        rids[7] = rid;
        mPath = new Path();
        paintFlagsDrawFilter = new PaintFlagsDrawFilter(0, Paint.ANTI_ALIAS_FLAG | Paint.FILTER_BITMAP_FLAG);
        setLayerType(View.LAYER_TYPE_HARDWARE, null);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        mPath.reset();
        mPath.addRoundRect(mRectF, rids, Path.Direction.CW);
        canvas.setDrawFilter(paintFlagsDrawFilter);
        canvas.save();
        canvas.clipPath(mPath);
        super.onDraw(canvas);
        canvas.restore();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mRectF = new RectF(0, 0, w, h);
    }

}
