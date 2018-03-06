package com.oldfriends.app.view;

/**
 * Created by Administrator on 2016/2/23.
 */

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import com.oldfriends.app.R;

public class CustomExportBillDialog extends Dialog {
    private ExportBillListener mCallback;
    private Context mContext;

    public CustomExportBillDialog(Context paramContext) {
        super(paramContext);
        this.mContext = paramContext;
    }

    public CustomExportBillDialog(Context paramContext, int paramInt, ExportBillListener paramExportBillListener) {
        super(paramContext, paramInt);
        this.mContext = paramContext;
        this.mCallback = paramExportBillListener;
    }

    private void initView() {
    }

    protected void onCreate(Bundle paramBundle) {
        super.onCreate(paramBundle);
        setContentView(R.layout.custom_export_bill_dialog);
        initView();
        Window localWindow = getWindow();
        WindowManager.LayoutParams localLayoutParams = localWindow.getAttributes();
        localLayoutParams.width = ((WindowManager) this.mContext.getSystemService("window")).getDefaultDisplay().getWidth();
        localWindow.setGravity(80);
        localWindow.setWindowAnimations(R.style.translateDialogStyle);
        localWindow.setAttributes(localLayoutParams);
    }

    public static abstract interface ExportBillListener {
    }
}
