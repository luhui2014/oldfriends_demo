package com.oldfriends.app.view;

/**
 * lh on 2016/2/25.
 */

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import com.oldfriends.app.R;
import com.oldfriends.app.view.wheel.WheelView;
import com.oldfriends.app.view.wheel.adapters.ArrayWheelAdapter;

public class CustomWheelDialog extends Dialog
{
    private WheelDialogOnclickListener mCallback;
    private Context mContext;
    private String[] mPercentDates = new String[100];
    private TextView titleContent;

    public CustomWheelDialog(Context paramContext)
    {
        super(paramContext);
    }

    public CustomWheelDialog(Context paramContext, int paramInt, WheelDialogOnclickListener paramWheelDialogOnclickListener)
    {
        super(paramContext, paramInt);
        this.mContext = paramContext;
        this.mCallback = paramWheelDialogOnclickListener;
    }

    private void initView()
    {
        this.titleContent = ((TextView)findViewById(R.id.wheel_dialog_title));
        for (int i = 0; i < this.mPercentDates.length; i++)
        {
            String[] arrayOfString = this.mPercentDates;
            int j = i;
            arrayOfString[j] = (1 + i + "");
        }
        final WheelView localWheelView = (WheelView)findViewById(R.id.id_percent);
        ArrayWheelAdapter localArrayWheelAdapter = new ArrayWheelAdapter(this.mContext, this.mPercentDates);
        localWheelView.setViewAdapter(localArrayWheelAdapter);
        localWheelView.setWheelForeground(R.drawable.wheel_bg);
        localWheelView.setVisibleItems(7);
        View localView = findViewById(R.id.btn_confirm);
        View.OnClickListener local1 = new View.OnClickListener()
        {
            public void onClick(View paramAnonymousView)
            {
                int i = localWheelView.getCurrentItem();
                String str = CustomWheelDialog.this.mPercentDates[i];
                Log.d("CustomWheelDialog", "WheelView currentItem=" + i + ",result=" + str);
                CustomWheelDialog.this.mCallback.onSubmit(str);
                CustomWheelDialog.this.dismiss();
            }
        };
        localView.setOnClickListener(local1);
    }

    public TextView getTitleContent()
    {
        return this.titleContent;
    }

    protected void onCreate(Bundle paramBundle)
    {
        super.onCreate(paramBundle);
        setContentView(R.layout.custom_wheel_layout);
        initView();
        Window localWindow = getWindow();
        WindowManager.LayoutParams localLayoutParams = localWindow.getAttributes();
        DisplayMetrics localDisplayMetrics = new DisplayMetrics();
        getWindow().getWindowManager().getDefaultDisplay().getMetrics(localDisplayMetrics);
        localLayoutParams.width = localDisplayMetrics.widthPixels;
        localWindow.setGravity(80);
        localWindow.setWindowAnimations(R.style.translateDialogStyle);
        localWindow.setAttributes(localLayoutParams);
    }

    public static abstract interface WheelDialogOnclickListener
    {
        public abstract void onSubmit(String paramString);
    }
}
