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
import android.widget.TextView;
import com.oldfriends.app.R;

public class CustomNoticeDialog extends Dialog
{
    private TextView bottomText;
    private TextView contentText;
    private Context mContext;

    public CustomNoticeDialog(Context paramContext)
    {
        super(paramContext);
    }

    public CustomNoticeDialog(Context paramContext, int paramInt)
    {
        super(paramContext, paramInt);
        this.mContext = paramContext;
    }

    private void initView()
    {
        this.contentText = ((TextView)findViewById(R.id.custom_notice_dialog_content));
        this.bottomText = ((TextView)findViewById(R.id.custom_notice_dialog_bottom));
        TextView localTextView = this.bottomText;
        View.OnClickListener local1 = new View.OnClickListener()
        {
            public void onClick(View paramAnonymousView)
            {
                CustomNoticeDialog.this.dismiss();
            }
        };
        localTextView.setOnClickListener(local1);
    }

    public TextView getBottomText()
    {
        return this.bottomText;
    }

    public TextView getContentText()
    {
        return this.contentText;
    }

    protected void onCreate(Bundle paramBundle)
    {
        super.onCreate(paramBundle);
        setContentView(R.layout.custom_notice_dialog_layout);
        initView();
        Window localWindow = getWindow();
        WindowManager.LayoutParams localLayoutParams = localWindow.getAttributes();
        localLayoutParams.width = (3 * (((WindowManager)this.mContext.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay().getWidth() / 4));
        localWindow.setAttributes(localLayoutParams);
    }
}
