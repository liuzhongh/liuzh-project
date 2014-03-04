package org.news.component;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.widget.FrameLayout;

public class MoveBackgroundLayout extends FrameLayout
{
    Bitmap backgroundBitmap;
    int    backgoundWidth;
    int    backgoundHeight;
    int    screenWidth;
    int    screenHeight;
    private int startX = 0 ;
    public MoveBackgroundLayout(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        //backgroundBitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.tuitional_carousel);

        DisplayMetrics dm = new DisplayMetrics();
        //取得窗口属性
        ((Activity)context).getWindowManager().getDefaultDisplay().getMetrics(dm);

        //窗口的宽度
        screenWidth = dm.widthPixels;
        //窗口高度
        screenHeight = dm.heightPixels;

       //backgroundBitmap = Utils.scaleBitmap(backgroundBitmap,screenHeight,screenWidth*5);

        backgoundWidth = screenWidth*5;
        backgoundHeight = screenHeight;

        startX =  0;
    }

    public void setOffsetX(float offset)
    {
        startX = -(int)(offset*1.0*(backgoundWidth - screenWidth));
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas)
    {
        //Bitmap b2 = Bitmap.createBitmap(backgroundBitmap, startX, 0, screenWidth, backgoundHeight);
        //canvas.drawBitmap(backgroundBitmap, startX, 0, null);
        super.onDraw(canvas);
        //b2.recycle();
    }
}
