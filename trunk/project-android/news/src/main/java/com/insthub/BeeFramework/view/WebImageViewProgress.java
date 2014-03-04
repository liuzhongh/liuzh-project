package com.insthub.BeeFramework.view;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.animation.*;
import com.external.activeandroid.util.Log;

import org.news.R;

public class WebImageViewProgress extends WebImageView
{
    private boolean mHasAnimation;
    private int mDuration;
    Bitmap bitMap;



    public WebImageViewProgress(Context context) {
        super(context);
    }

    public WebImageViewProgress(Context context, AttributeSet attSet) {
        super(context, attSet);

        mDuration = 4000;

        Resources rsrc = this.getResources();

        bitMap = BitmapFactory.decodeResource(rsrc, R.drawable.progress);



    }

    public WebImageViewProgress(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

        mDuration = 4000;
        Resources rsrc = this.getResources();

        bitMap = BitmapFactory.decodeResource(rsrc,R.drawable.progress);


    }

    public void setImageWithURL(Context context, String urlString, Drawable placeholderDrawable, int diskCacheTimeoutInSeconds)
    {
        if (urlString != null )
        {
            if (null != this.urlString && urlString.compareTo(this.urlString) == 0)
            {
                return;
            }
            mHasAnimation = true;
        }
        super.setImageWithURL(context, urlString, placeholderDrawable, diskCacheTimeoutInSeconds);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);


        if (bitMap != null) {
            // Translate canvas so a indeterminate circular progress bar with padding
            // rotates properly in its animation
            canvas.save();

            long time = getDrawingTime();
            int angle = (int) (360*(time%mDuration)/mDuration);
            if (mHasAnimation) {

                int canvasWidth = getWidth();
                int canvasHeight = getHeight();

               canvas.rotate(angle,canvasWidth/2,canvasHeight/2);

                try {
                    int width = bitMap.getWidth();
                    int height = bitMap.getHeight();

                    canvas.drawBitmap(bitMap, (float)((1.0 * (canvasWidth - width)) / 2), (float)((1.0 *(canvasHeight- height))/2),null);
                } finally {

                }
                postInvalidateOnAnimation();
            }

            canvas.restore();
        }
    }
    public void setImageBitmap(Bitmap bm)
    {
        super.setImageBitmap(bm);
        mHasAnimation = false;
    }


}
