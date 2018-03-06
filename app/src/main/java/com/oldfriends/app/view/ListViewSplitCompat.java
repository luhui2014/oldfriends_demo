package com.oldfriends.app.view;

/**
 * Created by Administrator on 2016/2/23.
 */
import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ListView;
import com.oldfriends.app.fragment.FragmentSplitPay;

public class ListViewSplitCompat extends ListView
{
    private static final String TAG = "ListViewCompat";
    private SlideView mFocusedItemView;

    public ListViewSplitCompat(Context paramContext)
    {
        super(paramContext);
    }

    public ListViewSplitCompat(Context paramContext, AttributeSet paramAttributeSet)
    {
        super(paramContext, paramAttributeSet);
    }

    public ListViewSplitCompat(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
    {
        super(paramContext, paramAttributeSet, paramInt);
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN: {
                int x = (int) event.getX();
                int y = (int) event.getY();
                int position = pointToPosition(x, y);
                Log.e(TAG, "postion=" + position);
                if (position != INVALID_POSITION) {
                    FragmentSplitPay.MessageItem data = (FragmentSplitPay.MessageItem) getItemAtPosition(position);
                    mFocusedItemView = data.slideView;
//                Log.e(TAG, "FocusedItemView=" + mFocusedItemView);
                }
            }
            default:
                break;
        }

        if (mFocusedItemView != null) {
            mFocusedItemView.onRequireTouchEvent(event);
        }

        return super.onTouchEvent(event);
    }

    public void shrinkListItem(int position) {
        View item = getChildAt(position);

        if (item != null) {
            try {
                ((SlideView) item).shrink();
            } catch (ClassCastException e) {
                e.printStackTrace();
            }
        }
    }
}
