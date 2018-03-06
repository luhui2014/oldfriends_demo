package com.oldfriends.app.activity;

/**
 * lh on 2016/2/24.
 */
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.oldfriends.app.R;
import com.oldfriends.app.util.Utility;

public class InviteFriendsActivity extends BaseActivity
{
    private void initContent()
    {
    }

    private void initTop()
    {
        RelativeLayout localRelativeLayout = (RelativeLayout)findViewById(R.id.invite_friend_top);
        View localView = localRelativeLayout.findViewById(R.id.common_backFont_left_layout);
        View.OnClickListener local1 = new View.OnClickListener()
        {
            public void onClick(View paramAnonymousView)
            {
                Utility.finishActivityTranslate(InviteFriendsActivity.this);
            }
        };
        localView.setOnClickListener(local1);
        ((TextView)localRelativeLayout.findViewById(R.id.common_backFont_title)).setText("邀请好友");
        localRelativeLayout.findViewById(R.id.common_backFont_finish).setVisibility(View.GONE);
    }

    protected int getLayoutResId()
    {
        return R.layout.activity_invite_friends_layout;
    }

    protected void initView()
    {
        initTop();
        initContent();
    }
}
