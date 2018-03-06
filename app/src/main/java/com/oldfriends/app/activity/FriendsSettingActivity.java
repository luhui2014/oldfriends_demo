package com.oldfriends.app.activity;

/**
 * lh on 2016/2/25.
 */
import android.content.Intent;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.oldfriends.app.R;
import com.oldfriends.app.util.Utility;
import com.sevenheaven.iosswitch.ShSwitchView;

public class FriendsSettingActivity extends BaseActivity
{
    private static final int INTENT_TO_REMARK_REQUEST = 1;
    private TextView remarkNameText;

    private void initTop()
    {
        RelativeLayout localRelativeLayout = (RelativeLayout)findViewById(R.id.friend_setting_top);
        View localView = localRelativeLayout.findViewById(R.id.common_left_layout);
        View.OnClickListener local3 = new View.OnClickListener()
        {
            public void onClick(View paramAnonymousView)
            {
                Utility.finishActivityTranslate(FriendsSettingActivity.this);
            }
        };
        localView.setOnClickListener(local3);
        localRelativeLayout.findViewById(R.id.common_left_btn).setBackgroundResource(R.drawable.back_icon);
        ((TextView)localRelativeLayout.findViewById(R.id.common_title)).setText("好友设置");
    }

    protected int getLayoutResId()
    {
        return R.layout.activity_friends_setting_layout;
    }

    protected void initView()
    {
        initTop();
        View localView1 = findViewById(R.id.friend_setting_remark_layout);
        View.OnClickListener local1 = new View.OnClickListener()
        {
            public void onClick(View paramAnonymousView)
            {
                Intent localIntent = new Intent(FriendsSettingActivity.this, FriendRemarkActivity.class);
                FriendsSettingActivity.this.startActivityForResult(localIntent, 1);
                FriendsSettingActivity.this.overridePendingTransition(R.anim.activity_left_in, R.anim.activity_alpha_out);
            }
        };
        localView1.setOnClickListener(local1);
        this.remarkNameText = ((TextView)findViewById(R.id.friend_setting_remark_name));
        View localView2 = findViewById(R.id.friend_setting_qr_layout);
        View.OnClickListener local2 = new View.OnClickListener()
        {
            public void onClick(View paramAnonymousView)
            {
                Intent localIntent = new Intent(FriendsSettingActivity.this, QRcodeActivity.class);
                Utility.startActivitytranslate(FriendsSettingActivity.this, localIntent);
            }
        };
        localView2.setOnClickListener(local2);
        ((ShSwitchView)findViewById(R.id.switch_pay_for_friend)).setOn(true);
    }

    protected void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
    {
        if ((paramInt2 == -1) && (paramIntent != null) && (paramInt1 == 1))
            this.remarkNameText.setText(paramIntent.getStringExtra("FRIEND_RENAME_NAME"));
    }
}
