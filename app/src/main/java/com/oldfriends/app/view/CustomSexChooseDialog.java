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

public class CustomSexChooseDialog extends Dialog
{
    private SexClickListener mCallback;
    private Context mContext;

    public CustomSexChooseDialog(Context paramContext)
    {
        super(paramContext);
        this.mContext = paramContext;
    }

    public CustomSexChooseDialog(Context paramContext, int paramInt, SexClickListener paramSexClickListener)
    {
        super(paramContext, paramInt);
        this.mContext = paramContext;
        this.mCallback = paramSexClickListener;
    }

    private void initView()
    {
        View localView1 = findViewById(R.id.choose_sex_man);
        View.OnClickListener local1 = new View.OnClickListener()
        {
            public void onClick(View paramAnonymousView)
            {
                CustomSexChooseDialog.this.mCallback.onManClick();
            }
        };
        localView1.setOnClickListener(local1);
        View localView2 = findViewById(R.id.choose_sex_woman);
        View.OnClickListener local2 = new View.OnClickListener()
        {
            public void onClick(View paramAnonymousView)
            {
                CustomSexChooseDialog.this.mCallback.onWomanClick();
            }
        };
        localView2.setOnClickListener(local2);
        View localView3 = findViewById(R.id.choose_sex_cancel);
        View.OnClickListener local3 = new View.OnClickListener()
        {
            public void onClick(View paramAnonymousView)
            {
                CustomSexChooseDialog.this.dismiss();
            }
        };
        localView3.setOnClickListener(local3);
    }

    protected void onCreate(Bundle paramBundle)
    {
        super.onCreate(paramBundle);
        setContentView(R.layout.custom_user_sex_dialog);
        initView();
        Window localWindow = getWindow();
        WindowManager.LayoutParams localLayoutParams = localWindow.getAttributes();
        localLayoutParams.width = ((WindowManager)this.mContext.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay().getWidth();
        localWindow.setGravity(80);
        localWindow.setWindowAnimations(R.style.translateDialogStyle);
        localWindow.setAttributes(localLayoutParams);
    }

    public static abstract interface SexClickListener
    {
        public abstract void onManClick();

        public abstract void onWomanClick();
    }
}
