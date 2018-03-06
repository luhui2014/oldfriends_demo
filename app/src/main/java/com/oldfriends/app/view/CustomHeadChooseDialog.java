package com.oldfriends.app.view;

/**
 * Created by Administrator on 2016/2/23.
 */
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.view.WindowManager.LayoutParams;
import com.oldfriends.app.R;

public class CustomHeadChooseDialog extends Dialog
{
    private UserChooseHeadListener mCallback;
    private Context mContext;

    public CustomHeadChooseDialog(Context paramContext)
    {
        super(paramContext);
        this.mContext = paramContext;
    }

    public CustomHeadChooseDialog(Context paramContext, int paramInt, UserChooseHeadListener paramUserChooseHeadListener)
    {
        super(paramContext, paramInt);
        this.mContext = paramContext;
        this.mCallback = paramUserChooseHeadListener;
    }

    private void initView()
    {
        View localView1 = findViewById(R.id.userHead_dialog_cancel);
        View.OnClickListener local1 = new View.OnClickListener()
        {
            public void onClick(View paramAnonymousView)
            {
                CustomHeadChooseDialog.this.dismiss();
            }
        };
        localView1.setOnClickListener(local1);
        View localView2 = findViewById(R.id.userHead_dialog_camera);
        View.OnClickListener local2 = new View.OnClickListener()
        {
            public void onClick(View paramAnonymousView)
            {
                CustomHeadChooseDialog.this.mCallback.cameraClick();
                CustomHeadChooseDialog.this.dismiss();
            }
        };
        localView2.setOnClickListener(local2);
        View localView3 = findViewById(R.id.userHead_dialog_photo);
        View.OnClickListener local3 = new View.OnClickListener()
        {
            public void onClick(View paramAnonymousView)
            {
                CustomHeadChooseDialog.this.mCallback.photoClick();
                CustomHeadChooseDialog.this.dismiss();
            }
        };
        localView3.setOnClickListener(local3);
    }

    protected void onCreate(Bundle paramBundle)
    {
        super.onCreate(paramBundle);
        setContentView(R.layout.custom_user_head_image_dialog_layout);
        initView();
        Window localWindow = getWindow();
        WindowManager.LayoutParams localLayoutParams = localWindow.getAttributes();
        localLayoutParams.width = ((WindowManager)this.mContext.getSystemService("window")).getDefaultDisplay().getWidth();
        localWindow.setGravity(80);
        localWindow.setWindowAnimations(R.style.translateDialogStyle);
        localWindow.setAttributes(localLayoutParams);
    }

    public static abstract interface UserChooseHeadListener
    {
        public abstract void cameraClick();

        public abstract void photoClick();
    }
}