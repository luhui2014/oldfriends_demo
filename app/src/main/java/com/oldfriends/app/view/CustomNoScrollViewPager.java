package com.oldfriends.app.view;

/**
 * lh on 2016/2/25.
 */
import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

public class CustomNoScrollViewPager extends ViewPager
{
    private boolean scrollble = true;

    public CustomNoScrollViewPager(Context paramContext)
    {
        super(paramContext);
    }

    public CustomNoScrollViewPager(Context paramContext, AttributeSet paramAttributeSet)
    {
        super(paramContext, paramAttributeSet);
    }

    public boolean isScrollble()
    {
        return this.scrollble;
    }

    public boolean onInterceptTouchEvent(MotionEvent paramMotionEvent)
    {
        if (this.scrollble)
            return false;
        return super.onInterceptTouchEvent(paramMotionEvent);
    }

    public boolean onTouchEvent(MotionEvent paramMotionEvent)
    {
        if (this.scrollble)
            return false;
        return super.onTouchEvent(paramMotionEvent);
    }

    public void setScrollble(boolean paramBoolean)
    {
        this.scrollble = paramBoolean;
    }
}
