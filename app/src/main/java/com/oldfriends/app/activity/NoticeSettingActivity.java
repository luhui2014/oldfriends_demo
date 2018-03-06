package com.oldfriends.app.activity;

/**
 * lh on 2016/2/25.
 */
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.oldfriends.app.R;
import com.oldfriends.app.util.Utility;

public class NoticeSettingActivity extends BaseActivity
{
    private void initTop()
    {
        RelativeLayout localRelativeLayout = (RelativeLayout)findViewById(R.id.notice_top_layout);
        ((TextView)localRelativeLayout.findViewById(R.id.common_backFont_text)).setText("我");
        View localView = localRelativeLayout.findViewById(R.id.common_backFont_left_layout);
        View.OnClickListener local1 = new View.OnClickListener()
        {
            public void onClick(View paramAnonymousView)
            {
                Utility.finishActivityTranslate(NoticeSettingActivity.this);
            }
        };
        localView.setOnClickListener(local1);
        ((TextView)localRelativeLayout.findViewById(R.id.common_backFont_title)).setText("通知设置");
        localRelativeLayout.findViewById(R.id.common_backFont_finish).setVisibility(View.GONE);
    }

    protected int getLayoutResId()
    {
        return R.layout.activity_notification_layout;
    }

    protected void initView()
    {
        initTop();
    }
}
