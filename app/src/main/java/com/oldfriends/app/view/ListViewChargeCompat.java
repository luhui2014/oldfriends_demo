package com.oldfriends.app.view;

/**
 * Created by Administrator on 2016/2/23.
 */
import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ExpandableListView;
import com.oldfriends.app.fragment.FragmentCharge;
//import com.oldfriends.app.fragment.FragmentCharge.MessageItem;

public class ListViewChargeCompat extends ExpandableListView
{
    private static final String TAG = "ListViewCompat";

    private SlideView mFocusedItemView;

    public ListViewChargeCompat(Context context) {
        super(context);
    }

    public ListViewChargeCompat(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ListViewChargeCompat(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
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

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN: {
                int x = (int) event.getX();
                int y = (int) event.getY();
                int position = pointToPosition(x, y);
                if (position != INVALID_POSITION) {
                    FragmentCharge.MessageItem data = (FragmentCharge.MessageItem)getItemAtPosition(position);
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
}
