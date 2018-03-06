package com.oldfriends.app.view;

/**
 * Created by Administrator on 2016/2/23.
 */

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import com.oldfriends.app.R;

public class CustomChargeChooseDialog extends Dialog
{
    private ChargeSpendTimeChooseListener mCallback;
    private Context mContext;

    public CustomChargeChooseDialog(Context paramContext)
    {
        super(paramContext);
        this.mContext = paramContext;
    }

    public CustomChargeChooseDialog(Context paramContext, int paramInt, ChargeSpendTimeChooseListener paramChargeSpendTimeChooseListener)
    {
        super(paramContext, paramInt);
        this.mContext = paramContext;
        this.mCallback = paramChargeSpendTimeChooseListener;
    }

    private void initView()
    {
        View localView1 = findViewById(R.id.charge_timeChoose_cancel);
        View.OnClickListener local1 = new View.OnClickListener()
        {
            public void onClick(View paramAnonymousView)
            {
                CustomChargeChooseDialog.this.dismiss();
            }
        };
        localView1.setOnClickListener(local1);
        View localView2 = findViewById(R.id.charge_timeChoose_all);
        View.OnClickListener local2 = new View.OnClickListener()
        {
            public void onClick(View paramAnonymousView)
            {
                CustomChargeChooseDialog.this.mCallback.allTimeClick();
                CustomChargeChooseDialog.this.dismiss();
            }
        };
        localView2.setOnClickListener(local2);
        View localView3 = findViewById(R.id.charge_timeChoose_month);
        View.OnClickListener local3 = new View.OnClickListener()
        {
            public void onClick(View paramAnonymousView)
            {
                CustomChargeChooseDialog.this.mCallback.monthClick();
                CustomChargeChooseDialog.this.dismiss();
            }
        };
        localView3.setOnClickListener(local3);
        View localView4 = findViewById(R.id.charge_timeChoose_season);
        View.OnClickListener local4 = new View.OnClickListener()
        {
            public void onClick(View paramAnonymousView)
            {
                CustomChargeChooseDialog.this.mCallback.seasonClick();
                CustomChargeChooseDialog.this.dismiss();
            }
        };
        localView4.setOnClickListener(local4);
        View localView5 = findViewById(R.id.charge_timeChoose_year);
        View.OnClickListener local5 = new View.OnClickListener()
        {
            public void onClick(View paramAnonymousView)
            {
                CustomChargeChooseDialog.this.mCallback.yearClick();
                CustomChargeChooseDialog.this.dismiss();
            }
        };
        localView5.setOnClickListener(local5);
    }

    protected void onCreate(Bundle paramBundle)
    {
        super.onCreate(paramBundle);
        setContentView(R.layout.custom_choose_charge_timedialog_layout);
        initView();
        Window localWindow = getWindow();
        WindowManager.LayoutParams localLayoutParams = localWindow.getAttributes();
        localLayoutParams.width = ((WindowManager)this.mContext.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay().getWidth();
        localWindow.setGravity(80);
        localWindow.setWindowAnimations(R.style.translateDialogStyle);
        localWindow.setAttributes(localLayoutParams);
    }

    public static abstract interface ChargeSpendTimeChooseListener
    {
        public abstract void allTimeClick();

        public abstract void monthClick();

        public abstract void seasonClick();

        public abstract void yearClick();
    }
}
