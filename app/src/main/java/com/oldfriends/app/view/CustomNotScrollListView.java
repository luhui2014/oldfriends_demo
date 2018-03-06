package com.oldfriends.app.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;

/**
 * lh on 2015/4/10.
 */
public class CustomNotScrollListView extends ListView{

    public CustomNotScrollListView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public CustomNotScrollListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomNotScrollListView(Context context) {
        super(context);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }
}
