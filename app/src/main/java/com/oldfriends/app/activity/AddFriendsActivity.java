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

public class AddFriendsActivity extends BaseActivity
{
    public static final String ADDFRIEND_INTENT_TO_QRACTIVITY = "addfriend_intent_to_qractivity";

    private void initContent()
    {
        View localView1 = findViewById(R.id.add_friends_create_charge_layout);
        View.OnClickListener local2 = new View.OnClickListener()
        {
            public void onClick(View paramAnonymousView)
            {
            }
        };
        localView1.setOnClickListener(local2);
        View localView2 = findViewById(R.id.add_friends_addressBook_layout);
        View.OnClickListener local3 = new View.OnClickListener()
        {
            public void onClick(View paramAnonymousView)
            {
                Intent localIntent = new Intent(AddFriendsActivity.this, ContactActivity.class);
                Utility.startActivitytranslate(AddFriendsActivity.this, localIntent);
            }
        };
        localView2.setOnClickListener(local3);
        View localView3 = findViewById(R.id.add_friends_invite_layout);
        View.OnClickListener local4 = new View.OnClickListener()
        {
            public void onClick(View paramAnonymousView)
            {
            }
        };
        localView3.setOnClickListener(local4);
        View localView4 = findViewById(R.id.add_friends_scan_layout);
        View.OnClickListener local5 = new View.OnClickListener()
        {
            public void onClick(View paramAnonymousView)
            {
                Intent localIntent = new Intent();
                localIntent.setClass(AddFriendsActivity.this, MipcaActivityCapture.class);
                localIntent.setFlags(67108864);
                localIntent.putExtra(ADDFRIEND_INTENT_TO_QRACTIVITY, true);
                AddFriendsActivity.this.startActivity(localIntent);
            }
        };
        localView4.setOnClickListener(local5);
    }

    private void initTop()
    {
        RelativeLayout localRelativeLayout = (RelativeLayout)findViewById(R.id.add_friends_top_layout);
        localRelativeLayout.findViewById(R.id.common_left_btn).setBackgroundResource(R.drawable.back_icon);
        View localView = localRelativeLayout.findViewById(R.id.common_left_layout);
        View.OnClickListener local1 = new View.OnClickListener()
        {
            public void onClick(View paramAnonymousView)
            {
                Utility.finishActivityTranslate(AddFriendsActivity.this);
            }
        };
        localView.setOnClickListener(local1);
        ((TextView)localRelativeLayout.findViewById(R.id.common_title)).setText("添加好友");
    }

    protected int getLayoutResId()
    {
        return R.layout.activity_add_friends_layout;
    }

    protected void initView()
    {
        initTop();
        initContent();
    }
}
