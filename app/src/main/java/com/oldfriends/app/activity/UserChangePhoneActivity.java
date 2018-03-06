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

public class UserChangePhoneActivity extends BaseActivity
{
    private static final int CHANGEPHONE_INTENT_TO_CHOOSECUNTRY_REQUESTCODE = 1;
    private TextView countryText;
    private TextView userNameText;

    private void initContent()
    {
        this.countryText = ((TextView)findViewById(R.id.user_changePhone_phoneNumber_country));
        View localView = findViewById(R.id.user_changePhone_country_layout);
        View.OnClickListener local3 = new View.OnClickListener()
        {
            public void onClick(View paramAnonymousView)
            {
                Intent localIntent = new Intent(UserChangePhoneActivity.this, ChooseCountryActivity.class);
                UserChangePhoneActivity.this.startActivityForResult(localIntent, 1);
                UserChangePhoneActivity.this.overridePendingTransition(R.anim.activity_left_in, R.anim.activity_alpha_out);
            }
        };
        localView.setOnClickListener(local3);
    }

    private void initTop()
    {
        RelativeLayout localRelativeLayout = (RelativeLayout)findViewById(R.id.user_changePhone_top);
        View localView1 = localRelativeLayout.findViewById(R.id.common_backFont_left_layout);
        View.OnClickListener local1 = new View.OnClickListener()
        {
            public void onClick(View paramAnonymousView)
            {
                Utility.finishActivityTranslate(UserChangePhoneActivity.this);
            }
        };
        localView1.setOnClickListener(local1);
        ((TextView)localRelativeLayout.findViewById(R.id.common_backFont_title)).setText("更改手机号码");
        View localView2 = localRelativeLayout.findViewById(R.id.common_backFont_finish);
        View.OnClickListener local2 = new View.OnClickListener()
        {
            public void onClick(View paramAnonymousView)
            {
            }
        };
        localView2.setOnClickListener(local2);
    }

    protected int getLayoutResId()
    {
        return R.layout.activity_change_phone_layout;
    }

    protected void initView()
    {
        initTop();
        initContent();
    }

    protected void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
    {
        if ((paramInt2 == -1) && (paramIntent != null) && (paramInt1 == 1))
            this.countryText.setText(paramIntent.getStringExtra("country_data"));
    }
}
