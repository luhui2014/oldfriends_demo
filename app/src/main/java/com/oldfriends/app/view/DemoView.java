package com.oldfriends.app.view;

/**
 * Created by Administrator on 2016/2/23.
 */
import android.content.Context;
import android.util.AttributeSet;
import com.oldfriends.app.org.xclcharts.common.DensityUtil;
import com.oldfriends.app.org.xclcharts.view.ChartView;

public class DemoView extends ChartView
{
    public DemoView(Context paramContext)
    {
        super(paramContext);
    }

    public DemoView(Context paramContext, AttributeSet paramAttributeSet)
    {
        super(paramContext, paramAttributeSet);
    }

    public DemoView(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
    {
        super(paramContext, paramAttributeSet, paramInt);
    }

    protected int[] getBarLnDefaultSpadding()
    {
        int[] arrayOfInt = new int[4];
        arrayOfInt[0] = DensityUtil.dip2px(getContext(), 40.0F);
        arrayOfInt[1] = DensityUtil.dip2px(getContext(), 60.0F);
        arrayOfInt[2] = DensityUtil.dip2px(getContext(), 20.0F);
        arrayOfInt[3] = DensityUtil.dip2px(getContext(), 40.0F);
        return arrayOfInt;
    }

    protected int[] getPieDefaultSpadding()
    {
        int[] arrayOfInt = new int[4];
        arrayOfInt[0] = DensityUtil.dip2px(getContext(), 20.0F);
        arrayOfInt[1] = DensityUtil.dip2px(getContext(), 65.0F);
        arrayOfInt[2] = DensityUtil.dip2px(getContext(), 20.0F);
        arrayOfInt[3] = DensityUtil.dip2px(getContext(), 20.0F);
        return arrayOfInt;
    }

    protected void onSizeChanged(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
    {
        super.onSizeChanged(paramInt1, paramInt2, paramInt3, paramInt4);
    }
}
