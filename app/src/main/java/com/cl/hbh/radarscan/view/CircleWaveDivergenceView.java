package com.cl.hbh.radarscan.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.RelativeLayout;

import com.cl.hbh.radarscan.R;

public class CircleWaveDivergenceView extends RelativeLayout{

    public Context context;
    private float offsetArgs = 0;
    private boolean isSearching = false;
    private int width;
    private int height;
    private Bitmap bitmap;
    private Bitmap bitmap1;
    private Bitmap bitmap2;

    public CircleWaveDivergenceView(Context context) {
        super(context);
        this.context = context;
        initBitmap();
    }

    public CircleWaveDivergenceView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        initBitmap();
    }

    public CircleWaveDivergenceView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.context = context;
        initBitmap();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        width = w;
        height = h;
    }

    private void initBitmap(){
        if(bitmap == null){
            bitmap = Bitmap.createBitmap(BitmapFactory.decodeResource(context.getResources(), R.mipmap.gplus_search_bg));
        }
        if(bitmap1 == null){
            bitmap1 = Bitmap.createBitmap(BitmapFactory.decodeResource(context.getResources(), R.mipmap.locus_round_click));
        }
        if(bitmap2 == null){
            bitmap2 = Bitmap.createBitmap(BitmapFactory.decodeResource(context.getResources(), R.mipmap.gplus_search_args));
        }
    }

    @SuppressLint("DrawAllocation")
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawBitmap(bitmap, width / 2 - bitmap.getWidth() / 2, height / 2 - bitmap.getHeight() / 2, null);
        if(isSearching){
            Rect rMoon = new Rect(width / 2 - bitmap2.getWidth(), height / 2, width / 2, height / 2 + bitmap2.getHeight());
            canvas.rotate(offsetArgs, width / 2, height / 2);
            canvas.drawBitmap(bitmap2,null,rMoon,null);
            offsetArgs = offsetArgs + 3;
        }else{
            canvas.drawBitmap(bitmap2,  width / 2  - bitmap2.getWidth() , height / 2, null);
        }
        canvas.drawBitmap(bitmap1,  width / 2 - bitmap1.getWidth() / 2, height / 2 - bitmap1.getHeight() / 2, null);
        if(isSearching) invalidate();
    }

    public void setSearching(boolean isSearching) {
        this.isSearching = isSearching;
        offsetArgs = 0;
        invalidate();
    }
}
