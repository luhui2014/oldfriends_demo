package com.oldfriends.app.activity;

/**
 * lh on 2016/2/24.
 */
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.oldfriends.app.R;
import com.oldfriends.app.util.Utility;

public class QRcodeActivity extends BaseActivity
{
    public static final String INTENT_QRCODE_STATE = "INTENT_QRCODE_STATE";
    private int qrcodeState;

    private void initTop()
    {
        RelativeLayout localRelativeLayout = (RelativeLayout)findViewById(R.id.qr_code_top_layout);
        View localView = localRelativeLayout.findViewById(R.id.common_backFont_left_layout);
        View.OnClickListener local1 = new View.OnClickListener()
        {
            public void onClick(View paramAnonymousView)
            {
                Utility.finishActivityTranslate(QRcodeActivity.this);
            }
        };
        localView.setOnClickListener(local1);
        TextView localTextView = (TextView)localRelativeLayout.findViewById(R.id.common_backFont_title);
        if (this.qrcodeState == 1) {
            localTextView.setText("好友二维码");
        }else if (this.qrcodeState == 2) {
            localTextView.setText("群账二维码");
        }else {
            localTextView.setText("我的二维码");
        }
        localRelativeLayout.findViewById(R.id.common_backFont_finish).setVisibility(View.GONE);
    }

    protected int getLayoutResId()
    {
        return R.layout.activity_qr_code_layout;
    }

    protected void initView()
    {
        this.qrcodeState = getIntent().getIntExtra("INTENT_QRCODE_STATE", 1);
        initTop();
    }
}